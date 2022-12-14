package com.healthcare.babysoft.dtos;

import com.healthcare.babysoft.enums.*;
import com.healthcare.babysoft.models.FichaPacienteModel;
import com.healthcare.babysoft.models.MaeModel;
import com.healthcare.babysoft.services.validation.FichaPacienteInsertValid;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serial;
import java.io.Serializable;

@FichaPacienteInsertValid
public class FichaPacienteDTO implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    String cpfPaciente;

//    @NotBlank(message = "Campo obrigatório.")
    String nomePaciente;
    @NotNull(message = "Campo obrigatório.")
    private TipoSanguineo tipoSanguineo;
    @NotNull(message = "Campo obrigatório.")
    private SoroPositivo soroPositivo;
    @NotNull(message = "Campo obrigatório.")
    private Hipertensao hipertensao;
    @NotNull(message = "Campo obrigatório.")
    private Diabetes diabetes;
    @NotNull(message = "Campo obrigatório.")
    private MedicacaoControlada medicacaoControlada;
    @NotNull(message = "Campo obrigatório.")
    private Double peso;
    @NotNull(message = "Campo obrigatório.")
    private Double altura;

    public FichaPacienteDTO() {
    }

    public FichaPacienteDTO(TipoSanguineo tipoSanguineo, SoroPositivo soroPositivo, Hipertensao hipertensao, Diabetes diabetes, MedicacaoControlada medicacaoControlada, Double peso, Double altura) {
        this.tipoSanguineo = tipoSanguineo;
        this.soroPositivo = soroPositivo;
        this.hipertensao = hipertensao;
        this.diabetes = diabetes;
        this.medicacaoControlada = medicacaoControlada;
        this.peso = peso;
        this.altura = altura;
    }

    public FichaPacienteDTO(FichaPacienteModel fichaPacienteModel) {
        cpfPaciente = fichaPacienteModel.getMae().getCpf();
        nomePaciente = fichaPacienteModel.getMae().getNome();
        tipoSanguineo = fichaPacienteModel.getTipoSanguineo();
        soroPositivo = fichaPacienteModel.getSoroPositivo();
        hipertensao = fichaPacienteModel.getHipertensao();
        diabetes = fichaPacienteModel.getDiabetes();
        medicacaoControlada = fichaPacienteModel.getMedicacaoControlada();
        peso = fichaPacienteModel.getPeso();
        altura = fichaPacienteModel.getAltura();
    }

    public String getCpfPaciente() {
        return cpfPaciente;
    }

    public void setCpfPaciente(String cpfPaciente) {
        this.cpfPaciente = cpfPaciente;
    }

    public String getNomePaciente() {
        return nomePaciente;
    }

    public void setNomePaciente(String nomePaciente) {
        this.nomePaciente = nomePaciente;
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
}