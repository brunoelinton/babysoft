package com.healthcare.babysoft.controllers;

import com.healthcare.babysoft.dtos.EquipePartoDTO;
import com.healthcare.babysoft.services.EquipePartoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/equipes_parto")
public class EquipePartoController {

    @Autowired
    private EquipePartoService equipePartoService;

    @GetMapping
    public ResponseEntity<Page<EquipePartoDTO>> buscarTodasEquipesParto(@PageableDefault(page = 0, size = 12, sort = "equipePartoId", direction = Sort.Direction.ASC) Pageable pageable) {
        Page<EquipePartoDTO> equipesParto = equipePartoService.buscarTodasEquipesParto(pageable);
        return ResponseEntity.status(HttpStatus.OK).body(equipesParto);
    }
}
