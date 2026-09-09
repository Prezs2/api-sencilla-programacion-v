package com.spring.activity.service;

import com.spring.activity.domain.estudiante.EstudianteRequest;
import com.spring.activity.domain.estudiante.EstudianteResponse;
import com.spring.activity.entity.Estudiante;
import com.spring.activity.entity.ProgramaAcademico;
import com.spring.activity.repository.EstudianteRepository;
import com.spring.activity.repository.ProgramaAcademicoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EstudianteService {

    private final EstudianteRepository estudianteRepository;
    private final ProgramaAcademicoRepository programaAcademicoRepository;

    public EstudianteService(EstudianteRepository estudianteRepository, ProgramaAcademicoRepository programaAcademicoRepository) {
        this.estudianteRepository = estudianteRepository;
        this.programaAcademicoRepository = programaAcademicoRepository;
    }

    public EstudianteResponse crear (EstudianteRequest request){
        ProgramaAcademico programaAcademico = programaAcademicoRepository
                .findById(request.programaAcademidoID())
                .orElseThrow(() ->
                        new RuntimeException("Programa académico no encontrado")
                );

        Estudiante estudiante = new Estudiante();

        estudiante.setDocumento(request.documento());
        estudiante.setNombre(request.nombre());
        estudiante.setCorreo(request.correo());
        estudiante.setProgramaAcademico(programaAcademico);

        Estudiante estudianteGuardado = estudianteRepository.save(estudiante);

        return convertirAResponse(estudianteGuardado);
    }

    public List<EstudianteResponse> listar(){
        return estudianteRepository.findAll().stream().map(this::convertirAResponse).toList();
    }

    public EstudianteResponse buscarPorId(Long id){
        return estudianteRepository.findById(id).map(this::convertirAResponse).orElse(null);
    }

    public EstudianteResponse actualizar(Long id, EstudianteRequest request){
        Estudiante estudiante = estudianteRepository.findById(id).orElse(null);

        if (estudiante == null){
            return null;
        }

        estudiante.setNombre(request.nombre());
        estudiante.setDocumento(request.documento());
        estudiante.setCorreo(request.correo());

        Estudiante estudianteActualizado = estudianteRepository.save(estudiante);
        return convertirAResponse(estudianteActualizado);
    }

    public boolean eliminar(Long id){
        if (estudianteRepository.existsById(id)){
            estudianteRepository.deleteById(id);
            return true;
        }

        return false;
    }

    private EstudianteResponse convertirAResponse(Estudiante estudiante){
        return new EstudianteResponse(
                estudiante.getId(),
                estudiante.getNombre(),
                estudiante.getDocumento(),
                estudiante.getProgramaAcademico().getId(),
                estudiante.getProgramaAcademico().getNombre());
    }
}
