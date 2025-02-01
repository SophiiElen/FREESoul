package com.sophia.FREESoul.service;

import com.sophia.FREESoul.model.Diario;
import com.sophia.FREESoul.model.Dicas;
import com.sophia.FREESoul.repository.DicasRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 *
 * @author sophi
 */

@Service
public class DicasService {
    
    @Autowired
    DicasRepository dicasRepository;

    public Dicas criar(Dicas dicas) {
        dicasRepository.save(dicas);
        return dicas;

    }
    
    public List<Dicas> buscarTodos() {
        return dicasRepository.findAll();
    
    }

    

}
