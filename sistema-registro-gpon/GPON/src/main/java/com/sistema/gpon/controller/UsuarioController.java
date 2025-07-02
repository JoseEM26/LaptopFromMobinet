package com.sistema.gpon.controller;

import java.util.List;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.sistema.gpon.model.Usuario;
import com.sistema.gpon.service.RolService;
import com.sistema.gpon.service.UsuarioService;
import com.sistema.gpon.utils.Alert;
import com.sistema.gpon.utils.ResultadoResponse;

@Controller
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;
    
    @Autowired
    private RolService rolesService;

    @GetMapping({"", "/"})
    public String listado(HttpServletRequest request, Model model) {
        model.addAttribute("uri", request.getRequestURI());

        List<Usuario> lstUsuarios = usuarioService.listarUsuarios();
        model.addAttribute("lstUsuarios", lstUsuarios);
        return "usuarios/index";
    }

    @GetMapping("/nuevo")
    public String nuevo(Model model) {
        model.addAttribute("roles", rolesService.listarRoles());
        model.addAttribute("usuario", new Usuario());
        return "usuarios/nuevo";
    }

    @PostMapping("/registrar")
    public String registrar(@Validated @ModelAttribute Usuario usuarios, BindingResult bindingResults, Model model, RedirectAttributes flash) {

        if (bindingResults.hasErrors()) {
            model.addAttribute("roles", rolesService.listarRoles());
            model.addAttribute("alert", Alert.sweetAlertInfo("Agregue informacion de la Promocion"));
            return "usuarios/nuevo";
        }

        ResultadoResponse response = usuarioService.crearUsuario(usuarios);

        if (!response.success) {
            model.addAttribute("roles", rolesService.listarRoles());
            model.addAttribute("alert", Alert.sweetAlertError(response.mensaje));
            return "usuarios/nuevo";
        }

        String toast = Alert.sweetToast(response.mensaje, "success", 5000);
        flash.addFlashAttribute("alert", toast);
        return "redirect:/usuarios";
    }

    @GetMapping("/edicion/{id}")
    public String edicion(@PathVariable Integer id, Model model) {
        model.addAttribute("roles", rolesService.listarRoles());
        Usuario usuario = usuarioService.buscarPorId(id);
        model.addAttribute("usuario", usuario);
        return "usuarios/edicion";
    }

    @PostMapping("/guardar")
    public String guardar(@Validated @ModelAttribute Usuario usuario, BindingResult bindingResult, Model model,
                          RedirectAttributes flash) {

        if (bindingResult.hasErrors()) {
            model.addAttribute("roles", rolesService.listarRoles());
            model.addAttribute("alert", Alert.sweetAlertInfo("Agregue informacion del Usuario"));
            return "usuarios/edicion";
        }

        ResultadoResponse response = usuarioService.modificarUsuario(usuario);

        if (!response.success) {
            model.addAttribute("roles", rolesService.listarRoles());
            model.addAttribute("alert", Alert.sweetAlertError(response.mensaje));
            return "usuarios/edicion";
        }

        String toast = Alert.sweetToast(response.mensaje, "success", 5000);
        flash.addFlashAttribute("alert", toast);
        return "redirect:/usuarios";
    }
    
    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Integer id, RedirectAttributes flash) {
        boolean eliminado = usuarioService.eliminarUsuario(id);
        if (eliminado) {
            flash.addFlashAttribute("alert", Alert.sweetToast("Usuario eliminado correctamente", "success", 3000));
        } else {
            flash.addFlashAttribute("alert", Alert.sweetAlertError("No se pudo eliminar al Usuario."));
        }
        return "redirect:/usuarios";
    }
}
