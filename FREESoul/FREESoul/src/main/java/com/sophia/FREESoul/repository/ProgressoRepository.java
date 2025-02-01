package com.sophia.FREESoul.repository;

import com.sophia.FREESoul.model.Progresso;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author sophi
 */
@Repository
public interface ProgressoRepository extends JpaRepository<Progresso,Integer> {

    Progresso findByPacienteId(Integer idPaciente);
}
