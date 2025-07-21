package com.FullStack_Back.FullStack_Back.Service.Impl;


import com.FullStack_Back.FullStack_Back.Model.Estudiante;
import com.FullStack_Back.FullStack_Back.Repository.EstudianteRepository;
import com.FullStack_Back.FullStack_Back.Service.EstudianteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EstudianteServiceImpl implements EstudianteService {

    @Autowired
    private EstudianteRepository estudianteRepository;

    @Override
    public Estudiante crearEstudiante(Estudiante estudiante) {
        return estudianteRepository.save(estudiante);
    }

    @Override
    public List<Estudiante> listarEstudiantes() {
        return estudianteRepository.findAll();
    }

    @Override
    public Estudiante buscarPorId(Integer id) {
        return estudianteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Estudiante no encontrado con ID: " + id));
    }

    @Override
    public Estudiante actualizarEstudiante(Estudiante estudiante) {
        if (!estudianteRepository.existsById(estudiante.getIdEstudiante())) {
            throw new RuntimeException("Estudiante no encontrado con ID: " + estudiante.getIdEstudiante());
        }
        return estudianteRepository.save(estudiante);
    }

    @Override
    public void eliminarEstudiante(Integer id) {
        if (!estudianteRepository.existsById(id)) {
            throw new RuntimeException("Estudiante no encontrado con ID: " + id);
        }
        estudianteRepository.deleteById(id);
    }
}