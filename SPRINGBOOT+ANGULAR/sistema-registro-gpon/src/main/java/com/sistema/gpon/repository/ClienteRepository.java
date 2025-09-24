package com.sistema.gpon.repository;

import com.sistema.gpon.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import com.sistema.gpon.model.Cliente;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, String> {

    @Query("""
		    SELECT c FROM Cliente c
		    WHERE (:activo IS NULL OR c.activo = :activo)
		""")
    List<Cliente> findAllWithFilter(@Param("activo") Boolean activo);
}
