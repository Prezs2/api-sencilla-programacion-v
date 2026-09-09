package com.spring.activity.controller;

import com.spring.activity.domain.estudiante.EstudianteRequest;
import com.spring.activity.domain.estudiante.EstudianteResponse;
import com.spring.activity.service.EstudianteService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/estudiantes")
public class EstudianteController {

    private final EstudianteService estudianteService;

    public EstudianteController(EstudianteService estudianteService) {
        this.estudianteService = estudianteService;
    }

    @PostMapping
    public ResponseEntity<EstudianteResponse> crear(@Valid @RequestBody EstudianteRequest request){
        EstudianteResponse estudianteCreado = estudianteService.crear(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(estudianteCreado);
    }

    @GetMapping
    public ResponseEntity <List<EstudianteResponse>> listarEstudiantes(){
        List<EstudianteResponse> estudiantes = estudianteService.listar();
        return ResponseEntity.ok(estudiantes);
    }

    @GetMapping("/{id}")
    public ResponseEntity <EstudianteResponse> buscarPorId(@PathVariable Long id){
        EstudianteResponse estudianteResponse = estudianteService.buscarPorId(id);

        if (estudianteResponse == null){
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(estudianteResponse);
    }

    @PutMapping("/{id}")
    public ResponseEntity <EstudianteResponse> actualizar(@PathVariable Long id, @Valid @RequestBody EstudianteRequest request){
        EstudianteResponse estudianteResponse = estudianteService.actualizar(id, request);

        if (estudianteResponse == null){
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(estudianteResponse);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id){
       boolean eliminado = estudianteService.eliminar(id);

       if (!eliminado){
           return ResponseEntity.notFound().build();
       }

       return ResponseEntity.noContent().build();
    }
}
