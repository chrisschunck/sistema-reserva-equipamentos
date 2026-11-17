package com.reserva.equipamentos.controller;

import com.reserva.equipamentos.dto.ApiResponse;
import com.reserva.equipamentos.dto.ReservaRequest;
import com.reserva.equipamentos.dto.ReservaResponse;
import com.reserva.equipamentos.service.ReservaService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.ApiResponse;
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
    @ApiResponse(responseCode="200", description="OK")
    @ApiResponse(responseCode="201", description="CREATED")
    @ApiResponse(responseCode="204", description="NO CONTENT")
    @ApiResponse(responseCode="400", description="BAD REQUEST")
    @ApiResponse(responseCode="401", description="Unauthorized")
    @ApiResponse(responseCode="403", description="Forbidden")
    @ApiResponse(responseCode="404", description="Not Found")
    @ApiResponse(responseCode="500", description="Internal Error")
    @PostMapping
    public ResponseEntity<ApiResponse<ReservaResponse>> criar(
            @Valid @RequestBody ReservaRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.success(
                        "Reserva realizada com sucesso",
                        service.criar(request)));
    }

    @Operation(summary = "Listar reservas")
    @ApiResponse(responseCode="200", description="OK")
    @ApiResponse(responseCode="201", description="CREATED")
    @ApiResponse(responseCode="204", description="NO CONTENT")
    @ApiResponse(responseCode="400", description="BAD REQUEST")
    @ApiResponse(responseCode="401", description="Unauthorized")
    @ApiResponse(responseCode="403", description="Forbidden")
    @ApiResponse(responseCode="404", description="Not Found")
    @ApiResponse(responseCode="500", description="Internal Error")
    @GetMapping
    public ResponseEntity<ApiResponse<List<ReservaResponse>>> listar() {
        return ResponseEntity.ok(
                ApiResponse.success("Reservas encontradas", service.listar()), HttpStatus.OK);
    }

    @Operation(summary = "Buscar reserva por ID")
    @ApiResponse(responseCode="200", description="OK")
    @ApiResponse(responseCode="201", description="CREATED")
    @ApiResponse(responseCode="204", description="NO CONTENT")
    @ApiResponse(responseCode="400", description="BAD REQUEST")
    @ApiResponse(responseCode="401", description="Unauthorized")
    @ApiResponse(responseCode="403", description="Forbidden")
    @ApiResponse(responseCode="404", description="Not Found")
    @ApiResponse(responseCode="500", description="Internal Error")
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<ReservaResponse>> buscar(@PathVariable Long id) {
        return ResponseEntity.ok(
                ApiResponse.success("Reserva encontrada", service.buscarPorId(id)), HttpStatus.OK);
    }

    @Operation(summary = "Excluir reserva")
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
                ApiResponse.success("Reserva excluída com sucesso", null), HttpStatus.OK);
    }
}
