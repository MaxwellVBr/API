package com.example.API.repository.usuarioRepository;

import com.example.API.model.User.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
}
