package com.healthcare.babysoft.dtos;

import com.healthcare.babysoft.models.EspecialidadeModel;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

public class EspecialidadeDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer especialidadeId;

    @NotBlank
    private String nome;

    public EspecialidadeDTO() {}

    public EspecialidadeDTO(Integer especialidadeId, String nome) {
        this.especialidadeId = especialidadeId;
        this.nome = nome;
    }

    public EspecialidadeDTO(EspecialidadeModel especialidadeModel) {
        this.especialidadeId = especialidadeModel.getEspecialidadeId();
        this.nome = especialidadeModel.getNome();
    }

    public Integer getEspecialidadeId() {
        return especialidadeId;
    }

    public void setEspecialidadeId(Integer especialidadeId) {
        this.especialidadeId = especialidadeId;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
