package com.healthcare.babysoft.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.healthcare.babysoft.enums.Multifetal;
import com.healthcare.babysoft.enums.PartoRisco;
import com.healthcare.babysoft.enums.TipoParto;
import com.healthcare.babysoft.models.EquipePartoModel;
import com.healthcare.babysoft.models.PartoModel;
import com.healthcare.babysoft.services.validation.PartoInsertValid;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import java.io.Serializable;
import java.time.LocalDateTime;

@PartoInsertValid
public class PartoDTO implements Serializable {

    private Long partoId;
    @NotNull(message = "Campo obrigatório.")
    @PastOrPresent(message = "A data deve ser passada ou presente.")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss")
    private LocalDateTime dataParto;

    @NotNull(message = "Campo obrigatório.")
    private MaeDTO mae;
    @NotNull(message = "Campo obrigatório.")
    private RecemNascidoDTO recemNascido;
    @NotNull(message = "Campo obrigatório.")
    private TipoParto tipoParto;
    @NotNull(message = "Campo obrigatório.")
    private PartoRisco partoRisco;
    @NotNull(message = "Campo obrigatório.")
    private Multifetal multifetal;
    @NotBlank(message = "Campo obrigatório.")
    private String observacao;

    @NotNull(message = "Campo obrigatório.")
    private EquipePartoDTO equipeParto;

    public PartoDTO() {}

    public PartoDTO(Long partoId, LocalDateTime dataParto, MaeDTO mae, RecemNascidoDTO recemNascido, TipoParto tipoParto, PartoRisco partoRisco, Multifetal multifetal, String observacao, EquipePartoDTO equipeParto) {
        this.partoId = partoId;
        this.dataParto = dataParto;
        this.mae = mae;
        this.recemNascido = recemNascido;
        this.tipoParto = tipoParto;
        this.partoRisco = partoRisco;
        this.multifetal = multifetal;
        this.observacao = observacao;
        this.equipeParto = equipeParto;
    }

    public PartoDTO(PartoModel partoModel) {
        partoId = partoModel.getPartoId();
        dataParto = partoModel.getRecemNascido().getDataNascimento();
        mae = new MaeDTO(partoModel.getRecemNascido().getMaeModel());
        recemNascido = new RecemNascidoDTO(partoModel.getRecemNascido());
        tipoParto = partoModel.getTipoParto();
        partoRisco = partoModel.getPartoRisco();
        multifetal = partoModel.getMultifetal();
        observacao = partoModel.getObservacao();
        equipeParto = new EquipePartoDTO(partoModel.getEquipeParto());
    }

    public Long getPartoId() {
        return partoId;
    }

    public void setPartoId(Long partoId) {
        this.partoId = partoId;
    }

    public LocalDateTime getDataParto() {
        return dataParto;
    }

    public void setDataParto(LocalDateTime dataParto) {
        this.dataParto = dataParto;
    }

    public MaeDTO getMae() {
        return mae;
    }

    public void setMae(MaeDTO mae) {
        this.mae = mae;
    }

    public RecemNascidoDTO getRecemNascido() {
        return recemNascido;
    }

    public void setRecemNascido(RecemNascidoDTO recemNascido) {
        this.recemNascido = recemNascido;
    }

    public TipoParto getTipoParto() {
        return tipoParto;
    }

    public void setTipoParto(TipoParto tipoParto) {
        this.tipoParto = tipoParto;
    }

    public PartoRisco getPartoRisco() {
        return partoRisco;
    }

    public void setPartoRisco(PartoRisco partoRisco) {
        this.partoRisco = partoRisco;
    }

    public Multifetal getMultifetal() {
        return multifetal;
    }

    public void setMultifetal(Multifetal multifetal) {
        this.multifetal = multifetal;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public EquipePartoDTO getEquipeParto() {
        return equipeParto;
    }

    public void setEquipeParto(EquipePartoDTO equipeParto) {
        this.equipeParto = equipeParto;
    }
}
