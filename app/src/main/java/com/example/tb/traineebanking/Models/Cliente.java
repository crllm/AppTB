package com.example.tb.traineebanking.Models;

import com.fasterxml.jackson.annotation.JsonRootName;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by rafa_ on 04/12/2018.
 */
@JsonRootName("Cliente")
public class Cliente implements Serializable {

    @SerializedName("CPF")
    public String cpf;

    @SerializedName("Nome")
    public String nome;

    @SerializedName("RG")
    public String rg;

    @SerializedName("Telefone")
    public String telefone;

    @SerializedName("Celular")
    public String celular;

    @SerializedName("Email")
    public String email;

    @SerializedName("DataCadastroCliente")
    public Date dataCadastroCliente;

    @SerializedName("Endereco")
    public Endereco endereco;


}
