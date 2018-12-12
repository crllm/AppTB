package com.example.tb.traineebanking.Models;

import com.fasterxml.jackson.annotation.JsonRootName;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by rafa_ on 04/12/2018.
 */
@JsonRootName("Investimento")

public class Investimento implements Serializable {

    @SerializedName("IdInvestimento")
    public int idInvestimento;

    @SerializedName("Valor")
    public double valor;

    @SerializedName("DataInvestimento")
    public Date dataInvestimento;

    @SerializedName("Conta")
    public Conta conta;

    @SerializedName("Tipo")
    public String tipo;

    @SerializedName("Juros")
    public double juros;

    @SerializedName("Multa")
    public double multa;

    @SerializedName("DataTermino")
    public Date dataTermino;

    @SerializedName("Status")
    public int status;

    public Investimento() {}

    public int getIdInvestimento() {
        return idInvestimento;
    }

    public Double getValor() {
        return valor;
    }

    public Date getDataInvestimento() {
        return dataInvestimento;
    }

    public Conta getConta() {
        return conta;
    }

    public String getTipo() {
        return tipo;
    }

    public double getJuros() {
        return juros;
    }

    public double getMulta() {
        return multa;
    }

    public Date getDataTermino() {
        return dataTermino;
    }

    public int getStatus() {
        return status;
    }

    public void setIdInvestimento(int idInvestimento) {
        this.idInvestimento = idInvestimento;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public void setDataInvestimento(Date dataInvestimento) {
        this.dataInvestimento = dataInvestimento;
    }

    public void setConta(Conta conta) {
        this.conta = conta;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public void setJuros(double juros) {
        this.juros = juros;
    }

    public void setMulta(double multa) {
        this.multa = multa;
    }

    public void setDataTermino(Date dataTermino) {
        this.dataTermino = dataTermino;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
