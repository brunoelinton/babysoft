package com.healthcare.babysoft.dtos;

import com.healthcare.babysoft.models.EquipeEnfermagemModel;

import java.io.Serial;
import java.io.Serializable;

public class EquipeEnfermagemDTO implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private EnfermeiroDTO enfermeiro;

    public EquipeEnfermagemDTO() {}

    public EquipeEnfermagemDTO(EnfermeiroDTO enfermeiro) {
        this.enfermeiro = enfermeiro;
    }

    public EquipeEnfermagemDTO(EquipeEnfermagemModel equipeEnfermagem) {
        enfermeiro = new EnfermeiroDTO(equipeEnfermagem.getEnfermeiro());
    }

    public EnfermeiroDTO getEnfermeiro() {
        return enfermeiro;
    }

    public void setEnfermeiro(EnfermeiroDTO enfermeiro) {
        this.enfermeiro = enfermeiro;
    }
}
