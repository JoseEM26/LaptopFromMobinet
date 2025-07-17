package com.spring_boot_Backend.Backend_Estudiante.Controller;

import com.spring_boot_Backend.Backend_Estudiante.Model.Estudiante;
import com.spring_boot_Backend.Backend_Estudiante.Service.EstudianteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/estudiante")
public class EstudianteController {

    @Autowired
    EstudianteService estudianteService;

    @GetMapping
    public ResponseEntity<List<Estudiante>> ListarEstudiantes(){
        return ResponseEntity.ok(estudianteService.listarEstudiante());
    }
}
