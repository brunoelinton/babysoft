package com.healthcare.babysoft.models;

import com.healthcare.babysoft.enums.DOULA;

import javax.persistence.*;
import java.io.Serial;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "TB_EQUIPE_PARTO")
public class EquipePartoModel implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long equipePartoId;
    @Enumerated(EnumType.STRING)
    private DOULA doula;

    @OneToMany(mappedBy = "equipeMedicaId.equipeParto", cascade = CascadeType.ALL)
    private Set<EquipeMedicaModel> equipeMedica = new HashSet<>();

    @OneToMany(mappedBy = "equipeEnfermagemId.equipeParto", cascade = CascadeType.ALL)
    private Set<EquipeEnfermagemModel> equipeEnfermagemModel = new HashSet<>();

    @OneToMany(mappedBy = "equipeParto")
    private Set<PartoModel> partos = new HashSet<>();

    public EquipePartoModel() {}

    public EquipePartoModel(Long equipePartoId, DOULA doula) {
        this.equipePartoId = equipePartoId;
        this.doula = doula;
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

    public Set<EquipeMedicaModel> getEquipeMedica() {
        return equipeMedica;
    }

    public Set<PartoModel> getPartos() {
        return partos;
    }

    public Set<EquipeEnfermagemModel> getEquipeEnfermagemModel() {
        return equipeEnfermagemModel;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EquipePartoModel that = (EquipePartoModel) o;
        return equipePartoId.equals(that.equipePartoId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(equipePartoId);
    }
}
