package com.sistema.gpon.dto;

import com.sistema.gpon.model.EstadoRegistro;
import jakarta.persistence.Column;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RucDTOActualizar {
    private Integer idRegistro;

    // Datos del contacto principal
    @NotBlank(message = "El nombre del contacto principal es obligatorio")
    private String nombreContacto;

    @Pattern(regexp = "\\d{8}", message = "El DNI del contacto debe tener 8 dígitos numéricos")
    private String dniContacto;

    @Email(message = "El correo del contacto no es válido")
    private String correoContacto;

    @Pattern(regexp = "\\d{9}", message = "El teléfono del contacto debe tener 9 dígitos")
    private String telefonoContacto;

    // Datos del contacto Secundario
    @NotBlank(message = "El nombre del contacto secundario es obligatorio")
    private String nombreContactoSec;

    @Pattern(regexp = "\\d{8}", message = "El DNI del contacto secundario debe tener 8 dígitos numéricos")
    private String dniContactoSec;

    @Email(message = "El correo del contacto secundario no es válido")
    private String correoContactoSec;

    @Pattern(regexp = "\\d{9}", message = "El teléfono del contacto secundario debe tener 9 dígitos")
    private String telefonoContactoSec;

    // Datos del cronograma
    @NotBlank(message = "El rango de instalación es obligatorio")
    private String rangoInstalacion;

    @NotBlank(message = "El lugar de instalación es obligatorio")
    private String lugarInstalacion;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate fechaInstalacion;

    // Datos del registro
    @Min(value = 1, message = "Debe seleccionar un consultor")
    private Integer idUsuarioConsultor;

    @Min(value = 1, message = "Debe seleccionar un supervisor")
    private Integer idUsuarioSupervisor;

    @Min(value = 1, message = "Debe seleccionar un plan")
    private Integer idPlan;

    private Integer idPromocion;
    private String observacion;

    // Datos del cliente
    @Pattern(regexp = "\\d{8}", message = "El DNI debe tener 8 dígitos numéricos")
    private String dniCliente;

    @Pattern(regexp = "\\d{11}", message = "El RUC debe tener 11 dígitos numéricos")
    private String rucCliente;

    @NotBlank(message = "El nombre es obligatorio")
    private String nombreCliente;

    @NotBlank(message = "El apellido es obligatorio")
    private String apellidoCliente;

    @Pattern(regexp = "\\d{9}", message = "El teléfono debe tener 9 dígitos")
    private String telefonoCliente;

    private Integer idEstado;

    private String idSolicitud ;

    private String idInstalacion ;

    private String idCarrito ;
}