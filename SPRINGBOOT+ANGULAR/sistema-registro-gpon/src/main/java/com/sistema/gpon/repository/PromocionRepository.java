package com.sistema.gpon.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sistema.gpon.model.Promocion;
import org.springframework.stereotype.Repository;

@Repository
public interface PromocionRepository extends JpaRepository<Promocion, Integer> {
	List<Promocion> findAllByOrderByIdPromocionDesc();
}
