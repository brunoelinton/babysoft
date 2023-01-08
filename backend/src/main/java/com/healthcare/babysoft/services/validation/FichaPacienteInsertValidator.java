package com.healthcare.babysoft.services.validation;

import com.healthcare.babysoft.controllers.exceptions.FieldMessage;
import com.healthcare.babysoft.dtos.EnfermeiroDTO;
import com.healthcare.babysoft.dtos.FichaPacienteDTO;
import com.healthcare.babysoft.models.EnfermeiroModel;
import com.healthcare.babysoft.models.FichaPacienteModel;
import com.healthcare.babysoft.models.MaeModel;
import com.healthcare.babysoft.repositories.EnfermeiroRepository;
import com.healthcare.babysoft.repositories.FichaPacienteRepository;
import com.healthcare.babysoft.repositories.MaeRepository;
import com.healthcare.babysoft.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class FichaPacienteInsertValidator implements ConstraintValidator<FichaPacienteInsertValid, FichaPacienteDTO> {

    @Autowired
    private FichaPacienteRepository repository;

    @Autowired
    private MaeRepository maeRepository;

    @Override
    public void initialize(FichaPacienteInsertValid ann) {
    }

    @Override
    public boolean isValid(FichaPacienteDTO dto, ConstraintValidatorContext context) {

        List<FieldMessage> list = new ArrayList<>();

        if(dto.getCpfPaciente().isEmpty()) {
            list.add(new FieldMessage("cpfPaciente", "Campo obrigatório."));
        } else if(dto.getCpfPaciente().matches("[0-9]{3}\\.?[0-9]{3}\\.?[0-9]{3}\\-?[0-9]{2}")) {
            Optional<FichaPacienteModel> fichaPacienteByCpf = repository.findByCpf(dto.getCpfPaciente());

            if(fichaPacienteByCpf.isPresent()) {
                list.add(new FieldMessage("cpfPaciente", "CPF já cadastrado no sistema."));
            }

            Optional<MaeModel> maeByCpf = maeRepository.findByCpf(dto.getCpfPaciente());

            if(maeByCpf.isEmpty()) {
                list.add(new FieldMessage("cpfPaciente", "Paciente com o CPF informado não cadastrado no sistema."));
            }
        } else {
            list.add((new FieldMessage("cpfPaciente", "CPF inválido.")));
        }

        for (FieldMessage e : list) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName())
                    .addConstraintViolation();
        }

        return list.isEmpty();
    }
}