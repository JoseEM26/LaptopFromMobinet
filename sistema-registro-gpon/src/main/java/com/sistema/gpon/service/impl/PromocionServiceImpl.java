package com.sistema.gpon.service.impl;

import java.util.List;

import com.sistema.gpon.service.PromocionService;
import com.sistema.gpon.utils.ResultadoResponse;
import org.springframework.beans.factory.annotation.Autowired;

import com.sistema.gpon.model.Promocion;
import com.sistema.gpon.repository.PromocionRepository;
import org.springframework.stereotype.Service;

@Service
public class PromocionServiceImpl implements PromocionService {
	
	@Autowired
	private PromocionRepository promocionRepository;

	@Override
	public ResultadoResponse crearPromocion(Promocion promocion) {		
		try {	
			
			if (promocion.getEstado() == null || promocion.getEstado() == false) {
				promocion.setEstado(true);
	        }
			
			Promocion registrada = promocionRepository.save(promocion);
			
			String mensaje = String.format("Promocion nueva registrada correctamente", registrada);		
			return new ResultadoResponse(true, mensaje);
			
		}catch (Exception ex) {
			ex.printStackTrace();
			return new ResultadoResponse(false, "Error al registrar: " + ex.getMessage());
		}		
	}

	@Override
	public List<Promocion> listarPromociones() {
		return promocionRepository.findAllByOrderByIdPromocionDesc();
	}

	@Override
	public Promocion buscarPorId(Integer idPromocion) {
		return promocionRepository.findById(idPromocion).orElseThrow();
	}

	@Override
	public ResultadoResponse actualizarPromocion(Promocion promocion) {
		try {
			Promocion actualizado = promocionRepository.save(promocion);

			String mensaje = String.format("Promocion actualizada correctamente", actualizado.getIdPromocion());
			return new ResultadoResponse(true, mensaje);

		} catch (Exception ex) { 
			return new ResultadoResponse(false, "Error al actualizar: " + ex.getMessage());
		}		
	}

	@Override
	public boolean eliminarPromocion(Integer idPromocion) {
	    try {
	        if (promocionRepository.existsById(idPromocion)) {
	            promocionRepository.deleteById(idPromocion);
	            return true;
	        } else {
	            return false;
	        }
	    } catch (Exception ex) {
	        ex.printStackTrace();
	        return false; 
	    }
	}
	
	public Promocion getOne(Integer id) {
		return promocionRepository.findById(id).orElseThrow();
	}

	@Override
	public ResultadoResponse cambiarEstado(Integer id) {
		Promocion promo = this.getOne(id);
		String accion = promo.getEstado() ? "desactivado" : "activado";

		promo.setEstado(!promo.getEstado());

		try {
			Promocion registrada = promocionRepository.save(promo);

			String mensaje = String.format("Promocion con código %s %s", registrada.getIdPromocion(), accion);
			return new ResultadoResponse(true, mensaje);

		} catch (Exception ex) {
			ex.printStackTrace();
			return new ResultadoResponse(false, "Error al cambiar de estado: " + ex.getMessage());
		}
	}
}
