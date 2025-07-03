package com.sistema.gpon.repository;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.sistema.gpon.model.Usuario;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
	@Query("SELECT u FROM Usuario u ORDER BY u.estado DESC, u.idUsuario DESC")
	List<Usuario> findAllOrderByEstadoAndIdUsuarioDesc();

	List<Usuario> findByRol_Descripcion(String descripcion);
	List<Usuario> findByEstadoOrderByIdUsuarioDesc(Boolean estado);
	
	@Query("""
		    SELECT u FROM Usuario u
		    WHERE (:idRol IS NULL OR u.rol.idRol = :idRol)
		    ORDER BY u.estado DESC, u.idUsuario DESC
		""")
		List<Usuario> findAllWithFilter(@Param("idRol") Integer idRol);

	
	/*CODIGO AÑADIDO ELIMINAR SI DA ERROR*/
	Usuario findByCorreoAndContrasena(String correo, String contrasena);
}
