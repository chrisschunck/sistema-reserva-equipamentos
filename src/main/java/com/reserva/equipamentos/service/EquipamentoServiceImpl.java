package com.reserva.equipamentos.service;

import com.reserva.equipamentos.dto.EquipamentoRequest;
import com.reserva.equipamentos.dto.EquipamentoResponse;
import com.reserva.equipamentos.entity.Equipamento;
import com.reserva.equipamentos.exception.ResourceNotFoundException;
import com.reserva.equipamentos.repository.EquipamentoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EquipamentoServiceImpl implements EquipamentoService {

    private final EquipamentoRepository repository;

    @Override
    public EquipamentoResponse criar(EquipamentoRequest request) {
        Equipamento equipamento = Equipamento.builder()
                .nome(request.nome())
                .tipo(request.tipo())
                .ativo(request.ativo())
                .build();

        return toResponse(repository.save(equipamento));
    }

    @Override
    public List<EquipamentoResponse> listar() {
        return repository.findAll().stream().map(this::toResponse).toList();
    }

    @Override
    public EquipamentoResponse buscarPorId(Long id) {
        return toResponse(findEntity(id));
    }

    @Override
    public EquipamentoResponse atualizar(Long id, EquipamentoRequest request) {
        Equipamento equipamento = findEntity(id);
        equipamento.setNome(request.nome());
        equipamento.setTipo(request.tipo());
        equipamento.setAtivo(request.ativo());
        return toResponse(repository.save(equipamento));
    }

    @Override
    public void excluir(Long id) {
        Equipamento equipamento = findEntity(id);
        repository.delete(equipamento);
    }

    private Equipamento findEntity(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Equipamento não encontrado: " + id));
    }

    private EquipamentoResponse toResponse(Equipamento e) {
        return new EquipamentoResponse(e.getId(), e.getNome(), e.getTipo(), e.isAtivo());
    }
}
