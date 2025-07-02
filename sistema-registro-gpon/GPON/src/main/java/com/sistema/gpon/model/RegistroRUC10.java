package com.sistema.gpon.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegistroRUC10 {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idRegistro;

	@ManyToOne
	@JoinColumn(name = "IdUsuarioConsulto", nullable = false)
	private Usuario usuarioConsulto;

	@ManyToOne
	@JoinColumn(name = "IdUsuarioSupervisor", nullable = false)
	private Usuario usuarioSupervisor;

	@ManyToOne
	@JoinColumn(name = "DniCliente",
//			 referencedColumnName = "DniCliente",
			nullable = false)	private Cliente cliente;

	@ManyToOne
	@JoinColumn(name = "IdContactoPrincipal", nullable = false)
	private ContactoPrincipal contactoPrincipal;

	@ManyToOne
	@JoinColumn(name = "idContactoSecundario", nullable = false)
	private ContactoSecundario contactoSecundario;

	@ManyToOne
	@JoinColumn(name = "IdPlan", nullable = false)
	private Plan plan;

	@ManyToOne
	@JoinColumn(name = "IdPromocion")
	private Promocion promocion;

	@ManyToOne
	@JoinColumn(name = "IdCronograma")
	private Cronograma cronograma;

	@ManyToOne
	@JoinColumn(name = "IdEstado")
	private EstadoRegistro estado;

	@Column(name = "IdSolicitud")
	private String idSolicitud = "ninguno";

	@Column(name = "IdInstalacion")
	private String idInstalacion = "ninguno";

	@Column(name = "IdCarrito" )
	private String idCarrito = "ninguno";

	@Column(name = "Observacion")
	private String observacion;

	@Column(name = "activo")
	private int activo =1;

}
