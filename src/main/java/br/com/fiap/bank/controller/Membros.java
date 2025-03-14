package br.com.fiap.bank.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class Membros {

    @GetMapping
    public String index() {
        return """
                GUILHERME GUIMARÃES RM557074 |
                MATHEUS OLIVEIRA DE LUNA RM555547
                """;
    }
}
