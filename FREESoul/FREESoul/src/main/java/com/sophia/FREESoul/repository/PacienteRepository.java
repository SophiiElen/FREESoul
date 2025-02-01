package com.sophia.FREESoul.repository;

import com.sophia.FREESoul.model.Diario;
import com.sophia.FREESoul.model.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 *
 * @author sophi
 */
@Repository
public interface PacienteRepository extends JpaRepository<Paciente,Integer> {

    List<Paciente> findByCpf(String cpf);

    List<Paciente> findByEmailAndSenha(String email, String senha);;

}
