package com.example.tb.traineebanking.Models;

import com.fasterxml.jackson.annotation.JsonRootName;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by rafa_ on 04/12/2018.
 */
@JsonRootName("Conta")
public class Conta implements Serializable {

    @SerializedName("IdConta")
    public int idConta;

    @SerializedName("Senha")
    public String senha;

    @SerializedName("Tipo")
    public char tipo;

    @SerializedName("Saldo")
    public Double saldo;

    @SerializedName("Cliente")
    public Cliente cliente;

    @SerializedName("Agencia")
    public Agencia agencia;

    @SerializedName("DataRegistroConta")
    public Date dataRegistroConta;


}
