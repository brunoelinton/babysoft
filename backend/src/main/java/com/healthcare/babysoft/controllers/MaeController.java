package com.healthcare.babysoft.controllers;

import com.healthcare.babysoft.dtos.MaeDTO;
import com.healthcare.babysoft.services.MaeService;
import com.healthcare.babysoft.services.exceptions.ResourceConflictPersistence;
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
@RequestMapping(value = "/maes")
public class MaeController {

    @Autowired
    private MaeService maeService;

    @GetMapping
    public ResponseEntity<Page<MaeDTO>> buscarTodasMaes(@PageableDefault(page = 0, size = 12, sort = "cpf", direction = Sort.Direction.ASC) Pageable pageable) {
        Page<MaeDTO> lista = maeService.buscarTodasMaes(pageable);
        return ResponseEntity.status(HttpStatus.OK).body(lista);
    }

    @GetMapping("/{cpf}")
    ResponseEntity<MaeDTO> buscarUmaMae(@PathVariable String cpf) {
        MaeDTO maeDTO = maeService.buscarUmaMae(cpf);
        return ResponseEntity.status(HttpStatus.OK).body(maeDTO);
    }

    @PostMapping
    public ResponseEntity<MaeDTO> cadastrarMae(@Valid @RequestBody MaeDTO maeDTO) {
        MaeDTO novaMaeDTO = maeService.cadastrarMae(maeDTO);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(maeDTO.getCpf()).toUri();
        return ResponseEntity.created(uri).body(novaMaeDTO);
    }
}
