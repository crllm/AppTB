package com.example.tb.traineebanking.Models;

import com.fasterxml.jackson.annotation.JsonRootName;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by rafa_ on 04/12/2018.
 */
@JsonRootName("user")
public class Agencia implements Serializable {

    @SerializedName("IdAgencia")
    public int idAgencia;

    @SerializedName("Endereco")
    private Endereco endereco;


}
