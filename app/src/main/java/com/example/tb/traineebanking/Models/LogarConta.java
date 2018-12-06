package com.example.tb.traineebanking.Models;

import com.fasterxml.jackson.annotation.JsonRootName;
import com.google.gson.annotations.SerializedName;


@JsonRootName("LogarConta")
public class LogarConta {

    @SerializedName("Agencia")
    public int agencia;

    @SerializedName("Conta")
    public int conta;

    @SerializedName("Senha")
    public String senha;

}
