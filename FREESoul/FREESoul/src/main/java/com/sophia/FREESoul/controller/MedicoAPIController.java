package com.sophia.FREESoul.controller;

import com.sophia.FREESoul.model.Diario;
import com.sophia.FREESoul.service.DiarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/Medico")
public class MedicoAPIController {

    @Autowired
    DiarioService diarioService;

    @GetMapping("/listarDiarios/{cpf}")
    public ResponseEntity<List<Diario>> getDiariosByCpf(@PathVariable String cpf) {
        List<Diario> diarios = diarioService.buscarDiariosPorCpf(cpf);
        if (diarios.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(diarios);
    }
    

}
