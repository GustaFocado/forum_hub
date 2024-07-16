package com.alura.forumHub.forum_hub.service;


import com.alura.forumHub.forum_hub.dto.UsuarioDTO;
import com.alura.forumHub.forum_hub.model.Usuario;
import com.alura.forumHub.forum_hub.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public List<Usuario> obterTodosUsuarios() {
        return usuarioRepository.findAll();
    }

    public Optional<Usuario> obterUsuarioPorId(Long id) {
        return usuarioRepository.findById(id);
    }

    public Usuario criarUsuario(UsuarioDTO usuarioDTO) {
        // Aqui você pode implementar validações, regras de negócio, etc.
        Usuario usuario = new Usuario();
        usuario.setNome(usuarioDTO.getNome());
        usuario.setEmail(usuarioDTO.getEmail());
        // Implemente outras atribuições necessárias

        return usuarioRepository.save(usuario);
    }

    public Optional<Usuario> atualizarUsuario(Long id, UsuarioDTO usuarioDTO) {
        Optional<Usuario> optionalUsuario = usuarioRepository.findById(id);
        if (optionalUsuario.isPresent()) {
            Usuario usuario = optionalUsuario.get();
            usuario.setNome(usuarioDTO.getNome());
            usuario.setEmail(usuarioDTO.getEmail());
            // Implemente outras atribuições necessárias

            return Optional.of(usuarioRepository.save(usuario));
        }
        return Optional.empty();
    }

    public void deletarUsuario(Long id) {
        usuarioRepository.deleteById(id);
    }
}
