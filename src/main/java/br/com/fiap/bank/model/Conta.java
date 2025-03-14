package br.com.fiap.bank.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Random;

public class Conta {
    private long numero;
    private int agencia;
    private String nomeTitular;
    private LocalDate dataAbertura;
    private BigDecimal saldoInicial;
    private boolean ativa;
    private TipoConta tipo;

    public Conta() {
    }

    public Conta(int agencia, String nomeTitular, LocalDate dataAbertura, BigDecimal saldoInicial, boolean ativa, TipoConta tipo) {
        this.numero = Math.abs( new Random().nextLong() );
        this.agencia = agencia;
        this.nomeTitular = nomeTitular;
        this.dataAbertura = dataAbertura;
        this.saldoInicial = saldoInicial;
        this.ativa = ativa;
        this.tipo = tipo;
    }

    public int getAgencia() {
        return agencia;
    }

    public String getNomeTitular() {
        return nomeTitular;
    }

    public long getNumero() {
        return numero;
    }

    public LocalDate getDataAbertura() {
        return dataAbertura;
    }

    public BigDecimal getSaldoInicial() {
        return saldoInicial;
    }

    public boolean isAtiva() {
        return ativa;
    }

    public TipoConta getTipo() {
        return tipo;
    }
}
