package br.com.fiap.bank.controller;

import br.com.fiap.bank.model.Conta;
import br.com.fiap.bank.model.Deposito;
import br.com.fiap.bank.model.Pix;
import br.com.fiap.bank.model.Saque;
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

    @PostMapping("/deposito")
    public ResponseEntity<?> realizarDeposito(@RequestBody Deposito deposito) {
        Conta conta = getContaPorId(deposito.getIdConta());

        if (verificarDeposito(deposito.getValorDeposito(), conta)) {
            conta.depositar(deposito.getValorDeposito());
            repository.save(conta);
            return ResponseEntity.status(201).body(conta);
        }

        return ResponseEntity.status(400).body("mensagem: valor do depósito inválido ou conta inativa");
    }

    @PostMapping("/saque")
    public ResponseEntity<?> realizarSaque(@RequestBody Saque saque) {
        Conta conta = getContaPorId(saque.getIdConta());

        if (verificarSaque(saque.getValorSaque(), conta)) {
            conta.sacar(saque.getValorSaque());
            repository.save(conta);
            return ResponseEntity.status(201).body(conta);
        }

        return ResponseEntity.status(400).body("mensagem: valor do saque inválido ou conta inativa");
    }

    @PostMapping("/pix")
    public ResponseEntity<?> realizarPix(@RequestBody Pix pix) {
        Conta contaOrigem = getContaPorId(pix.getIdContaOrigem());
        Conta contaDestino = getContaPorId(pix.getIdContaDestino());

        if (verificarPix(pix.getValorPix(), contaOrigem, contaDestino)) {
            contaOrigem.sacar(pix.getValorPix());
            contaDestino.depositar(pix.getValorPix());

            repository.save(contaOrigem);
            repository.save(contaDestino);

            return ResponseEntity.status(201).body(contaOrigem);
        }

        return ResponseEntity.status(400).body("mensagem: valor inválido ou conta inativa");
    }


    @DeleteMapping("{id}")
    public void destroy(@PathVariable Long id) {
        Conta conta = getContaPorId(id);

        conta.setAtiva(false);
        repository.save(conta);
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

    private boolean verificarDeposito(double valor, Conta conta) {
        if (valor <= 0 || !conta.isAtiva()) {
            return false;
        }

        return true;
    }

    private boolean verificarSaque(double valor, Conta conta) {
        if (valor <= 0 || valor > conta.getSaldoInicial()) {
            return false;
        } else if (!conta.isAtiva()) {
            return false;
        }

        return true;
    }

    private boolean verificarPix(double valor, Conta contaOrigem, Conta contaDestino) {
        if (valor <= 0 || valor > contaOrigem.getSaldoInicial()) {
            return false;
        } else if (!contaOrigem.isAtiva() || !contaDestino.isAtiva()) {
            return false;
        }

        return true;
    }
}
