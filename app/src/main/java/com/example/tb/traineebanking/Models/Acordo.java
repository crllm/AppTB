package com.example.tb.traineebanking.Models;

import com.fasterxml.jackson.annotation.JsonRootName;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

@JsonRootName("Acordo")
public class Acordo implements Serializable {

    @SerializedName("IdEmprestimo")
    private int idEmprestimo;

    @SerializedName("Tipo")
    private String tipo;

    @SerializedName("Valor")
    private double valor;

    @SerializedName("Juros")
    private double juros;

    @SerializedName("Saldo")
    private double saldo;

    public Acordo() {}

    public int getIdEmprestimo() {
        return idEmprestimo;
    }

    public String getTipo() {
        return tipo;
    }

    public double getValor() {
        return valor;
    }

    public double getJuros() {
        return juros;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setIdEmprestimo(int idEmprestimo) {
        this.idEmprestimo = idEmprestimo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public void setJuros(double juros) {
        this.juros = juros;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }
}
