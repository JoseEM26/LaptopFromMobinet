package com.sistema.gpon.service.impl;

import com.sistema.gpon.model.EstadoRegistro;
import com.sistema.gpon.repository.EstadoRepository;
import com.sistema.gpon.service.EstadoRegistroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EstadoRegistroImpl implements EstadoRegistroService {

    @Autowired
    private EstadoRepository estadoRepository;

    @Override
    public EstadoRegistro crearEstado(EstadoRegistro estadoRegistro) {
        return estadoRepository.save(estadoRegistro);
    }

    @Override
    public List<EstadoRegistro> listarEstado() {
        return estadoRepository.findAll();
    }

    @Override
    public EstadoRegistro buscarPorId(Integer idEstado) {
        return estadoRepository.findById(idEstado)
                .orElseThrow(() -> new RuntimeException("Estado no encontrado con ID: " + idEstado));
    }

    @Override
    public EstadoRegistro actualizarEstado(EstadoRegistro estado) {
        if (!estadoRepository.existsById(estado.getIdEstado())) {
            throw new RuntimeException("No existe el estado con ID: " + estado.getIdEstado());
        }
        return estadoRepository.save(estado);
    }

    @Override
    public boolean eliminarEstado(Integer idEstado) {
        if (estadoRepository.existsById(idEstado)) {
            estadoRepository.deleteById(idEstado);
            return true;
        }
        return false;
    }
}
