package com.spring.activity.repository;

import com.spring.activity.entity.ProgramaAcademico;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProgramaAcademicoRepository extends JpaRepository<ProgramaAcademico,Long> {
}
