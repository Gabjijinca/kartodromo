package com.example.kartodromo.DTO;

import com.example.kartodromo.Entity.Campeonato;
import com.example.kartodromo.Entity.Piloto;
import com.example.kartodromo.Exception.PistainvalidaException;
import com.example.kartodromo.Repositorio.PiltotoReposit;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CadastroCampeonatoDTO(

        @NotBlank
        String nome,

        @NotNull(message = "A pista é obrigatótia")
         String pista,
        @NotNull(message = "número de voltas obrigatório")

        Integer NumeroDeVoltas,
         @NotNull(message = "id do primeiro colocado é obrigatório")
         Long first,
        @NotNull(message = "id do segundo colocado é obrigatório")

        Long second,
        @NotNull(message = "id do terceiro colocado é obrigatório")

        Long third) {



    public Campeonato mapeamento(Piloto piloto1, Piloto piloto2, Piloto piloto3){


        Campeonato camp = new Campeonato();
        camp.setFirst(piloto1);
        camp.setSecond(piloto2);
        camp.setThird(piloto3);
        camp.setNumeroDeVoltas(this.NumeroDeVoltas);
        camp.setNome(this.nome);

        try {
            Pista pistaEnum = Pista.valueOf(this.pista.toUpperCase());
            camp.setPista(pistaEnum);
        } catch (IllegalArgumentException e) {
            throw new PistainvalidaException("Pista inválida: " + pista);
        }




        return camp;
    }
}
