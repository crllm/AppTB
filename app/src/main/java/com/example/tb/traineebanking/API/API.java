package com.example.tb.traineebanking.API;

import com.example.tb.traineebanking.Models.Acordo;
import com.example.tb.traineebanking.Models.Boleto;
import com.example.tb.traineebanking.Models.Conta;
import com.example.tb.traineebanking.Models.Emprestimo;
import com.example.tb.traineebanking.Models.Extrato;
import com.example.tb.traineebanking.Models.Financiamento;
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

    @POST("api/extrato")
    Call<List<Extrato>> getExtrato(@Body Conta conta);

    @POST("api/emprestimo")
    Call<List<Emprestimo>> getEmprestimos(@Body Conta conta);

    @POST("api/investimento")
    Call<List<Investimento>> getInvestimentos(@Body Conta conta);

    @GET("api/investimento//{id}")
    Call<List<Investimento>> getInvestimentosId(@Path("id") int id);

    @POST("api/boleto")
    Call<List<Boleto>> getBoletos(@Body Conta conta);

    @PUT("/api/Boleto")
    Call<Boleto> pagarBoleto(@Body Boleto boleto);

    @PUT("/api/Conta")
    Call<Conta> alterarConta(@Body Conta conta);

    @GET("api/extrato/{id}")
    Call<List<Extrato>> getExtrato(@Path("id") int id);

    @POST("api/emprestimo")
    Call<Emprestimo> gerarEmprestimo(@Body Financiamento financiamento);

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
