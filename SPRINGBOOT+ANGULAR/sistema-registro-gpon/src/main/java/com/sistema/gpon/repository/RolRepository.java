package com.sistema.gpon.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sistema.gpon.model.Rol;

import org.springframework.stereotype.Repository;

@Repository
public interface RolRepository extends JpaRepository<Rol, Integer> {
	List<Rol> findAllByOrderByIdRolDesc();
}
