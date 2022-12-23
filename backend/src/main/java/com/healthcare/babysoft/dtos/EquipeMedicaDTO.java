package com.healthcare.babysoft.dtos;

import com.healthcare.babysoft.models.EquipeMedicaModel;

import java.io.Serial;
import java.io.Serializable;

public class EquipeMedicaDTO implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private MedicoDTO medico;

    public EquipeMedicaDTO() {}

    public EquipeMedicaDTO(MedicoDTO medico) {
        this.medico = medico;
    }

    public EquipeMedicaDTO(EquipeMedicaModel equipeMedica) {
        medico = new MedicoDTO(equipeMedica.getMedico());
    }

    public MedicoDTO getMedico() {
        return medico;
    }

    public void setMedico(MedicoDTO medico) {
        this.medico = medico;
    }
}
