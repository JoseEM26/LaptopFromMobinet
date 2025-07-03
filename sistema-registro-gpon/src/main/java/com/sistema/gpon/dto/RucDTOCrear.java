package com.sistema.gpon.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RucDTOCrear {

    // Datos del contacto principal
    private String nombreContacto;
    private String dniContacto;
    private String correoContacto;
    private String telefonoContacto;

    // Datos del contacto secundario
    private String nombreContactoSec;
    private String dniContactoSec;
    private String correoContactoSec;
    private String telefonoContactoSec;

    // Datos del cronograma
    private String nombreDistrito;
    private String nombreSector;
    private String departamento;
    private String provincia;
    private String rangoInstalacion;
    private String numero;
    private String interior;
    private String referencia;

    // Datos del registro
    private Integer idUsuarioConsultor;
    private Integer idUsuarioSupervisor;
    private Integer idPlan;
    private Integer idPromocion;
    private String observacion;

    // Datos del cliente
    private String dniCliente;
    private String rucCliente;
    private String nombreCliente;
    private String apellidoCliente;
    private String telefonoCliente;
}