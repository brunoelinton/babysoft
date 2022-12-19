package com.healthcare.babysoft.services;

import com.healthcare.babysoft.dtos.PartoDTO;
import com.healthcare.babysoft.models.MaeModel;
import com.healthcare.babysoft.models.PartoModel;
import com.healthcare.babysoft.models.RecemNascidoModel;
import com.healthcare.babysoft.models.pk.RecemNascidoPK;
import com.healthcare.babysoft.repositories.MaeRepository;
import com.healthcare.babysoft.repositories.PartoRepository;
import com.healthcare.babysoft.repositories.RecemNascidoRepository;
import com.healthcare.babysoft.services.exceptions.ResourceConflictPersistence;
import com.healthcare.babysoft.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class PartoService {

    @Autowired
    private PartoRepository partoRepository;

    @Autowired
    private RecemNascidoRepository recemNascidoRepository;

    @Autowired
    private MaeRepository maeRepository;

    @Transactional(readOnly = true)
    public Page<PartoDTO> buscarTodosPartos(Pageable pageable) {
        Page<PartoModel> partos = partoRepository.findAll(pageable);
        return partos.map(parto -> new PartoDTO(parto));
    }

    @Transactional(readOnly = true)
    public PartoDTO buscarUmParto(Long partoId) {
        Optional<PartoModel> obj = partoRepository.findById(partoId);
        PartoModel parto = obj.orElseThrow(() -> new ResourceNotFoundException("Parto com ID informado não presente no sistema."));
        return new PartoDTO(parto);
    }

    @Transactional
    public PartoDTO cadastrarParto(PartoDTO partoDTO) {
        Optional<MaeModel> objMae = maeRepository.findByCpf(partoDTO.getMae().getCpf());
        MaeModel mae = objMae.orElseThrow(() -> new ResourceNotFoundException("Mãe não encontrada no sistema."));

        Optional<RecemNascidoModel> objRecemNascido = recemNascidoRepository.findByRecemNascidoId(new RecemNascidoPK(mae, partoDTO.getDataParto()));
        RecemNascidoModel recemNascido = objRecemNascido.orElseThrow(() -> new ResourceNotFoundException("Recém-nascido não encontrado no sistema."));

        Optional<PartoModel> objParto = partoRepository.findByRecemNascido(recemNascido);
        if(objParto.isPresent()) {
            throw new ResourceConflictPersistence("Parto já cadastrado no sistema.");
        }
        PartoModel novoParto = new PartoModel();
        novoParto.setPartoRisco(partoDTO.getPartoRisco());
        novoParto.setTipoParto(partoDTO.getTipoParto());
        novoParto.setMultifetal(partoDTO.getMultifetal());
        novoParto.setObservacao(partoDTO.getObservacao());
        novoParto.setRecemNascido(recemNascido);
        System.out.println("Vai salvar");
        return new PartoDTO(partoRepository.save(novoParto));
    }
}
