package com.healthcare.babysoft.services;

import com.healthcare.babysoft.dtos.EquipePartoDTO;
import com.healthcare.babysoft.models.EquipePartoModel;
import com.healthcare.babysoft.repositories.EquipePartoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class EquipePartoService {

    @Autowired
    private EquipePartoRepository equipePartoRepository;

    @Transactional(readOnly = true)
    public Page<EquipePartoDTO> buscarTodasEquipesParto(Pageable pageable) {
        Page<EquipePartoModel> equipesParto = equipePartoRepository.findAll(pageable);
        return equipesParto.map(equipeParto -> new EquipePartoDTO(equipeParto));
    }
}
