package com.example.kartodromo.service;

import com.example.kartodromo.DTO.CadastroCampeonatoDTO;
import com.example.kartodromo.Entity.Campeonato;
import com.example.kartodromo.Entity.Piloto;
import com.example.kartodromo.Exception.DuplicateException;
import com.example.kartodromo.Exception.NotFoundException;
import com.example.kartodromo.Repositorio.CampeonatoReposit;
import com.example.kartodromo.Repositorio.PiltotoReposit;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CampeonatoService {

    private final CampeonatoReposit reposit;
    private final PiltotoReposit pilotoRepo;
    private final ValidarCampeonato campeonatoValidate;


    public CampeonatoService(CampeonatoReposit reposit, PiltotoReposit pilotoRepo,ValidarCampeonato campeonato) {
        this.reposit = reposit;
        this.pilotoRepo = pilotoRepo;
        this.campeonatoValidate = campeonato;
    }

    public Campeonato salvar(CadastroCampeonatoDTO dto) {
        List<Long> ids = List.of(dto.first(), dto.second(), dto.third())    ;
        Set<Long> unicos = Set.copyOf(ids);
        if(ids.size() != unicos.size()){
            throw new DuplicateException("Um piloto não pode ocupar mais de uma posição no Póido");
        }


        Piloto piloto1 = pilotoRepo.findById(dto.first()).orElseThrow();
        Piloto piloto2 = pilotoRepo.findById(dto.second()).orElseThrow();
        Piloto piloto3 = pilotoRepo.findById(dto.third()).orElseThrow();

        Campeonato campeonato = dto.mapeamento(piloto1, piloto2, piloto3);

        if(reposit.findByNome(dto.nome()).isPresent()){
            throw new DuplicateException("Campeonato com o mesmo nome já cadastrado");
        }

        return reposit.save(campeonato);
    }

    public Campeonato buscarCampeonato(Long id){
        return reposit.findById(id)
                .orElseThrow(() -> new NotFoundException("Campeonato com o id: " + id + " não foi encontrado."));
    }

    public List<Campeonato> listagemCamp(){
        return reposit.findAll();
    }


    public Campeonato atualizar(Long id, CadastroCampeonatoDTO dto) {
        if (!reposit.existsById(id)) {
            throw new NotFoundException("Campeonato com id " + id + " não encontrado.");
        }

        Piloto p1 = buscarPiloto(dto.first());
        Piloto p2 = buscarPiloto(dto.second());
        Piloto p3 = buscarPiloto(dto.third());

        Campeonato campeonato = dto.mapeamento(p1, p2, p3);
        campeonato.setId(id);

        campeonatoValidate.validar(campeonato);

        return reposit.save(campeonato);
    }

    public Piloto buscarPiloto(Long id) {
        return pilotoRepo.findById(id)
                .orElseThrow(() -> new NotFoundException("Piloto com id " + id + " não encontrado."));
    }


    public void deletarCampeonato(Long id){
            reposit.findById(id).orElseThrow(() -> new NotFoundException("Campeonato com id " + id + " não encontrado."));
            reposit.deleteById(id);
        }










}
