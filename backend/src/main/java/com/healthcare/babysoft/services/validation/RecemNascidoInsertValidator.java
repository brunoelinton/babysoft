package com.healthcare.babysoft.services.validation;

import com.healthcare.babysoft.controllers.exceptions.FieldMessage;
import com.healthcare.babysoft.dtos.EnfermeiroDTO;
import com.healthcare.babysoft.dtos.RecemNascidoDTO;
import com.healthcare.babysoft.models.EnfermeiroModel;
import com.healthcare.babysoft.models.FuncionarioModel;
import com.healthcare.babysoft.models.MaeModel;
import com.healthcare.babysoft.models.RecemNascidoModel;
import com.healthcare.babysoft.repositories.EnfermeiroRepository;
import com.healthcare.babysoft.repositories.MaeRepository;
import com.healthcare.babysoft.repositories.RecemNascidoRepository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class RecemNascidoInsertValidator implements ConstraintValidator<RecemNascidoInsertValid, RecemNascidoDTO> {

    @Autowired
    private RecemNascidoRepository repository;

    @Autowired
    private MaeRepository maeRepository;

    @Autowired
    private RecemNascidoRepository recemNascidoRepository;

    @Override
    public void initialize(RecemNascidoInsertValid ann) {
    }

    @Override
    public boolean isValid(RecemNascidoDTO dto, ConstraintValidatorContext context) {

        List<FieldMessage> list = new ArrayList<>();

        if(dto.getCpfMae().isEmpty()) {
            list.add(new FieldMessage("cpfMae", "CPF da mãe é obrigatório."));
        } else if(dto.getCpfMae().matches("[0-9]{3}\\.?[0-9]{3}\\.?[0-9]{3}\\-?[0-9]{2}")) {
            Optional<MaeModel> maeByCpf = maeRepository.findByCpf(dto.getCpfMae());

            if(maeByCpf.isPresent()) {
                RecemNascidoModel recemNascido = new RecemNascidoModel();
                recemNascido.setMaeModel(maeByCpf.get());
                recemNascido.setDataNascimento(dto.getDataNascimento());

                Optional<RecemNascidoModel> recemNascidoModelOptional = recemNascidoRepository.findByRecemNascidoId(recemNascido.getRecemNascidoId());

                if(recemNascidoModelOptional.isPresent()) {
                    list.add(new FieldMessage("recemNascidoId", "RecemNascidoId já cadastrado no sistema."));
                }
            }
        } else {
            list.add((new FieldMessage("cpfMae", "CPF inválido.")));
        }




        for (FieldMessage e : list) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName())
                    .addConstraintViolation();
        }


        return list.isEmpty();
    }
}