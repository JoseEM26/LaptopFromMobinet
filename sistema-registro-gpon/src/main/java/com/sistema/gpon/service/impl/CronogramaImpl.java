package com.sistema.gpon.service.impl;

import com.sistema.gpon.model.Cronograma;
import com.sistema.gpon.repository.CronogramaRepository;
import com.sistema.gpon.service.CronogramaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CronogramaImpl implements CronogramaService {

    @Autowired
    private CronogramaRepository cronogramaRepository;

    @Override
    public Cronograma crearCronograma(Cronograma cronograma) {
        return cronogramaRepository.save(cronograma);
    }

    @Override
    public List<Cronograma> listarCronograma() {
        return cronogramaRepository.findAll();
    }

    @Override
    public Cronograma buscarPorId(Integer idCronograma) {
        return cronogramaRepository.findById(idCronograma).orElse(null);
    }

    @Override
    public Cronograma actualizarCronograma(Cronograma cronograma) {
        return cronogramaRepository.save(cronograma);
    }

    @Override
    public boolean eliminarCronograma(Integer idCronograma) {
        if (cronogramaRepository.existsById(idCronograma)) {
            cronogramaRepository.deleteById(idCronograma);
            return true;
        }
        return false;
    }
}
