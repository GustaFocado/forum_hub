package com.alura.forumHub.forum_hub.dto;

import jakarta.validation.constraints.NotBlank;

public class TopicoDTO {

    @NotBlank
    private String titulo;

    @NotBlank
    private String mensagem;

    @NotBlank
    private String autor;

    @NotBlank
    private String curso;

    public @NotBlank String getTitulo() {
        return titulo;
    }

    public void setTitulo(@NotBlank String titulo) {
        this.titulo = titulo;
    }

    public @NotBlank String getMensagem() {
        return mensagem;
    }

    public void setMensagem(@NotBlank String mensagem) {
        this.mensagem = mensagem;
    }

    public @NotBlank String getAutor() {
        return autor;
    }

    public void setAutor(@NotBlank String autor) {
        this.autor = autor;
    }

    public @NotBlank String getCurso() {
        return curso;
    }

    public void setCurso(@NotBlank String curso) {
        this.curso = curso;
    }
}
