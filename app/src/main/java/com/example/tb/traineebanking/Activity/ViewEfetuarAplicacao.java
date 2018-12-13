package com.example.tb.traineebanking.Activity;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.tb.traineebanking.API.API;
import com.example.tb.traineebanking.Models.Aplicacao;
import com.example.tb.traineebanking.Models.Conta;
import com.example.tb.traineebanking.Models.Financiamento;
import com.example.tb.traineebanking.R;
import com.example.tb.traineebanking.Utils.ServiceGenerator;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ViewEfetuarAplicacao extends AppCompatActivity {
    private EditText txtValorInvestimento;
    private RadioButton rdbPoupanca;
    private RadioButton rdbPreFixado;
    private Button btnInvestir;
    private RelativeLayout pbLoading;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.actibity_view_efetuar_investimento);

        txtValorInvestimento = findViewById(R.id.txtValorInvestimento);
        rdbPoupanca = findViewById(R.id.rdbPoupanca);
        rdbPreFixado = findViewById(R.id.rdbPreFixado);
        btnInvestir = findViewById(R.id.btnInvestir);
        pbLoading = findViewById(R.id.pbLoading);

        btnInvestir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pbLoading.setVisibility(ProgressBar.VISIBLE);

                gerarInvestimento();
            }
        });
    }

    private Aplicacao buscarAplicacao() {
        Aplicacao aplicacao = new Aplicacao();

        aplicacao.setIdConta(ServiceGenerator.CONTA.idConta);
        aplicacao.setValor(Double.parseDouble(txtValorInvestimento.getText().toString()));

        if(rdbPoupanca.isChecked())
            aplicacao.setTipo(1);
        else
            aplicacao.setTipo(0);

        return aplicacao;
    }

    public void gerarInvestimento() {
        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ss").create();

        Retrofit retrofit = new Retrofit
                .Builder()
                .baseUrl(getString(R.string.ip))
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        API api = retrofit.create(API.class);
        Call<Conta> call = api.gerarInvestimento(buscarAplicacao());
        call.enqueue(new Callback<Conta>() {

            @Override
            public void onResponse(Call<Conta> call, Response<Conta> response) {
                if (response.isSuccessful()) {
                    ServiceGenerator.CONTA = response.body();

                    Toast.makeText(
                            ViewEfetuarAplicacao.this,
                            "investimento realizado com sucesso!",
                            Toast.LENGTH_LONG
                    ).show();
                    Intent i = new Intent(ViewEfetuarAplicacao.this, ViewInvestimentos.class);
                    startActivity(i);
                    finish();

                } else {
                    pbLoading.setVisibility(ProgressBar.INVISIBLE);
                    Toast.makeText(
                            ViewEfetuarAplicacao.this,
                            "Não foi possível realizar o investimento!",
                            Toast.LENGTH_LONG
                    ).show();
                }
            }

            @Override
            public void onFailure(Call<Conta> call, Throwable t) {
                pbLoading.setVisibility(ProgressBar.INVISIBLE);
                Toast.makeText(
                        ViewEfetuarAplicacao.this,
                        "Erro, tente acessar mais tarde!",
                        Toast.LENGTH_LONG
                ).show();
            }
        });
    }
}
