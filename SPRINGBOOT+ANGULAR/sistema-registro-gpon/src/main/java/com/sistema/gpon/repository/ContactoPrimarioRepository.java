package com.sistema.gpon.repository;

import com.sistema.gpon.model.Cliente;
import com.sistema.gpon.model.ContactoPrincipal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContactoPrimarioRepository extends JpaRepository<ContactoPrincipal, Integer> {
}
