package br.com.fiap.bank.model;

public enum TipoConta {
    CORRENTE("Corrente"),
    POUPANCA("Poupanca"),
    SALARIO("Salario");

    private String tipoConta;

    TipoConta(String valor) {
        tipoConta = valor;
    }
}
