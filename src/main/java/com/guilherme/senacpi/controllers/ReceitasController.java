/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.guilherme.senacpi.controllers;

import com.guilherme.senacpi.models.Receita;
import com.guilherme.senacpi.repositories.ReceitaRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ReceitasController {
    
    @Autowired
    private ReceitaRepository repository;
    
    @GetMapping("/api/receitas")
    public List<Receita> getReceitas(){
        return repository.findAll();
    }
    
    @PostMapping("/api/cadastrar")
    public ResponseEntity<String> addReceita(@RequestBody Receita r){
        try {
           if (r.getNome().isBlank() || r.getReceita().isBlank() || r.getImagem().isBlank() || 
                    r.getQuantiaPessoas() < 1 || r.getQuantiaPessoas() > 12) {
               return ResponseEntity.status(HttpStatusCode.valueOf(400)).body("Falha: Algum campo está invalido.");
           }
           repository.save(r);
           return ResponseEntity.ok("Cadastrado com sucesso");
        }catch(Exception e){
            return ResponseEntity.status(HttpStatusCode.valueOf(400)).body("Erro ao adicionar receita. " + e.getMessage());
        }
    }
    
    @GetMapping("/api/receita/{id}")
    public ResponseEntity<?> getReceita(@PathVariable String id){
        try {
            Long r_id = Long.valueOf(id);
            if(!repository.existsById(r_id)){
                return ResponseEntity.status(HttpStatusCode.valueOf(404)).body("Receita não encontrada.");
            }
            
            Receita r = repository.findById(r_id).orElse(null);
            
            if (r != null) {
                return ResponseEntity.ok(r);
            } else {
                return ResponseEntity.status(404).body("Receita não encontrada.");
            }
        }catch(NumberFormatException e){
            return ResponseEntity.status(HttpStatusCode.valueOf(404)).body("Receita não encontrada.");
        }
    }
    
    @DeleteMapping("/api/deletar/{id}")
    public ResponseEntity<String> deletarReceita(@PathVariable String id){
        try {
            Long r_id = Long.valueOf(id);
            if(!repository.existsById(r_id)){
                return ResponseEntity.status(HttpStatusCode.valueOf(400)).body("O ID passado era inválido.");
            }
            
            repository.deleteById(r_id);
            return ResponseEntity.ok("Deletado com sucesso.");
        }catch(NumberFormatException e){
            return ResponseEntity.status(HttpStatusCode.valueOf(400)).body("O ID passado era inválido.");
        }
    }
    
    
}
