package com.example.kartodromo.Controller;

import com.example.kartodromo.DTO.CadastroCampeonatoDTO;
import com.example.kartodromo.DTO.ResponseCampeonatoDTO;
import com.example.kartodromo.Entity.Campeonato;
import com.example.kartodromo.Exception.ErroResponse;
import com.example.kartodromo.Exception.PistainvalidaException;
import com.example.kartodromo.Repositorio.PiltotoReposit;
import com.example.kartodromo.service.CampeonatoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("camp")
public class CampeonatoController {


    private final CampeonatoService service;
    private final PiltotoReposit reposit;

    public CampeonatoController(CampeonatoService service, PiltotoReposit reposit) {
        this.service = service;
        this.reposit = reposit;
    }


    @PostMapping
    public ResponseEntity<?> Postagem(@RequestBody @Valid CadastroCampeonatoDTO dto) {
        try {
            Campeonato salvo = service.salvar(dto);
            ResponseCampeonatoDTO response = ResponseCampeonatoDTO.mapear(salvo);
            return ResponseEntity.ok(response);
        }catch (PistainvalidaException e){
            var erroDTO = ErroResponse.respostaPadrao(e.getMessage());
            return ResponseEntity.status(erroDTO.status()).body(erroDTO);
        }
    }


   @GetMapping
   public ResponseEntity<?>Listagem(){
        var ops = service.listagemCamp();
    return ResponseEntity.ok(ops);
    }



   @GetMapping("{id}")
   public ResponseEntity<?>BuscaPorCampeonato(@PathVariable Long id){
           Campeonato campeonato = service.buscarCampeonato(id);
           return ResponseEntity.ok(ResponseCampeonatoDTO.mapear(campeonato));
       }


    @PutMapping("{id}")
    public ResponseEntity<Campeonato> atualizar(@PathVariable Long id, @RequestBody @Valid CadastroCampeonatoDTO dto) {
        Campeonato atualizado = service.atualizar(id, dto);
        return ResponseEntity.ok(atualizado);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id){
        service.deletarCampeonato(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }







}
