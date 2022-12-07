package com.healthcare.babysoft.models;

import com.healthcare.babysoft.dtos.FichaPacienteDTO;
import com.healthcare.babysoft.dtos.MaeDTO;
import lombok.Data;

import javax.persistence.*;
import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

@Data
@Entity
@Table(name = "TB_MAE")
@Inheritance(strategy = InheritanceType.JOINED)
public class MaeModel implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    private String cpf;
    private String nome;
    private LocalDate dataNascimento;
    private String telefone;
    private String endereco;
    private String bairro;
    private Integer numero;
    private String complemento;
    private String uf;
    private String cep;

    @OneToOne(mappedBy = "mae", cascade = CascadeType.ALL)
    private FichaPacienteModel fichaPacienteModel;

    public MaeModel() {
    }

    public MaeModel(String cpf, String nome, LocalDate dataNascimento, String telefone, String endereco, String bairro, Integer numero, String complemento, String uf, String cep) {
        this.cpf = cpf;
        this.nome = nome;
        this.dataNascimento = dataNascimento;
        this.telefone = telefone;
        this.endereco = endereco;
        this.bairro = bairro;
        this.numero = numero;
        this.complemento = complemento;
        this.uf = uf;
        this.cep = cep;
    }

    public MaeModel(MaeDTO maeDTO) {
        cpf = maeDTO.getCpf();
        nome = maeDTO.getNome();
        dataNascimento = maeDTO.getDataNascimento();
        telefone = maeDTO.getTelefone();
        endereco = maeDTO.getEndereco();
        bairro = maeDTO.getBairro();
        numero = maeDTO.getNumero();
        complemento = maeDTO.getComplemento();
        uf = maeDTO.getUf();
        cep = maeDTO.getCep();

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

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
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

    public FichaPacienteModel preencherFichaPaciente(FichaPacienteDTO fichaPacienteDTO) {
        FichaPacienteModel fichaPacienteModel = new FichaPacienteModel();
        fichaPacienteModel.setTipoSanguineo(fichaPacienteDTO.getTipoSanguineo());
        fichaPacienteModel.setDiabetes(fichaPacienteDTO.getDiabetes());
        fichaPacienteModel.setHipertensao(fichaPacienteDTO.getHipertensao());
        fichaPacienteModel.setSoroPositivo(fichaPacienteDTO.getSoroPositivo());
        fichaPacienteModel.setMedicacaoControlada(fichaPacienteDTO.getMedicacaoControlada());
        fichaPacienteModel.setPeso(fichaPacienteDTO.getPeso());
        fichaPacienteModel.setAltura(fichaPacienteDTO.getAltura());
        fichaPacienteModel.setMae(this);
        this.fichaPacienteModel = fichaPacienteModel;
        return fichaPacienteModel;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MaeModel maeModel = (MaeModel) o;
        return Objects.equals(cpf, maeModel.cpf);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cpf);
    }
}
