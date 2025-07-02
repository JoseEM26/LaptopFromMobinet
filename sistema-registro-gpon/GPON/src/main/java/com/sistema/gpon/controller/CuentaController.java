package com.sistema.gpon.controller;

import com.sistema.gpon.model.Usuario;
import com.sistema.gpon.repository.UsuarioRepository;
import com.sistema.gpon.service.UsuarioService;
import com.sistema.gpon.service.impl.UsuarioServiceImpl;
import com.sistema.gpon.utils.Alert;

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
	
	
	/*CODIGO AÑADIDO ELIMINAR SI DA ERROR*/
	@Autowired
	private UsuarioServiceImpl repoUusuario;
	
    @GetMapping("/login")
    public String login(Model model) {
    	model.addAttribute("usuario",new Usuario());
        return "cuenta/login";
    }

     @PostMapping("/iniciarSesion")
     public String iniciarSesionPagina(@ModelAttribute Usuario filter, HttpSession session, Model model,RedirectAttributes flash) {
    	 
    	 Usuario usuarioIngresado = repoUusuario.Autenticacion(filter);
    	 
    	 if(usuarioIngresado == null) {
    		 model.addAttribute("usuario", new Usuario());
    		 
    		 String errorInicio = Alert.sweetAlertError("El correo/contraseña Incorrecto");
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
    	 
    	 
    	 /*Obtengo informacion del Usuario que se esta logeando*/
    	 String nomUsuario =  String.format("%s %s", usuarioIngresado.getNombre() , usuarioIngresado.getContrasena());
    	 session.setAttribute("idUsuario", usuarioIngresado.getIdUsuario());
    	 session.setAttribute("nombresCompletos", nomUsuario);
    	 session.setAttribute("cuenta", usuarioIngresado.getNombre());
    	 session.setAttribute("rolUsuario", usuarioIngresado.getRol().getDescripcion());
    	 
    	 
    	 
    	 
    	 
    	 String alertExistoso = Alert.sweetAlertSuccess("Bienvenido a Movinet " + usuarioIngresado.getNombre());
    	 flash.addFlashAttribute("alertExistoso",alertExistoso);
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
    

}
