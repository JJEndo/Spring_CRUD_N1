package com.nebrija.crudN1.service;

import com.nebrija.crudN1.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import com.nebrija.crudN1.model.Usuario;

import java.util.stream.Collectors;

public class UsuarioService implements UserDetailsService {

    private UsuarioRepository usuarioRepository;
    @Autowired
    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Se intenta buscar el usuario en el repositorio
        Usuario usuario = usuarioRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado"));


        return new User(
                usuario.getUsername(),
                usuario.getPassword(),
                usuario.getRoles().stream()
                        .map(rol -> new SimpleGrantedAuthority(rol.getNombre()))
                        .collect(Collectors.toList())
        );
    }

}//cierra clase
