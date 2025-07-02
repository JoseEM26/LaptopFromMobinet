package com.sistema.gpon.service;

import java.util.List;

import com.sistema.gpon.model.Usuario;
import com.sistema.gpon.utils.ResultadoResponse;

public interface UsuarioService {
	ResultadoResponse crearUsuario(Usuario usuario);
    List<Usuario> listarUsuarios();
    Usuario buscarPorId(Integer idUsuario);
    ResultadoResponse modificarUsuario(Usuario usuario);
    boolean eliminarUsuario(Integer idUsuario);
    List<Usuario> findByRol_Descripcion(String descripcion); 
}
