package com.alura.forumHub.forum_hub.dto;


import com.alura.forumHub.forum_hub.model.Topico;
import com.alura.forumHub.forum_hub.model.Usuario;

public class RespostaDTO {

    private String mensagem;
    private Usuario autor;
    private Topico topico;
    private boolean solucao;

    // Construtores, Getters e Setters

    public RespostaDTO() {
    }

    public RespostaDTO(String mensagem, Usuario autor, Topico topico, boolean solucao) {
        this.mensagem = mensagem;
        this.autor = autor;
        this.topico = topico;
        this.solucao = solucao;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public Usuario getAutor() {
        return autor;
    }

    public void setAutor(Usuario autor) {
        this.autor = autor;
    }

    public Topico getTopico() {
        return topico;
    }

    public void setTopico(Topico topico) {
        this.topico = topico;
    }

    public boolean isSolucao() {
        return solucao;
    }

    public void setSolucao(boolean solucao) {
        this.solucao = solucao;
    }
}