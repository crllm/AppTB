package com.example.tb.traineebanking.Activity;

import com.example.tb.traineebanking.API.API;
import com.example.tb.traineebanking.Adapters.ResgateAdapter;
import com.example.tb.traineebanking.Models.Investimento;
import com.example.tb.traineebanking.R;
import com.example.tb.traineebanking.Utils.ServiceGenerator;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ViewInvestimentos extends AppCompatActivity {
    private TextView saldo;
    private TextView totalInvestido;
    private ImageView btnVisibleSaldo;
    private ImageView btnInvisibleSaldo;
    private ImageView btnVisibleInvestimento;
    private ImageView btnInvisibleInvestimento;
    private ImageView btnResgatar;
    private ImageView btnDetalhes;
    private ImageView btnAplicar;

    private List<Investimento> mList = new ArrayList<>();
    private double totalInv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_investimentos);

        saldo = findViewById(R.id.txtSaldo);
        totalInvestido = findViewById(R.id.txtTotalInvestido);
        btnVisibleSaldo = findViewById(R.id.btnVisibleSaldo);
        btnInvisibleSaldo = findViewById(R.id.btnInvisibleSaldo);
        btnVisibleInvestimento = findViewById(R.id.btnVisibleInvestimento);
        btnInvisibleInvestimento = findViewById(R.id.btnInvisibleInvestimento);
        btnResgatar = findViewById(R.id.btnResgatar);
        btnDetalhes = findViewById(R.id.btnDetalhes);
        btnAplicar = findViewById(R.id.btnAplicar);

        btnVisibleSaldo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    new Thread().sleep(200);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                btnVisibleSaldo.setVisibility(v.INVISIBLE);
                btnInvisibleSaldo.setVisibility(v.VISIBLE);

                saldo.setText("R$ " + String.valueOf(ServiceGenerator.CONTA.saldo));
                saldo.setVisibility(v.VISIBLE);

            }
        });

        btnInvisibleSaldo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnInvisibleSaldo.setVisibility(v.INVISIBLE);
                btnVisibleSaldo.setVisibility(v.VISIBLE);

                saldo.setVisibility(v.INVISIBLE);

            }
        });

        saldo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnInvisibleSaldo.setVisibility(v.INVISIBLE);
                btnVisibleSaldo.setVisibility(v.VISIBLE);

                saldo.setVisibility(v.INVISIBLE);

            }
        });

        btnVisibleInvestimento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InvestimentoAPI();

                try {
                    new Thread().sleep(200);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                btnVisibleInvestimento.setVisibility(v.INVISIBLE);
                btnInvisibleInvestimento.setVisibility(v.VISIBLE);

                totalInvestido.setVisibility(v.VISIBLE);
            }
        });

        btnInvisibleInvestimento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnInvisibleInvestimento.setVisibility(v.INVISIBLE);
                btnVisibleInvestimento.setVisibility(v.VISIBLE);

                totalInvestido.setVisibility(v.INVISIBLE);
                totalInv = 0;
            }
        });

        totalInvestido.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnInvisibleInvestimento.setVisibility(v.INVISIBLE);
                btnVisibleInvestimento.setVisibility(v.VISIBLE);

                totalInvestido.setVisibility(v.INVISIBLE);
                totalInv = 0;
            }
        });

        btnResgatar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ViewInvestimentos.this, ViewResgatarActivity.class);
                startActivity(intent);
            }
        });

        btnDetalhes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ViewInvestimentos.this, ViewDetalhesActivity.class);
                startActivity(intent);
            }
        });

        btnAplicar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ViewInvestimentos.this, ViewAplicacoesActivity.class);
                startActivity(intent);
            }
        });

    }

    public void InvestimentoAPI() {
        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ss").create();

        Retrofit retrofit = new Retrofit
                .Builder()
                .baseUrl("http://10.0.2.2:49283")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        API api = retrofit.create(API.class);
        Call<List<Investimento>> call = api.getInvestimentos(ServiceGenerator.CONTA);

        call.enqueue(new Callback<List<Investimento>>() {
            @Override
            public void onResponse(Call<List<Investimento>> call, Response<List<Investimento>> response) {
                if (response.body() != null) {
                    mList = response.body();

                    for (Investimento item: mList) {
                        totalInv += item.getValor();
                    }

                    totalInvestido.setText("R$ " + String.valueOf(totalInv));
                }
            }

            @Override
            public void onFailure(Call<List<Investimento>> call, Throwable t) {
                Toast.makeText(ViewInvestimentos.this,
                        "Não vai dar não",
                        Toast.LENGTH_LONG
                ).show();
            }
        });
    }
}