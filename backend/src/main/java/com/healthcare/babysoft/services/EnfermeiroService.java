package com.healthcare.babysoft.services;

import com.healthcare.babysoft.dtos.EnfermeiroDTO;
import com.healthcare.babysoft.enums.Status;
import com.healthcare.babysoft.models.EnfermeiroModel;
import com.healthcare.babysoft.models.FuncionarioModel;
import com.healthcare.babysoft.repositories.EnfermeiroRepository;
import com.healthcare.babysoft.repositories.FuncionarioRepository;
import com.healthcare.babysoft.services.exceptions.ResourceConflictPersistence;
import com.healthcare.babysoft.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class EnfermeiroService {

    @Autowired
    private EnfermeiroRepository enfermeiroRepository;

    @Autowired
    private FuncionarioRepository funcionarioRepository;

    @Transactional(readOnly = true)
    public Page<EnfermeiroDTO> buscarTodosEnfermeiros(Pageable pageable) {
        Page<EnfermeiroModel> lista = enfermeiroRepository.findAll(pageable);
        return lista.map((enfermeiro) -> new EnfermeiroDTO(enfermeiro));
    }

    @Transactional(readOnly = true)
    public EnfermeiroDTO buscarUmEnfermeiro(String inscricaoCoren) {
        Optional<EnfermeiroModel> obj = enfermeiroRepository.findByInscricaoCoren(inscricaoCoren);
        EnfermeiroModel enfermeiroModel = obj.orElseThrow(() -> new ResourceNotFoundException("Não há enfermeiro com esta inscrição COREN cadastrado no sistema."));
        return new EnfermeiroDTO(enfermeiroModel);
    }

    @Transactional
    public EnfermeiroDTO cadastrarEnfermeiro(EnfermeiroDTO enfermeiroDTO) {
        Optional<FuncionarioModel> objFuncionario;

        objFuncionario = funcionarioRepository.findByCpf(enfermeiroDTO.getCpf());
        if(objFuncionario.isPresent()) throw new ResourceConflictPersistence("Funcionário com o CPF informado já cadastrado no sistema");

        objFuncionario = funcionarioRepository.findByEmail(enfermeiroDTO.getEmail());
        if(objFuncionario.isPresent()) throw new ResourceConflictPersistence("E-mail em uso.");

        Optional<EnfermeiroModel> objEnfermeiro = enfermeiroRepository.findByInscricaoCoren(enfermeiroDTO.getInscricaoCoren());
        if(objEnfermeiro.isPresent()) throw new ResourceConflictPersistence("Já existe enfermeiro cadastrado no sistema com a Inscrição COREN informada.");

        EnfermeiroModel enfermeiroModel = new EnfermeiroModel();
        enfermeiroModel.setCpf(enfermeiroDTO.getCpf());
        enfermeiroModel.setNome(enfermeiroDTO.getNome());
        enfermeiroModel.setEmail(enfermeiroDTO.getEmail());
        enfermeiroModel.setSenha(enfermeiroDTO.getSenha());
        enfermeiroModel.setStatus(Status.ATIVO);
        enfermeiroModel.setInscricaoCoren(enfermeiroDTO.getInscricaoCoren());
        return new EnfermeiroDTO(enfermeiroRepository.save(enfermeiroModel));
    }
}
