package com.example.tb.traineebanking.API;

import com.example.tb.traineebanking.Models.*;


import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface API {

    @POST("/api/conta")
    Call<Conta>  verificarAcesso(@Body LogarConta logarConta);

    @GET("api/extrato/{id}")
    Call<List<Extrato>> getExtrato(@Path("id") int id);

    @POST("api/emprestimo")
    Call<Emprestimo> gerarEmprestimo(@Body Financiamento financiamento);

    @POST("api/investimento")
    Call<List<Investimento>> getInvestimentos(@Body Conta conta);

    @GET("api/investimento//{id}")
    Call<List<Investimento>> getInvestimentosId(@Path("id") int id);

    @GET("api/listartodosinvestimentos//{id}")
    Call<List<Investimento>> getTotosInvestimentos(@Path("id") int id);

    @PUT("/api/investimento")
    Call<Investimento> resgatarInvestimento(@Body Investimento investimento);

    @GET("api/boleto//{id}")
    Call<Boleto> getBoleto(@Path("id") int id);

    @GET("api/acordo/{id}")
    Call<List<Acordo>> getAcordos(@Path("id") int id);

    @PUT("api/emprestimo")
    Call<Emprestimo> gerarAcordo(@Body Acordo acordo);

    @PUT("/api/conta/{id}")
    Call<Conta>  alterarDadosCadastrais(@Path("id") int id, @Body Conta conta);

}