package com.healthcare.babysoft.models;

import com.healthcare.babysoft.enums.Status;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "TB_ENFERMEIRO")
@PrimaryKeyJoinColumn(name = "cpf_funcionario")
public class EnfermeiroModel extends FuncionarioModel {

    @Column(name = "inscricao_coren", unique = true, nullable = false)
    private String inscricaoCoren;

    @OneToMany(mappedBy = "equipeEnfermagemId.enfermeiro", cascade = CascadeType.ALL)
    private Set<EquipeEnfermagemModel> equipeEnfermagemModel = new HashSet<>();

    public EnfermeiroModel() {}

    public EnfermeiroModel(String inscricaoCoren) {
        this.inscricaoCoren = inscricaoCoren;
    }

    public EnfermeiroModel(String cpf, String nome, String email, String senha, Status status, String inscricaoCoren) {
        super(cpf, nome, email, senha, status);
        this.inscricaoCoren = inscricaoCoren;
    }

    public String getInscricaoCoren() {
        return inscricaoCoren;
    }

    public void setInscricaoCoren(String inscricaoCoren) {
        this.inscricaoCoren = inscricaoCoren;
    }

    public Set<EquipeEnfermagemModel> getEquipeEnfermagemModel() {
        return equipeEnfermagemModel;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        EnfermeiroModel that = (EnfermeiroModel) o;
        return inscricaoCoren.equals(that.inscricaoCoren);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), inscricaoCoren);
    }
}
