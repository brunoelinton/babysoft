package com.healthcare.babysoft.dtos;

import com.healthcare.babysoft.enums.Status;
import com.healthcare.babysoft.models.EnfermeiroModel;
import com.healthcare.babysoft.models.FuncionarioModel;
import com.healthcare.babysoft.services.validation.EnfermeiroInsertValid;

import javax.validation.constraints.NotBlank;

@EnfermeiroInsertValid
public class EnfermeiroDTO extends FuncionarioDTO{

    @NotBlank
    private String inscricaoCoren;

    public EnfermeiroDTO() {}

    public EnfermeiroDTO(String inscricaoCoren) {
        this.inscricaoCoren = inscricaoCoren;
    }

    public EnfermeiroDTO(String cpf, String nome, String email, String senha, Status status, String inscricaoCoren) {
        super(cpf, nome, email, senha, status);
        this.inscricaoCoren = inscricaoCoren;
    }

    public EnfermeiroDTO(FuncionarioModel funcionarioModel, String inscricaoCoren) {
        super(funcionarioModel);
        this.inscricaoCoren = inscricaoCoren;
    }

    public EnfermeiroDTO(EnfermeiroModel enfermeiroModel) {
        super(enfermeiroModel);
        inscricaoCoren = enfermeiroModel.getInscricaoCoren();
    }

    public String getInscricaoCoren() {
        return inscricaoCoren;
    }

    public void setInscricaoCoren(String inscricaoCoren) {
        this.inscricaoCoren = inscricaoCoren;
    }
}
