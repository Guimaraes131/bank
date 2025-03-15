package br.com.fiap.bank.controller;

import br.com.fiap.bank.model.Conta;
import br.com.fiap.bank.repository.ContaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/conta")
public class ContaController {

    @Autowired
    private ContaRepository repository;

    @GetMapping
    public List<Conta> index() {
        return repository.findAll();
    }

    @GetMapping("{id}")
    public Conta getPorId(@PathVariable Long id) {
        return getContaPorId(id);
    }

    @GetMapping("/cpf/{cpf}")
    public Conta getPorCpf(@PathVariable String cpf) {
        return getContaPorCpf(cpf);
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody Conta conta) {
        if (!validarConta(conta)) {
            Map<String, String> erro = new HashMap<>();
            erro.put("mensagem", "Conta inválida. Verifique os dados informados.");
            return ResponseEntity.status(400).body(erro);
        }

        repository.save(conta);
        return ResponseEntity.status(201).body(conta);
    }

    @DeleteMapping("{id}")
    public void destroy(@PathVariable Long id) {
        Optional<Conta> conta = repository.findById(id);

        if (conta.isPresent()) {
            conta.get().setAtiva(false);
            repository.save(conta.get());
        }
    }

    private Conta getContaPorId(Long id) {
        return repository.findById(id)
                .orElseThrow( () -> new ResponseStatusException(HttpStatus.NOT_FOUND) );
    }

    private Conta getContaPorCpf(String cpf) {
        List<Conta> contas = repository.findAll();

        return contas.stream()
                .filter(c -> c.getCpf().equals(cpf))
                .findFirst()
                .orElseThrow( () -> new ResponseStatusException(HttpStatus.NOT_FOUND) );
    }

    private boolean validarConta(Conta conta) {
        String[] tipoContas = {"Salario", "Poupança", "Corrente"};

        if (conta.getNomeTitular().isEmpty() || conta.getCpf().isEmpty()) {
            return false;
        } else if (conta.getDataAbertura().isAfter(LocalDate.now())) {
            return false;
        } else if (conta.getSaldoInicial() < 0) {
            return false;
        } else if (!Arrays.asList(tipoContas).contains(conta.getTipoConta())) {
            return false;
        }

        return true;
    }
}
