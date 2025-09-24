package com.sistema.gpon.service.impl;

import com.sistema.gpon.model.ContactoSecundario;
import com.sistema.gpon.service.ContactoSecundarioService;

import java.util.List;

import com.sistema.gpon.model.ContactoSecundario;
import com.sistema.gpon.repository.ContactoSecundarioRepository;
import com.sistema.gpon.service.ContactoSecundarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContactoSecundarioImpl implements ContactoSecundarioService {

    @Autowired
    private ContactoSecundarioRepository contactoSecundarioRepository;

    @Override
    public ContactoSecundario creaContactoSec(ContactoSecundario contactoSecundario) {
        return contactoSecundarioRepository.save(contactoSecundario);
    }

    @Override
    public List<ContactoSecundario> lsitarContactoSec() {
        return contactoSecundarioRepository.findAll();
    }

    @Override
    public ContactoSecundario buscarPorId(Integer idContacto) {
        return contactoSecundarioRepository.findById(idContacto).orElse(null);
    }

    @Override
    public ContactoSecundario actualizarContactoSec(ContactoSecundario contactoSecundario) {
        return contactoSecundarioRepository.save(contactoSecundario);
    }

    @Override
    public boolean eliminarContactoSec(Integer idContacto) {
        if (contactoSecundarioRepository.existsById(idContacto)) {
            contactoSecundarioRepository.deleteById(idContacto);
            return true;
        }
        return false;
    }
}
