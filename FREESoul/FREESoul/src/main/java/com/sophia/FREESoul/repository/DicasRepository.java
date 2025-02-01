package com.sophia.FREESoul.repository;

import com.sophia.FREESoul.model.Dicas;
import com.sophia.FREESoul.model.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 *
 * @author sophi
 */
@Repository
public interface DicasRepository extends JpaRepository<Dicas,Integer> {


}
