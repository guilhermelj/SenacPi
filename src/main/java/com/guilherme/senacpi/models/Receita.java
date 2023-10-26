/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.guilherme.senacpi.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

/**
 *
 * @author Guilherme Luiz
 */
@Entity
public class Receita {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String receita;
    private int quantiaPessoas;
    private String imagem;
    
    public void setId(Long id){
        this.id = id;
    }
    
    public void setReceita(String receita){
        this.receita = receita;
    }
    
    public void setNome(String nome){
        this.nome = nome;
    }
    
    public void setQuantiaPessoas(int quantia){
        this.quantiaPessoas = quantia;
    }
    
    public void setImagem(String imagem){
        this.imagem = imagem;
    }
    
    public String getImagem(){
        return imagem;
    }
    
    public Long getId(){
        return id;
    }
    
    public String getReceita(){
        return receita;
    }
    
    public int getQuantiaPessoas(){
        return quantiaPessoas;
    }
    
    public String getNome(){
        return nome;
    }
}
