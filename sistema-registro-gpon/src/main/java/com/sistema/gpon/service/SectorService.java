package com.sistema.gpon.service;

import java.util.List;

import com.sistema.gpon.model.Sector;

public interface SectorService {
    Sector crearSector(Sector sector);
    List<Sector> listarSectores();
    Sector buscarPorId(Integer idSector);
    Sector modificarSector(Sector sector);
    boolean eliminarSector(Integer idSector);
}
