package com.spring.activity.config;

import com.spring.activity.entity.Estudiante;
import com.spring.activity.entity.ProgramaAcademico;
import com.spring.activity.repository.EstudianteRepository;
import com.spring.activity.repository.ProgramaAcademicoRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.File;
import java.util.List;

@Configuration
public class DataInitializer {

    @Bean
    CommandLineRunner initData(
            ProgramaAcademicoRepository programaRepository,
            EstudianteRepository estudianteRepository
    ) {

        return args -> {

            System.out.println("========== DATA INITIALIZER ==========");
            System.out.println(
                    "Base de datos: " +
                            new File("database.db").getAbsolutePath()
            );

            System.out.println(
                    "Programas existentes: " +
                            programaRepository.count()
            );

            if (programaRepository.count() > 0) {
                System.out.println("Ya existen datos. No se insertarán nuevamente.");
                return;
            }

            ProgramaAcademico sistemas = new ProgramaAcademico();
            sistemas.setCodigo("SIS");
            sistemas.setNombre("Ingeniería de Sistemas");

            ProgramaAcademico administracion = new ProgramaAcademico();
            administracion.setCodigo("ADM");
            administracion.setNombre("Administración de Empresas");

            programaRepository.saveAll(
                    List.of(sistemas, administracion)
            );

            System.out.println("Programas académicos guardados");

            Estudiante estudiante1 = new Estudiante();
            estudiante1.setDocumento("1001001001");
            estudiante1.setNombre("Ana Torres");
            estudiante1.setCorreo("anaTorres@universidad.edu.co");
            estudiante1.setProgramaAcademico(sistemas);

            Estudiante estudiante2 = new Estudiante();
            estudiante2.setDocumento("1001001002");
            estudiante2.setNombre("Jhon Ramirez");
            estudiante2.setCorreo("jhonRamirez@universidad.edu.co");
            estudiante2.setProgramaAcademico(sistemas);

            Estudiante estudiante3 = new Estudiante();
            estudiante3.setDocumento("1001001003");
            estudiante3.setNombre("Carlos López");
            estudiante3.setCorreo("carlosLopez@universidad.edu.co");
            estudiante3.setProgramaAcademico(sistemas);

            Estudiante estudiante4 = new Estudiante();
            estudiante4.setDocumento("1001001004");
            estudiante4.setNombre("Paco Travolta");
            estudiante4.setCorreo("pacoTravolta@universidad.edu.co");
            estudiante4.setProgramaAcademico(administracion);

            Estudiante estudiante5 = new Estudiante();
            estudiante5.setDocumento("1001001005");
            estudiante5.setNombre("Esteban Kito");
            estudiante5.setCorreo("estebanKito@universidad.edu.co");
            estudiante5.setProgramaAcademico(administracion);

            estudianteRepository.saveAll(
                    List.of(
                            estudiante1,
                            estudiante2,
                            estudiante3,
                            estudiante4,
                            estudiante5
                    )
            );

            System.out.println(
                    "Programas guardados: " +
                            programaRepository.count()
            );

            System.out.println(
                    "Estudiantes guardados: " +
                            estudianteRepository.count()
            );

            System.out.println("========== FIN DATA INITIALIZER ==========");
        };
    }
}