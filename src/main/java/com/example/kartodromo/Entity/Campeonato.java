package com.example.kartodromo.Entity;

import com.example.kartodromo.DTO.Pista;
import jakarta.persistence.*;

@Entity
public class Campeonato {

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
@Enumerated(EnumType.STRING)
  private Pista pista;
  private Integer NumeroDeVoltas ;
  @ManyToOne
  private Piloto first;
    @ManyToOne
    private Piloto second;
    @ManyToOne
    private Piloto third;

    private String nome;


    public Campeonato(){}


    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Pista getPista() {
        return pista;
    }

    public void setPista(Pista pista) {
        this.pista = pista;
    }

    public Integer getNumeroDeVoltas() {
        return NumeroDeVoltas;
    }

    public void setNumeroDeVoltas(Integer numeroDeVoltas) {
        NumeroDeVoltas = numeroDeVoltas;
    }

    public Piloto getFirst() {
        return first;
    }

    public void setFirst(Piloto first) {
        this.first = first;
    }

    public Piloto getSecond() {
        return second;
    }

    public void setSecond(Piloto second) {
        this.second = second;
    }

    public Piloto getThird() {
        return third;
    }

    public void setThird(Piloto third) {
        this.third = third;
    }


    @Override
    public String toString() {
        return "Campeonato{" +
                "id=" + id +
                ", pista=" + pista +
                ", NumeroDeVoltas=" + NumeroDeVoltas +
                ", first=" + first +
                ", second=" + second +
                ", third=" + third +
                '}';
    }
}
