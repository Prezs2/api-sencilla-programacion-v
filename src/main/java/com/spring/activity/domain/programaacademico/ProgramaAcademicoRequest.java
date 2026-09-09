package com.spring.activity.domain.programaacademico;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record ProgramaAcademicoRequest(

        @NotBlank(message = "El nombre es obligatorio")
        String nombre,

        @NotBlank(message = "El código es obligatorio")
        @Size(max = 10, message = "El código no puede superar los 10 caracteres")
        String codigo
) {
}
