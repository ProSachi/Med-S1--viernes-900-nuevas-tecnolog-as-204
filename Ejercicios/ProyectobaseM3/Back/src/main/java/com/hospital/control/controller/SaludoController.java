package com.hospital.control.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class SaludoController {

    @GetMapping("/saludos")
    public List<String> obtenerSaludos() {
        return List.of("Hola", "Hello", "Bonjour");
    }

    @GetMapping("/despedidas")
    public List<String> obtenerDespedidas() {
        return List.of("chao", "bye", "aurefoir");
    }

        @GetMapping("/mariana")
    public String saludarMariana() {
        return "Hola Mariana";
    }
        @GetMapping("/Jhosep")
    public String saludarJhosep() {
        return "Hola Jhosep";
    }

}
