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

    public int getIdEmprestimo() {
        return idEmprestimo;
    }

    public double getValor() {
        return valor;
    }

    public String getTipo() {
        return tipo;
    }

    public double getJuros() {
        return juros;
    }

    public Date getDataEmprestimo() {
        return dataEmprestimo;
    }

    public Conta getConta() {
        return conta;
    }

    public int getStatus() {
        return status;
    }

    public void setIdEmprestimo(int idEmprestimo) {
        this.idEmprestimo = idEmprestimo;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public void setJuros(double juros) {
        this.juros = juros;
    }

    public void setDataEmprestimo(Date dataEmprestimo) {
        this.dataEmprestimo = dataEmprestimo;
    }

    public void setConta(Conta conta) {
        this.conta = conta;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
