package com.sistema.gpon.repository;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.sistema.gpon.model.Usuario;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
	List<Usuario> findAllByOrderByIdUsuarioDesc();
	List<Usuario> findByRol_Descripcion(String descripcion);
	List<Usuario> findByEstadoOrderByIdUsuarioDesc(String estado);

	
	/*CODIGO AÑADIDO ELIMINAR SI DA ERROR*/
	Usuario findByCorreoAndContrasena(String correo, String contrasena);
}
