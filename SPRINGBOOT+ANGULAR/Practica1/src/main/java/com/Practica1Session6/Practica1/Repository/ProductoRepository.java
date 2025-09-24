package com.Practica1Session6.Practica1.Repository;

import com.Practica1Session6.Practica1.Model.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductoRepository extends JpaRepository<Producto,String> {
   List<Producto> findAllByOrderByCodProductoDesc();

}
