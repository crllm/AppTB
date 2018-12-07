package com.example.tb.traineebanking.API;

import com.example.tb.traineebanking.Models.Conta;
import com.example.tb.traineebanking.Models.LogarConta;


import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface API {

    @POST("/api/Conta")
    Call<Conta> verificarAcesso(@Body LogarConta logarConta);

}
