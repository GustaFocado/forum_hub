package com.alura.forumHub.forum_hub.repository;

import com.alura.forumHub.forum_hub.model.Topico;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TopicoRepository extends JpaRepository<Topico, Long> {

    boolean existsByTituloAndMensagem(String titulo, String mensagem);

    boolean existsByTituloAndMensagemAndIdNot(String titulo, String mensagem, Long id);

    List<Topico> findAllByOrderByDataCriacaoAsc();

    List<Topico> findByCursoAndDataCriacaoYear(String curso, int year);
}