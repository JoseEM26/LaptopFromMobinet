package com.sistema.gpon.repository;

import com.sistema.gpon.model.ContactoPrincipal;
import com.sistema.gpon.model.ContactoSecundario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContactoSecundarioRepository extends JpaRepository<ContactoSecundario, Integer> {
}
