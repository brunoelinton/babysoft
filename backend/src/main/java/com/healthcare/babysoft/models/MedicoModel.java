package com.healthcare.babysoft.models;

import com.healthcare.babysoft.enums.Status;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import java.util.Objects;

@Entity
@Table(name = "TB_MEDICO")
@PrimaryKeyJoinColumn(name = "cpf_funcionario")
public class MedicoModel extends FuncionarioModel {

    @Column(unique = true)
    private String crm;

    public MedicoModel() {}

    public MedicoModel(String crm) {
        this.crm = crm;
    }

    public MedicoModel(String cpf, String nome, String email, String senha, Status status, String crm) {
        super(cpf, nome, email, senha, status);
        this.crm = crm;
    }

    public String getCrm() {
        return crm;
    }

    public void setCrm(String crm) {
        this.crm = crm;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        MedicoModel that = (MedicoModel) o;
        return crm.equals(that.crm);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), crm);
    }
}
