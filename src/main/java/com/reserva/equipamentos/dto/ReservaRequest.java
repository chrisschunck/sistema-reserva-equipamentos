package com.reserva.equipamentos.dto;

import jakarta.validation.constraints.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Set;

public record ReservaRequest(
        @NotBlank(message = "Professor é obrigatório")
        @Size(max = 150, message = "Professor deve ter no máximo 150 caracteres")
        String professor,

        @NotBlank(message = "Curso é obrigatório")
        @Size(max = 150, message = "Curso deve ter no máximo 150 caracteres")
        String curso,

        @NotBlank(message = "Sala é obrigatória")
        @Size(max = 30, message = "Sala deve ter no máximo 30 caracteres")
        String sala,

        @NotNull(message = "Data é obrigatória")
        LocalDate data,

        @NotNull(message = "Horário de retirada é obrigatório")
        LocalTime horarioRetirada,

        @NotNull(message = "Horário de entrega é obrigatório")
        LocalTime horarioEntrega,

        @NotEmpty(message = "Informe pelo menos um equipamento")
        Set<@NotNull(message = "ID do equipamento não pode ser nulo") Long> equipamentosIds
) {}
