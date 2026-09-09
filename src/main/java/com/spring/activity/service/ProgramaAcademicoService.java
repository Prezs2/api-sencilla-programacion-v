package com.spring.activity.service;

import com.spring.activity.domain.programaacademico.*;
import com.spring.activity.entity.ProgramaAcademico;
import com.spring.activity.repository.ProgramaAcademicoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProgramaAcademicoService {

    private final ProgramaAcademicoRepository programaAcademicoRepository;

    public ProgramaAcademicoService(ProgramaAcademicoRepository programaAcademicoRepository) {
        this.programaAcademicoRepository = programaAcademicoRepository;
    }

    public ProgramaAcademicoResponse crear (ProgramaAcademicoRequest request){
        ProgramaAcademico programaAcademico = new ProgramaAcademico();

        programaAcademico.setCodigo(request.codigo());
        programaAcademico.setNombre(request.nombre());

        ProgramaAcademico programaAcademicoGuardado = programaAcademicoRepository.save(programaAcademico);

        return convertirAResponse(programaAcademicoGuardado);
    }

    public List<ProgramaAcademicoResponse> listar(){
        return programaAcademicoRepository.findAll().stream().map(this::convertirAResponse).toList();
    }

    public ProgramaAcademicoResponse buscarPorId(Long id){
        return programaAcademicoRepository.findById(id).map(this::convertirAResponse).orElse(null);
    }

    public ProgramaAcademicoResponse actualizar(Long id, ProgramaAcademicoRequest request){
        ProgramaAcademico programaAcademico = programaAcademicoRepository.findById(id).orElse(null);

        if (programaAcademico == null){
            return null;
        }

        programaAcademico.setNombre(request.nombre());
        programaAcademico.setCodigo(request.codigo());

        ProgramaAcademico programaAcademicoActualizado = programaAcademicoRepository.save(programaAcademico);
        return convertirAResponse(programaAcademicoActualizado);
    }

    public boolean eliminar(Long id){
        if (programaAcademicoRepository.existsById(id)){
            programaAcademicoRepository.deleteById(id);
            return true;
        }

        return false;
    }

    private ProgramaAcademicoResponse convertirAResponse(ProgramaAcademico programaAcademico){
        return new ProgramaAcademicoResponse(
                programaAcademico.getId(),
                programaAcademico.getNombre(),
                programaAcademico.getCodigo());
    }
}