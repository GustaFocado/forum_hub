package com.alura.forumHub.forum_hub.controller;


import com.alura.forumHub.forum_hub.dto.RespostaDTO;
import com.alura.forumHub.forum_hub.model.Resposta;
import com.alura.forumHub.forum_hub.service.RespostaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/respostas")
public class RespostaController {

    @Autowired
    private RespostaService respostaService;

    @GetMapping
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<List<Resposta>> listarRespostas() {
        List<Resposta> respostas = respostaService.obterTodasRespostas();
        return ResponseEntity.ok(respostas);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<Resposta> obterRespostaPorId(@PathVariable Long id) {
        Optional<Resposta> resposta = respostaService.obterRespostaPorId(id);
        return resposta.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<Resposta> criarResposta(@Valid @RequestBody RespostaDTO respostaDTO) {
        Resposta respostaCriada = respostaService.criarResposta(respostaDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(respostaCriada);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<Resposta> atualizarResposta(@PathVariable Long id, @Valid @RequestBody RespostaDTO respostaDTO) {
        Optional<Resposta> respostaAtualizada = respostaService.atualizarResposta(id, respostaDTO);
        return respostaAtualizada.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<Void> deletarResposta(@PathVariable Long id) {
        respostaService.deletarResposta(id);
        return ResponseEntity.noContent().build();
    }
}