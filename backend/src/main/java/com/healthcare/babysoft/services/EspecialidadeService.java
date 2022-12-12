package com.healthcare.babysoft.services;

import com.healthcare.babysoft.dtos.EspecialidadeDTO;
import com.healthcare.babysoft.models.EspecialidadeModel;
import com.healthcare.babysoft.repositories.EspecialidadeRepository;
import com.healthcare.babysoft.services.exceptions.ResourceConflictPersistence;
import com.healthcare.babysoft.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EspecialidadeService {

    @Autowired
    private EspecialidadeRepository especialidadeRepository;

    @Transactional(readOnly = true)
    public List<EspecialidadeDTO> buscarTodasEspecialidades() {
        List<EspecialidadeModel> lista = especialidadeRepository.findAll();
        return lista.stream().map(EspecialidadeDTO::new).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public EspecialidadeDTO buscarUmaEspecialiade(Integer especialidadeId) {
        Optional<EspecialidadeModel> obj = especialidadeRepository.findById(especialidadeId);
        EspecialidadeModel especialidadeModel = obj.orElseThrow(() -> new ResourceNotFoundException("Especialidade não cadastrada no sistema."));
        return new EspecialidadeDTO(especialidadeModel);
    }

    @Transactional
    public EspecialidadeDTO cadastrarEspecialidade(EspecialidadeDTO especialidadeDTO) {
        Optional<EspecialidadeModel> obj = especialidadeRepository.findByNome(especialidadeDTO.getNome());

        if(obj.isPresent()) throw new ResourceConflictPersistence("Especialidade já cadastrada no sistema.");

        EspecialidadeModel especialidadeModel = new EspecialidadeModel();
        especialidadeModel.setNome(especialidadeDTO.getNome());
        return new EspecialidadeDTO(especialidadeRepository.save(especialidadeModel));
    }
}
