package com.sistema.gpon.service;

import com.sistema.gpon.model.ContactoPrincipal;
import com.sistema.gpon.model.Cronograma;

import java.util.List;

public interface CronogramaService {
    Cronograma crearCronograma(Cronograma cronograma);
    List<Cronograma> listarCronograma();
    Cronograma buscarPorId(Integer idCronograma);
    Cronograma actualizarCronograma(Cronograma cronograma);
    boolean eliminarCronograma(Integer idCronograma);

}
