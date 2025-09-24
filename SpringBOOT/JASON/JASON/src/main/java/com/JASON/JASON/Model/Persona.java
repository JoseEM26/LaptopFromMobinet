package com.JASON.JASON.Model;


import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "persona")
public class Persona {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "nombre", nullable = false, length = 100)
    private String nombre;

    @Column(name = "apellido", nullable = false, length = 100)
    private String apellido;

    @Column(name = "dni", unique = true, length = 15)
    private String dni;

    @Column(name = "edad")
    private int edad;

    @Column(name = "direccion", length = 150)
    private String direccion;

    @Column(name = "email", length = 100)
    private String email;

    @Column(name = "telefono", length = 20)
    private String telefono;



    // Getters y Setters
}
