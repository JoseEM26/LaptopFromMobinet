package com.spring_boot_Backend.Backend_Estudiante.Service.Impl;

import com.spring_boot_Backend.Backend_Estudiante.Model.Estudiante;
import com.spring_boot_Backend.Backend_Estudiante.Repository.EstudianteRepository;
import com.spring_boot_Backend.Backend_Estudiante.Service.EstudianteService;
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
    public List<Estudiante> listarEstudiante() {
        return estudianteRepository.findAll();
    }

    @Override
    public Estudiante buscarPorId(Integer id) {
        return estudianteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Estudiante no encontrado con ID: " + id));
    }

    @Override
    public Estudiante modificarEstudiante(Estudiante estudiante) {
        return estudianteRepository.save(estudiante);
    }

}
