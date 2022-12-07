package com.healthcare.babysoft.models;

import com.healthcare.babysoft.enums.*;

import javax.persistence.*;
import javax.validation.constraints.Digits;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "TB_FICHA_PACIENTE")
public class FichaPacienteModel implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    private String cpf;

    @Convert(converter = TipoSanguineo.Mapeador.class)
    private TipoSanguineo tipoSanguineo;
    @Enumerated(EnumType.STRING)
    private SoroPositivo soroPositivo;
    @Enumerated(EnumType.STRING)
    private Hipertensao hipertensao;
    @Enumerated(EnumType.STRING)
    private Diabetes diabetes;
    @Enumerated(EnumType.STRING)
    private MedicacaoControlada medicacaoControlada;
    @Column(precision = 2, scale = 2)
    @Digits(integer = 10, fraction = 0)
    private Double peso;
    @Column(precision = 2, scale = 2)
    private Double altura;

    @OneToOne(fetch = FetchType.EAGER)
    @MapsId
    @JoinColumn(name = "cpf_paciente")
    private MaeModel mae;

    public FichaPacienteModel() {
    }

    public FichaPacienteModel(TipoSanguineo tipoSanguineo, SoroPositivo soroPositivo, Hipertensao hipertensao, Diabetes diabetes, MedicacaoControlada medicacaoControlada, Double peso, Double altura) {
        this.tipoSanguineo = tipoSanguineo;
        this.soroPositivo = soroPositivo;
        this.hipertensao = hipertensao;
        this.diabetes = diabetes;
        this.medicacaoControlada = medicacaoControlada;
        this.peso = peso;
        this.altura = altura;
    }

    public TipoSanguineo getTipoSanguineo() {
        return tipoSanguineo;
    }

    public void setTipoSanguineo(TipoSanguineo tipoSanguineo) {
        this.tipoSanguineo = tipoSanguineo;
    }

    public SoroPositivo getSoroPositivo() {
        return soroPositivo;
    }

    public void setSoroPositivo(SoroPositivo soroPositivo) {
        this.soroPositivo = soroPositivo;
    }

    public Hipertensao getHipertensao() {
        return hipertensao;
    }

    public void setHipertensao(Hipertensao hipertensao) {
        this.hipertensao = hipertensao;
    }

    public Diabetes getDiabetes() {
        return diabetes;
    }

    public void setDiabetes(Diabetes diabetes) {
        this.diabetes = diabetes;
    }

    public MedicacaoControlada getMedicacaoControlada() {
        return medicacaoControlada;
    }

    public void setMedicacaoControlada(MedicacaoControlada medicacaoControlada) {
        this.medicacaoControlada = medicacaoControlada;
    }

    public Double getPeso() {
        return peso;
    }

    public void setPeso(Double peso) {
        this.peso = peso;
    }

    public Double getAltura() {
        return altura;
    }

    public void setAltura(Double altura) {
        this.altura = altura;
    }

    public MaeModel getMae() {
        return mae;
    }

    public void setMae(MaeModel mae) {
        this.mae = mae;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FichaPacienteModel that = (FichaPacienteModel) o;
        return cpf.equals(that.cpf);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cpf);
    }
}
