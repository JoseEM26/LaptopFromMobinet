package com.sistema.gpon.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.validation.constraints.Pattern;

@Entity
@Table(name = "Cliente")
@Data
@NoArgsConstructor
public class Cliente {

	@Id
	@Column(name = "DniCliente")
	@Pattern(regexp = "\\d{8}", message = "El DNI debe tener 8 dígitos numéricos")
	private String dniCliente;

	@Column(name = "Ruc", nullable = false)
	@Pattern(regexp = "\\d{11}", message = "El RUC debe tener 11 dígitos numéricos")
	private String ruc;

	@Column(name = "Nombre", nullable = false)
	@NotBlank(message = "El nombre es obligatorio")
	private String nombre;

	@Column(name = "Apellido", nullable = false)
	@NotBlank(message = "El apellido es obligatorio")
	private String apellido;

	@Column(name = "Telefono", nullable = false)
	@Pattern(regexp = "\\d{9}", message = "El teléfono debe tener 9 dígitos")
	private String telefono;

	@Column(name = "Activo", nullable = false)
	private Boolean activo;
}
