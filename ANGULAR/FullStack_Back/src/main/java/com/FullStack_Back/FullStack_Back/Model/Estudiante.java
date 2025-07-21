package com.FullStack_Back.FullStack_Back.Model;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "Estudiante")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Estudiante {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer idEstudiante;

    @Column(name = "nombre", nullable = false, length = 100)
    private String nombre;

    @Column(name = "apellido", nullable = false, length = 100)
    private String apellido;

    @Column(name = "telefono", nullable = false)
    private Integer telefono;

    @Column(name = "correo", nullable = false, length = 100)
    private String correo;
}