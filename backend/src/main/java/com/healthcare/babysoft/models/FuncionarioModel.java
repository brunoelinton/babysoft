package com.healthcare.babysoft.models;

import com.healthcare.babysoft.enums.Status;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
@Table(name = "TB_FUNCIONARIO")
@Inheritance(strategy = InheritanceType.JOINED)
public class FuncionarioModel implements UserDetails, Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    private String cpf;
    private String nome;
    @Column(unique = true)
    private String email;
    private String password;
    @Enumerated(EnumType.STRING)
    private Status status;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "TB_FUNCIONARIO_PERFIL",
    joinColumns = @JoinColumn(name = "cpf_funcionario"),
    inverseJoinColumns = @JoinColumn(name = "perfil_id"))
    private Set<Perfil> perfis = new HashSet<>();

    public FuncionarioModel() {}

    public FuncionarioModel(String cpf, String nome, String email, String password, Status status) {
        this.cpf = cpf;
        this.nome = nome;
        this.email = email;
        this.password = password;
        this.status = status;
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

    public Set<Perfil> getPerfis() {
        return perfis;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FuncionarioModel that = (FuncionarioModel) o;
        return cpf.equals(that.cpf);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cpf);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return perfis.stream().map(perfil -> new SimpleGrantedAuthority(perfil.getAutoridade()))
                .collect(Collectors.toList());
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
