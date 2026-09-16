package com.reserva.equipamentos.service;

import com.reserva.equipamentos.dto.ReservaRequest;
import com.reserva.equipamentos.dto.ReservaResponse;

import java.util.List;

public interface ReservaService {
    ReservaResponse criar(ReservaRequest request);
    List<ReservaResponse> listar();
    ReservaResponse buscarPorId(Long id);
    void excluir(Long id);
}
