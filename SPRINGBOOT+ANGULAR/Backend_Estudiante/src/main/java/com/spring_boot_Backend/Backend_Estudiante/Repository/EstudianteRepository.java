package com.spring_boot_Backend.Backend_Estudiante.Repository;

import com.spring_boot_Backend.Backend_Estudiante.Model.Estudiante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EstudianteRepository extends JpaRepository<Estudiante,Integer> {
}
