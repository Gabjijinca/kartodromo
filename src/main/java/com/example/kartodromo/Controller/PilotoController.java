package com.example.kartodromo.Controller;

import com.example.kartodromo.DTO.CadastroPilotoDTO;
import com.example.kartodromo.DTO.PilotoResponseDTO;
import com.example.kartodromo.Entity.Piloto;
import com.example.kartodromo.Exception.DuplicateException;
import com.example.kartodromo.Exception.EquipeInvalidaException;
import com.example.kartodromo.Exception.ErroResponse;
import com.example.kartodromo.service.PilotoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("pilotos")
public class PilotoController {

    private final PilotoService pilotoService;


    public PilotoController(PilotoService pilotoService) {
        this.pilotoService = pilotoService;
    }


    @GetMapping
    public ResponseEntity<List<Piloto>> listagemGeral(){
        var ops = pilotoService.listarPilotos();
        return ResponseEntity.ok(ops);
    }

    @GetMapping("{id}")
    public ResponseEntity<?> ListagemDePiloto(@PathVariable Long id) {
            PilotoResponseDTO dto = pilotoService.buscarPilotoComVitorias(id);
            return ResponseEntity.ok(dto);
    }



    @PostMapping
    public ResponseEntity<?> salvar(@RequestBody @Valid CadastroPilotoDTO dto){
        try {
            Piloto pilot = dto.mapearParaEntidade();
            pilotoService.salvar(pilot);
            return ResponseEntity.ok(pilot);
        }catch (EquipeInvalidaException e){
            var erroDTO = ErroResponse.respostaPadrao(e.getMessage());
            return ResponseEntity.status(erroDTO.status()).body(erroDTO);
        }
    }


@PutMapping("{id}")
public ResponseEntity<?> atualizar(@PathVariable Long id, @RequestBody @Valid CadastroPilotoDTO dto) {
        Piloto piloto = dto.mapearParaEntidade();
        piloto.setId(id);
        Piloto atualizado = pilotoService.atualizar(piloto);
        return ResponseEntity.ok(atualizado);

}
    @DeleteMapping("{id}")
    public ResponseEntity<?> deletar(@PathVariable Long id){
               pilotoService.deletar(id);
               return ResponseEntity.noContent().build();
    }






}
