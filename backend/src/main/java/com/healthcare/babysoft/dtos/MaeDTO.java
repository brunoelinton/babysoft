package com.healthcare.babysoft.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.healthcare.babysoft.models.MaeModel;
import com.healthcare.babysoft.services.validation.MaeInsertValid;
import com.healthcare.babysoft.services.validation.MedicoInsertValid;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;

@MaeInsertValid
public class MaeDTO implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private String cpf;
    @NotBlank(message = "Campo obrigatório")
    private String nome;
    @NotNull(message = "Campo obrigatório")
    @PastOrPresent(message = "A data não pode ser futura.")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private LocalDate dataNascimento;
    @NotBlank(message = "Campo obrigatório")
    private String telefone;
    @NotBlank(message = "Campo obrigatório")
    private String endereco;
    @NotNull(message = "Campo obrigatório")
    private Integer numero;
    @NotBlank(message = "Campo obrigatório")
    private String bairro;
    @NotBlank(message = "Campo obrigatório")
    private String uf;
    @NotBlank(message = "Campo obrigatório")
    private String cep;
    private String complemento;

    public MaeDTO() {
    }

    public MaeDTO(String cpf, String nome, LocalDate dataNascimento, String telefone, String endereco, Integer numero, String bairro, String uf, String cep, String complemento) {
        this.cpf = cpf;
        this.nome = nome;
        this.dataNascimento = dataNascimento;
        this.telefone = telefone;
        this.endereco = endereco;
        this.numero = numero;
        this.bairro = bairro;
        this.uf = uf;
        this.cep = cep;
        this.complemento = complemento;
    }

    public MaeDTO(MaeModel maeModel) {
        cpf = maeModel.getCpf();
        nome = maeModel.getNome();
        dataNascimento = maeModel.getDataNascimento();
        telefone = maeModel.getTelefone();
        endereco = maeModel.getEndereco();
        numero = maeModel.getNumero();
        bairro = maeModel.getBairro();
        complemento = maeModel.getComplemento();
        uf = maeModel.getUf();
        cep = maeModel.getCep();
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }
}
