package br.com.fiap.bank.model;

import lombok.Data;

@Data
public class Pix {
    private Long idContaOrigem;
    private Long idContaDestino;
    private double valorPix;
}
