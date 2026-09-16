package com.reserva.equipamentos.service;

import com.reserva.equipamentos.dto.EquipamentoResponse;
import com.reserva.equipamentos.dto.ReservaRequest;
import com.reserva.equipamentos.dto.ReservaResponse;
import com.reserva.equipamentos.entity.Equipamento;
import com.reserva.equipamentos.entity.Reserva;
import com.reserva.equipamentos.exception.BusinessException;
import com.reserva.equipamentos.exception.ResourceNotFoundException;
import com.reserva.equipamentos.repository.EquipamentoRepository;
import com.reserva.equipamentos.repository.ReservaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class ReservaServiceImpl implements ReservaService {

    private final ReservaRepository reservaRepository;
    private final EquipamentoRepository equipamentoRepository;

    @Override
    @Transactional
    public ReservaResponse criar(ReservaRequest request) {
        validarAntecedencia(request);
        validarHorario(request);

        Set<Equipamento> equipamentos = buscarEquipamentos(request.equipamentosIds());
        validarEquipamentosAtivos(equipamentos);
        validarConflitoEquipamentos(request, equipamentos);
        validarConflitoSala(request);

        Reserva reserva = Reserva.builder()
                .professor(request.professor())
                .curso(request.curso())
                .sala(request.sala())
                .data(request.data())
                .horarioRetirada(request.horarioRetirada())
                .horarioEntrega(request.horarioEntrega())
                .equipamentos(equipamentos)
                .build();

        return toResponse(reservaRepository.save(reserva));
    }

    @Override
    public List<ReservaResponse> listar() {
        return reservaRepository.findAll().stream().map(this::toResponse).toList();
    }

    @Override
    public ReservaResponse buscarPorId(Long id) {
        return toResponse(findEntity(id));
    }

    @Override
    public void excluir(Long id) {
        reservaRepository.delete(findEntity(id));
    }

    private void validarAntecedencia(ReservaRequest request) {
        LocalDate dataMinima = LocalDate.now().plusDays(7);

        if (request.data().isBefore(dataMinima)) {
            throw new BusinessException(
                    "A reserva deve ser realizada com no mínimo 7 dias de antecedência. " +
                    "Data mínima permitida: " + dataMinima
            );
        }
    }

    private void validarHorario(ReservaRequest request) {
        if (!request.horarioRetirada().isBefore(request.horarioEntrega())) {
            throw new BusinessException(
                    "O horário de retirada deve ser anterior ao horário de entrega."
            );
        }
    }

    private Set<Equipamento> buscarEquipamentos(Set<Long> ids) {
        List<Equipamento> encontrados = equipamentoRepository.findAllById(ids);

        if (encontrados.size() != ids.size()) {
            throw new ResourceNotFoundException(
                    "Um ou mais equipamentos informados não foram encontrados."
            );
        }

        return new HashSet<>(encontrados);
    }

    private void validarEquipamentosAtivos(Set<Equipamento> equipamentos) {
        equipamentos.stream()
                .filter(e -> !e.isAtivo())
                .findFirst()
                .ifPresent(e -> {
                    throw new BusinessException(
                            "O equipamento " + e.getNome() +
                            " está inativo e não pode ser reservado."
                    );
                });
    }

    private void validarConflitoEquipamentos(
            ReservaRequest request,
            Set<Equipamento> equipamentos) {

        Set<Long> ids = equipamentos.stream()
                .map(Equipamento::getId)
                .collect(java.util.stream.Collectors.toSet());

        if (!reservaRepository.buscarConflitoEquipamentos(
                request.data(),
                ids,
                request.horarioRetirada(),
                request.horarioEntrega()
        ).isEmpty()) {
            throw new BusinessException(
                    "Existe conflito de horário com um ou mais equipamentos solicitados."
            );
        }
    }

    private void validarConflitoSala(ReservaRequest request) {
        if (!reservaRepository.buscarConflitoSala(
                request.data(),
                request.sala(),
                request.horarioRetirada(),
                request.horarioEntrega()
        ).isEmpty()) {
            throw new BusinessException(
                    "A sala " + request.sala() +
                    " já possui uma reserva nesse período."
            );
        }
    }

    private Reserva findEntity(Long id) {
        return reservaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Reserva não encontrada: " + id));
    }

    private ReservaResponse toResponse(Reserva r) {
        Set<EquipamentoResponse> equipamentos = r.getEquipamentos().stream()
                .map(e -> new EquipamentoResponse(
                        e.getId(), e.getNome(), e.getTipo(), e.isAtivo()))
                .collect(java.util.stream.Collectors.toSet());

        return new ReservaResponse(
                r.getId(),
                r.getProfessor(),
                r.getCurso(),
                r.getSala(),
                r.getData(),
                r.getHorarioRetirada(),
                r.getHorarioEntrega(),
                equipamentos
        );
    }
}
