package com.example.tb.traineebanking.Models;

import com.fasterxml.jackson.annotation.JsonRootName;
import com.google.gson.annotations.SerializedName;

/**
 * Created by rafa_ on 13/12/2018.
 */

@JsonRootName("Aplicacao")
public class Aplicacao {

    @SerializedName("IdConta")
    private int idConta;

    @SerializedName("Valor")
    private double valor;

    @SerializedName("Tipo")
    private int tipo;
    
    public Aplicacao() {}

    public int getIdConta() {
        return idConta;
    }

    public void setIdConta(int idConta) {
        this.idConta = idConta;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }
}
