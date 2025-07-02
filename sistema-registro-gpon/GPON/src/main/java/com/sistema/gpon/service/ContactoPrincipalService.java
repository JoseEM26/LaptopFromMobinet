package com.sistema.gpon.service;

import com.sistema.gpon.model.ContactoPrincipal;
import com.sistema.gpon.model.Distrito;

import java.util.List;

public interface ContactoPrincipalService {
    ContactoPrincipal crearContactoPrin(ContactoPrincipal contactoPrincipal);
    List<ContactoPrincipal> listarContactoPrin();
    ContactoPrincipal buscarPorId(Integer idCOntacto);
    ContactoPrincipal actualizarContactoPrin(ContactoPrincipal contactoPrincipal);
    boolean eliminarContactoPrin(Integer idContacto);

}
