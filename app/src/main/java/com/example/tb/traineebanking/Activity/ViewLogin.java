package com.example.tb.traineebanking.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.tb.traineebanking.API.API;
import com.example.tb.traineebanking.Models.Boleto;
import com.example.tb.traineebanking.Models.Conta;
import com.example.tb.traineebanking.Models.LogarConta;
import com.example.tb.traineebanking.R;
import com.example.tb.traineebanking.Utils.JsonUtils;
import com.example.tb.traineebanking.Utils.ServiceGenerator;
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
    private RelativeLayout pbLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_login);

        agencia = findViewById(R.id.idAgencia);
        conta = findViewById(R.id.idConta);
        senha = findViewById(R.id.idSenha);
        btnLogar = findViewById(R.id.idbtnLogar);
        pbLogin = findViewById(R.id.pbLogin);

        btnLogar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pbLogin.setVisibility(ProgressBar.VISIBLE);

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
                .baseUrl(getString(R.string.ip))
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        API api = retrofit.create(API.class);
        Call<Conta> call = api.verificarAcesso(logarConta);
        call.enqueue(new Callback<Conta>() {

            @Override
            public void onResponse(Call<Conta> call, Response<Conta> response) {
                if (response.body() != null) {
                    ServiceGenerator.CONTA = response.body();
                    Toast.makeText(
                            ViewLogin.this,
                            "Acessado com sucesso",
                            Toast.LENGTH_LONG
                    ).show();
                    Intent i = new Intent(ViewLogin.this, ViewHome.class);
                    startActivity(i);
                    finish();

                } else {
                    pbLogin.setVisibility(ProgressBar.INVISIBLE);
                    Toast.makeText(
                            ViewLogin.this,
                            "Dados estão incorretos",
                            Toast.LENGTH_LONG
                    ).show();
                }
            }

            @Override
            public void onFailure(Call<Conta> call, Throwable t) {
                pbLogin.setVisibility(ProgressBar.INVISIBLE);
                Toast.makeText(
                        ViewLogin.this,
                        "Erro, tente acessar mais tarde",
                        Toast.LENGTH_LONG
                ).show();
            }
        });
    }
}
