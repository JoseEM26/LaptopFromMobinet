package com.sistema.gpon.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Promocion")
@Data
@NoArgsConstructor
public class Promocion {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "IdPromocion")
	private Integer idPromocion;

	@Column(name = "Descripcion", nullable = false)
	private String descripcion;
}
