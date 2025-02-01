package com.sophia.FREESoul.controller;

import com.sophia.FREESoul.model.Diario;
import com.sophia.FREESoul.model.Paciente;
import com.sophia.FREESoul.service.DiarioService;
import com.sophia.FREESoul.service.MedicoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class MedicoController {

    @Autowired
    MedicoService medicoService;

    @Autowired
    DiarioService diarioService;

    @GetMapping("/registrospaciente")
    public String registros(){
        //model.addAttribute("lista", diarioService.buscarDiariosPorCpf());
        return "registrospaciente";

    }

    @GetMapping("/buscarporcpf")
    public String registrosPacientes(Model model, @RequestParam("cpf") String cpf) {

        List<Diario> registrosEncontrados = diarioService.buscarDiariosPorCpf(cpf);

        model.addAttribute("registros", registrosEncontrados);
        return "registrospaciente";
    }



}
