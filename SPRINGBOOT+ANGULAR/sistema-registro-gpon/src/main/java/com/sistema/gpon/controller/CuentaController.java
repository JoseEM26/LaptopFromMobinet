package com.sistema.gpon.controller;

import com.sistema.gpon.model.Usuario;
import com.sistema.gpon.repository.UsuarioRepository;
import com.sistema.gpon.service.UsuarioService;
import com.sistema.gpon.service.impl.UsuarioServiceImpl;
import com.sistema.gpon.utils.Alert;
import com.sistema.gpon.utils.ResultadoResponse;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import javax.swing.text.Document;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/cuenta")
public class CuentaController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping("/login")
    public String login(Model model) {
        model.addAttribute("usuario", new Usuario());
        return "cuenta/login";
    }

    @PostMapping("/iniciarSesion")
    public String iniciarSesionPagina(@ModelAttribute Usuario filter, HttpSession session, Model model, RedirectAttributes flash) {
        Usuario usuarioIngresado = usuarioService.autenticacion(filter);

        if (usuarioIngresado == null) {
            model.addAttribute("usuario", new Usuario());

            String errorInicio = Alert.sweetAlertError("El correo o contraseña son incorrectos");
            flash.addFlashAttribute("errorInicio", errorInicio);

            return "redirect:/cuenta/login";
        }

        int rol = usuarioIngresado.getRol().getIdRol();

        switch (rol) {
            case 1:
                session.setAttribute("mostrarRegistro", true);
                session.setAttribute("mostrarClientes", false);
                session.setAttribute("mostrarPlanes", true);
                session.setAttribute("mostrarPromociones", true);
                session.setAttribute("mostrarUsuarios", false);
                break;
            case 2:
                session.setAttribute("mostrarRegistro", false);
                session.setAttribute("mostrarClientes", false);
                session.setAttribute("mostrarPlanes", false);
                session.setAttribute("mostrarPromociones", false);
                session.setAttribute("mostrarUsuarios", true);
                break;
            case 3:
                session.setAttribute("mostrarRegistro", true);
                session.setAttribute("mostrarClientes", true);
                session.setAttribute("mostrarPlanes", true);
                session.setAttribute("mostrarPromociones", true);
                session.setAttribute("mostrarUsuarios", true);
                break;
            case 4:
                session.setAttribute("mostrarRegistro", true);
                session.setAttribute("mostrarClientes", true);
                session.setAttribute("mostrarPlanes", true);
                session.setAttribute("mostrarPromociones", true);
                session.setAttribute("mostrarUsuarios", true);
                break;
            default:
                session.setAttribute("mostrarRegistro", false);
                session.setAttribute("mostrarClientes", false);
                session.setAttribute("mostrarPlanes", false);
                session.setAttribute("mostrarPromociones", false);
                session.setAttribute("mostrarUsuarios", false);
                break;
        }

        // Obtengo informacion del Usuario que se esta logeando
        String nomUsuario = String.format("%s %s", usuarioIngresado.getNombre(), usuarioIngresado.getContrasena());
        session.setAttribute("idUsuario", usuarioIngresado.getIdUsuario());
        session.setAttribute("nombresCompletos", nomUsuario);
        session.setAttribute("cuenta", usuarioIngresado.getNombre());
        session.setAttribute("rolUsuario", usuarioIngresado.getRol().getDescripcion());

        String alertExitoso = Alert.sweetAlertSuccess("¡Te damos la bienvenida a Mobinet, " + usuarioIngresado.getNombre() + "!");
        flash.addFlashAttribute("alertExistoso", alertExitoso);

        return "redirect:/";
    }

    @GetMapping("/cerrarSesion")
    public String cerrarSesion(HttpSession session) {
        session.invalidate();
        return "redirect:/cuenta/login";
    }

    @GetMapping("/registro")
    public String mostrarFormularioRegistro(Model model) {
        model.addAttribute("usuario", new Usuario());
        return "cuenta/registro";
    }

    @GetMapping("/perfil")
    public String perfil(HttpServletRequest request, HttpSession session, Model model, RedirectAttributes flash) {
        model.addAttribute("uri", request.getRequestURI());

        Integer idUsuario = (Integer) session.getAttribute("idUsuario");

        if (idUsuario == null) {
            flash.addFlashAttribute("errorInicio", Alert.sweetAlertError("Debe iniciar sesión para acceder al perfil"));
            return "redirect:/cuenta/login";
        }

        Usuario usuario = usuarioService.buscarPorId(idUsuario);

        if (usuario == null) {
            flash.addFlashAttribute("errorInicio", Alert.sweetAlertError("No se encontró el usuario"));
            return "redirect:/cuenta/login";
        }

        model.addAttribute("usuario", usuario);
        return "cuenta/perfil";
    }
    
    @PostMapping("/actualizar")
    public String actualizarPerfil(@ModelAttribute Usuario formUsuario,
                                   @RequestParam String contrasenaActual,
                                   @RequestParam(required = false) String nuevaContrasena,
                                   HttpSession session,
                                   RedirectAttributes flash) {
        Integer idUsuario = (Integer) session.getAttribute("idUsuario");

        if (idUsuario == null) {
            flash.addFlashAttribute("errorInicio", Alert.sweetAlertError("Debe iniciar sesión para actualizar el perfil"));
            return "redirect:/cuenta/login";
        }

        Usuario usuarioExistente = usuarioService.buscarPorId(idUsuario);

        if (usuarioExistente == null) {
            flash.addFlashAttribute("errorInicio", Alert.sweetAlertError("No se encontró el usuario"));
            return "redirect:/cuenta/login";
        }

        if (!usuarioExistente.getContrasena().equals(contrasenaActual)) {
            flash.addFlashAttribute("alert", Alert.sweetAlertError("La contraseña actual es incorrecta"));
            return "redirect:/cuenta/perfil";
        }

        usuarioExistente.setCorreo(formUsuario.getCorreo());
        if (nuevaContrasena != null && !nuevaContrasena.isBlank()) {
            usuarioExistente.setContrasena(nuevaContrasena);
        }

        ResultadoResponse resultado = usuarioService.modificarUsuario(usuarioExistente);

        if (!resultado.success) {
            flash.addFlashAttribute("alert", Alert.sweetAlertError(resultado.mensaje));
            return "redirect:/cuenta/perfil";
        }

        flash.addFlashAttribute("alert", Alert.sweetToast("Perfil actualizado correctamente", "success", 3000));
        return "redirect:/cuenta/perfil";
    }
}
