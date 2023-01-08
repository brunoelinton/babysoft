package com.healthcare.babysoft.services.validation;

import com.healthcare.babysoft.controllers.exceptions.FieldMessage;
import com.healthcare.babysoft.dtos.EnfermeiroDTO;
import com.healthcare.babysoft.dtos.MaeDTO;
import com.healthcare.babysoft.models.EnfermeiroModel;
import com.healthcare.babysoft.models.MaeModel;
import com.healthcare.babysoft.repositories.EnfermeiroRepository;
import com.healthcare.babysoft.repositories.MaeRepository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class MaeInsertValidator implements ConstraintValidator<MaeInsertValid, MaeDTO> {

    @Autowired
    private MaeRepository repository;

    @Override
    public void initialize(MaeInsertValid ann) {
    }

    @Override
    public boolean isValid(MaeDTO dto, ConstraintValidatorContext context) {

        List<FieldMessage> list = new ArrayList<>();

        if(dto.getCpf().isEmpty()) {
            list.add(new FieldMessage("cpf", "Campo obrigatório."));
        } else if(dto.getCpf().matches("[0-9]{3}\\.?[0-9]{3}\\.?[0-9]{3}\\-?[0-9]{2}")){
            Optional<MaeModel> maeByCpf = repository.findByCpf(dto.getCpf());

            if(maeByCpf.isPresent()) {
                list.add(new FieldMessage("cpf", "CPF informado já cadastrado no sistema."));
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