package com.sistema.gpon.service.impl;

import java.util.List;

import com.sistema.gpon.service.UsuarioService;
import com.sistema.gpon.utils.ResultadoResponse;

import org.springframework.beans.factory.annotation.Autowired;

import com.sistema.gpon.dto.UsuarioFilter;
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
			
			 if (usuario.getEstado() == null || usuario.getEstado() == false) {
		            usuario.setEstado(true);
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
	    return usuarioRepository.findAllOrderByEstadoAndIdUsuarioDesc();
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
	            usuario.setEstado(false);
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

	@Override
	public Usuario buscarPorId(Integer idUsuario) {
		return usuarioRepository.findById(idUsuario).orElseThrow();
	}

	@Override
	public ResultadoResponse cambiarEstado(Integer id) {
		Usuario usuario = this.buscarPorId(id);
		Boolean accion = usuario.getEstado() ? false : true;
		String texto;
		
		if(accion == true) {
			 texto = "Activo";
		} else {
			 texto = "Inactivo";
		}

		usuario.setEstado(!usuario.getEstado());

		try {
			Usuario registrado = usuarioRepository.save(usuario);

			String mensaje = String.format("Usuario con código %s %s", registrado.getIdUsuario(), texto);
			return new ResultadoResponse(true, mensaje);

		} catch (Exception ex) {
			ex.printStackTrace();
			return new ResultadoResponse(false, "Error al cambiar de estado: " + ex.getMessage());
		}
	}

	@Override
	public List<Usuario> listarFiltros(UsuarioFilter filtro) {
		return usuarioRepository.findAllWithFilter(filtro.getIdRol());
	}
	
  @Override
	public Usuario autenticacion(Usuario filter) {
		return usuarioRepository.findByCorreoAndContrasena(filter.getCorreo(), filter.getContrasena());
	}
}