package com.sistema.gpon.controller;

import com.sistema.gpon.model.Cliente;
import com.sistema.gpon.model.Promocion;
import com.sistema.gpon.utils.Alert;
import com.sistema.gpon.utils.ResultadoResponse;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.sistema.gpon.service.ClienteService;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/clientes")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @GetMapping({"", "/"})
    public String listado(HttpServletRequest request, Model model) {
        model.addAttribute("uri", request.getRequestURI());

        List<Cliente> lstClientes = clienteService.listarClientes();
        model.addAttribute("lstClientes", lstClientes);

        return "clientes/index";
    }

    @GetMapping("/nuevo")
    public String nuevo(Model model) {
        model.addAttribute("cliente", new Cliente());

        return "clientes/nuevo";
    }

    @PostMapping("/registrar")
    public String registrar(@Validated @ModelAttribute Cliente cliente, BindingResult bindingResults, Model model, RedirectAttributes flash) {
        ResultadoResponse response = clienteService.crearCliente(cliente);

        if (!response.success) {
            model.addAttribute("alert", Alert.sweetAlertError(response.mensaje));
            return "clientes/nuevo";
        }

        flash.addFlashAttribute("alert", Alert.sweetToast(response.mensaje, "success", 5000));

        return "redirect:/clientes";
    }

    @GetMapping("/edicion/{id}")
    public String edicion(@PathVariable String id, Model model) {
        Cliente cliente = clienteService.buscarPorDni(id);
        model.addAttribute("cliente", cliente);

        return "clientes/edicion";
    }

    @PostMapping("/guardar")
    public String guardar(@Validated @ModelAttribute Cliente cliente, BindingResult bindingResult, Model model, RedirectAttributes flash) {
        ResultadoResponse response = clienteService.modificarCliente(cliente);

        if (!response.success) {
            model.addAttribute("alert", Alert.sweetAlertError(response.mensaje));
            return "clientes/edicion";
        }

        String toast = Alert.sweetToast(response.mensaje, "success", 5000);
        flash.addFlashAttribute("alert", toast);

        return "redirect:/clientes";
    }
}
