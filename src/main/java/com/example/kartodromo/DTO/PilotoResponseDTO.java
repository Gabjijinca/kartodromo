package com.example.kartodromo.DTO;

import com.example.kartodromo.Entity.Piloto;

public record PilotoResponseDTO(Long id, String nome, Equipe equipe, Integer vitorias,Integer Participacoes) {



}
