package com.example.tb.traineebanking.Models;

import java.util.Date;

/**
 * Created by rafa_ on 04/12/2018.
 */

public class Extrato {
    private Date data;
    private String dado;
    private double valor;

    public Extrato() {

    }

    public Date getData() {
        return data;
    }

    public String getDado() {
        return dado;
    }

    public double getValor() {
        return valor;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public void setDado(String dado) {
        this.dado = dado;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }
}
