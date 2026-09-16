package com.reserva.equipamentos.service;

import com.reserva.equipamentos.dto.EquipamentoRequest;
import com.reserva.equipamentos.dto.EquipamentoResponse;

import java.util.List;

public interface EquipamentoService {
    EquipamentoResponse criar(EquipamentoRequest request);
    List<EquipamentoResponse> listar();
    EquipamentoResponse buscarPorId(Long id);
    EquipamentoResponse atualizar(Long id, EquipamentoRequest request);
    void excluir(Long id);
}
