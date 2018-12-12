package com.example.tb.traineebanking.Activity;

import com.example.tb.traineebanking.API.API;
import com.example.tb.traineebanking.Models.Boleto;
import com.example.tb.traineebanking.R;
import com.example.tb.traineebanking.Utils.ServiceGenerator;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import android.app.Service;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.time.LocalDateTime;
import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ViewPagamento extends AppCompatActivity implements View.OnClickListener {

    private EditText txtCodigo;
    private EditText txtDataEmissao;
    private EditText txtValorBoleto;
    private EditText txtDescricao;

    private TextView lblDataEmissao;
    private TextView lblValorBoleto;
    private TextView lblDescricao;
    private TextView lblSaldo;

    private LinearLayout linearBotoes;

    Boleto boleto = new Boleto();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_pagamento);

        txtCodigo = findViewById(R.id.txtCodigo);
        txtDataEmissao = findViewById(R.id.txtDataEmissao);
        txtValorBoleto = findViewById(R.id.txtValorBoleto);
        txtDescricao = findViewById(R.id.txtDescrição);

        linearBotoes = findViewById(R.id.linearBotoes);

        lblDataEmissao = findViewById(R.id.lblDataEmissao);
        lblValorBoleto = findViewById(R.id.lblValorBoleto);
        lblDescricao = findViewById(R.id.lblDescricao);
        lblSaldo = findViewById(R.id.lblSaldo);

        findViewById(R.id.btnPagar).setOnClickListener(this);
        findViewById(R.id.btnPesquisar).setOnClickListener(this);
        findViewById(R.id.btnPagar).setOnClickListener(this);
        findViewById(R.id.btnCancelar).setOnClickListener(this);
        findViewById(R.id.btnExibirSaldo).setOnClickListener(this);

        lblSaldo.setText(String.format(("R$ ") + "%.2f", ServiceGenerator.CONTA.saldo));


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnPesquisar:
                if (isValidoPesquisa()) {
                    buscarBoleto();
                } else {
                    return;
                }
                break;

            case R.id.btnPagar:
                if (isValidoPagar() && isSaldoValido()) {
                    pagarBoleto();
                } else {
                    return;
                }
                break;

            case R.id.btnCancelar:
                esconderCampos();
                break;

            case R.id.btnExibirSaldo:
                exibirSaldo();
                break;
        }
    }


    private void buscarBoleto() {

        int numBoleto = Integer.parseInt(txtCodigo.getText().toString());

        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ss").create();

        Retrofit retrofit = new Retrofit
                .Builder()
                .baseUrl("http://10.0.2.2:49283")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        API api = retrofit.create(API.class);

        Call<Boleto> call = api.getBoleto(numBoleto);
        call.enqueue(new Callback<Boleto>() {
            @Override
            public void onResponse(Call<Boleto> call, Response<Boleto> response) {
                if (response.isSuccessful() && response.body() != null) {
                    boleto = response.body();
                    if (boleto.getStatus() == 1) {
                        Toast.makeText(ViewPagamento.this, "O boleto já está pago", Toast.LENGTH_LONG).show();
                    } else
                        exibirBoleto();

                } else {
                    Toast.makeText(ViewPagamento.this, "Não foi encontrado nenhum boleto", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<Boleto> call, Throwable t) {
                Toast.makeText(ViewPagamento.this, "Erro, tente mais tarde", Toast.LENGTH_LONG).show();
            }
        });
    }


    private void pagarBoleto() {

        boleto.setDescricao(txtDescricao.getText().toString());

        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ss").create();

        Retrofit retrofit = new Retrofit
                .Builder()
                .baseUrl("http://10.0.2.2:49283")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        API api = retrofit.create(API.class);

        Call<Boleto> call = api.pagarBoleto(boleto);
        call.enqueue(new Callback<Boleto>() {
            @Override
            public void onResponse(Call<Boleto> call, Response<Boleto> response) {
                if (response.isSuccessful() && response.body() != null) {
                    Toast.makeText(ViewPagamento.this, "Pagamento realizado com sucesso", Toast.LENGTH_LONG).show();
                    esconderCampos();
                    ServiceGenerator.CONTA.saldo -= boleto.getValor();
                }
            }

            @Override
            public void onFailure(Call<Boleto> call, Throwable t) {
                Toast.makeText(ViewPagamento.this, "Houve um erro, tente mais tarde", Toast.LENGTH_LONG).show();
            }
        });


    }

    private boolean isValidoPagar() {
        String descricao = txtDescricao.getText().toString().trim();

        boolean aux = true;

        if (descricao.isEmpty()) {
            txtDescricao.setError("Preencher o campo descrição");
            txtDescricao.requestFocus();
            aux = false;
        }

        return aux;
    }

    private boolean isValidoPesquisa() {
        boolean aux = true;

        String codBoleto = txtCodigo.getText().toString().trim();

        if (codBoleto.isEmpty()) {
            txtCodigo.setError("Informe o código do boleto");
            txtCodigo.requestFocus();
            aux = false;
        }

        return aux;
    }

    private boolean isSaldoValido() {
        boolean aux = true;

        Double saldo = ServiceGenerator.CONTA.saldo;
        Double valorBoleto = boleto.getValor();

        if (saldo < valorBoleto) {
            Toast.makeText(ViewPagamento.this, "Saldo insuficiente", Toast.LENGTH_LONG).show();
            aux = false;
        }

        return aux;
    }

    public void exibirBoleto() {
        txtDataEmissao.setVisibility(View.VISIBLE);
        txtValorBoleto.setVisibility(View.VISIBLE);
        txtDescricao.setVisibility(View.VISIBLE);
        lblDataEmissao.setVisibility(View.VISIBLE);
        lblValorBoleto.setVisibility(View.VISIBLE);
        lblDescricao.setVisibility(View.VISIBLE);
        linearBotoes.setVisibility(View.VISIBLE);

        Date dataEmissao = boleto.getDataBoleto();
        Double valorBoleto = boleto.getValor();

        txtDataEmissao.setText(dataEmissao.toString());
        txtValorBoleto.setText("R$ " + valorBoleto.toString());

    }

    public void limparCampos() {
        txtCodigo.getText().clear();
        txtDescricao.getText().clear();
        txtDataEmissao.getText().clear();
        txtValorBoleto.getText().clear();
        txtDescricao.getText().clear();
    }

    public void esconderCampos() {
        txtDataEmissao.setVisibility(View.INVISIBLE);
        txtValorBoleto.setVisibility(View.INVISIBLE);
        txtDescricao.setVisibility(View.INVISIBLE);

        lblDataEmissao.setVisibility(View.INVISIBLE);
        lblValorBoleto.setVisibility(View.INVISIBLE);
        lblDescricao.setVisibility(View.INVISIBLE);
        linearBotoes.setVisibility(View.INVISIBLE);

        limparCampos();
    }

    public void exibirSaldo() {
        if (lblSaldo.getVisibility() == View.VISIBLE) {
            lblSaldo.setVisibility(View.INVISIBLE);
        } else {
            lblSaldo.setVisibility(View.VISIBLE);
        }

    }

    /*private void userSignup() {
            editNome.setError("Preencher o campo nome");
            editNome.requestFocus();
            return;
        } else if (email.isEmpty()) {
            editEmail.setError("Preencher o campo email");
            editEmail.requestFocus();
            return;
        } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            editEmail.setError("Email inválido");
            editEmail.requestFocus();
            return;
        } else if (senha.isEmpty()) {
            editSenha.setError("Preencher o campo senha");
            editSenha.requestFocus();
            return;
        } else if (senha.length() < 6) {
            editSenha.setError("Informar uma senha com pelo menos 6 caracteres");
            editSenha.requestFocus();
            return;
        } else {
            cadastrar();
        }
    }*/
}
