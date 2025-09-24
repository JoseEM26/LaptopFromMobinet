package com.sistema.gpon.service;

import java.util.List;

import com.sistema.gpon.model.Distrito;

public interface DistritoService {
    Distrito crearDistrito(Distrito distrito);
    List<Distrito> listarDistritos();
    Distrito buscarPorId(Integer idDistrito);
    Distrito actualizarDistrito(Distrito distrito);
    boolean eliminarDistrito(Integer idDistrito);

}
