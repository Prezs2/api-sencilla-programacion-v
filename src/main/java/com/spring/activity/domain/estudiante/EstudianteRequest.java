package com.spring.activity.domain.estudiante;

import jakarta.validation.constraints.*;

public record EstudianteRequest(
        @NotBlank(message = "El documento es obligatorio")
        @Size(min = 6, max = 20, message = "El documento debe tener entre 6 y 20 caracteres")
        @Pattern(regexp = "\\d+", message = "El documento solo debe contener números")
        String documento,

        @NotBlank(message = "El nombre es obligatorio")
        @Size(min = 3, max = 100, message = "El nombre debe tener entre 3 y 100 caracteres")
        String nombre,

        @NotBlank(message = "El correo es obligatorio")
        @Email(message = "El correo no tiene un formato válido")
        @Size(max = 150, message = "El correo no puede superar los 150 caracteres")
        String correo,

        @NotNull(message = "El programa académico es obligatorio")
        @Positive(message = "El ID del programa académico debe ser mayor que 0")
        Long programaAcademicoId
) { }
