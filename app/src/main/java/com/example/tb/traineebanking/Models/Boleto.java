package com.example.tb.traineebanking.Models;

import java.util.Date;

/**
 * Created by rafa_ on 04/12/2018.
 */

public class Boleto {
    private int idBoleto;
    private int numero;
    private double valor;
    private int status;
    private Date dataBoleto;
    private Date dataPagamento;
    private Emprestimo emprestimo;
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
