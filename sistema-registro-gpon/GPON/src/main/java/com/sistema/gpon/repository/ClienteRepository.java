package com.sistema.gpon.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sistema.gpon.model.Cliente;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, String> {
}
