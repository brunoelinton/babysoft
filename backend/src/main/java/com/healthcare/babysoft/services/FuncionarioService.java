package com.healthcare.babysoft.services;

import com.healthcare.babysoft.dtos.FuncionarioDTO;
import com.healthcare.babysoft.dtos.PerfilDTO;
import com.healthcare.babysoft.enums.Status;
import com.healthcare.babysoft.models.FuncionarioModel;
import com.healthcare.babysoft.models.Perfil;
import com.healthcare.babysoft.repositories.FuncionarioRepository;
import com.healthcare.babysoft.repositories.PerfilRepository;
import com.healthcare.babysoft.services.exceptions.ResourceConflictPersistence;
import com.healthcare.babysoft.services.exceptions.ResourceNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class FuncionarioService implements UserDetailsService {

    private static Logger logger = LoggerFactory.getLogger(FuncionarioService.class);

    @Autowired
    private FuncionarioRepository funcionarioRepository;

    @Autowired
    private PerfilRepository perfilRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

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
//        Optional<FuncionarioModel> funcionarioModelOptional = funcionarioRepository.findByCpf(funcionarioDTO.getCpf());
//        if(funcionarioModelOptional.isPresent()) {
//            throw new ResourceConflictPersistence("Funcionário com o CPF informado já cadastrado no sistema");
//        }
//        funcionarioModelOptional = funcionarioRepository.findByEmail(funcionarioDTO.getEmail());
//        if(funcionarioModelOptional.isPresent()) {
//            throw new ResourceConflictPersistence("E-mail em uso.");
//        }
        FuncionarioModel funcionarioModel = new FuncionarioModel();
        funcionarioModel.setCpf(funcionarioDTO.getCpf());
        funcionarioModel.setNome(funcionarioDTO.getNome());
        funcionarioModel.setEmail(funcionarioDTO.getEmail());
        funcionarioModel.setPassword(passwordEncoder.encode(funcionarioDTO.getPassword()));
        funcionarioModel.setStatus(Status.ATIVO);

        funcionarioModel.getPerfis().clear();
        for(PerfilDTO perfilDTO: funcionarioDTO.getPerfis()) {
            Perfil perfil = perfilRepository.getReferenceById(perfilDTO.getId());
            funcionarioModel.getPerfis().add(perfil);
        }
        return new FuncionarioDTO(funcionarioRepository.save(funcionarioModel));
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<FuncionarioModel> objFuncionario = funcionarioRepository.findByEmail(username);
        if(objFuncionario.isEmpty()) {
            logger.error("Funcionário não encontrado: " + username);
            throw new UsernameNotFoundException("E-mail não encontrado.");
        }
        logger.info("Funcionário encontrado: " + username);
        return objFuncionario.get();
    }
}
