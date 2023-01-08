package com.healthcare.babysoft.services;

import com.healthcare.babysoft.dtos.EquipeEnfermagemDTO;
import com.healthcare.babysoft.dtos.EquipeMedicaDTO;
import com.healthcare.babysoft.dtos.EquipePartoDTO;
import com.healthcare.babysoft.models.*;
import com.healthcare.babysoft.repositories.EnfermeiroRepository;
import com.healthcare.babysoft.repositories.EquipePartoRepository;
import com.healthcare.babysoft.repositories.EspecialidadeRepository;
import com.healthcare.babysoft.repositories.MedicoRepository;
import com.healthcare.babysoft.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class EquipePartoService {

    @Autowired
    private EquipePartoRepository equipePartoRepository;

    @Autowired
    private MedicoRepository medicoRepository;

    @Autowired
    private EnfermeiroRepository enfermeiroRepository;

    @Transactional(readOnly = true)
    public Page<EquipePartoDTO> buscarTodasEquipesParto(Pageable pageable) {
        Page<EquipePartoModel> equipesParto = equipePartoRepository.findAll(pageable);
        return equipesParto.map(equipeParto -> new EquipePartoDTO(equipeParto));
    }

    @Transactional(readOnly = true)
    public EquipePartoDTO buscarUmaEquipeParto(Long equipePartoId) {
        Optional<EquipePartoModel>  objEquipeParto = equipePartoRepository.findById(equipePartoId);
        EquipePartoModel equipeParto = objEquipeParto.orElseThrow(() -> new ResourceNotFoundException("Equipe de parto não encontrada no sistema."));
        return new EquipePartoDTO(equipeParto);
    }

    @Transactional
    public EquipePartoDTO cadastrarEquipeParto(EquipePartoDTO equipePartoDTO) {
        EquipePartoModel novaEquipeParto = new EquipePartoModel();
        novaEquipeParto.setDoula(equipePartoDTO.getDoula());
        novaEquipeParto = equipePartoRepository.save(novaEquipeParto);

        Set<EquipeMedicaModel> equipeMedica = new HashSet<>();
        for(EquipeMedicaDTO em: equipePartoDTO.getEquipeMedica()) {
            Optional<MedicoModel> objMedico = medicoRepository.findByCrm(em.getMedico().getCrm());
            MedicoModel medico = objMedico.orElseThrow(() -> new ResourceNotFoundException("Médico com o CRM informado não cadastrado no sistema."));
            EquipeMedicaModel novaEquipeMedica = new EquipeMedicaModel(medico, novaEquipeParto);
            equipeMedica.add(novaEquipeMedica);
            novaEquipeParto.getEquipeMedica().add(novaEquipeMedica);
        }


        Set<EquipeEnfermagemModel> equipeEnfermagem = new HashSet<>();
        for(EquipeEnfermagemDTO ef: equipePartoDTO.getEquipeEnfermagem()) {
            Optional<EnfermeiroModel> objEnfermeiro = enfermeiroRepository.findByInscricaoCoren(ef.getEnfermeiro().getInscricaoCoren());
            EnfermeiroModel enfermeiro = objEnfermeiro.orElseThrow(() -> new ResourceNotFoundException("Enfermeiro com o CRM informado não cadastrado no sistema."));
            EquipeEnfermagemModel novaEquipeEnfermagem = new EquipeEnfermagemModel(enfermeiro, novaEquipeParto);
            equipeEnfermagem.add(novaEquipeEnfermagem);
            novaEquipeParto.getEquipeEnfermagem().add(novaEquipeEnfermagem);
        }
        return new EquipePartoDTO(novaEquipeParto);
    }
}
