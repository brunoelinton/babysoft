package com.healthcare.babysoft.services;

import com.healthcare.babysoft.dtos.MaeDTO;
import com.healthcare.babysoft.models.MaeModel;
import com.healthcare.babysoft.repositories.MaeRepository;
import com.healthcare.babysoft.services.exceptions.ResourceConflictPersistence;
import com.healthcare.babysoft.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import java.util.Optional;

@Service
public class MaeService {

    @Autowired
    private MaeRepository maeRepository;

    @Transactional(readOnly = true)
    public Page<MaeDTO> buscarTodasMaes(Pageable pageable) {
        Page<MaeModel> list = maeRepository.findAll(pageable);
        return list.map(mae -> new MaeDTO(mae));
    }

    @Transactional(readOnly = true)
    public MaeDTO buscarUmaMae(String cpf) {
        Optional<MaeModel> obj = maeRepository.findByCpf(cpf);
        MaeModel maeModel = obj.orElseThrow(() -> new ResourceNotFoundException("Não há mãe com esse CPF."));
        return new MaeDTO(maeModel);
    }

    @Transactional
    public MaeDTO cadastrarMae(@Valid @RequestBody MaeDTO maeDTO) {
//        Optional<MaeModel> maeModelOptional = maeRepository.findByCpf(maeDTO.getCpf());
//        if(maeModelOptional.isPresent()) {
//            throw new ResourceConflictPersistence("Registro com CPF informado já presente no sistema.");
//        }

        MaeModel maeModel = new MaeModel();
        maeModel.setCpf(maeDTO.getCpf());
        maeModel.setNome(maeDTO.getNome());
        maeModel.setDataNascimento(maeDTO.getDataNascimento());
        maeModel.setTelefone(maeDTO.getTelefone());
        maeModel.setEndereco(maeDTO.getEndereco());
        maeModel.setNumero(maeDTO.getNumero());
        maeModel.setComplemento(maeDTO.getComplemento());
        maeModel.setBairro(maeDTO.getBairro());
        maeModel.setUf(maeDTO.getUf());
        maeModel.setCep(maeDTO.getCep());
        return new MaeDTO(maeRepository.save(maeModel));
    }
}
