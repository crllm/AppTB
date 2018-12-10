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


}
