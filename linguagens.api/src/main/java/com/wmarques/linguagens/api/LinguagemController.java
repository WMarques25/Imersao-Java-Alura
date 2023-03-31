package com.wmarques.linguagens.api;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
public class LinguagemController {
    
    @Autowired
    private LinguagemRepository repositorio;

    @GetMapping("/linguagens") // Buscando tds as linguagens
    public List<Linguagem> obterLinguagens(){
        List<Linguagem> linguagens = repositorio.findByOrderByRanking();
        return linguagens;
        
    }

    @GetMapping("/linguagens/{id}") // Buscando linguagem por id
    public Linguagem obterLinguagemPorId(@PathVariable String id){
        return repositorio.findById(id)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/linguagens") // Adicionando linguagem
    public ResponseEntity<Linguagem> cadastrarLinguagem(@RequestBody Linguagem linguagem){
        Linguagem newSave = repositorio.save(linguagem);
        return new ResponseEntity<>(newSave, HttpStatus.CREATED);
    }

    @PutMapping("/linguagens/{id}") // Editando linguagem por id
    public Linguagem atualizarLinguagemPorId(@PathVariable String id , @RequestBody Linguagem linguagem){
        repositorio.findById(id)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        linguagem.setId(id);

        Linguagem linguagemSalva = repositorio.save(linguagem);
        return linguagemSalva;
    }
    
    @DeleteMapping("/linguagens/{id}") // Deletando linguagem por id
    public void excluirLinguagem(@PathVariable String id){
        repositorio.deleteById(id);

    }
}
