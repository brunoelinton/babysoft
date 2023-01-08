package com.healthcare.babysoft.services.validation;

import com.healthcare.babysoft.controllers.exceptions.FieldMessage;
import com.healthcare.babysoft.dtos.PartoDTO;
import com.healthcare.babysoft.repositories.PartoRepository;

import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import java.util.ArrayList;
import java.util.List;

public class PartoInsertValidator implements ConstraintValidator<PartoInsertValid, PartoDTO> {

    @Autowired
    private PartoRepository repository;

    @Override
    public void initialize(PartoInsertValid ann) {
    }

    @Override
    public boolean isValid(PartoDTO dto, ConstraintValidatorContext context) {

        List<FieldMessage> list = new ArrayList<>();

        /*------------------ VALIDAÇÃO DADOS DA MÃE ------------------*/
        if(dto.getMae().getCpf().isEmpty()) {
            list.add(new FieldMessage("mae.cpf", "CPF da mãe é obrigatório."));
        } else if(!dto.getMae().getCpf().matches("[0-9]{3}\\.?[0-9]{3}\\.?[0-9]{3}\\-?[0-9]{2}")) {
            list.add(new FieldMessage("mae.cpf", "CPF da mãe é inválido."));
        }

        if(dto.getMae().getNome().isEmpty()) {
            list.add(new FieldMessage("mae.nome", "Nome da mãe é obrigatório."));
        }

        if (dto.getMae().getDataNascimento() == null) {
            list.add(new FieldMessage("mae.dataNascimento", "Data de nascimento da mãe é obrigatório."));
        }
        // VALIDAR DATA NASCIMENTO MAE

        if(dto.getMae().getTelefone().isEmpty()) {
            list.add(new FieldMessage("mae.telefone", "Telefone da mãe é obrigatório."));
        }

        if(dto.getMae().getEndereco().isEmpty()) {
            list.add(new FieldMessage("mae.endereco", "Endereço da mãe é obrigatório."));
        }

        if(dto.getMae().getNumero() == null) {
            list.add(new FieldMessage("mae.numero", "Número da residência da mãe é obrigatório."));
        } else if(dto.getMae().getNumero() < 0) {
            list.add(new FieldMessage("mae.numero", "Número da residência da mãe é inválido."));
        }

        if(dto.getMae().getBairro().isEmpty()) {
            list.add(new FieldMessage("mae.bairro", "Bairro da residência da mãe é obrigatório."));
        }

        if(dto.getMae().getUf().isEmpty()) {
            list.add(new FieldMessage("mae.uf", "UF da residência da mãe é obrigatório."));
        }

        if(dto.getMae().getCep().isEmpty()) {
            list.add(new FieldMessage("mae.cep", "cep da residência da mãe é obrigatório."));
        }

        /*------------------ VALIDAÇÃO DADOS DO RECÉM-NASCIDO ------------------*/
        if(dto.getRecemNascido().getCpfMae().isEmpty()) {
            list.add(new FieldMessage("recemNascido.cpfMae", "CPF da mãe do recém-nascido é obrigatório."));
        } else if(!dto.getRecemNascido().getCpfMae().matches("[0-9]{3}\\.?[0-9]{3}\\.?[0-9]{3}\\-?[0-9]{2}")) {
            list.add(new FieldMessage("recemNascido.cpfMae", "CPF da mãe do recém-nascido é inválido."));
        }

        if(dto.getRecemNascido().getNomeMae().isEmpty()) {
            list.add(new FieldMessage("recemNascido.nomeMae", "Nome da mãe do recém-nascido é obrigatório."));
        }

        if(dto.getRecemNascido().getNome().isEmpty()) {
            list.add(new FieldMessage("recemNascido.nome", "Nome do recém-nascido é obrigatório."));
        }

        if(dto.getRecemNascido().getCondicao() == null) {
            list.add(new FieldMessage("recemNascido.condicao", "Condição do recém-nascido é obrigatório."));
        }

        if(dto.getRecemNascido().getAltura() == null) {
            list.add(new FieldMessage("recemNascido.altura", "Altura do recém-nascido é obrigatório."));
        } else if(dto.getRecemNascido().getAltura() < 0 || dto.getRecemNascido().getAltura() > 100) {
            list.add(new FieldMessage("recemNascido.altura", "Altura do recém-nascido deve ser entre 0 e 100."));
        }

        if(dto.getRecemNascido().getPeso() == null) {
            list.add(new FieldMessage("recemNascido.peso", "Peso do recém-nascido é obrigatório."));
        } else if(dto.getRecemNascido().getPeso() < 0 || dto.getRecemNascido().getPeso() > 10) {
            list.add(new FieldMessage("recemNascido.altura", "Peso do recém-nascido deve ser entre 0 e 10kg."));
        }

        if(dto.getRecemNascido().getSexo() == null) {
            list.add(new FieldMessage("recemNascido.sexo", "Sexo do recém-nascido é obrigatório."));
        }

        if(dto.getRecemNascido().getDataNascimento() == null) {
            list.add(new FieldMessage("recemNascido.dataNascimento", "Data de nascimento do recém-nascido é obrigatório."));
        }

        /*------------------ VALIDAÇÃO DADOS DO PARTO ------------------*/
        if(dto.getMultifetal() == null) {
            list.add(new FieldMessage("multifetal", "É obrigatório informar se o parto é multifetal ou não."));
        }

        if(dto.getTipoParto() == null) {
            list.add(new FieldMessage("tipoParto", "É obrigatório informar o tipo de parto."));
        }

        if(dto.getPartoRisco() == null) {
            list.add(new FieldMessage("partoRisco", "É obrigatório informar se o parto é de risco ou não."));
        }

        if(dto.getDataParto() == null) {
            list.add(new FieldMessage("dataParto", "Data do parto o é obrigatório."));
        }

        for (FieldMessage e : list) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName())
                    .addConstraintViolation();
        }
        return list.isEmpty();
    }
}