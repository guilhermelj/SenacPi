/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.guilherme.senacpi.controllers;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author Guilherme Luiz
 */
@RestController
@RequestMapping("/api/upload")
public class FileUploadController {

    @PostMapping
    public Map<String, String> uploadFile(@RequestPart("file") MultipartFile file) {
        Map<String, String> response = new HashMap<>();
        
        if (file.isEmpty()) {
            response.put("message", "Por favor, selecione um arquivo para fazer upload.");
            response.put("status", "failed");
            return response;
        }

        try {
            // Salvar o arquivo no servidor
            String uploadDir = System.getProperty("user.dir");
            String extension = getFileExtension(file);
            
            if(!extension.equals("jpg") && !extension.equals("png")) {
                response.put("message", "Falha: O tipo do arquivo deve ser jpg ou png.");
                response.put("status", "failed");
                return response;
            }
            
            UUID uuid = UUID.randomUUID();
            File destFile = new File(uploadDir + "\\src\\main\\resources\\static\\images\\" + uuid.toString() + "." + extension);
            file.transferTo(destFile);
            
            response.put("message", "Sucesso ao enviar arquivo.");
            response.put("result", uuid.toString() + "." + extension);
            response.put("status", "success");

            return response;
        } catch (IOException e) {
            response.clear();
            
            response.put("message", "Erro ao fazer upload do arquivo: " + e.getMessage());
            response.put("status", "failed");
            return response;
        }
    }
    
    private String getFileExtension(MultipartFile file) {
    if (file != null) {
        String originalFileName = file.getOriginalFilename();
        if (originalFileName != null) {
            int lastIndex = originalFileName.lastIndexOf('.');
            if (lastIndex > 0) {
                String extension = originalFileName.substring(lastIndex + 1);
                return extension;
            }
        }
    }
    return null; // Retorna null se não for possível obter a extensão
}
}