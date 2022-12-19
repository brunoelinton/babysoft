package com.healthcare.babysoft.models;

import com.healthcare.babysoft.dtos.PartoDTO;
import com.healthcare.babysoft.enums.Multifetal;
import com.healthcare.babysoft.enums.PartoRisco;
import com.healthcare.babysoft.enums.TipoParto;
import com.healthcare.babysoft.models.pk.RecemNascidoPK;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "TB_PARTO", uniqueConstraints=@UniqueConstraint(columnNames={"data_parto","cpf_mae"}))
public class PartoModel implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long partoId;

    @OneToOne
    @JoinColumns({
            @JoinColumn(name = "data_parto"),
            @JoinColumn(name = "cpf_mae")
    })
    // @Column(unique = true)
    private RecemNascidoModel recemNascido;

/*----------------------------------------------------
    @EmbeddedId
    private RecemNascidoPK recemNascidoPK;

    @OneToOne
    @JoinColumns(
            {@JoinColumn(name ="cpf_mae", referencedColumnName = "mae", insertable = false, updatable = false), @JoinColumn(name = "data_parto", referencedColumnName = "dataNascimento", insertable = false, updatable = false)})
    private RecemNascidoModel recemNascido;
    ----------------------------------------------------*/

    /*-------------- MELHOR ATÉ AGORA ---------------------
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumns({
            @JoinColumn(name = "data_parto"),
            @JoinColumn(name = "cpf_mae")
    })
    private RecemNascidoModel recemNascido;
----------------------------------------------------------*/

    /*----------------------------------------------------

//    @Id
//    private RecemNascidoPK recemNascidoPK;
//
//    @MapsId
//    @OneToOne
//    @JoinColumns({
//            @JoinColumn(name = "data_parto"),
//            @JoinColumn(name = "cpf_mae")})
//    private RecemNascidoModel recemNascido;
    ----------------------------------------------------*/
    @Enumerated(EnumType.STRING)
    private TipoParto tipoParto;
    @Enumerated(EnumType.STRING)
    private Multifetal multifetal;
    @Enumerated(EnumType.STRING)
    private PartoRisco partoRisco;
    private String observacao;

    public PartoModel() {}

    public PartoModel(Long partoId, RecemNascidoModel recemNascido, TipoParto tipoParto, Multifetal multifetal, PartoRisco partoRisco, String observacao) {
        this.partoId = partoId;
        this.recemNascido = recemNascido;
        this.tipoParto = tipoParto;
        this.multifetal = multifetal;
        this.partoRisco = partoRisco;
        this.observacao = observacao;
    }

    public Long getPartoId() {
        return partoId;
    }

    public void setPartoId(Long partoId) {
        this.partoId = partoId;
    }

    public RecemNascidoModel getRecemNascido() {
        return recemNascido;
    }

    public void setRecemNascido(RecemNascidoModel recemNascido) {
        this.recemNascido = recemNascido;
    }

    public TipoParto getTipoParto() {
        return tipoParto;
    }

    public void setTipoParto(TipoParto tipoParto) {
        this.tipoParto = tipoParto;
    }

    public Multifetal getMultifetal() {
        return multifetal;
    }

    public void setMultifetal(Multifetal multifetal) {
        this.multifetal = multifetal;
    }

    public PartoRisco getPartoRisco() {
        return partoRisco;
    }

    public void setPartoRisco(PartoRisco partoRisco) {
        this.partoRisco = partoRisco;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PartoModel that = (PartoModel) o;
        return recemNascido.equals(that.recemNascido);
    }

    @Override
    public int hashCode() {
        return Objects.hash(recemNascido);
    }
}
