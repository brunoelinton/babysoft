package com.healthcare.babysoft.models;

import javax.persistence.*;
import java.io.Serial;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "TB_ESPECIALIDADE")
public class EspecialidadeModel implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer especialidadeId;
    @Column(unique = true)
    private String nome;

    @OneToMany(mappedBy = "especialidade", cascade = CascadeType.ALL)
    private Set<MedicoModel> medicos = new HashSet<>();

    public EspecialidadeModel() {
    }

    public EspecialidadeModel(Integer especialidadeId, String nome) {
        this.especialidadeId = especialidadeId;
        this.nome = nome;
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

    public Set<MedicoModel> getMedicos() {
        return medicos;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EspecialidadeModel that = (EspecialidadeModel) o;
        return especialidadeId.equals(that.especialidadeId);
    }

    public void adicionarMedicos(MedicoModel medicoModel) {
        medicos.add(medicoModel);
    }

    @Override
    public int hashCode() {
        return Objects.hash(especialidadeId);
    }
}
