package com.sophia.FREESoul.controller;

import com.sophia.FREESoul.model.Medico;
import com.sophia.FREESoul.model.Paciente;
import com.sophia.FREESoul.repository.MedicoRepository;
import com.sophia.FREESoul.repository.PacienteRepository;
import com.sophia.FREESoul.service.MedicoService;
import com.sophia.FREESoul.service.PacienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/FREESoul")
public class LoginAPIController {
    @Autowired
    PacienteService pacienteService;

    @Autowired
    MedicoService medicoService;

    @Autowired
    private PacienteRepository pacienteRepository;

    @Autowired
    private MedicoRepository medicoRepository;

    @PostMapping("/cadastrarpaciente")
    public ResponseEntity<Paciente> criarPaciente(@RequestBody Paciente paciente){
        Paciente novoPaciente = pacienteService.criar(paciente);
        return new ResponseEntity<>(novoPaciente, HttpStatus.CREATED);

    }

    @PostMapping("/cadastrarmedico")
    public ResponseEntity<Medico> criarMedico(@RequestBody Medico medico){
        Medico novoMedico = medicoService.criar(medico);
        return new ResponseEntity<>(novoMedico, HttpStatus.CREATED);

    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@PathVariable String email, @PathVariable String senha, @PathVariable Integer id) {

        List<Paciente> pacientes = pacienteRepository.findByEmailAndSenha(email, senha);
        if (!pacientes.isEmpty()) {
        Paciente paciente = pacientes.get(0);
            return ResponseEntity.ok(paciente);
        }

        List<Medico> medicos = medicoRepository.findByEmailAndSenha(email, senha);
        if (!medicos.isEmpty()) {
            Medico medico = medicos.get(0);
            return ResponseEntity.ok(medico);
        }

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("E-mail ou senha inválidos.");
    }


}
