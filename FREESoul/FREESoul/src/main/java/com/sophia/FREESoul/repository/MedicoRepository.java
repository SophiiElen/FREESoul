package com.sophia.FREESoul.repository;

import com.sophia.FREESoul.model.Medico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 *
 * @author sophi*/
@Repository
public interface MedicoRepository extends JpaRepository<Medico,Integer> {

    List<Medico> findByEmailAndSenha(String email, String senha);
}
