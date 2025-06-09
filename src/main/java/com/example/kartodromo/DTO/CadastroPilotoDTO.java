package com.example.kartodromo.DTO;

import com.example.kartodromo.Entity.Campeonato;
import com.example.kartodromo.Entity.Piloto;
import com.example.kartodromo.Exception.EquipeInvalidaException;
import com.example.kartodromo.Repositorio.CampeonatoReposit;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.List;
import java.util.Optional;

public record CadastroPilotoDTO(Long id,
                                @Size(max = 100,message = "campo fora do tamanho padrão")
                                @NotBlank(message = "campo obrigatório")
                                String nome,
                                @NotBlank(message = "campo obrigatório")
                                String equipe
) {
public Piloto mapearParaEntidade(){
    Piloto piloto = new Piloto();
    piloto.setNome(this.nome);

    try {
        Equipe equipeEnum =  Equipe.valueOf(this.equipe.toUpperCase());
        piloto.setEquipe(equipeEnum);

    }catch(IllegalArgumentException e){
        throw new EquipeInvalidaException("Equipe: "+this.equipe + " Inválida");
    }


    return piloto;
}




}
