package com.reserva.equipamentos.repository;

import com.reserva.equipamentos.entity.Reserva;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public interface ReservaRepository extends JpaRepository<Reserva, Long> {

    @Query("""
        SELECT DISTINCT r FROM Reserva r
        JOIN r.equipamentos e
        WHERE r.data = :data
          AND e.id IN :equipamentosIds
          AND r.horarioRetirada < :horarioEntrega
          AND r.horarioEntrega > :horarioRetirada
    """)
    List<Reserva> buscarConflitoEquipamentos(
            @Param("data") LocalDate data,
            @Param("equipamentosIds") java.util.Set<Long> equipamentosIds,
            @Param("horarioRetirada") LocalTime horarioRetirada,
            @Param("horarioEntrega") LocalTime horarioEntrega
    );

    @Query("""
        SELECT r FROM Reserva r
        WHERE r.data = :data
          AND LOWER(r.sala) = LOWER(:sala)
          AND r.horarioRetirada < :horarioEntrega
          AND r.horarioEntrega > :horarioRetirada
    """)
    List<Reserva> buscarConflitoSala(
            @Param("data") LocalDate data,
            @Param("sala") String sala,
            @Param("horarioRetirada") LocalTime horarioRetirada,
            @Param("horarioEntrega") LocalTime horarioEntrega
    );
}
