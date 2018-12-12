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



    public Conta() {

    }


    public int getIdConta() {
        return idConta;
    }

    public void setIdConta(int idConta) {
        this.idConta = idConta;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public char getTipo() {
        return tipo;
    }

    public void setTipo(char tipo) {
        this.tipo = tipo;
    }

    public Double getSaldo() {
        return saldo;
    }

    public void setSaldo(Double saldo) {
        this.saldo = saldo;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Agencia getAgencia() {
        return agencia;
    }

    public void setAgencia(Agencia agencia) {
        this.agencia = agencia;
    }

    public Date getDataRegistroConta() {
        return dataRegistroConta;
    }

    public void setDataRegistroConta(Date dataRegistroConta) {
        this.dataRegistroConta = dataRegistroConta;
    }
}
