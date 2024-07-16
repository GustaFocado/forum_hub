package com.alura.forumHub.forum_hub.controller;

import com.alura.forumHub.forum_hub.dto.TopicoDTO;
import com.alura.forumHub.forum_hub.model.Topico;
import com.alura.forumHub.forum_hub.service.TopicoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/topicos")
public class TopicoController {

    @Autowired
    private TopicoService topicoService;

    @GetMapping
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<List<Topico>> listarTopicos() {
        List<Topico> topicos = topicoService.obterTodosTopicos();
        return ResponseEntity.ok(topicos);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<Topico> obterTopicoPorId(@PathVariable Long id) {
        Optional<Topico> topico = topicoService.obterTopicoPorId(id);
        return topico.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Topico> criarTopico(@Valid @RequestBody TopicoDTO topicoDTO) {
        Topico topicoCriado = topicoService.criarTopico(topicoDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(topicoCriado);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Topico> atualizarTopico(@PathVariable Long id, @Valid @RequestBody TopicoDTO topicoDTO) {
        Optional<Topico> topicoAtualizado = Optional.ofNullable(topicoService.atualizarTopico(id, topicoDTO));
        return topicoAtualizado.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deletarTopico(@PathVariable Long id) {
        topicoService.deletarTopico(id);
        return ResponseEntity.noContent().build();
    }
}
