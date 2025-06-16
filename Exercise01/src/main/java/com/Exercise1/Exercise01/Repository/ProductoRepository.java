package com.Exercise1.Exercise01.Repository;

import com.Exercise1.Exercise01.Model.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductoRepository  extends JpaRepository<Producto , String> {
}
