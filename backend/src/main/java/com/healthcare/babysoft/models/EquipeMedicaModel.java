package com.healthcare.babysoft.models;

import com.healthcare.babysoft.models.pk.EquipeMedicaPK;

import javax.persistence.*;
import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "TB_EQUIPE_MEDICA")
public class EquipeMedicaModel implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @EmbeddedId
    private EquipeMedicaPK equipeMedicaId = new EquipeMedicaPK();

    public EquipeMedicaModel() {
    }

    public EquipeMedicaModel(MedicoModel medico, EquipePartoModel equipeParto) {
        equipeMedicaId.setMedico(medico);
        equipeMedicaId.setEquipeParto(equipeParto);
    }

    public MedicoModel getMedico() {
        return equipeMedicaId.getMedico();
    }

    public void setMedico(MedicoModel medico) {
        equipeMedicaId.setMedico(medico);
    }

    public EquipePartoModel getEquipeParto() {
        return  equipeMedicaId.getEquipeParto();
    }

    public void setEquipeParto(EquipePartoModel equipeParto) {
        equipeMedicaId.setEquipeParto(equipeParto);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EquipeMedicaModel that = (EquipeMedicaModel) o;
        return equipeMedicaId.equals(that.equipeMedicaId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(equipeMedicaId);
    }
}
