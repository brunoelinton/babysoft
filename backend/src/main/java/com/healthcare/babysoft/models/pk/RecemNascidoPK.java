package com.healthcare.babysoft.models.pk;

import com.healthcare.babysoft.models.MaeModel;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

@Embeddable
public class RecemNascidoPK implements Serializable {
    private static final long serialVersionUID = 1L;

    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    @JoinColumn(name = "cpf_mae", nullable = false)
    private MaeModel mae;

    private LocalDateTime dataNascimento;

    public RecemNascidoPK() {}

    public RecemNascidoPK(MaeModel maeModel, LocalDateTime dataNascimento) {
        this.mae = maeModel;
        this.dataNascimento = dataNascimento;
    }

    public MaeModel getMaeModel() {
        return mae;
    }

    public void setMaeModel(MaeModel mae) {
        this.mae = mae;
    }

    public LocalDateTime getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDateTime dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RecemNascidoPK that = (RecemNascidoPK) o;
        return mae.equals(that.mae) && dataNascimento.equals(that.dataNascimento);
    }

    @Override
    public int hashCode() {
        return Objects.hash(mae, dataNascimento);
    }
}
