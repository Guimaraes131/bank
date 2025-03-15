package br.com.fiap.bank.model;

import lombok.Data;

@Data
public class Saque {
    private Long idConta;
    private double valorSaque;
}
