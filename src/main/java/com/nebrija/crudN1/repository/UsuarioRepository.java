package com.nebrija.crudN1.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import com.nebrija.crudN1.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    // Buscar un usuario por su nombre de usuario (único)
    Optional<Usuario> findByUsername(String username);

    // Buscar usuarios cuyo nombre de usuario contenga un fragmento específico (búsqueda parcial)
    List<Usuario> findByUsernameContaining(String fragmento);

    // Buscar usuarios que posean un rol específico (por ejemplo, "ROLE_ADMIN" o "ROLE_USER")
    // Se asume que en la entidad Usuario, los roles tienen un atributo 'nombre'
    List<Usuario> findByRolesNombre(String nombreRol);

}//cierra interface
