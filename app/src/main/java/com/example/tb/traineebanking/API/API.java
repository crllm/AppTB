package com.example.tb.traineebanking.API;

import com.example.tb.traineebanking.Models.Boleto;
import com.example.tb.traineebanking.Models.Conta;
import com.example.tb.traineebanking.Models.Emprestimo;
import com.example.tb.traineebanking.Models.Investimento;
import com.example.tb.traineebanking.Models.LogarConta;


import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface API {

    @POST("/api/Conta")
    Call<Conta>  verificarAcesso(@Body LogarConta logarConta);

    @GET("/emprestimos")
    Call<List<Emprestimo>> getEmprestimos();

    @GET("/investimentos")
    Call<List<Investimento>> getInvestimentos(@Body Investimento investimento);

    @GET("/boletos")
    Call<List<Boleto>> getBoletos();

}
