package com.Practica1Session6.Practica1.Repository;

import com.Practica1Session6.Practica1.Model.Inventario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InventarioRepository extends JpaRepository<Inventario,Integer> {

}
