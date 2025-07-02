package com.sistema.gpon.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sistema.gpon.model.RegistroRUC10;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RegistroRUC10Repository extends JpaRepository<RegistroRUC10, Integer> {
    List<RegistroRUC10> findByActivo(int activo);
}
