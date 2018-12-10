package com.example.tb.traineebanking.Models;

import com.fasterxml.jackson.annotation.JsonRootName;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by rafa_ on 04/12/2018.
 */
@JsonRootName("Endereco")
public class Endereco implements Serializable {

    @SerializedName("IdEndereco")
    public int idEndereco;

    @SerializedName("Rua")
    public String rua;

    @SerializedName("Bairro")
    public String bairro;

    @SerializedName("Numero")
    public String numero;

    @SerializedName("Complemento")
    public String complemento;

    @SerializedName("Cidade")
    public String cidade;

    @SerializedName("Estado")
    public String estado;

    @SerializedName("CEP")
    public String cep;

    @SerializedName("DataRegistroEndereco")
    public Date dataRegistroEndereco;


}
