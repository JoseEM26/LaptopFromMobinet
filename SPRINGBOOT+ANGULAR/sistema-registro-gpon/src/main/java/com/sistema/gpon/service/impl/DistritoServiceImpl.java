package com.sistema.gpon.service.impl;

import java.util.List;

import com.sistema.gpon.service.DistritoService;
import org.springframework.beans.factory.annotation.Autowired;

import com.sistema.gpon.model.Distrito;
import com.sistema.gpon.repository.DistritoRepository;
import org.springframework.stereotype.Service;

@Service
public class DistritoServiceImpl implements DistritoService {
	
	@Autowired
	private DistritoRepository distritoRepository;

	@Override
	public Distrito crearDistrito(Distrito distrito) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Distrito> listarDistritos() {
		// TODO Auto-generated method stub
		return distritoRepository.findAll();
	}

	@Override
	public Distrito buscarPorId(Integer idDistrito) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Distrito actualizarDistrito(Distrito distrito) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean eliminarDistrito(Integer idDistrito) {
		// TODO Auto-generated method stub
		return false;
	}
}
