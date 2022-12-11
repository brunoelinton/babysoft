package com.healthcare.babysoft.controllers;

import com.healthcare.babysoft.dtos.EspecialidadeDTO;
import com.healthcare.babysoft.services.EspecialidadeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/especialidades")
public class EspecialidadeController {

    @Autowired
    private EspecialidadeService especialidadeService;

    @GetMapping
    public ResponseEntity<List<EspecialidadeDTO>> buscarTodasEspecialidades() {
        List<EspecialidadeDTO> lista = especialidadeService.buscarTodasEspecialidades();
        return ResponseEntity.status(HttpStatus.OK).body(lista);
    }

    @GetMapping("/{especialidadeId}")
    public ResponseEntity<EspecialidadeDTO> buscarUmaEspecialidade(@PathVariable Integer especialidadeId) {
        EspecialidadeDTO especialidadeDTO = especialidadeService.buscarUmaEspecialiade(especialidadeId);
        return ResponseEntity.status(HttpStatus.OK).body(especialidadeDTO);
    }

    @PostMapping
    public ResponseEntity<EspecialidadeDTO> cadastrarEspecialidade(@Valid @RequestBody EspecialidadeDTO especialidadeDTO) {
        EspecialidadeDTO novaEspecialidadeDTO = especialidadeService.cadastrarEspecialidade(especialidadeDTO);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{especialidadeId}")
                .buildAndExpand(novaEspecialidadeDTO.getEspecialidadeId()).toUri();
        System.out.println(uri);
        return ResponseEntity.created(uri).body(novaEspecialidadeDTO);
    }
}
