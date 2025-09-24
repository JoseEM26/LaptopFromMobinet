package com.JASON.JASON.Controller;

import com.JASON.JASON.Model.Persona;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/personas")
public class PersonaController {

    @Autowired
    private com.JASON.JASON.Service.PersonaService personaService;

    // Mostrar lista
    @GetMapping
    public String listarPersonas(Model model) {
        model.addAttribute("listaPersonas", personaService.listarTodos());
        return "persona/listar"; // ruta a tu HTML en templates/persona/listar.html
    }

    // Formulario nuevo
    @GetMapping("/nuevo")
    public String nuevaPersona(Model model) {
        model.addAttribute("persona", new Persona());
        return "persona/formulario"; // ruta a tu HTML en templates/persona/formulario.html
    }

    // Guardar (registrar)
    @PostMapping("/guardar")
    public String guardar(@Validated @ModelAttribute("persona") Persona persona,
                          BindingResult result,
                          Model model) {
        if (result.hasErrors()) {
            return "persona/formulario";
        }
        personaService.guardar(persona);
        return "redirect:/personas";
    }

    // Editar
    @GetMapping("/editar/{id}")
    public String editar(@PathVariable Long id, Model model) {
        Persona persona = personaService.buscarPorId(id);
        model.addAttribute("persona", persona);
        return "persona/formulario";
    }

    // Eliminar
    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Long id) {
        personaService.eliminar(id);
        return "redirect:/personas";
    }
}
