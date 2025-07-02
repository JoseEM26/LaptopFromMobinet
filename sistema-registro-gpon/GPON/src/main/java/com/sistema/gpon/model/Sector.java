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
@Table(name = "Sector")
@Data
@NoArgsConstructor
public class Sector {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "IdSector")
	private Integer idSector;

	@Column(name = "NombreSector", nullable = false)
	private String nombreSector;
}
