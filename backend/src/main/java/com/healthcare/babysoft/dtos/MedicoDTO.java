package com.healthcare.babysoft.dtos;

import com.healthcare.babysoft.enums.Status;
import com.healthcare.babysoft.models.FuncionarioModel;
import com.healthcare.babysoft.models.MedicoModel;

import javax.validation.constraints.NotBlank;
import java.io.Serial;

public class MedicoDTO extends FuncionarioDTO {
    @NotBlank
    private String crm;

    public MedicoDTO() {}

    public MedicoDTO(String crm) {
        this.crm = crm;
    }

    public MedicoDTO(String cpf, String nome, String email, String senha, Status status, String crm) {
        super(cpf, nome, email, senha, status);
        this.crm = crm;
    }

    public MedicoDTO(FuncionarioModel funcionarioModel, String crm) {
        super(funcionarioModel);
        this.crm = crm;
    }

    public MedicoDTO(MedicoModel medicoModel) {
        super(medicoModel);
        crm = medicoModel.getCrm();
    }

    public String getCrm() {
        return crm;
    }

    public void setCrm(String crm) {
        this.crm = crm;
    }
}
