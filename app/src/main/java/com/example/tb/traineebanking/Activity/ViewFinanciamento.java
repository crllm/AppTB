package com.example.tb.traineebanking.Activity;
import com.example.tb.traineebanking.API.API;
import com.example.tb.traineebanking.Models.Conta;
import com.example.tb.traineebanking.Models.Emprestimo;
import com.example.tb.traineebanking.Models.Financiamento;
import com.example.tb.traineebanking.Models.LogarConta;
import com.example.tb.traineebanking.R;
import com.example.tb.traineebanking.Utils.ServiceGenerator;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ViewFinanciamento extends AppCompatActivity {

    private int garantia;
    private int parcelas;
    private int pagamento;

    private TextView lblFinanciamento;
    private EditText txtValor;
    private RadioButton rb6x;
    private RadioButton rb12x;
    private RadioButton rbBoleto;
    private RadioButton rbDebito;
    private Button btnSolicitarEmprestimo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_financiamento);

        lblFinanciamento = findViewById(R.id.lblFinanciamento);
        txtValor = findViewById(R.id.txtValor);
        rb6x = findViewById(R.id.rb6x);
        rb12x = findViewById(R.id.rb12x);
        rbBoleto = findViewById(R.id.rbBoleto);
        rbDebito = findViewById(R.id.rbDebito);
        btnSolicitarEmprestimo = findViewById(R.id.btnSolicitarEmprestimo);

        if(getIntent().hasExtra("garantia")){
            garantia = (int) getIntent().getSerializableExtra("garantia");
        }

        if(garantia == 1) {
            lblFinanciamento.setText("Crédito com garantia de investimento");
        }

        btnSolicitarEmprestimo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gerarEmprestimo();
            }
        });
    }

    private Financiamento buscarFinanciamento() {
        Financiamento financiamento = new Financiamento();

        financiamento.setIdConta(ServiceGenerator.CONTA.idConta);
        financiamento.setValor(Double.parseDouble(txtValor.getText().toString()));

        if(garantia == 1)
            financiamento.setTipo(1);
        else
            financiamento.setTipo(0);

        if(rb6x.isChecked())
            financiamento.setParcelas(1);
        else
            financiamento.setParcelas(0);

        if(rbBoleto.isChecked())
            financiamento.setPagamento(0);
        else
            financiamento.setPagamento(1);

        return financiamento;
    }

    public void gerarEmprestimo() {
        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ss").create();

        Retrofit retrofit = new Retrofit
                .Builder()
                .baseUrl("http://10.0.2.2:49283")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        API api = retrofit.create(API.class);
        Call<Emprestimo> call = api.gerarEmprestimo(buscarFinanciamento());
        call.enqueue(new Callback<Emprestimo>() {

            @Override
            public void onResponse(Call<Emprestimo> call, Response<Emprestimo> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(
                            ViewFinanciamento.this,
                            "Emprestimo realizado com sucesso!",
                            Toast.LENGTH_LONG
                    ).show();
                    Intent i = new Intent(ViewFinanciamento.this, ViewEmprestimo.class);
                    startActivity(i);
                    finish();

                } else {
                    Toast.makeText(
                            ViewFinanciamento.this,
                            "Não foi possível realizar o empréstimo!",
                            Toast.LENGTH_LONG
                    ).show();
                }
            }

            @Override
            public void onFailure(Call<Emprestimo> call, Throwable t) {
                Toast.makeText(
                        ViewFinanciamento.this,
                        "Erro, tente acessar mais tarde" + t.getMessage(),
                        Toast.LENGTH_LONG
                ).show();
            }
        });
    }
}
