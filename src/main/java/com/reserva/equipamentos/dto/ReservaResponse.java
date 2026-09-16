package com.reserva.equipamentos.dto;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Set;

public record ReservaResponse(
        Long id,
        String professor,
        String curso,
        String sala,
        LocalDate data,
        LocalTime horarioRetirada,
        LocalTime horarioEntrega,
        Set<EquipamentoResponse> equipamentos
) {}
