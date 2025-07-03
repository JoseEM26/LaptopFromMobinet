package com.sistema.gpon.repository;

import com.sistema.gpon.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import com.sistema.gpon.model.RegistroRUC10;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RegistroRUC10Repository extends JpaRepository<RegistroRUC10, Integer> {
    List<RegistroRUC10> findAllByOrderByIdRegistroDesc();

    @Query("""
		    SELECT r FROM RegistroRUC10 r
		    WHERE (:idEstado IS NULL OR r.estado.idEstado = :idEstado)
		    ORDER BY r.idRegistro DESC
		""")
    List<RegistroRUC10> findAllWithFilter(@Param("idEstado") Integer idEstado);

    int countByEstado_Descripcion(String descripcion);
}
