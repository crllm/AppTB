package com.example.tb.traineebanking.Models;

import java.util.Date;

/**
 * Created by rafa_ on 04/12/2018.
 */

public class Investimento {
    private int idInvestimento;
    private double valor;
    private Date dataInvestimento;
    private Conta conta;
    private String tipo;
    private double juros;
    private double multa;
    private Date dataTermino;
    private int status;

    public Investimento() {

    }
}
