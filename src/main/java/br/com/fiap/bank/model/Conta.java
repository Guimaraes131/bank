package br.com.fiap.bank.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

import java.time.LocalDate;

@Data
@Entity
public class Conta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long numero;
    private int agencia;
    private String nomeTitular;
    private String cpf;
    private LocalDate dataAbertura;
    private double saldoInicial;
    private boolean ativa;
    private String tipoConta;

    public void depositar(double valor) {
        this.saldoInicial += valor;
    }

    public void sacar(double valor) {
        this.saldoInicial -= valor;
    }
}
