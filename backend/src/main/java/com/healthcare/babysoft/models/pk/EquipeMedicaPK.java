package com.healthcare.babysoft.models.pk;

import com.healthcare.babysoft.models.EquipePartoModel;
import com.healthcare.babysoft.models.MedicoModel;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class EquipeMedicaPK implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @ManyToOne
    @JoinColumn(name = "crm", referencedColumnName = "crm")
    private MedicoModel medico;

    @ManyToOne
    @JoinColumn(name = "equipe_parto_id")
    private EquipePartoModel equipeParto;

    public EquipeMedicaPK() {}

    public EquipeMedicaPK(MedicoModel medico, EquipePartoModel equipeParto) {
        this.medico = medico;
        this.equipeParto = equipeParto;
    }

    public MedicoModel getMedico() {
        return medico;
    }

    public void setMedico(MedicoModel medico) {
        this.medico = medico;
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
        EquipeMedicaPK that = (EquipeMedicaPK) o;
        return medico.equals(that.medico) && equipeParto.equals(that.equipeParto);
    }

    @Override
    public int hashCode() {
        return Objects.hash(medico, equipeParto);
    }
}
