package com.alura.forumHub.forum_hub.service;

import com.alura.forumHub.forum_hub.dto.TopicoDTO;
import com.alura.forumHub.forum_hub.model.Topico;
import com.alura.forumHub.forum_hub.repository.TopicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;

@Service
public class TopicoService {
    @Autowired
    private TopicoRepository topicoRepositorio;

    public Topico atualizarTopico(Long id, TopicoDTO topicoDTO) {
        Optional<Topico> optionalTopico = topicoRepositorio.findById(id);
        if (optionalTopico.isEmpty()) {
            throw new IllegalArgumentException("Tópico não encontrado");
        }

        Topico topico = optionalTopico.get();


        if (topicoRepositorio.existsByTituloAndMensagemAndIdNot(topicoDTO.getTitulo(), topicoDTO.getMensagem(), id)) {
            throw new IllegalArgumentException("Já existe um outro tópico com o mesmo título e mensagem");
        }

        topico.setTitulo(topicoDTO.getTitulo());
        topico.setMensagem(topicoDTO.getMensagem());
        topico.setAutor(topicoDTO.getAutor());
        topico.setCurso(topicoDTO.getCurso());

        return topicoRepositorio.save(topico);
    }

    public List<Topico> obterTodosTopicos() {
        return topicoRepositorio.findAll();
    }

    public Optional<Topico> obterTopicoPorId(Long id) {
        return topicoRepositorio.findById(id);
    }

    public Topico criarTopico(TopicoDTO topicoDTO) {
        if (topicoRepositorio.existsByTituloAndMensagem(topicoDTO.getTitulo(), topicoDTO.getMensagem())) {
            throw new IllegalArgumentException("topico duplicado");
        }
        return null;
    }

    public void deletarTopico(Long id) {
        topicoRepositorio.deleteById(id);
    }
}



