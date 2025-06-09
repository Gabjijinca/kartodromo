package com.example.kartodromo.Repositorio;

import com.example.kartodromo.DTO.Equipe;
import com.example.kartodromo.Entity.Piloto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PiltotoReposit extends JpaRepository<Piloto,Long> {


List<Piloto> findByNomeAndEquipe(String nome, Equipe equipe);



}
