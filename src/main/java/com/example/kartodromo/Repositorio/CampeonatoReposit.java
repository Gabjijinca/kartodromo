package com.example.kartodromo.Repositorio;

import com.example.kartodromo.Entity.Campeonato;
import com.example.kartodromo.Entity.Piloto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CampeonatoReposit extends JpaRepository<Campeonato,Long> {
    boolean existsByFirstOrSecondOrThird(Piloto first, Piloto second, Piloto third);

    List<Campeonato> findByFirst(Piloto piloto);

    int countByFirst(Piloto piloto);

    int countByFirstOrSecondOrThird(Piloto first, Piloto second, Piloto third);


    Optional<Campeonato> findByNome(String nome);

}
