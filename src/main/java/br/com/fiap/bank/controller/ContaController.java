package br.com.fiap.bank.controller;

import br.com.fiap.bank.model.Conta;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/conta")
public class ContaController {

    private List<Conta> repositorio;

    @PostMapping
    public ResponseEntity<Conta> create(@RequestBody Conta conta) {
        repositorio.add(conta);
        return ResponseEntity.status(201).body(conta);
    }
}
