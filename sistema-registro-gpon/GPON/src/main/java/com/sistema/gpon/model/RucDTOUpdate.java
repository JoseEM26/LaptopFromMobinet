package com.sistema.gpon.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RucDTOUpdate {
    private Integer idRegistro;

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

    private String rangoInstalacion;
//    private String numero;
//    private String interior;
//    private String referencia;
//    private String nombreDistrito;
//    private String nombreSector;
//    private String departamento;
//    private String provincia;
      private String lugarInstalacion;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate  fechaInstalacion;

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
