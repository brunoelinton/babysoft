package com.healthcare.babysoft.controllers;

import com.healthcare.babysoft.dtos.FichaPacienteDTO;
import com.healthcare.babysoft.services.FichaPacienteService;
import com.healthcare.babysoft.services.MaeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/maes/{cpfPaciente}/fichas")
public class FichaPacienteController {

    @Autowired
    private FichaPacienteService fichaPacienteService;

    @Autowired
    private MaeService maeService;

    /*
    @GetMapping
    public ResponseEntity<Page<FichaPacienteDTO>>  buscarTodasFichas(@PageableDefault(page = 0, size = 12, sort = "cpf", direction = Sort.Direction.ASC) Pageable pageable) {
        Page<FichaPacienteDTO> lista = fichaPacienteService.buscarTodasFichas(pageable);
        return ResponseEntity.status(HttpStatus.OK).body(lista);
    }
    */

    @GetMapping
    public ResponseEntity<FichaPacienteDTO> buscarFichaPaciente(@PathVariable String cpfPaciente) {
        FichaPacienteDTO fichaPacienteDTO = fichaPacienteService.buscarFichaPaciente(cpfPaciente);
        return ResponseEntity.status(HttpStatus.OK).body(fichaPacienteDTO);
    }

    @PostMapping
    public ResponseEntity<FichaPacienteDTO> preencherFichaPaciente(@PathVariable String cpfPaciente, @RequestBody FichaPacienteDTO fichaPacienteDTO) {
        System.out.println("Controller");
        FichaPacienteDTO novaFichaPaciente = fichaPacienteService.preencherFichaPaciente(cpfPaciente, fichaPacienteDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(novaFichaPaciente);
    }
}
