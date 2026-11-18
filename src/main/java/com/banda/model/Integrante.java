package com.banda.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Integrante {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String instrumento;

    @ManyToOne
    @JoinColumn(name = "banda_id")
    private Banda banda;

    public Integrante() {
    }

    public Integrante(String nome, String instrumento, Banda banda) {
        this.nome = nome;
        this.instrumento = instrumento;
        this.banda = banda;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getInstrumento() {
        return instrumento;
    }

    public void setInstrumento(String instrumento) {
        this.instrumento = instrumento;
    }

    public Banda getBanda() {
        return banda;
    }

    public void setBanda(Banda banda) {
        this.banda = banda;
    }

    @Override
    public String toString() {
        return "Integrante{id=" + id + ", nome='" + nome + "', instrumento='" + instrumento + "'}";
    }
}
