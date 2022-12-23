package com.healthcare.babysoft.models;

import com.healthcare.babysoft.enums.Status;

import javax.persistence.*;
import javax.validation.Constraint;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "TB_MEDICO")
@PrimaryKeyJoinColumn(name = "cpf_funcionario")
public class MedicoModel extends FuncionarioModel {

    @Column(unique = true, nullable = false)
    private String crm;

    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    @JoinColumn(name = "especialidade_id", nullable = false)
    private EspecialidadeModel especialidade;

    @OneToMany(mappedBy = "equipeMedicaId.medico", cascade = CascadeType.ALL)
    private Set<EquipeMedicaModel> equipeMedica = new HashSet<>();

    public MedicoModel() {}

    public MedicoModel(String crm) {
        this.crm = crm;
    }

    public MedicoModel(String cpf, String nome, String email, String senha, Status status, String crm, EspecialidadeModel especialidade) {
        super(cpf, nome, email, senha, status);
        this.crm = crm;
    }

    public String getCrm() {
        return crm;
    }

    public void setCrm(String crm) {
        this.crm = crm;
    }

    public EspecialidadeModel getEspecialidade() {
        return especialidade;
    }

    public void setEspecialidade(EspecialidadeModel especialidade) { this.especialidade = especialidade; }

    public Set<EquipeMedicaModel> getEquipeMedica() {
        return equipeMedica;
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
