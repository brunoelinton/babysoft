package com.healthcare.babysoft.controllers;

import com.healthcare.babysoft.dtos.PartoDTO;
import com.healthcare.babysoft.services.PartoService;
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
@RequestMapping(value = "/partos")
public class PartoController {

    @Autowired
    private PartoService partoService;

    @GetMapping
    public ResponseEntity<Page<PartoDTO>> buscarTodosPartos(@PageableDefault(page = 0, size = 12, sort = "partoId", direction = Sort.Direction.ASC) Pageable pageable) {
        Page<PartoDTO> parto = partoService.buscarTodosPartos(pageable);
        return ResponseEntity.status(HttpStatus.OK).body(parto);
    }

    @GetMapping("/{partoId}")
    public ResponseEntity<PartoDTO> buscarUmParto(@PathVariable Long partoId) {
        PartoDTO parto = partoService.buscarUmParto(partoId);
        return ResponseEntity.status(HttpStatus.OK).body(parto);
    }

    @PostMapping
    public ResponseEntity<PartoDTO> cadastrarParto(@Valid @RequestBody PartoDTO partoDTO) {
        PartoDTO novoParto = partoService.cadastrarParto(partoDTO);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{partoId}")
                .buildAndExpand(novoParto.getPartoId()).toUri();
        return ResponseEntity.created(uri).body(novoParto);
    }
}
