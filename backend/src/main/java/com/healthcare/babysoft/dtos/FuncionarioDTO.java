package com.healthcare.babysoft.dtos;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonView;
import com.healthcare.babysoft.enums.Status;
import com.healthcare.babysoft.models.FuncionarioModel;
import com.healthcare.babysoft.services.validation.FuncionarioInsertValid;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serial;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@FuncionarioInsertValid
public class FuncionarioDTO implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private String cpf;
    @NotBlank(message = "Campo obrigatório.")
    private String nome;
    @NotBlank(message = "Campo obrigatório.")
    @Email(message = "Informe um e-mail válido.")
    private String email;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @NotBlank(message = "Campo obrigatório.")
    private String password;
    private Status status;

    @NotNull(message = "Campo obrigatório.")
    private Set<PerfilDTO> perfis = new HashSet<>();

    public FuncionarioDTO() {}

    public FuncionarioDTO(String cpf, String nome, String email, String password, Status status) {
        this.cpf = cpf;
        this.nome = nome;
        this.email = email;
        this.password = password;
        this.status = status;
    }

    public FuncionarioDTO(FuncionarioModel funcionarioModel) {
        cpf = funcionarioModel.getCpf();
        nome = funcionarioModel.getNome();
        email = funcionarioModel.getEmail();
        // senha = funcionarioModel.getSenha();
        status = funcionarioModel.getStatus();
        funcionarioModel.getPerfis().forEach(perfil -> this.perfis.add(new PerfilDTO(perfil)));
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
    public Set<PerfilDTO> getPerfis() {
        return perfis;
    }
}
