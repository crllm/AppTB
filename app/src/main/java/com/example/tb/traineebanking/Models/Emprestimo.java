package com.example.tb.traineebanking.Models;

import com.fasterxml.jackson.annotation.JsonRootName;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.Date;
@JsonRootName("Emprestimo")
public class Emprestimo implements Serializable {

    @SerializedName("IdEmprestimo")
    private int idEmprestimo;

    @SerializedName("Valor")
    private double valor;

    @SerializedName("Tipo")
    private String tipo;

    @SerializedName("Juros")
    private double juros;

    @SerializedName("DataEmprestimo")
    private Date dataEmprestimo;

    @SerializedName("Conta")
    private Conta conta;

    @SerializedName("Status")
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
