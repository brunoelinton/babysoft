package com.healthcare.babysoft.controllers;

import com.healthcare.babysoft.dtos.FuncionarioDTO;
import com.healthcare.babysoft.repositories.FuncionarioRepository;
import com.healthcare.babysoft.services.FuncionarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping(value = "/funcionarios")
public class FuncionarioController {

    @Autowired
    private FuncionarioService funcionarioService;

    @GetMapping
    public ResponseEntity<Page<FuncionarioDTO>> buscarTodosFuncionarios(Pageable pageable) {
        Page<FuncionarioDTO> lista = funcionarioService.buscarTodosFuncionarios(pageable);
        return ResponseEntity.status(HttpStatus.OK).body(lista);
    }

    @GetMapping("/{cpf}")
    public ResponseEntity<FuncionarioDTO> buscarUmaMae(@PathVariable String cpf) {
        FuncionarioDTO funcionarioDTO = funcionarioService.buscarUmFuncionario(cpf);
        return ResponseEntity.status(HttpStatus.OK).body(funcionarioDTO);
    }

    @PostMapping
    public ResponseEntity<FuncionarioDTO> cadastrarFuncionario(@Valid @RequestBody FuncionarioDTO funcionarioDTO) {
        FuncionarioDTO novoFuncionarioDTO = funcionarioService.cadastrarFuncionario(funcionarioDTO);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(novoFuncionarioDTO.getCpf()).toUri();
        return ResponseEntity.created(uri).body(novoFuncionarioDTO);
    }
}
