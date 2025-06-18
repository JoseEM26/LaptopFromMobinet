package com.Practica1Session6.Practica1.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tbl_producto")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Producto {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "nombre" ,nullable = false)
    private String nombre;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_categoria" ,nullable = false)
    private Categoria categoria;

    @Column(name = "stock_maximo" ,nullable = false)
    private int stockMax;

    @Column(name = "stock_actual" ,nullable = false)
    private int stockActual;
}
