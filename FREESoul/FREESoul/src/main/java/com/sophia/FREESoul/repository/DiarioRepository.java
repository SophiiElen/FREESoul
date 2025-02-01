package com.sophia.FREESoul.repository;

import com.sophia.FREESoul.model.Diario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 *
 * @author sophi
 */
@Repository
public interface DiarioRepository extends JpaRepository<Diario,Integer> {

    List<Diario> findByPacienteId(Integer idPaciente);

    List<Diario> findByPacienteCpf(String cpf);

}