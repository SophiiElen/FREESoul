package com.sophia.FREESoul.controller;

import com.sophia.FREESoul.model.Diario;
import com.sophia.FREESoul.model.Progresso;
import com.sophia.FREESoul.service.DiarioService;
import com.sophia.FREESoul.service.DicasService;
import com.sophia.FREESoul.service.ProgressoService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.ui.Model;
import com.sophia.FREESoul.model.Paciente;
import com.sophia.FREESoul.service.PacienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 *
 * @author sophi
 */

@Controller
public class PacienteController {

    @Autowired
    PacienteService pacienteService;

    @Autowired
    DiarioService diarioService;

    @Autowired
    ProgressoService progressoService;

    @GetMapping("/diario")
    public String diario(Model model) {
        model.addAttribute("diario", new Diario());
        return "diario";

    }

    @PostMapping("/registrar-diario")
    public String processarDiario(@ModelAttribute Diario diario, HttpServletRequest request, Model model) {
        Paciente pacienteLogado = pacienteService.getPacienteLogado(request);

        if (pacienteLogado == null) {
            return "redirect:/login";
        }

        diario.setPaciente(pacienteService.buscarPorId(pacienteLogado.getId()));
        diario.setPaciente(pacienteLogado);
        diarioService.criar(diario);
        System.out.println(pacienteLogado.getId());
        model.addAttribute("mensagem", "Diario cadastrado com sucesso!");
        return "redirect:/meusregistros";

    }

    @GetMapping("/dicas")
    public String dicas() {
        return "dicas";

    }

    @GetMapping("/meusregistros")
    public String meusRegistros(HttpServletRequest request, Model model) {

        Paciente pacienteLogado = pacienteService.getPacienteLogado(request);
        if (pacienteLogado == null) {
            return "redirect:/login";
        }

        Integer idPaciente = pacienteLogado.getId();
        List<Diario> registrosEncontrados = diarioService.buscarTodosPeloIdPaciente(idPaciente);

        model.addAttribute("registros", registrosEncontrados);

        return "meusregistros";
    }

    @GetMapping("/progresso")
    public String mostrarProgresso(HttpServletRequest request, Model model) {
        Paciente pacienteLogado = pacienteService.getPacienteLogado(request);
        if (pacienteLogado == null) {
            return "redirect:/login";
        }

        Integer idPaciente = pacienteLogado.getId();
        Progresso progresso = progressoService.atualizarHumores(idPaciente);
        model.addAttribute("progresso", progresso);
        return "progresso";
    }


}
