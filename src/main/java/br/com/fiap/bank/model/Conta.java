package br.com.fiap.bank.model;

import java.math.BigDecimal;

public class Conta {
    private int numero;
    private int agencia;
    private String nomeTitular;
    private String dataAbertura;
    private BigDecimal saldoInicial;
    private boolean ativa;
    private TipoConta tipo;

    public Conta(int numero, int agencia, String nomeTitular, String dataAbertura, BigDecimal saldoInicial, boolean ativa, TipoConta tipo) {
        this.numero = numero;
        this.agencia = agencia;
        this.nomeTitular = nomeTitular;
        this.dataAbertura = dataAbertura;
        this.saldoInicial = saldoInicial;
        this.ativa = ativa;
        this.tipo = tipo;
    }

    public int getNumero() {
        return numero;
    }

    public int getAgencia() {
        return agencia;
    }

    public String getNomeTitular() {
        return nomeTitular;
    }

    public String getDataAbertura() {
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
