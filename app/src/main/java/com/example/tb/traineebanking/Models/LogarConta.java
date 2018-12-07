package com.example.tb.traineebanking.Models;

import com.fasterxml.jackson.annotation.JsonRootName;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;


@JsonRootName("LogarConta")
public class LogarConta implements Serializable {

    @SerializedName("Agencia")
    public int agencia;

    @SerializedName("Conta")
    public int conta;

    @SerializedName("Senha")
    public String senha;

    public LogarConta() {

    }
    public LogarConta(int agencia, int conta, String senha) {
        this.agencia = agencia;
        this.conta = conta;
        this.senha = senha;
    }
}
