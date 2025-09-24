package com.sistema.gpon.service;

import com.sistema.gpon.model.ContactoPrincipal;
import com.sistema.gpon.model.ContactoSecundario;

import java.util.List;

public interface ContactoSecundarioService {
    ContactoSecundario creaContactoSec (ContactoSecundario contactoSecundario);
    List<ContactoSecundario> lsitarContactoSec();
    ContactoSecundario buscarPorId(Integer idCOntacto);
    ContactoSecundario actualizarContactoSec(ContactoSecundario contactoSecundario);
    boolean eliminarContactoSec(Integer idContacto);

}
