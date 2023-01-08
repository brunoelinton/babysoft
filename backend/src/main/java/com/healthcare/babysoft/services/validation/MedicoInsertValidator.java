package com.healthcare.babysoft.services.validation;

import com.healthcare.babysoft.controllers.exceptions.FieldMessage;
import com.healthcare.babysoft.dtos.FuncionarioDTO;
import com.healthcare.babysoft.dtos.MedicoDTO;
import com.healthcare.babysoft.models.FuncionarioModel;
import com.healthcare.babysoft.models.MedicoModel;
import com.healthcare.babysoft.repositories.FuncionarioRepository;
import com.healthcare.babysoft.repositories.MedicoRepository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class MedicoInsertValidator implements ConstraintValidator<MedicoInsertValid, MedicoDTO> {

    @Autowired
    private MedicoRepository repository;

    @Override
    public void initialize(MedicoInsertValid ann) {
    }

    @Override
    public boolean isValid(MedicoDTO dto, ConstraintValidatorContext context) {

        List<FieldMessage> list = new ArrayList<>();
        Optional<MedicoModel> medicoByCrm = repository.findByCrm(dto.getCrm());

        if(medicoByCrm.isPresent()) {
            list.add(new FieldMessage("crm", "CRM já cadastrado no sistema."));
        }

        for (FieldMessage e : list) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName())
                    .addConstraintViolation();
        }
        return list.isEmpty();
    }
}