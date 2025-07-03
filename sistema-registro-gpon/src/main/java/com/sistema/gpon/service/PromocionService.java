package com.sistema.gpon.service;

import java.util.List;

import com.sistema.gpon.model.Promocion;
import com.sistema.gpon.utils.ResultadoResponse;

public interface PromocionService {
	ResultadoResponse crearPromocion(Promocion promocion);
    List<Promocion> listarPromociones();
    Promocion buscarPorId(Integer idPromocion);
    ResultadoResponse actualizarPromocion(Promocion promocion);
    boolean eliminarPromocion(Integer idPromocion);
    ResultadoResponse cambiarEstado(Integer Id);
}
