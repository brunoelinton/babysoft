package com.healthcare.babysoft.models;

import com.healthcare.babysoft.models.pk.EquipeEnfermagemPK;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "TB_EQUIPE_ENFERMAGEM")
public class EquipeEnfermagemModel implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @EmbeddedId
    private EquipeEnfermagemPK equipeEnfermagemId;

    public EquipeEnfermagemModel() {}

    public EquipeEnfermagemModel(EnfermeiroModel enfermeiro, EquipePartoModel equipeParto) {
        equipeEnfermagemId.setEnfermeiro(enfermeiro);
        equipeEnfermagemId.setEquipeParto(equipeParto);
    }

    public EnfermeiroModel getEnfermeiro() { return equipeEnfermagemId.getEnfermeiro(); }

    public void setEnfermeiro(EnfermeiroModel enfermeiro) { equipeEnfermagemId.setEnfermeiro(enfermeiro); }

    public EquipePartoModel getEquipeParto() { return equipeEnfermagemId.getEquipeParto(); }

    public void setEquipeParto(EquipePartoModel equipeParto) { equipeEnfermagemId.setEquipeParto(equipeParto); }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EquipeEnfermagemModel that = (EquipeEnfermagemModel) o;
        return equipeEnfermagemId.equals(that.equipeEnfermagemId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(equipeEnfermagemId);
    }
}
