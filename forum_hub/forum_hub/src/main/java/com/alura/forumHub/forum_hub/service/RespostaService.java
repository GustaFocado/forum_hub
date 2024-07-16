package com.alura.forumHub.forum_hub.service;

import com.exemplo.projeto.dto.RespostaDTO;
import com.exemplo.projeto.modelo.Resposta;
import com.exemplo.projeto.repository.RespostaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class RespostaService {

    @Autowired
    private RespostaRepository respostaRepository;

    public List<Resposta> obterTodasRespostas() {
        return respostaRepository.findAll();
    }

    public Optional<Resposta> obterRespostaPorId(Long id) {
        return respostaRepository.findById(id);
    }

    public Resposta criarResposta(RespostaDTO respostaDTO) {
        Resposta resposta = new Resposta(
                respostaDTO.getMensagem(),
                LocalDateTime.now(),
                respostaDTO.getAutor(),
                respostaDTO.getTopico(),
                respostaDTO.isSolucao());

        return respostaRepository.save(resposta);
    }

    public Optional<Resposta> atualizarResposta(Long id, RespostaDTO respostaDTO) {
        Optional<Resposta> optionalResposta = respostaRepository.findById(id);
        if (optionalResposta.isPresent()) {
            Resposta resposta = optionalResposta.get();
            resposta.setMensagem(respostaDTO.getMensagem());
            resposta.setAutor(respostaDTO.getAutor());
            resposta.setTopico(respostaDTO.getTopico());
            resposta.setSolucao(respostaDTO.isSolucao());
            return Optional.of(respostaRepository.save(resposta));
        }
        return Optional.empty();
    }

    public void deletarResposta(Long id) {
        respostaRepository.deleteById(id);
    }
}


