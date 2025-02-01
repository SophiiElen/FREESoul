package com.sophia.FREESoul.service;

import com.sophia.FREESoul.model.Diario;
import com.sophia.FREESoul.model.Paciente;
import com.sophia.FREESoul.repository.DiarioRepository;
import com.sophia.FREESoul.repository.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 *    
 * @author sophi
 */

@Service
public class DiarioService {

    @Autowired
    DiarioRepository diarioRepository;

    @Autowired
    PacienteRepository pacienteRepository;

    public Diario criar(Diario diario) {
        diario.setId(null);
        diarioRepository.save(diario);
        return diario;
    }

    public List<Diario> buscarTodos() {
        return diarioRepository.findAll();
    
    }
    
    public Diario buscarPorId(Integer id) {
        return diarioRepository.findById(id).orElseThrow();
    
    }

    public List<Diario> buscarTodosPeloIdPaciente(Integer idPaciente){
        return diarioRepository.findByPacienteId(idPaciente);

    }


    public List<Diario> buscarDiariosPorCpf(String cpf) {
        return diarioRepository.findByPacienteCpf(cpf);
    }




}
