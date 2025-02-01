package com.sophia.FREESoul.service;

import com.sophia.FREESoul.model.Medico;
import com.sophia.FREESoul.repository.MedicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 *
 * @author sophi
 */

@Service
public class MedicoService {

    @Autowired
    MedicoRepository medicoRepository;
    
    
    public Medico criar(Medico medico) {
        
        medicoRepository.save(medico);
        return medico;
    
    }
    
    public List<Medico> buscarTodos() {
        return medicoRepository.findAll();
    
    }
    
    public Medico buscarPorId(Integer id) {
        return medicoRepository.findById(id).orElseThrow();
    
    }
    
}
