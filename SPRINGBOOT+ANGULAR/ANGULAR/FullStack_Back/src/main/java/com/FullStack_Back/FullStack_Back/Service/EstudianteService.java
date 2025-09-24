package com.FullStack_Back.FullStack_Back.Service;

import com.FullStack_Back.FullStack_Back.Model.Estudiante;

import java.util.List;

public interface EstudianteService {
    Estudiante crearEstudiante(Estudiante estudiante);
    List<Estudiante> listarEstudiantes();
    Estudiante buscarPorId(Integer id);
    Estudiante actualizarEstudiante(Estudiante estudiante);
    void eliminarEstudiante(Integer id);
}