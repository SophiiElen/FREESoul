package com.sophia.FREESoul.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author sophi
 */

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name="Dicas")
public class Dicas {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    
    private String dica;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDica() {
        return dica;
    }

    public void setDica(String dica) {
        this.dica = dica;
    }
}
