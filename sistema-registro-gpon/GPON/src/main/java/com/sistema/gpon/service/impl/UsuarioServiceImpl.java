package com.sistema.gpon.service.impl;

import java.util.List;

import com.sistema.gpon.service.UsuarioService;
import com.sistema.gpon.utils.ResultadoResponse;

import org.springframework.beans.factory.annotation.Autowired;

import com.sistema.gpon.model.Usuario;
import com.sistema.gpon.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;
    
   
	@Override
	public ResultadoResponse crearUsuario(Usuario usuario) {
		try {
			
			 if (usuario.getEstado() == null || usuario.getEstado().isBlank()) {
		            usuario.setEstado("activo");
		        }
			 
			Usuario registrado = usuarioRepository.save(usuario);

			String mensaje = String.format("Usuario con numero %s registrado correctamente", registrado.getIdUsuario());
			return new ResultadoResponse(true, mensaje);

		}catch (Exception ex) {
			ex.printStackTrace();
			return new ResultadoResponse(false, "Error al registrar: " + ex.getMessage());
		}
	}

	@Override
	public List<Usuario> listarUsuarios() {
	    return usuarioRepository.findByEstadoOrderByIdUsuarioDesc("activo");
	}


	@Override
	public ResultadoResponse modificarUsuario(Usuario usuario) {
		try {
			Usuario actualizado = usuarioRepository.save(usuario);

			String mensaje = String.format("Usuario con nro. %s actualizado", actualizado.getIdUsuario());
			return new ResultadoResponse(true, mensaje);

		} catch (Exception ex) {
			return new ResultadoResponse(false, "Error al actualizar: " + ex.getMessage());
		}
	}

	@Override
	public boolean eliminarUsuario(Integer idUsuario) {
	    try {
	        Usuario usuario = usuarioRepository.findById(idUsuario).orElse(null);
	        if (usuario != null) {
	            usuario.setEstado("inhabilitado");
	            usuarioRepository.save(usuario);
	            return true;
	        }
	        return false;
	    } catch (Exception ex) {
	        ex.printStackTrace();
	        return false;
	    }
	}


	@Override
	public List<Usuario> findByRol_Descripcion(String descripcion){
      return usuarioRepository.findByRol_Descripcion(descripcion);
	};
	
	/*CODIGO AÑADIDO ELIMINAR SI DA ERROR*/
	public Usuario Autenticacion(Usuario filter) {
		
		return usuarioRepository.findByCorreoAndContrasena(filter.getCorreo(), filter.getContrasena());
	}

	@Override
	public Usuario buscarPorId(Integer idUsuario) {
		return usuarioRepository.findById(idUsuario).orElseThrow();
	}
	
}