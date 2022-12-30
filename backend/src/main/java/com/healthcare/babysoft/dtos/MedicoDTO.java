package com.healthcare.babysoft.dtos;

import com.healthcare.babysoft.enums.Status;
import com.healthcare.babysoft.models.FuncionarioModel;
import com.healthcare.babysoft.models.MedicoModel;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serial;

public class MedicoDTO extends FuncionarioDTO {
    @NotBlank
    private String crm;

    @NotBlank
    private String especialidade;

    public MedicoDTO() {}

    public MedicoDTO(String crm){
        this.crm = crm;
    }

    public MedicoDTO(String cpf, String nome, String email, String senha, Status status, String crm, String especialidade) {
        super(cpf, nome, email, senha, status);
        this.crm = crm;
        this.especialidade = especialidade;
    }

    public MedicoDTO(FuncionarioModel funcionarioModel, String crm, String especialidade) {
        super(funcionarioModel);
        this.crm = crm;
        this.especialidade = especialidade;
    }

    public MedicoDTO(MedicoModel medicoModel) {
        super(medicoModel);
        crm = medicoModel.getCrm();
        especialidade = medicoModel.getEspecialidade().getNome();
    }

    public String getCrm() {
        return crm;
    }

    public void setCrm(String crm) {
        this.crm = crm;
    }

    public String getEspecialidade() {
        return especialidade;
    }

    public void setEspecialidade(String especialidade) {
        this.especialidade = especialidade;
    }
}