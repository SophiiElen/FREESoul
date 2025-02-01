package com.sophia.FREESoul.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;

/**
 *
 * @author sophi
 */

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name="Progresso")
public class Progresso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private int totalFeliz;
    
    private int totalAnimado;
    
    private int totalTranquilo;
    
    private int totalNeutro;
    
    private int totalNervoso;
    
    private int totalTriste;
    
    private int totalAnsioso;

    @ManyToOne
    @JoinColumn(name = "paciente_id")
    private Paciente paciente;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getTotalFeliz() {
        return totalFeliz;
    }

    public void setTotalFeliz(int totalFeliz) {
        this.totalFeliz = totalFeliz;
    }

    public int getTotalAnimado() {
        return totalAnimado;
    }

    public void setTotalAnimado(int totalAnimado) {
        this.totalAnimado = totalAnimado;
    }

    public int getTotalTranquilo() {
        return totalTranquilo;
    }

    public void setTotalTranquilo(int totalTranquilo) {
        this.totalTranquilo = totalTranquilo;
    }

    public int getTotalNeutro() {
        return totalNeutro;
    }

    public void setTotalNeutro(int totalNeutro) {
        this.totalNeutro = totalNeutro;
    }

    public int getTotalNervoso() {
        return totalNervoso;
    }

    public void setTotalNervoso(int totalNervoso) {
        this.totalNervoso = totalNervoso;
    }

    public int getTotalTriste() {
        return totalTriste;
    }

    public void setTotalTriste(int totalTriste) {
        this.totalTriste = totalTriste;
    }

    public int getTotalAnsioso() {
        return totalAnsioso;
    }

    public void setTotalAnsioso(int totalAnsioso) {
        this.totalAnsioso = totalAnsioso;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

}
