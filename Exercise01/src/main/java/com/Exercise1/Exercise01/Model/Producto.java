package com.Exercise1.Exercise01.Model;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;

@Entity
@Table(name = "tb_productos")
@Data
public class Producto {
    @Id
    @Column(name = "id_prod")
    private String id;
    @Column(name = "des_prod")
    private String descripcion;
    @Column(name = "stk_prod")
    private Integer stock;
    @Column(name = "pre_prod")
    private Double precio;
    @Column(name = "idcategoria")
    private Integer idCategoria;
    @Column(name = "est_prod")
    private int estadoProducto;
    @Column(name = "idproveedor")
    private Integer idProveedor;
}
