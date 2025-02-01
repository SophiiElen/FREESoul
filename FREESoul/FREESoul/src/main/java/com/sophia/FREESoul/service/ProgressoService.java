package com.sophia.FREESoul.service;

import com.sophia.FREESoul.model.Diario;
import com.sophia.FREESoul.model.Paciente;
import com.sophia.FREESoul.model.Progresso;
import com.sophia.FREESoul.repository.DiarioRepository;
import com.sophia.FREESoul.repository.PacienteRepository;
import com.sophia.FREESoul.repository.ProgressoRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 *
 * @author sophi
 */

@Service
public class ProgressoService {

    @Autowired
    ProgressoRepository progressoRepository;

    @Autowired
    DiarioRepository diarioRepository;

    @Autowired
    PacienteRepository pacienteRepository;

    public Progresso buscarOuCriarProgresso(Integer pacienteId) {
        Progresso progresso = progressoRepository.findByPacienteId(pacienteId);

        if (progresso == null) {
            Paciente paciente = pacienteRepository.findById(pacienteId).orElse(null);

            progresso = new Progresso();
            progresso.setPaciente(paciente);
            progressoRepository.save(progresso);
        }
        return progresso;
    }

    public Progresso atualizarHumores(Integer pacienteId) {
        Progresso progresso = buscarOuCriarProgresso(pacienteId);

        List<Diario> registros = diarioRepository.findByPacienteId(pacienteId);

        progresso.setTotalFeliz(0);
        progresso.setTotalAnimado(0);
        progresso.setTotalTranquilo(0);
        progresso.setTotalNeutro(0);
        progresso.setTotalNervoso(0);
        progresso.setTotalTriste(0);
        progresso.setTotalAnsioso(0);

        for (Diario diario : registros) {
            switch (diario.getHumor()) {
                case "Feliz":
                    progresso.setTotalFeliz(progresso.getTotalFeliz() + 1);
                    break;
                case "Animado":
                    progresso.setTotalAnimado(progresso.getTotalAnimado() + 1);
                    break;
                case "Tranquilo":
                    progresso.setTotalTranquilo(progresso.getTotalTranquilo() + 1);
                    break;
                case "Neutro":
                    progresso.setTotalNeutro(progresso.getTotalNeutro() + 1);
                    break;
                case "Nervoso":
                    progresso.setTotalNervoso(progresso.getTotalNervoso() + 1);
                    break;
                case "Triste":
                    progresso.setTotalTriste(progresso.getTotalTriste() + 1);
                    break;
                case "Ansioso":
                    progresso.setTotalAnsioso(progresso.getTotalAnsioso() + 1);
                    break;
            }
        }

        return progressoRepository.save(progresso);
    }
}
