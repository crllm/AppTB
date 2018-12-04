package com.example.tb.traineebanking.Models;

import java.util.Date;

/**
 * Created by rafa_ on 04/12/2018.
 */

public class Emprestimo {
    private int idEmprestimo;
    private double valor;
    private String tipo;
    private double juros;
    private Date dataEmprestimo;
    private Conta conta;
    private int status;

    public Emprestimo() {

    }
}
