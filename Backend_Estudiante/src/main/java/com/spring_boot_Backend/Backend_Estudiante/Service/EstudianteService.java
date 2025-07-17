package com.spring_boot_Backend.Backend_Estudiante.Service;

import com.spring_boot_Backend.Backend_Estudiante.Model.Estudiante;

import java.util.List;

public interface EstudianteService {
    Estudiante crearEstudiante(Estudiante estudiante);
    List<Estudiante> listarEstudiante();
    Estudiante buscarPorId(Integer id);
    Estudiante modificarEstudiante(Estudiante cliente);
}
