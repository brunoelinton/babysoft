package com.healthcare.babysoft.controllers;

import com.healthcare.babysoft.dtos.RecemNascidoDTO;
import com.healthcare.babysoft.services.RecemNascidoService;
import lombok.SneakyThrows;
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
@RequestMapping("/recemNascidos")
public class RecemNascidoController {

    @Autowired
    private RecemNascidoService recemNascidoService;

    @GetMapping
    public ResponseEntity<Page<RecemNascidoDTO>> buscarTodosRecemNascidos(Pageable pageable) {
        Page<RecemNascidoDTO> lista = recemNascidoService.buscarTodosRecemNascidos(pageable);
        return ResponseEntity.status(HttpStatus.OK).body(lista);
    }

    @SneakyThrows
    @GetMapping("/{recemNascidoId}")
    public ResponseEntity<RecemNascidoDTO> buscarUmRecemNascido(@PathVariable String recemNascidoId) {
        RecemNascidoDTO recemNascidoDTO = recemNascidoService.buscarUmRecemNascido(recemNascidoId);
        return ResponseEntity.status(HttpStatus.OK).body(recemNascidoDTO);
    }

    @PostMapping
    public ResponseEntity<Object> cadastrarRecemNascido(@Valid @RequestBody RecemNascidoDTO recemNascidoDTO) {
        RecemNascidoDTO novoRecemNascidoDTO = recemNascidoService.cadastrarRecemNascido(recemNascidoDTO);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{recemNascidoId}")
                .buildAndExpand(novoRecemNascidoDTO.getRecemNascidoId()).toUri();
        return ResponseEntity.created(uri).body(novoRecemNascidoDTO);
    }


}
