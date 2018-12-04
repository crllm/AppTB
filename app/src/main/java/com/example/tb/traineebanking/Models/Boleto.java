package com.example.tb.traineebanking.Models;

import java.util.Date;

/**
 * Created by rafa_ on 04/12/2018.
 */

public class Boleto {
    private int idBoleto;
    private int numero;
    private double valor;
    private int status;
    private Date dataBoleto;
    private Date dataPagamento;
    private Emprestimo emprestimo;
    private String descricao;

    public Boleto() {

    }
}
