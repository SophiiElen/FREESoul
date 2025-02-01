package com.sophia.FREESoul.controller;


import com.sophia.FREESoul.model.Medico;
import com.sophia.FREESoul.model.Paciente;
import com.sophia.FREESoul.repository.MedicoRepository;
import com.sophia.FREESoul.repository.PacienteRepository;
import com.sophia.FREESoul.service.MedicoService;
import com.sophia.FREESoul.service.PacienteService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
public class LoginController {

    @Autowired
    PacienteService pacienteService;

    @Autowired
    MedicoService medicoService;

    @Autowired
    private PacienteRepository pacienteRepository;

    @Autowired
    private MedicoRepository medicoRepository;

    @GetMapping("/")
    public String inicio(Model model) {
        return "index";

    }

    @GetMapping("/login")
    public String exibirLogin(Model model) {
        return "login";
    }

    @PostMapping("/validar")
    public String processarLogin(@RequestParam String email, @RequestParam String senha, Model model, HttpServletResponse response) {

        List<Paciente> pacientes = pacienteRepository.findByEmailAndSenha(email, senha);
        if (!pacientes.isEmpty()) {
            Paciente paciente = pacientes.get(0);

            String id = String.valueOf(paciente.getId());

            //cookie de autenticação
            Cookie cookieLog = new Cookie("logado", id);
            cookieLog.setMaxAge(7 * 24 * 60 * 60);
            cookieLog.setHttpOnly(true);
            cookieLog.setPath("/");

            response.addCookie(cookieLog);

            return "index";
        }


        List<Medico> medicos = medicoRepository.findByEmailAndSenha(email, senha);
        if (!medicos.isEmpty()) {
            Medico medico = medicos.get(0);

            String id = String.valueOf(medico.getId());

            //cookie de autenticação
            Cookie cookieLog = new Cookie("logado", id);
            cookieLog.setMaxAge(7 * 24 * 60 * 60);
            cookieLog.setHttpOnly(true);
            cookieLog.setPath("/");

            response.addCookie(cookieLog);
            return "index";
        }


        System.out.println("Credenciais invalidas");
        model.addAttribute("erro", "Credenciais inválidas. Verifique seu email e senha.");
        return "login";
    }

    @GetMapping("/cadastrarPaciente")
    public String cadastrarPaciente(Model model) {
        model.addAttribute("paciente", new Paciente());
        return "loginPaciente";
    }

    @PostMapping("/gravarPaciente")
    public String processarPaciente(@ModelAttribute Paciente paciente, Model model) {
        pacienteService.criar(paciente);
        model.addAttribute("mensagem", "Paciente cadastrado com sucesso!");
        return "login";
    }

    @GetMapping("/cadastrarMedico")
    public String cadastrarMedico(Model model) {
        model.addAttribute("medico", new Medico());
        return "loginMedico";
    }

    @PostMapping("/gravarMedico")
    public String processarMedico(@ModelAttribute Medico medico, Model model){
        medicoService.criar(medico);
        model.addAttribute("mensagem", "Medico cadastrado com sucesso!");
        return "registrospaciente";
    }


}
