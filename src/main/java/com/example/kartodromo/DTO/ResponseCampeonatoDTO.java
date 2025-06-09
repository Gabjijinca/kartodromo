package com.example.kartodromo.DTO;

import com.example.kartodromo.Entity.Campeonato;
import com.example.kartodromo.Entity.Piloto;

public record ResponseCampeonatoDTO(
        String nome,
        Pista pista,
        Integer NumeroDeVoltas,
        Piloto first,
        Piloto second,
        Piloto third) {

    public static ResponseCampeonatoDTO mapear(Campeonato campeonato){
       return new ResponseCampeonatoDTO(campeonato.getNome(),campeonato.getPista(),campeonato.getNumeroDeVoltas(),campeonato.getFirst(),campeonato.getSecond(),campeonato.getThird());
    }
}
