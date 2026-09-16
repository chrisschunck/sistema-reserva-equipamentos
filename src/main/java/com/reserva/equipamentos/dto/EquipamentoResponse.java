package com.reserva.equipamentos.dto;

public record EquipamentoResponse(
        Long id,
        String nome,
        String tipo,
        boolean ativo
) {}
