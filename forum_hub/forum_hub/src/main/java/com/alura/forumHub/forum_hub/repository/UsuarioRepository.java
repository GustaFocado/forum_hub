package com.alura.forumHub.forum_hub.repository;


import com.alura.forumHub.forum_hub.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Usuario findByLogin(String login);
}