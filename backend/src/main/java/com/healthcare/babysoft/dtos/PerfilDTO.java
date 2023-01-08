package com.healthcare.babysoft.dtos;

import com.healthcare.babysoft.models.Perfil;

import javax.validation.constraints.NotBlank;
import java.io.Serial;
import java.io.Serializable;

public class PerfilDTO implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private Long id;
    @NotBlank(message = "Campo obrigatório.")
    private String autoridade;

    public PerfilDTO() {}

    public PerfilDTO(Long id, String autoridade) {
        this.id = id;
        this.autoridade = autoridade;
    }

    public PerfilDTO(Perfil perfil) {
        id = perfil.getId();
        autoridade = perfil.getAutoridade();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAutoridade() {
        return autoridade;
    }

    public void setAutoridade(String autoridade) {
        this.autoridade = autoridade;
    }
}
