package com.healthcare.babysoft.dtos;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonView;
import com.healthcare.babysoft.enums.Status;
import com.healthcare.babysoft.models.FuncionarioModel;

import java.io.Serial;
import java.io.Serializable;

public class FuncionarioDTO implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private String cpf;
    private String nome;
    private String email;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String senha;
    private Status status;

    public FuncionarioDTO() {}

    public FuncionarioDTO(String cpf, String nome, String email, String senha, Status status) {
        this.cpf = cpf;
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.status = status;
    }

    public FuncionarioDTO(FuncionarioModel funcionarioModel) {
        cpf = funcionarioModel.getCpf();
        nome = funcionarioModel.getNome();
        email = funcionarioModel.getEmail();
        // senha = funcionarioModel.getSenha();
        status = funcionarioModel.getStatus();
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
