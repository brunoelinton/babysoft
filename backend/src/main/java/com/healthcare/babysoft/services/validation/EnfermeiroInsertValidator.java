package com.healthcare.babysoft.services.validation;

import com.healthcare.babysoft.controllers.exceptions.FieldMessage;
import com.healthcare.babysoft.dtos.EnfermeiroDTO;
import com.healthcare.babysoft.dtos.FuncionarioDTO;
import com.healthcare.babysoft.models.EnfermeiroModel;
import com.healthcare.babysoft.models.FuncionarioModel;
import com.healthcare.babysoft.repositories.EnfermeiroRepository;
import com.healthcare.babysoft.repositories.FuncionarioRepository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class EnfermeiroInsertValidator implements ConstraintValidator<EnfermeiroInsertValid, EnfermeiroDTO> {

    @Autowired
    private EnfermeiroRepository repository;

    @Override
    public void initialize(EnfermeiroInsertValid ann) {
    }

    @Override
    public boolean isValid(EnfermeiroDTO dto, ConstraintValidatorContext context) {

        List<FieldMessage> list = new ArrayList<>();
        Optional<EnfermeiroModel> enfermeiroByCoren = repository.findByInscricaoCoren(dto.getInscricaoCoren());

        if(enfermeiroByCoren.isPresent()) {
            list.add(new FieldMessage("inscricaoCoren", "COREN já cadastrado no sistema."));
        }

        for (FieldMessage e : list) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName())
                    .addConstraintViolation();
        }
        return list.isEmpty();
    }
}