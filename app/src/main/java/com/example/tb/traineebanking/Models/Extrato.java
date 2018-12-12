package com.example.tb.traineebanking.Models;

import com.fasterxml.jackson.annotation.JsonRootName;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.Date;

@JsonRootName("Extrato")
public class Extrato implements Serializable{

    @SerializedName("Data")
    private Date data;

    @SerializedName("Dado")
    private String dado;

    @SerializedName("Valor")
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
