package com.example.tb.traineebanking.Activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.tb.traineebanking.API.API;
import com.example.tb.traineebanking.Models.Conta;
import com.example.tb.traineebanking.Models.LogarConta;
import com.example.tb.traineebanking.R;
import com.example.tb.traineebanking.Utils.JsonUtils;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class ViewLogin extends AppCompatActivity {

    private EditText agencia;
    private EditText conta;
    private EditText senha;
    private Button btnLogar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_login);

        agencia = findViewById(R.id.idAgencia);
        conta = findViewById(R.id.idConta);
        senha = findViewById(R.id.idSenha);
        btnLogar = findViewById(R.id.idbtnLogar);

        btnLogar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                verificarAcesso();
            }
        });

    }

    private LogarConta pegarDados() {

        LogarConta logarConta = new LogarConta();
        logarConta.agencia = Integer.parseInt(agencia.getText().toString());
        logarConta.conta = Integer.parseInt(conta.getText().toString());
        logarConta.senha = senha.getText().toString();

        return logarConta;

    }

    public void verificarAcesso() {
        LogarConta logarConta = pegarDados();

        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ss").create();

        Retrofit retrofit = new Retrofit
                .Builder()
                .baseUrl("http://10.0.2.2:49283")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        API api = retrofit.create(API.class);
        Call<Conta> call = api.verificarAcesso(logarConta);
        call.enqueue(new Callback<Conta>() {


            @Override
            public void onResponse(Call<Conta> call, Response<Conta> response) {
                if (response.body() != null) {
                    Toast.makeText(
                            ViewLogin.this,
                            "SUCESSO TIO",
                            Toast.LENGTH_LONG
                    ).show();

                } else {
                    Toast.makeText(
                            ViewLogin.this,
                            "Resposta Vazia",
                            Toast.LENGTH_LONG
                    ).show();
                }
            }

            @Override
            public void onFailure(Call<Conta> call, Throwable t) {
                Toast.makeText(
                        ViewLogin.this,
                        "Deu ruim" + t.getMessage(),
                        Toast.LENGTH_LONG
                ).show();
            }
        });
    }
}
