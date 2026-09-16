package com.reserva.equipamentos.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "reservas")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Reserva {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 150)
    private String professor;

    @Column(nullable = false, length = 150)
    private String curso;

    @Column(nullable = false, length = 30)
    private String sala;

    @Column(nullable = false)
    private LocalDate data;

    @Column(nullable = false)
    private LocalTime horarioRetirada;

    @Column(nullable = false)
    private LocalTime horarioEntrega;

    @ManyToMany
    @JoinTable(
        name = "reserva_equipamentos",
        joinColumns = @JoinColumn(name = "reserva_id"),
        inverseJoinColumns = @JoinColumn(name = "equipamento_id")
    )
    @Builder.Default
    private Set<Equipamento> equipamentos = new HashSet<>();
}
