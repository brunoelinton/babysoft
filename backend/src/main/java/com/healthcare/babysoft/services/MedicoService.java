package com.healthcare.babysoft.services;

import com.healthcare.babysoft.dtos.MedicoDTO;
import com.healthcare.babysoft.enums.Status;
import com.healthcare.babysoft.models.EspecialidadeModel;
import com.healthcare.babysoft.models.FuncionarioModel;
import com.healthcare.babysoft.models.MedicoModel;
import com.healthcare.babysoft.repositories.EspecialidadeRepository;
import com.healthcare.babysoft.repositories.FuncionarioRepository;
import com.healthcare.babysoft.repositories.MedicoRepository;
import com.healthcare.babysoft.services.exceptions.ResourceConflictPersistence;
import com.healthcare.babysoft.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class MedicoService {

    @Autowired
    private FuncionarioRepository funcionarioRepository;

    @Autowired
    private MedicoRepository medicoRepository;

    @Autowired
    private EspecialidadeRepository especialidadeRepository;

    @Transactional(readOnly = true)
    public Page<MedicoDTO> buscarTodosMedicos(Pageable pageable) {
        Page<MedicoModel> lista = medicoRepository.findAll(pageable);
        return lista.map((medico) -> new MedicoDTO(medico));
    }

    @Transactional(readOnly = true)
    public MedicoDTO buscarUmMedico(String crm) {
        Optional<MedicoModel> obj = medicoRepository.findByCrm(crm);
        MedicoModel medicoModel = obj.orElseThrow(() -> new ResourceNotFoundException("Não há medico com este CRM cadastrado no sistema."));
        return new MedicoDTO(medicoModel);
    }

    @Transactional
    public MedicoDTO cadastrarMedico(MedicoDTO medicoDTO) {
        Optional<FuncionarioModel> objFuncionario = funcionarioRepository.findByCpf(medicoDTO.getCpf());
        if(objFuncionario.isPresent()) throw new ResourceConflictPersistence("Funcionário com o CPF informado já cadastrado no sistema");

        Optional<MedicoModel> objMedico = medicoRepository.findByCrm(medicoDTO.getCrm());
        if(objMedico.isPresent()) throw new ResourceConflictPersistence("Já existe médico cadastrado no sistema com o CRM informado.");

        Optional<EspecialidadeModel> objEspecialidade = especialidadeRepository.findByNome(medicoDTO.getEspecialidade());
        if(objEspecialidade.isEmpty()) throw new ResourceNotFoundException("Especialidade não encontrada no sistema.");

        EspecialidadeModel especialidadeModel = objEspecialidade.get();
        MedicoModel medicoModel = new MedicoModel();
        medicoModel.setCpf(medicoDTO.getCpf());
        medicoModel.setNome(medicoDTO.getNome());
        medicoModel.setEmail(medicoDTO.getEmail());
        medicoModel.setSenha(medicoDTO.getSenha());
        medicoModel.setStatus(Status.ATIVO);
        medicoModel.setCrm(medicoDTO.getCrm());
        medicoModel.setEspecialidade(especialidadeModel);
        return new MedicoDTO(medicoRepository.save(medicoModel));
    }
}
