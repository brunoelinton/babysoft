package com.healthcare.babysoft.services;

import com.healthcare.babysoft.dtos.FuncionarioDTO;
import com.healthcare.babysoft.enums.Status;
import com.healthcare.babysoft.models.FuncionarioModel;
import com.healthcare.babysoft.repositories.FuncionarioRepository;
import com.healthcare.babysoft.services.exceptions.ResourceConflictPersistence;
import com.healthcare.babysoft.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class FuncionarioService {

    @Autowired
    private FuncionarioRepository funcionarioRepository;

    @Transactional(readOnly = true)
    public Page<FuncionarioDTO> buscarTodosFuncionarios(Pageable pageable) {
        Page<FuncionarioModel> lista = funcionarioRepository.findAll(pageable);
        return lista.map(funcionarioModel -> new FuncionarioDTO(funcionarioModel));
    }

    @Transactional(readOnly = true)
    public FuncionarioDTO buscarUmFuncionario(String cpf) {
        Optional<FuncionarioModel> obj = funcionarioRepository.findByCpf(cpf);
        FuncionarioModel funcionarioModel = obj.orElseThrow(() -> new ResourceNotFoundException("Não há funcionário com esse CPF."));
        return new FuncionarioDTO(funcionarioModel);
    }

    @Transactional
    public FuncionarioDTO cadastrarFuncionario(FuncionarioDTO funcionarioDTO) {
        Optional<FuncionarioModel> funcionarioModelOptional = funcionarioRepository.findByCpf(funcionarioDTO.getCpf());
        if(funcionarioModelOptional.isPresent()) {
            throw new ResourceConflictPersistence("Funcionário com o CPF informado já cadastrado no sistema");
        }
        FuncionarioModel funcionarioModel = new FuncionarioModel();
        funcionarioModel.setCpf(funcionarioDTO.getCpf());
        funcionarioModel.setNome(funcionarioDTO.getNome());
        funcionarioModel.setEmail(funcionarioDTO.getEmail());
        funcionarioModel.setSenha(funcionarioDTO.getSenha());
        funcionarioModel.setStatus(Status.ATIVO);
        return new FuncionarioDTO(funcionarioRepository.save(funcionarioModel));
    }
}
