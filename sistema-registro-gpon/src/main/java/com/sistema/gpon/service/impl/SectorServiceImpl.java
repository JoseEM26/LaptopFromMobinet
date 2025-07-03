package com.sistema.gpon.service.impl;

import java.util.List;

import com.sistema.gpon.service.SectorService;
import org.springframework.beans.factory.annotation.Autowired;

import com.sistema.gpon.model.Sector;
import com.sistema.gpon.repository.SectorRepository;
import org.springframework.stereotype.Service;

@Service
public class SectorServiceImpl implements SectorService {
	
    @Autowired
    private SectorRepository sectorRepository;

	@Override
	public Sector crearSector(Sector sector) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Sector> listarSectores() {
		// TODO Auto-generated method stub
		return sectorRepository.findAll();
	}

	@Override
	public Sector buscarPorId(Integer idSector) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Sector modificarSector(Sector sector) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean eliminarSector(Integer idSector) {
		// TODO Auto-generated method stub
		return false;
	}
}
