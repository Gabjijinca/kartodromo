package com.example.kartodromo.service;

import com.example.kartodromo.Entity.Campeonato;
import com.example.kartodromo.Entity.Piloto;
import com.example.kartodromo.Exception.DuplicateException;
import com.example.kartodromo.Repositorio.CampeonatoReposit;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class ValidarCampeonato {
    private final CampeonatoReposit reposit;

    public ValidarCampeonato(CampeonatoReposit reposit) {
        this.reposit = reposit;
    }


    public void validar(Campeonato campeonato) {
        Optional<Campeonato> encontrados = reposit.findByNome(campeonato.getNome());
        if (encontrados.isPresent()) {
            throw new DuplicateException("Campeonato com esse nome já cadastrado.");

        }
        validarPiloto(campeonato);
    }


    private void validarPiloto(Campeonato campeonato){
        Long fisrt = campeonato.getFirst().getId();
        Long second = campeonato.getSecond().getId();
        Long third = campeonato.getThird().getId();

        if(fisrt.equals(second) || fisrt.equals(third) || second.equals(third)){
            throw new DuplicateException("mesmo piloto em posições diferentes no Pódio");
        }
    }


}
