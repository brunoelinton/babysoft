package com.healthcare.babysoft.controllers;

import com.healthcare.babysoft.dtos.EnfermeiroDTO;
import com.healthcare.babysoft.models.EnfermeiroModel;
import com.healthcare.babysoft.services.EnfermeiroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping(value = "/enfermeiros")
public class EnfermeiroController {

    @Autowired
    private EnfermeiroService enfermeiroService;

    @GetMapping
    public ResponseEntity<Page<EnfermeiroDTO>> buscarTodosEnfermeiros(Pageable pageable) {
        Page<EnfermeiroDTO> lista = enfermeiroService.buscarTodosEnfermeiros(pageable);
        return ResponseEntity.status(HttpStatus.OK).body(lista);
    }

    @GetMapping("/{inscricaoCoren}")
    public ResponseEntity<EnfermeiroDTO> buscarUmEnfermeiro(@PathVariable String inscricaoCoren) {
        EnfermeiroDTO enfermeiroDTO = enfermeiroService.buscarUmEnfermeiro(inscricaoCoren);
        return ResponseEntity.status(HttpStatus.OK).body(enfermeiroDTO);
    }

    @PostMapping
    public ResponseEntity<EnfermeiroDTO> casdastrarEnfermeiro(@Valid @RequestBody EnfermeiroDTO enfermeiroDTO) {
        EnfermeiroDTO novoEnfermeiroDTO = enfermeiroService.cadastrarEnfermeiro(enfermeiroDTO);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{crm}")
                .buildAndExpand(enfermeiroDTO.getInscricaoCoren()).toUri();
        return ResponseEntity.created(uri).body(novoEnfermeiroDTO);
    }
}
