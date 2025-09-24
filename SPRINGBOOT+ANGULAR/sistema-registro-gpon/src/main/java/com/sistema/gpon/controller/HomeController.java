package com.sistema.gpon.controller;

import com.sistema.gpon.service.impl.RegistroRUC10ServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class HomeController {

    @Autowired
    RegistroRUC10ServiceImpl _Service;

    @GetMapping({"", "/"})
    public String index(HttpServletRequest request, Model model) {
        model.addAttribute("uri", request.getRequestURI());

        model.addAttribute("pendiente",_Service.CountEstado("Pendiente instalacion"));
        model.addAttribute("Anulado",_Service.CountEstado("Anulado"));
        model.addAttribute("Observado",_Service.CountEstado("Observado"));
        model.addAttribute("Instalado",_Service.CountEstado("Instalado"));
        return "home/dashboard";
    }
}