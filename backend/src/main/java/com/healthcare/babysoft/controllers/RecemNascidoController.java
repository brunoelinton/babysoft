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

import javax.validation.Valid;

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
        return ResponseEntity.status(HttpStatus.CREATED).body(recemNascidoService.cadastrarRecemNascido(recemNascidoDTO));
    }


}
