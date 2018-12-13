package com.example.tb.traineebanking.Models;

import com.fasterxml.jackson.annotation.JsonRootName;
import com.google.gson.annotations.SerializedName;

@JsonRootName("Financiamento")
public class Financiamento {

    @SerializedName("IdConta")
    private int idConta;

    @SerializedName("Tipo")
    private int tipo;

    @SerializedName("Valor")
    private double valor;

    @SerializedName("Parcelas")
    private int parcelas;

    @SerializedName("Pagamento")
    private int pagamento;

    public Financiamento() {}

    public int getIdConta() {
        return idConta;
    }

    public int getTipo() {
        return tipo;
    }

    public double getValor() {
        return valor;
    }

    public int getParcelas() {
        return parcelas;
    }

    public int getPagamento() {
        return pagamento;
    }

    public void setIdConta(int idConta) {
        this.idConta = idConta;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public void setParcelas(int parcelas) {
        this.parcelas = parcelas;
    }

    public void setPagamento(int pagamento) {
        this.pagamento = pagamento;
    }
}
