package com.healthcare.babysoft.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;

import com.healthcare.babysoft.enums.Condicao;
import com.healthcare.babysoft.models.MaeModel;
import com.healthcare.babysoft.models.RecemNascidoModel;
import com.healthcare.babysoft.utils.FactoryId;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import java.io.Serial;
import java.io.Serializable;

import java.time.LocalDateTime;

public class RecemNascidoDTO implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private String recemNascidoId;
    @NotBlank
    private String nome;
    private String cpfPai;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss")
    @NotNull
    private LocalDateTime dataNascimento;

    @NotNull
    private Character sexo;
    @NotNull
    private Double peso;
    @NotNull
    private Double altura;
    @NotNull
    private Condicao condicao;

    @NotBlank
    private String cpfMae;

    private String nomeMae;

    public RecemNascidoDTO() {}

    public RecemNascidoDTO(String nome, String cpfPai, LocalDateTime dataNascimento, Character sexo, Double peso, Double altura, Condicao condicao, MaeModel mae) {
        String data = FactoryId.buildId(dataNascimento);
        this.recemNascidoId = mae.getCpf() + data;
        this.nome = nome;
        this.cpfPai = cpfPai;
        this.dataNascimento = dataNascimento;
        this.sexo = sexo;
        this.peso = peso;
        this.altura = altura;
        this.condicao = condicao;
        this.nomeMae = mae.getNome();
    }

    public RecemNascidoDTO(RecemNascidoModel recemNascidoModel) {
        String data = FactoryId.buildId(recemNascidoModel.getDataNascimento());

        recemNascidoId = (recemNascidoModel.getMaeModel().getCpf() + data);
        cpfMae = recemNascidoModel.getMaeModel().getCpf();
        nomeMae = recemNascidoModel.getMaeModel().getNome();
        nome = recemNascidoModel.getNome();
        cpfPai = recemNascidoModel.getCpfPai();
        dataNascimento = recemNascidoModel.getDataNascimento();
        sexo = recemNascidoModel.getSexo();
        peso = recemNascidoModel.getPeso();
        altura = recemNascidoModel.getAltura();
        condicao = recemNascidoModel.getCondicao();
    }

    public String getRecemNascidoId() {
        return recemNascidoId;
    }

    public void setRecemNascidoId(String recemNascidoId) {
        this.recemNascidoId = recemNascidoId;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpfPai() {
        return cpfPai;
    }

    public void setCpfPai(String cpfPai) {
        this.cpfPai = cpfPai;
    }

    public LocalDateTime getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDateTime dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public Character getSexo() {
        return sexo;
    }

    public void setSexo(Character sexo) {
        this.sexo = sexo;
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

    public Condicao getCondicao() {
        return condicao;
    }

    public void setCondicao(Condicao condicao) {
        this.condicao = condicao;
    }

    public String getCpfMae() {
        return cpfMae;
    }

    public void setCpfMae(String cpfMae) {
        this.cpfMae = cpfMae;
    }

    public String getNomeMae() { return nomeMae; }

    public void setNomeMae (String nomeMae) { this.nomeMae = nomeMae; }
}
