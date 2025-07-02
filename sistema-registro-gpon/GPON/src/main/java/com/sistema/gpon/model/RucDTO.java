package com.sistema.gpon.model;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RucDTO {

    // Datos del contacto principal
    private String nombreContacto;
    private String dniContacto;
    private String correoContacto;
    private String telefonoContacto;

    // Datos del contacto Secundario
    private String nombreContactoSec;
    private String dniContactoSec;
    private String correoContactoSec;
    private String telefonoContactoSec;

    // Datos del cronograma
    //estos dos se deben de unir para formar lugarInstalcion
    private String nombreDistrito;
    private String nombreSector;
    private String departamento;
    private String provincia;
    private String rangoInstalacion;
    private String numero;
    private String interior;
    private String referencia;

    // Datos del registro
      private Integer idUsuarioConsulto;
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
