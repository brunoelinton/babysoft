package com.healthcare.babysoft.dtos;

import com.healthcare.babysoft.models.EspecialidadeModel;
import com.healthcare.babysoft.services.validation.EspecialidadeInsertValid;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@EspecialidadeInsertValid
public class EspecialidadeDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer especialidadeId;

    @NotBlank(message = "Campo obrigatório.")
    private String especialidadeNome;

    public EspecialidadeDTO() {}

    public EspecialidadeDTO(Integer especialidadeId, String especialidadeNome) {
        this.especialidadeId = especialidadeId;
        this.especialidadeNome = especialidadeNome;
    }

    public EspecialidadeDTO(EspecialidadeModel especialidadeModel) {
        this.especialidadeId = especialidadeModel.getEspecialidadeId();
        this.especialidadeNome = especialidadeModel.getNome();
    }

    public Integer getEspecialidadeId() {
        return especialidadeId;
    }

    public void setEspecialidadeId(Integer especialidadeId) {
        this.especialidadeId = especialidadeId;
    }

    public String getEspecialidadeNome() {
        return especialidadeNome;
    }

    public void setEspecialidadeNome(String especialidadeNome) {
        this.especialidadeNome = especialidadeNome;
    }
}
