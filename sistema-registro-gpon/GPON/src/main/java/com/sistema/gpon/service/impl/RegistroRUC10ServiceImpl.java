package com.sistema.gpon.service.impl;

import java.util.List;
import java.util.Optional;

import com.sistema.gpon.service.RegistroRUC10Service;
import com.sistema.gpon.utils.ResultadoResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sistema.gpon.model.RegistroRUC10;
import com.sistema.gpon.repository.RegistroRUC10Repository;

@Service
public class RegistroRUC10ServiceImpl implements RegistroRUC10Service {

	@Autowired
	private RegistroRUC10Repository registroRUC10Repository;

//	@Autowired
//	private RegistroRUC10Service _RegistroRUC10Service;

	@Override
	public RegistroRUC10 crearRegistro(RegistroRUC10 registro) {
		return registroRUC10Repository.save(registro);
	}

	@Override
	public List<RegistroRUC10> listarRegistros() {
		return registroRUC10Repository.findAll();
	}

	@Override
	public List<RegistroRUC10> listarRegistrosActivos(int activo) {
		return registroRUC10Repository.findByActivo(activo);
	}

	@Override
	public RegistroRUC10 buscarPorId(Integer idRegistro) {
		Optional<RegistroRUC10> optional = registroRUC10Repository.findById(idRegistro);
		return optional.orElse(null);
	}

	@Override
	public RegistroRUC10 actualizarRegistro(RegistroRUC10 registro) {
		if (registro.getIdRegistro() != null && registroRUC10Repository.existsById(registro.getIdRegistro())) {
			return registroRUC10Repository.save(registro);
		}
		return null;
	}

	@Override
	public boolean eliminarRegistro(Integer idRegistro) {
		if (registroRUC10Repository.existsById(idRegistro)) {
			registroRUC10Repository.deleteById(idRegistro);
			return true;
		}
		return false;
	}

	@Override
	public ResultadoResponse cambiarEstado(int id) {
		RegistroRUC10 registroRUC10 = this.buscarPorId(id); // ✅ usar this

		int estadoActual = registroRUC10.getActivo(); // 1 o 0
		int nuevoEstado = (estadoActual == 1) ? 0 : 1;
		String accion = (nuevoEstado == 1) ? "activado" : "desactivado";

		registroRUC10.setActivo(nuevoEstado);

		try {
			RegistroRUC10 registrado = this.crearRegistro(registroRUC10); // ✅ usar this

			String mensaje = String.format("Registro con código %s %s", registrado.getIdRegistro(), accion);
			return new ResultadoResponse(true, mensaje);
		} catch (Exception ex) {
			ex.printStackTrace();
			return new ResultadoResponse(false, "Error al cambiar de estado: " + ex.getMessage());
		}
	}

}


