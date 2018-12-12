package com.example.tb.traineebanking.API;

import com.example.tb.traineebanking.Models.Acordo;
import com.example.tb.traineebanking.Models.Boleto;
import com.example.tb.traineebanking.Models.Conta;
import com.example.tb.traineebanking.Models.Emprestimo;
import com.example.tb.traineebanking.Models.Extrato;
import com.example.tb.traineebanking.Models.Investimento;
import com.example.tb.traineebanking.Models.LogarConta;


import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface API {

    @POST("/api/conta")
    Call<Conta>  verificarAcesso(@Body LogarConta logarConta);

    @GET("api/extrato/{id}")
    Call<List<Extrato>> getExtrato(@Path("id") int id);

    @POST("api/emprestimo")
    Call<List<Emprestimo>> getEmprestimos(@Body Conta conta);

    @PUT("api/emprestimo")
    Call<Emprestimo> gerarAcordo(@Body Acordo acordo);

    @POST("api/investimento")
    Call<List<Investimento>> getInvestimentos(@Body Conta conta);

    @POST("api/boleto")
    Call<List<Boleto>> getBoletos(@Body Conta conta);

    @GET("api/acordo/{id}")
    Call<List<Acordo>> getAcordos(@Path("id") int id);

}
