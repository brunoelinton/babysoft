package com.healthcare.babysoft.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.healthcare.babysoft.enums.Condicao;
import com.healthcare.babysoft.models.pk.RecemNascidoPK;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "TB_RECEM_NASCIDO")
public class RecemNascidoModel implements Serializable {
    private static final long serialVersionUID = 1L;

    @EmbeddedId
    private RecemNascidoPK recemNascidoId = new RecemNascidoPK();
    @Column(nullable = false, length = 50)
    private String nome;
    @JsonIgnore
    @Column(nullable = true, length = 50)
    private String cpfPai;
    @Column(nullable = false, length = 1)
    private Character sexo;
    @Column(nullable = false, precision = 2, scale = 2)
    private Double peso;
    @Column(nullable = false, precision = 2, scale = 2)
    private Double altura;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Condicao condicao;

    @OneToOne(mappedBy = "recemNascido", cascade = CascadeType.ALL)
    private PartoModel parto;

    public RecemNascidoModel(){}

    public RecemNascidoModel(MaeModel maeModel, String nome, String cpfPai, LocalDateTime dataNascimento, Character sexo, Double peso, Double altura, Condicao condicao) {
        super();
        recemNascidoId.setDataNascimento(dataNascimento);
        recemNascidoId.setMaeModel(maeModel);
        this.nome = nome;
        this.cpfPai = cpfPai;
        this.sexo = sexo;
        this.peso = peso;
        this.altura = altura;
        this.condicao = condicao;
    }

    public MaeModel getMaeModel() {
        return recemNascidoId.getMaeModel();
    }

    public void setMaeModel(MaeModel maeModel) {
        recemNascidoId.setMaeModel(maeModel);
    }

    public LocalDateTime getDataNascimento() {
        return recemNascidoId.getDataNascimento();
    }

    public void setDataNascimento(LocalDateTime dataNascimento) {
        recemNascidoId.setDataNascimento(dataNascimento);
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

    public RecemNascidoPK getRecemNascidoId() {
        return recemNascidoId;
    }

    public void setRecemNascidoId(RecemNascidoPK recemNascidoId) {
        this.recemNascidoId = recemNascidoId;
    }
}
