package com.spring.activity.domain.estudiante;

public record EstudianteResponse(
        Long id,
        String nombre,
        String documento,
        Long programaAcademidoID,
        String programaAcademico) {
}
