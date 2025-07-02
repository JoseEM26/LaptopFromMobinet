package com.sistema.gpon.service;

import java.util.List;

import com.sistema.gpon.model.RegistroRUC10;
import com.sistema.gpon.utils.ResultadoResponse;

public interface RegistroRUC10Service {
    RegistroRUC10 crearRegistro(RegistroRUC10 registro);
    List<RegistroRUC10> listarRegistros();
    List<RegistroRUC10> listarRegistrosActivos(int activo);
    RegistroRUC10 buscarPorId(Integer idRegistro);
    RegistroRUC10 actualizarRegistro(RegistroRUC10 registro);
    boolean eliminarRegistro(Integer idRegistro);
    ResultadoResponse cambiarEstado(int id);
}
