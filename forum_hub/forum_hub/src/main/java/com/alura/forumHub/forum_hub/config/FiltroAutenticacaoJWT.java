package com.alura.forumHub.forum_hub.config;

import com.alura.forumHub.forum_hub.repository.UsuarioRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;


import java.io.IOException;

@Component
public class FiltroAutenticacaoJWT extends OncePerRequestFilter {

    @Autowired
    private TokenService tokenServico;

    @Autowired
    private UsuarioRepository repositorioUsuario;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        var tokenJWT = recuperarToken(request);

        if (tokenJWT != null) {
            var login = tokenServico.getUsuario(tokenJWT);
            var usuario = repositorioUsuario.findByLogin((String) login);

            if (usuario != null) {
                var autenticacao = new UsernamePasswordAuthenticationToken(usuario, null, usuario.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(autenticacao);
            }
        }

        filterChain.doFilter(request, response);
    }

    private String recuperarToken(HttpServletRequest request) {
        var headerAutorizacao = request.getHeader("Authorization");
        if (headerAutorizacao != null && headerAutorizacao.startsWith("Bearer ")) {
            return headerAutorizacao.replace("Bearer ", "");
        }

        return null;
    }
}