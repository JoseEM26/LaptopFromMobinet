package com.FullStack_Back.FullStack_Back.Controller;


import com.FullStack_Back.FullStack_Back.Model.Estudiante;
import com.FullStack_Back.FullStack_Back.Service.EstudianteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/estudiante")
@CrossOrigin("http://localhost:4200")
public class EstudianteController {

    @Autowired
    private EstudianteService estudianteService;

    // GET /estudiante/listar
    @GetMapping
    public ResponseEntity<List<Estudiante>> listarEstudiantes() {
        List<Estudiante> estudiantes = estudianteService.listarEstudiantes();
        return ResponseEntity.ok(estudiantes);
    }
    @GetMapping("/listar/{id}")
    public ResponseEntity<Estudiante> EncontrarByID(@PathVariable Integer id) {
        Estudiante estudiantes = estudianteService.buscarPorId(id);
        return ResponseEntity.ok(estudiantes);
    }
    @PostMapping("/registrar")
    public ResponseEntity<Estudiante> registrar(@RequestBody Estudiante estudiante) {
        Estudiante estudiantes = estudianteService.crearEstudiante(estudiante);
        return ResponseEntity.ok(estudiantes);
    }
    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<String> eliminar(@PathVariable Integer id) {
        estudianteService.eliminarEstudiante(id);
        return ResponseEntity.ok("Estudiante eliminado correctamente con ID: " + id);
    }
}