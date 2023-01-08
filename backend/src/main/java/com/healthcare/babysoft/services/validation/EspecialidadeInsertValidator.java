package com.healthcare.babysoft.services.validation;

import com.healthcare.babysoft.controllers.exceptions.FieldMessage;
import com.healthcare.babysoft.dtos.EnfermeiroDTO;
import com.healthcare.babysoft.dtos.EspecialidadeDTO;
import com.healthcare.babysoft.models.EnfermeiroModel;
import com.healthcare.babysoft.models.EspecialidadeModel;
import com.healthcare.babysoft.repositories.EnfermeiroRepository;
import com.healthcare.babysoft.repositories.EspecialidadeRepository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class EspecialidadeInsertValidator implements ConstraintValidator<EspecialidadeInsertValid, EspecialidadeDTO> {

    @Autowired
    private EspecialidadeRepository repository;

    @Override
    public void initialize(EspecialidadeInsertValid ann) {
    }

    @Override
    public boolean isValid(EspecialidadeDTO dto, ConstraintValidatorContext context) {

        List<FieldMessage> list = new ArrayList<>();
        Optional<EspecialidadeModel> especialidadeByNome = repository.findByNome(dto.getEspecialidadeNome());

        if(especialidadeByNome.isPresent()) {
            list.add(new FieldMessage("especialidadeNome", "Especialidade já cadastrada no sistema."));
        }

        for (FieldMessage e : list) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName())
                    .addConstraintViolation();
        }
        return list.isEmpty();
    }
}