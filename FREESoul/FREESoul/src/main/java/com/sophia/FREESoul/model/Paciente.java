package com.sophia.FREESoul.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author sophi
 */

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name="Paciente")
public class Paciente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    private String nome;
    
    private int idade;

    @Column(unique = true)
    private String cpf;
    
    private String email;
    
    private String senha;

    @OneToMany
    @JoinColumn(name = "diario_pac")
    private List<Diario> diarios;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public List<Diario> getDiarios() {
        return diarios;
    }

    public void setDiarios(Diario diario) {
        this.diarios = diarios;
    }
}
