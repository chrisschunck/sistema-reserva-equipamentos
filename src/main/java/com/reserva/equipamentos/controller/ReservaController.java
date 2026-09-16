package com.reserva.equipamentos.controller;

import com.reserva.equipamentos.dto.ApiResponse;
import com.reserva.equipamentos.dto.ReservaRequest;
import com.reserva.equipamentos.dto.ReservaResponse;
import com.reserva.equipamentos.service.ReservaService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reservas")
@RequiredArgsConstructor
public class ReservaController {

    private final ReservaService service;

    @Operation(
            summary = "Criar reserva",
            description = "Cria uma reserva de um ou vários equipamentos após validar as regras de negócio."
    )
    @PostMapping
    public ResponseEntity<ApiResponse<ReservaResponse>> criar(
            @Valid @RequestBody ReservaRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.success(
                        "Reserva realizada com sucesso",
                        service.criar(request)));
    }

    @Operation(summary = "Listar reservas")
    @GetMapping
    public ResponseEntity<ApiResponse<List<ReservaResponse>>> listar() {
        return ResponseEntity.ok(
                ApiResponse.success("Reservas encontradas", service.listar()));
    }

    @Operation(summary = "Buscar reserva por ID")
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<ReservaResponse>> buscar(@PathVariable Long id) {
        return ResponseEntity.ok(
                ApiResponse.success("Reserva encontrada", service.buscarPorId(id)));
    }

    @Operation(summary = "Excluir reserva")
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> excluir(@PathVariable Long id) {
        service.excluir(id);
        return ResponseEntity.ok(
                ApiResponse.success("Reserva excluída com sucesso", null));
    }
}
