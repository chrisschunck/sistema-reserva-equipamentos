package com.reserva.equipamentos.repository;

import com.reserva.equipamentos.entity.Equipamento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EquipamentoRepository extends JpaRepository<Equipamento, Long> {
}
