package com.sistema.gpon.model;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ContactoPrincipal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IdContactoPrincipal")
    private Integer idContactoPrincipal;

    @Column(name = "NombreContacto",nullable = false, length = 30)
    private String nombreContacto;

    @Column(name = "Dni",nullable = false, length = 8)
    private String dni;

    @Column(name = "Correo",nullable = false, length = 30)
    private String correo;

    @Column(name = "Telefono",nullable = false, length = 9)
    private String telefono;
}