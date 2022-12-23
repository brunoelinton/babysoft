package com.healthcare.babysoft.dtos;

import com.healthcare.babysoft.enums.DOULA;
import com.healthcare.babysoft.models.EquipeEnfermagemModel;
import com.healthcare.babysoft.models.EquipeMedicaModel;
import com.healthcare.babysoft.models.EquipePartoModel;

import javax.validation.constraints.NotNull;
import java.io.Serial;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class EquipePartoDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;
    private Long equipePartoId;

    @NotNull
    private DOULA doula;

    @NotNull
    private Set<EquipeMedicaDTO> equipeMedica = new HashSet<>();

    @NotNull
    private Set<EquipeEnfermagemDTO> equipeEnfermagem = new HashSet<>();

    public EquipePartoDTO() {}

    public EquipePartoDTO(Long equipePartoId, DOULA doula) {
        this.equipePartoId = equipePartoId;
        this.doula = doula;
    }

    public EquipePartoDTO(EquipePartoModel equipeParto) {
        equipePartoId = equipeParto.getEquipePartoId();
        doula = equipeParto.getDoula();

        for(EquipeMedicaModel em: equipeParto.getEquipeMedica()){
            equipeMedica.add(new EquipeMedicaDTO(em));
        }

        for(EquipeEnfermagemModel ef: equipeParto.getEquipeEnfermagemModel()) {
            equipeEnfermagem.add(new EquipeEnfermagemDTO(ef));
        }
    }

    public Long getEquipePartoId() {
        return equipePartoId;
    }

    public void setEquipePartoId(Long equipePartoId) {
        this.equipePartoId = equipePartoId;
    }

    public DOULA getDoula() {
        return doula;
    }

    public void setDoula(DOULA doula) {
        this.doula = doula;
    }

    public Set<EquipeMedicaDTO> getEquipeMedica() {
        return equipeMedica;
    }

    public Set<EquipeEnfermagemDTO> getEquipeEnfermagem() {
        return equipeEnfermagem;
    }
}
