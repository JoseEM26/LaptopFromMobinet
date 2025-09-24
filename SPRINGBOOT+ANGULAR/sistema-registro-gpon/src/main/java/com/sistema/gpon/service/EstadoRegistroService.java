package com.sistema.gpon.service;

import com.sistema.gpon.model.Distrito;
import com.sistema.gpon.model.EstadoRegistro;

import java.util.List;

public interface EstadoRegistroService {
    EstadoRegistro crearEstado(EstadoRegistro estadoRegistro);
    List<EstadoRegistro> listarEstado();
    EstadoRegistro buscarPorId(Integer idEstado);
    EstadoRegistro actualizarEstado(EstadoRegistro Estado);
    boolean eliminarEstado(Integer idEstado);
}
