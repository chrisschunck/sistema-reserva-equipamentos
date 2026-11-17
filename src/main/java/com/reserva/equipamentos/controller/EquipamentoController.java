package com.reserva.equipamentos.controller;

import com.reserva.equipamentos.dto.ApiResponse;
import com.reserva.equipamentos.dto.EquipamentoRequest;
import com.reserva.equipamentos.dto.EquipamentoResponse;
import com.reserva.equipamentos.service.EquipamentoService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.ApiResponse;
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
    @ApiResponse(responseCode="200", description="OK")
    @ApiResponse(responseCode="201", description="CREATED")
    @ApiResponse(responseCode="204", description="NO CONTENT")
    @ApiResponse(responseCode="400", description="BAD REQUEST")
    @ApiResponse(responseCode="401", description="Unauthorized")
    @ApiResponse(responseCode="403", description="Forbidden")
    @ApiResponse(responseCode="404", description="Not Found")
    @ApiResponse(responseCode="500", description="Internal Error")
    @PostMapping
    public ResponseEntity<ApiResponse<EquipamentoResponse>> criar(
            @Valid @RequestBody EquipamentoRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.success(
                        "Equipamento cadastrado com sucesso",
                        service.criar(request)));
    }

    @Operation(summary = "Listar equipamentos")
    @ApiResponse(responseCode="200", description="OK")
    @ApiResponse(responseCode="201", description="CREATED")
    @ApiResponse(responseCode="204", description="NO CONTENT")
    @ApiResponse(responseCode="400", description="BAD REQUEST")
    @ApiResponse(responseCode="401", description="Unauthorized")
    @ApiResponse(responseCode="403", description="Forbidden")
    @ApiResponse(responseCode="404", description="Not Found")
    @ApiResponse(responseCode="500", description="Internal Error")
    @GetMapping
    public ResponseEntity<ApiResponse<List<EquipamentoResponse>>> listar() {
        return ResponseEntity.ok(
                ApiResponse.success("Equipamentos encontrados", service.listar()), HttpStatus.OK);
    }

    @Operation(summary = "Buscar equipamento por ID")
    @ApiResponse(responseCode="200", description="OK")
    @ApiResponse(responseCode="201", description="CREATED")
    @ApiResponse(responseCode="204", description="NO CONTENT")
    @ApiResponse(responseCode="400", description="BAD REQUEST")
    @ApiResponse(responseCode="401", description="Unauthorized")
    @ApiResponse(responseCode="403", description="Forbidden")
    @ApiResponse(responseCode="404", description="Not Found")
    @ApiResponse(responseCode="500", description="Internal Error")
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<EquipamentoResponse>> buscar(@PathVariable Long id) {
        return ResponseEntity.ok(
                ApiResponse.success("Equipamento encontrado", service.buscarPorId(id)), HttpStatus.OK);
    }

    @Operation(summary = "Atualizar equipamento")
    @ApiResponse(responseCode="200", description="OK")
    @ApiResponse(responseCode="201", description="CREATED")
    @ApiResponse(responseCode="204", description="NO CONTENT")
    @ApiResponse(responseCode="400", description="BAD REQUEST")
    @ApiResponse(responseCode="401", description="Unauthorized")
    @ApiResponse(responseCode="403", description="Forbidden")
    @ApiResponse(responseCode="404", description="Not Found")
    @ApiResponse(responseCode="500", description="Internal Error")
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<EquipamentoResponse>> atualizar(
            @PathVariable Long id,
            @Valid @RequestBody EquipamentoRequest request) {
        return ResponseEntity.ok(
                ApiResponse.success("Equipamento atualizado com sucesso",
                        service.atualizar(id, request)), HttpStatus.OK);
    }

    @Operation(summary = "Excluir equipamento")
    @ApiResponse(responseCode="200", description="OK")
    @ApiResponse(responseCode="201", description="CREATED")
    @ApiResponse(responseCode="204", description="NO CONTENT")
    @ApiResponse(responseCode="400", description="BAD REQUEST")
    @ApiResponse(responseCode="401", description="Unauthorized")
    @ApiResponse(responseCode="403", description="Forbidden")
    @ApiResponse(responseCode="404", description="Not Found")
    @ApiResponse(responseCode="500", description="Internal Error")
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> excluir(@PathVariable Long id) {
        service.excluir(id);
        return ResponseEntity.ok(
                ApiResponse.success("Equipamento excluído com sucesso", null), HttpStatus.OK);
    }
}
