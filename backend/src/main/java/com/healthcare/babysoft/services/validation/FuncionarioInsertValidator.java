package com.healthcare.babysoft.services.validation;

import com.healthcare.babysoft.controllers.exceptions.FieldMessage;
import com.healthcare.babysoft.dtos.FuncionarioDTO;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.healthcare.babysoft.models.FuncionarioModel;
import com.healthcare.babysoft.repositories.FuncionarioRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class FuncionarioInsertValidator implements ConstraintValidator<FuncionarioInsertValid, FuncionarioDTO> {

    @Autowired
    private FuncionarioRepository repository;

    @Override
    public void initialize(FuncionarioInsertValid ann) {
    }

    @Override
    public boolean isValid(FuncionarioDTO dto, ConstraintValidatorContext context) {

        List<FieldMessage> list = new ArrayList<>();
        Optional<FuncionarioModel> funcionarioByEmail = repository.findByEmail(dto.getEmail());

        if(funcionarioByEmail.isPresent()) {
            list.add(new FieldMessage("email", "E-mail já existente"));
        }

        if(dto.getCpf().isEmpty()) {
            list.add(new FieldMessage("cpf", "Campo obrigatório."));
            System.out.println("Erro de cpf");
        } else if(dto.getCpf().matches("[0-9]{3}\\.?[0-9]{3}\\.?[0-9]{3}\\-?[0-9]{2}")) {
            Optional<FuncionarioModel> funcionarioByCpf = repository.findByCpf(dto.getCpf());

            if(funcionarioByCpf.isPresent()) {
                list.add(new FieldMessage("cpf", "CPF já cadastrado no sistema."));
            }
        } else {
            list.add((new FieldMessage("cpf", "CPF inválido.")));
        }


        for (FieldMessage e : list) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName())
                    .addConstraintViolation();
        }
        return list.isEmpty();
    }
}