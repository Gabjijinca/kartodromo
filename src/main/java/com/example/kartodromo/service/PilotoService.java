package com.example.kartodromo.service;

import com.example.kartodromo.DTO.CadastroPilotoDTO;
import com.example.kartodromo.DTO.PilotoResponseDTO;
import com.example.kartodromo.Entity.Campeonato;
import com.example.kartodromo.Entity.Piloto;
import com.example.kartodromo.Exception.DuplicateException;
import com.example.kartodromo.Exception.NotFoundException;
import com.example.kartodromo.Repositorio.CampeonatoReposit;
import com.example.kartodromo.Repositorio.PilotoView;
import com.example.kartodromo.Repositorio.PiltotoReposit;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PilotoService {
    private final PiltotoReposit reposit;
    private final ValidarPiloto validarPiloto;
    private final CampeonatoReposit campeonatoReposit;

    public PilotoService(PiltotoReposit reposit, ValidarPiloto validarPiloto, CampeonatoReposit campeonatoReposit) {
        this.reposit = reposit;
        this.validarPiloto = validarPiloto;
        this.campeonatoReposit = campeonatoReposit;
    }


    public Piloto salvar(Piloto piloto){
        validarPiloto.validar(piloto);
        return reposit.save(piloto);
    }
    @Cacheable(value = "pilotos")
    public List<Piloto> listarPilotos(){
        return reposit.findAll();
    }

    public Piloto atualizar(Piloto piloto) {
        validarPiloto.validar(piloto);
        if (!reposit.existsById(piloto.getId())) {
            throw new NotFoundException("Piloto com id " + piloto.getId() + " não encontrado.");
        }
        return reposit.save(piloto);
    }


    public void deletar(Long id) {
        Optional<Piloto> piloto = reposit.findById(id);
        if (piloto.isEmpty()) {
            throw new NotFoundException("Piloto com id " + id + " não encontrado");
        }
      if(existePiloto(piloto.get())){
            throw new DuplicateException("Não é possível deletar: piloto participa de um campeonato");
        }
        reposit.deleteById(id);
    }

    public boolean existePiloto(Piloto piloto){
        return campeonatoReposit.existsByFirstOrSecondOrThird(piloto,piloto,piloto);
    }

    public int contarVitorias(Piloto piloto) {
        return campeonatoReposit.countByFirst(piloto);
    }

    @Cacheable(value = "pilotosComVitorias", key = "#id")
    public PilotoResponseDTO buscarPilotoComVitorias(Long id) {
        Optional<Piloto> pilotoOpt = reposit.findById(id);
        if (pilotoOpt.isEmpty()) {
            throw new NotFoundException("Piloto com id " + id + " não encontrado");
        }
        Piloto piloto = pilotoOpt.get();
        Integer vitorias = campeonatoReposit.countByFirst(piloto);
        Integer participacoes = campeonatoReposit.countByFirstOrSecondOrThird(piloto,piloto,piloto);
        return new PilotoResponseDTO(piloto.getId(), piloto.getNome(), piloto.getEquipe(), vitorias,participacoes);
    }

    @Cacheable(value = "resumoPilotos")
    public List<PilotoView> listarResumo() {
        return reposit.findAllBy();
    }






}
