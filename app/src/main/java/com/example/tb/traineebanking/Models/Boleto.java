package com.example.tb.traineebanking.Models;

import com.fasterxml.jackson.annotation.JsonRootName;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.Date;
@JsonRootName("Boleto")
public class Boleto implements Serializable {

    @SerializedName("IdBoleto")
    private int idBoleto;

    @SerializedName("Numero")
    private int numero;

    @SerializedName("Valor")
    private double valor;

    @SerializedName("Status")
    private int status;

    @SerializedName("DataBoleto")
    private Date dataBoleto;

    @SerializedName("DataPagamento")
    private Date dataPagamento;

    @SerializedName("Emprestimo")
    private Emprestimo emprestimo;

    @SerializedName("Descricao")
    private String descricao;

    public Boleto() {

    }

    public int getIdBoleto() {
        return idBoleto;
    }

    public int getNumero() {
        return numero;
    }

    public double getValor() {
        return valor;
    }

    public int getStatus() {
        return status;
    }

    public Date getDataBoleto() {
        return dataBoleto;
    }

    public Date getDataPagamento() {
        return dataPagamento;
    }

    public Emprestimo getEmprestimo() {
        return emprestimo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setIdBoleto(int idBoleto) {
        this.idBoleto = idBoleto;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public void setDataBoleto(Date dataBoleto) {
        this.dataBoleto = dataBoleto;
    }

    public void setDataPagamento(Date dataPagamento) {
        this.dataPagamento = dataPagamento;
    }

    public void setEmprestimo(Emprestimo emprestimo) {
        this.emprestimo = emprestimo;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}