package com.sophia.FREESoul.service;
import java.util.List;

import com.sophia.FREESoul.model.Paciente;
import com.sophia.FREESoul.repository.PacienteRepository;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author sophi
 */

@Service
public class PacienteService {

    @Autowired
    PacienteRepository pacienteRepository;
    
    
    public Paciente criar(Paciente paciente) {
        
        pacienteRepository.save(paciente);
        return paciente;
    
    }
    
    public List<Paciente> buscarTodos() {
        return pacienteRepository.findAll();
    
    }
    
    public Paciente buscarPorId(Integer id) {
        return pacienteRepository.findById(id).orElseThrow();
    
    }

    public Paciente getPacienteLogado(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if ("logado".equals(cookie.getName())) {
                    String id = cookie.getValue();
                    try {
                        int pacienteId = Integer.parseInt(id);
                        return pacienteRepository.findById(pacienteId).orElse(null);
                    } catch (NumberFormatException e) {
                        System.out.println("Erro ao converter o ID do cookie: " + e.getMessage());
                    }
                }
            }
        }
        return null;
    }


    
    
    
}
