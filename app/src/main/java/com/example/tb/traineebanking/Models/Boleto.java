package com.example.tb.traineebanking.Models;

import com.fasterxml.jackson.annotation.JsonRootName;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.Date;


@JsonRootName("Boleto")
public class Boleto implements Serializable {

    @SerializedName("idBoleto")
    public int idBoleto;

    @SerializedName("Numero")
    public int numero;

    @SerializedName("Valor")
    public double valor;

    @SerializedName("Status")
    public int status;

    @SerializedName("DataBoleto")
    public Date dataBoleto;

    @SerializedName("DataPagamento")
    public Date dataPagamento;

    @SerializedName("Emprestimo_idEmprestimo")
    public Emprestimo emprestimo;

    @SerializedName("Descricao")
    public String descricao;
}
