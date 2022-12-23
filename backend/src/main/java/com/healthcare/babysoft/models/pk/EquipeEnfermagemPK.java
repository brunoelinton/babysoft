package com.healthcare.babysoft.models.pk;

import com.healthcare.babysoft.models.EnfermeiroModel;
import com.healthcare.babysoft.models.EquipePartoModel;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class EquipeEnfermagemPK implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @ManyToOne
    @JoinColumn(name = "coren", referencedColumnName = "inscricao_coren")
    private EnfermeiroModel enfermeiro;

    @ManyToOne
    @JoinColumn(name = "equipe_parto_id")
    private EquipePartoModel equipeParto;

    public EquipeEnfermagemPK() {}

    public EquipeEnfermagemPK(EnfermeiroModel enfermeiro, EquipePartoModel equipeParto) {
        this.enfermeiro = enfermeiro;
        this.equipeParto = equipeParto;
    }

    public EnfermeiroModel getEnfermeiro() {
        return enfermeiro;
    }

    public void setEnfermeiro(EnfermeiroModel enfermeiro) {
        this.enfermeiro = enfermeiro;
    }

    public EquipePartoModel getEquipeParto() {
        return equipeParto;
    }

    public void setEquipeParto(EquipePartoModel equipeParto) {
        this.equipeParto = equipeParto;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EquipeEnfermagemPK that = (EquipeEnfermagemPK) o;
        return enfermeiro.equals(that.enfermeiro) && equipeParto.equals(that.equipeParto);
    }

    @Override
    public int hashCode() {
        return Objects.hash(enfermeiro, equipeParto);
    }
}
