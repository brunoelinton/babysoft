package com.healthcare.babysoft.models;

import javax.persistence.*;
import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EspecialidadeModel that = (EspecialidadeModel) o;
        return especialidadeId.equals(that.especialidadeId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(especialidadeId);
    }
}
