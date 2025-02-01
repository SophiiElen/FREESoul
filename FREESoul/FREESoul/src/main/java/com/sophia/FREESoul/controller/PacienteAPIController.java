package com.sophia.FREESoul.controller;

import com.sophia.FREESoul.model.Diario;
import com.sophia.FREESoul.model.Progresso;
import com.sophia.FREESoul.service.DiarioService;
import com.sophia.FREESoul.service.ProgressoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Paciente")
public class PacienteAPIController {

    @Autowired
    DiarioService diarioService;

    @Autowired
    ProgressoService progressoService;

    @PostMapping("/registrar-diario")
    public ResponseEntity<Diario> criar(@RequestBody Diario diario) {
        Diario novoDiario = diarioService.criar(diario);
        return new ResponseEntity<>(novoDiario, HttpStatus.CREATED);

    }

    @GetMapping("/meusregistros/{idPaciente}")
    public ResponseEntity<List> buscar(@PathVariable Integer idPaciente) {
        List<Diario> listarTodosDiarios = diarioService.buscarTodosPeloIdPaciente(idPaciente);
        return new ResponseEntity<>(listarTodosDiarios, HttpStatus.OK);

    }

    @GetMapping("/progresso/{idPaciente}")
    public Progresso buscarProgresso(@PathVariable Integer idPaciente) {
        return progressoService.atualizarHumores(idPaciente);
    }
}

