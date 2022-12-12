package com.healthcare.babysoft.controllers;

import com.healthcare.babysoft.dtos.MedicoDTO;
import com.healthcare.babysoft.services.MedicoService;
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
@RequestMapping(value = "/medicos")
public class MedicoController {

    @Autowired
    private MedicoService medicoService;

    @GetMapping
    public ResponseEntity<Page<MedicoDTO>> buscarTodosMedicos(Pageable pageable) {
        Page<MedicoDTO> lista = medicoService.buscarTodosMedicos(pageable);
        return ResponseEntity.status(HttpStatus.OK).body(lista);
    }

    @GetMapping("/{crm}")
    public ResponseEntity<MedicoDTO> buscarUmMedico(@PathVariable String crm) {
        MedicoDTO medicoDTO = medicoService.buscarUmMedico(crm);
        return ResponseEntity.status(HttpStatus.OK).body(medicoDTO);
    }

    @PostMapping
    public ResponseEntity<MedicoDTO> cadastrarMedico(@Valid @RequestBody MedicoDTO medicoDTO) {
        MedicoDTO novoMedicoDTO = medicoService.cadastrarMedico(medicoDTO);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{crm}")
                .buildAndExpand(novoMedicoDTO.getCrm()).toUri();
        return ResponseEntity.created(uri).body(novoMedicoDTO);
    }
}
