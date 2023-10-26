/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.guilherme.senacpi.repositories;

import com.guilherme.senacpi.models.Receita;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Guilherme Luiz
 */
@Repository
public interface ReceitaRepository extends JpaRepository<Receita, Long> {
    
}
