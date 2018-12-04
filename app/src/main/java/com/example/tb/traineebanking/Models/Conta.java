package com.example.tb.traineebanking.Models;

import java.util.Date;

/**
 * Created by rafa_ on 04/12/2018.
 */

public class Conta {
    private int idConta;
    private String senha;
    private char tipo;
    private Double saldo;
    private Cliente cliente;
    private Agencia agencia;
    private Date dataRegistroConta;

    public Conta() {

    }
}
