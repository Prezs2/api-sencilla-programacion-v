package com.spring.activity.controller;

import com.spring.activity.domain.programaacademico.ProgramaAcademicoRequest;
import com.spring.activity.domain.programaacademico.ProgramaAcademicoResponse;
import com.spring.activity.service.ProgramaAcademicoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/programas-academicos")
public class ProgramaAcademicoController {

    private final ProgramaAcademicoService programaAcademicoService;

    public ProgramaAcademicoController(ProgramaAcademicoService programaAcademicoService) {
        this.programaAcademicoService = programaAcademicoService;
    }

    @PostMapping
    public ResponseEntity<ProgramaAcademicoResponse> crear(@Valid @RequestBody ProgramaAcademicoRequest request){
        ProgramaAcademicoResponse response = programaAcademicoService.crear(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    public ResponseEntity <List<ProgramaAcademicoResponse>> listarProgramas(){
        List<ProgramaAcademicoResponse> response = programaAcademicoService.listar();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity <ProgramaAcademicoResponse> buscarPorId(@PathVariable Long id){
        ProgramaAcademicoResponse response = programaAcademicoService.buscarPorId(id);

        if (response == null){
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity <ProgramaAcademicoResponse> actualizar(@PathVariable Long id, @Valid @RequestBody ProgramaAcademicoRequest request){
        ProgramaAcademicoResponse response = programaAcademicoService.actualizar(id, request);

        if (response == null){
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id){
        boolean response = programaAcademicoService.eliminar(id);

        if (!response){
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.noContent().build();
    }
}
