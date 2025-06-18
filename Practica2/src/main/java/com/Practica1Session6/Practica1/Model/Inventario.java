package com.Practica1Session6.Practica1.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "tbl_inventario")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Inventario {
    @Id
    @Column(name = "numero")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int numero;

    @Column(name = "fecha_vencimiento" ,nullable = false)
    private LocalDate fechaVencimiento;

    @JoinColumn(name = "id_producto")
    @ManyToOne(fetch = FetchType.LAZY)
    private Producto producto;

    @Column(name = "costo_ingreso" ,nullable = false)
    private Double costoIngreso;

    @Column(name = "cantidad")
    private int cantidad;

    @Column(name = "lote" )
    private String lote;

    @Column(name = "cod_estado" )
    private String codEstado;
}
