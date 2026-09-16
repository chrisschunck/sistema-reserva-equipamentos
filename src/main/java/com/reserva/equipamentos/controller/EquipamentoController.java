package com.reserva.equipamentos.controller;

import com.reserva.equipamentos.dto.ApiResponse;
import com.reserva.equipamentos.dto.EquipamentoRequest;
import com.reserva.equipamentos.dto.EquipamentoResponse;
import com.reserva.equipamentos.service.EquipamentoService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/equipamentos")
@RequiredArgsConstructor
public class EquipamentoController {

    private final EquipamentoService service;

    @Operation(summary = "Cadastrar equipamento")
    @PostMapping
    public ResponseEntity<ApiResponse<EquipamentoResponse>> criar(
            @Valid @RequestBody EquipamentoRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.success(
                        "Equipamento cadastrado com sucesso",
                        service.criar(request)));
    }

    @Operation(summary = "Listar equipamentos")
    @GetMapping
    public ResponseEntity<ApiResponse<List<EquipamentoResponse>>> listar() {
        return ResponseEntity.ok(
                ApiResponse.success("Equipamentos encontrados", service.listar()));
    }

    @Operation(summary = "Buscar equipamento por ID")
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<EquipamentoResponse>> buscar(@PathVariable Long id) {
        return ResponseEntity.ok(
                ApiResponse.success("Equipamento encontrado", service.buscarPorId(id)));
    }

    @Operation(summary = "Atualizar equipamento")
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<EquipamentoResponse>> atualizar(
            @PathVariable Long id,
            @Valid @RequestBody EquipamentoRequest request) {
        return ResponseEntity.ok(
                ApiResponse.success("Equipamento atualizado com sucesso",
                        service.atualizar(id, request)));
    }

    @Operation(summary = "Excluir equipamento")
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> excluir(@PathVariable Long id) {
        service.excluir(id);
        return ResponseEntity.ok(
                ApiResponse.success("Equipamento excluído com sucesso", null));
    }
}
