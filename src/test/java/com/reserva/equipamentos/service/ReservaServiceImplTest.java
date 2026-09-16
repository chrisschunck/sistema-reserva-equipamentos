package com.reserva.equipamentos.service;

import com.reserva.equipamentos.dto.ReservaRequest;
import com.reserva.equipamentos.entity.Equipamento;
import com.reserva.equipamentos.exception.BusinessException;
import com.reserva.equipamentos.repository.EquipamentoRepository;
import com.reserva.equipamentos.repository.ReservaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

class ReservaServiceImplTest {

    @Mock
    private ReservaRepository reservaRepository;

    @Mock
    private EquipamentoRepository equipamentoRepository;

    @InjectMocks
    private ReservaServiceImpl service;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void deveRejeitarReservaComMenosDeSeteDias() {
        ReservaRequest request = new ReservaRequest(
                "João da Silva",
                "Engenharia de Software",
                "204",
                LocalDate.now().plusDays(3),
                LocalTime.of(18, 30),
                LocalTime.of(22, 30),
                Set.of(1L)
        );

        assertThrows(BusinessException.class, () -> service.criar(request));
    }

    @Test
    void deveRejeitarHorarioInvalido() {
        ReservaRequest request = new ReservaRequest(
                "João da Silva",
                "Engenharia de Software",
                "204",
                LocalDate.now().plusDays(7),
                LocalTime.of(22, 30),
                LocalTime.of(18, 30),
                Set.of(1L)
        );

        assertThrows(BusinessException.class, () -> service.criar(request));
    }

    @Test
    void deveRejeitarEquipamentoInativo() {
        Equipamento equipamento = Equipamento.builder()
                .id(1L)
                .nome("Microfone 01")
                .tipo("Microfone")
                .ativo(false)
                .build();

        Mockito.when(equipamentoRepository.findAllById(Set.of(1L)))
                .thenReturn(List.of(equipamento));

        ReservaRequest request = new ReservaRequest(
                "João da Silva",
                "Engenharia de Software",
                "204",
                LocalDate.now().plusDays(7),
                LocalTime.of(18, 30),
                LocalTime.of(22, 30),
                Set.of(1L)
        );

        assertThrows(BusinessException.class, () -> service.criar(request));
        Mockito.verify(reservaRepository, Mockito.never()).save(any());
    }
}
