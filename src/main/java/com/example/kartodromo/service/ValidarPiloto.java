package com.example.kartodromo.service;

import com.example.kartodromo.Entity.Piloto;
import com.example.kartodromo.Exception.DuplicateException;
import com.example.kartodromo.Repositorio.PiltotoReposit;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class ValidarPiloto {

    private final PiltotoReposit reposit;

    public ValidarPiloto(PiltotoReposit reposit) {
        this.reposit = reposit;
    }


    public void validar(Piloto piloto) {
        List<Piloto> encontrados = reposit.findByNomeAndEquipe(piloto.getNome(), piloto.getEquipe());
        if (!encontrados.isEmpty()) {
            throw new DuplicateException("Piloto já cadastrado com este nome e equipe.");
        }
    }

    public void ValidarPilotoParaAtualizacao(Piloto piloto) {
        List<Piloto> encontrados = reposit.findByNomeAndEquipe(piloto.getNome(), piloto.getEquipe());
        for (Piloto existente : encontrados) {
            if (!existente.getId().equals(piloto.getId())) {
                throw new DuplicateException("Já existe outro piloto com este nome e equipe.");
            }
        }
    }




}
