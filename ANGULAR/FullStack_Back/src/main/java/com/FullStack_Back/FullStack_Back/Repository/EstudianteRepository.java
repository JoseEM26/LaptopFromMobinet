package com.FullStack_Back.FullStack_Back.Repository;

import com.FullStack_Back.FullStack_Back.Model.Estudiante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EstudianteRepository extends JpaRepository<Estudiante, Integer> {
}