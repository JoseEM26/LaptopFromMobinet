package com.sistema.gpon.service;

import java.util.List;

import com.sistema.gpon.model.Rol;
import com.sistema.gpon.utils.ResultadoResponse;

public interface RolService {
	ResultadoResponse crearRol(Rol rol);
    List<Rol> listarRoles();
    Rol buscarPorId(Integer idRol);
    ResultadoResponse modificarRol(Rol rol);
    boolean eliminarRol(Integer idRol);
}
