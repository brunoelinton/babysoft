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
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/equipesparto")
public class EquipePartoController {

    @Autowired
    private EquipePartoService equipePartoService;

    @GetMapping
    public ResponseEntity<Page<EquipePartoDTO>> buscarTodasEquipesParto(@PageableDefault(page = 0, size = 12, sort = "equipePartoId", direction = Sort.Direction.ASC) Pageable pageable) {
        Page<EquipePartoDTO> equipesParto = equipePartoService.buscarTodasEquipesParto(pageable);
        return ResponseEntity.status(HttpStatus.OK).body(equipesParto);
    }

    @GetMapping("/{equipePartoId}")
    public ResponseEntity<EquipePartoDTO> buscarUmaEquipeParto(@PathVariable Long equipePartoId) {
        EquipePartoDTO equipeParto = equipePartoService.buscarUmaEquipeParto(equipePartoId);
        return ResponseEntity.status(HttpStatus.OK).body(equipeParto);
    }

    @PostMapping
    public ResponseEntity<EquipePartoDTO> cadastrarEquipeParto(@Valid @RequestBody EquipePartoDTO equipePartoDTO) {
        EquipePartoDTO novaEquipeParto = equipePartoService.cadastrarEquipeParto(equipePartoDTO);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{equipePartoId}")
                .buildAndExpand(novaEquipeParto.getEquipePartoId()).toUri();
        return ResponseEntity.created(uri).body(novaEquipeParto);
    }
}
