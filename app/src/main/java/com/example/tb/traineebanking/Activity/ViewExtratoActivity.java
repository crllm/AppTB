package com.example.tb.traineebanking.Activity;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tb.traineebanking.API.API;
import com.example.tb.traineebanking.Adapters.ExtratoAdapter;
import com.example.tb.traineebanking.Models.Boleto;
import com.example.tb.traineebanking.Models.Emprestimo;
import com.example.tb.traineebanking.Models.Extrato;
import com.example.tb.traineebanking.Models.Investimento;
import com.example.tb.traineebanking.R;
import com.example.tb.traineebanking.Utils.JsonUtils;
import com.example.tb.traineebanking.Utils.ServiceGenerator;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ViewExtratoActivity extends AppCompatActivity {
    private boolean entrada = true;
    private boolean saida = true;

    private List<Emprestimo> listEmp = new ArrayList<>();
    private List<Investimento> listInv = new ArrayList<>();
    private List<Boleto> listBol = new ArrayList<>();


    private List<Extrato> mList = new ArrayList<>();
    private RecyclerView mRecycler;
    private ExtratoAdapter mAdapter;

    private RadioGroup rgFiltro;
    private RadioButton rb7Dias;
    private RadioButton rb15Dias;
    private RadioButton rb30Dias;

    private TextView lblTudo;
    private TextView lblEntrada;
    private TextView lblSaida;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_extrato);

        mRecycler = findViewById(R.id.rvExtrato);

        rgFiltro = findViewById(R.id.rgFiltro);
        rb7Dias = findViewById(R.id.rb7Dias);
        rb15Dias = findViewById(R.id.rb15Dias);
        rb30Dias = findViewById(R.id.rb30Dias);

        lblTudo = findViewById(R.id.lblTudo);
        lblEntrada = findViewById(R.id.lblEntrada);
        lblSaida = findViewById(R.id.lblSaida);

        rb30Dias.isChecked();

        LinearLayoutManager manager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);

        mRecycler.setLayoutManager(manager);
        mRecycler.setHasFixedSize(true);

        loadData();

        /*lblTudo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                entrada = true;
                saida = true;

                loadData();
            }
        });

        lblEntrada.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                entrada = true;
                saida = false;

                loadData();
            }
        });

        lblSaida.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                entrada = false;
                saida = true;

                loadData();
            }
        });*/
    }

    private void loadData() {
        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ss").create();

        Retrofit retrofit = new Retrofit
                .Builder()
                .baseUrl("http://10.0.2.2:49283")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        API api = retrofit.create(API.class);

        Call<List<Extrato>> call = api.getExtrato(ServiceGenerator.CONTA);
        call.enqueue(new Callback<List<Extrato>>() {
            @Override
            public void onResponse(Call<List<Extrato>> call, Response<List<Extrato>> response) {
                if (response.isSuccessful()) {

                    mList = response.body();

                    mAdapter = new ExtratoAdapter(ViewExtratoActivity.this, mList);
                    mRecycler.setAdapter(mAdapter);

                } else {
                    Toast.makeText(
                            ViewExtratoActivity.this,
                            "Retornou vazio o extrato",
                            Toast.LENGTH_LONG
                    ).show();
                }
            }

            @Override
            public void onFailure(Call<List<Extrato>> call, Throwable t) {
                Toast.makeText(
                        ViewExtratoActivity.this,
                        "Deu ruim no extrato: " + t.getMessage(),
                        Toast.LENGTH_LONG
                ).show();
            }
        });


        /*if(entrada) {
            Call<List<Emprestimo>> call = api.getEmprestimos(ServiceGenerator.CONTA);
            call.enqueue(new Callback<List<Emprestimo>>() {
                @Override
                public void onResponse(Call<List<Emprestimo>> call, Response<List<Emprestimo>> response) {
                    if(response.isSuccessful()) {
                        listEmp = response.body();
                    } else {
                        Toast.makeText(
                                ViewExtratoActivity.this,
                                "Retornou vazio o emprestimo",
                                Toast.LENGTH_LONG
                        ).show();
                    }
                }

                @Override
                public void onFailure(Call<List<Emprestimo>> call, Throwable t) {
                    Toast.makeText(
                            ViewExtratoActivity.this,
                            "Deu ruim no emprestimo: " + t.getMessage(),
                            Toast.LENGTH_LONG
                    ).show();
                }
            });
        }

        if(saida) {
            Call<List<Investimento>> call = api.getInvestimentos(ServiceGenerator.CONTA);
            call.enqueue(new Callback<List<Investimento>>() {
                @Override
                public void onResponse(Call<List<Investimento>> call, Response<List<Investimento>> response) {
                    if(response.isSuccessful()) {
                        listInv = response.body();
                    } else {
                        Toast.makeText(
                                ViewExtratoActivity.this,
                                "Retornou vazio o investimento",
                                Toast.LENGTH_LONG
                        ).show();
                    }
                }

                @Override
                public void onFailure(Call<List<Investimento>> call, Throwable t) {
                    Toast.makeText(
                            ViewExtratoActivity.this,
                            "Deu ruim no investimento: " + t.getMessage(),
                            Toast.LENGTH_LONG
                    ).show();
                }
            });

            Call<List<Boleto>> callB = api.getBoletos(ServiceGenerator.CONTA);
            callB.enqueue(new Callback<List<Boleto>>() {
                @Override
                public void onResponse(Call<List<Boleto>> call, Response<List<Boleto>> response) {
                    if(response.isSuccessful()) {
                        listBol = response.body();
                    } else {
                        Toast.makeText(
                                ViewExtratoActivity.this,
                                "Retornou vazio o boleto",
                                Toast.LENGTH_LONG
                        ).show();
                    }
                }

                @Override
                public void onFailure(Call<List<Boleto>> call, Throwable t) {
                    Toast.makeText(
                            ViewExtratoActivity.this,
                            "Deu ruim no boleto: " + t.getMessage(),
                            Toast.LENGTH_LONG
                    ).show();
                }
            });
        }

        for (Emprestimo item: listEmp) {
            Extrato extrato = new Extrato();
            extrato.setData(item.getDataEmprestimo());
            extrato.setDado("Empréstimo - " + item.getTipo());
            extrato.setValor(item.getValor());

            mList.add(extrato);
        }

        for (Investimento item: listInv) {
            Extrato extrato = new Extrato();
            extrato.setData(item.getDataInvestimento());
            extrato.setDado("Investimento - " + item.getTipo());
            extrato.setValor(item.getValor());

            mList.add(extrato);
        }

        for (Boleto item: listBol) {
            Extrato extrato = new Extrato();
            extrato.setData(item.getDataBoleto());
            extrato.setDado("Pagamento - " + item.getDescricao());
            extrato.setValor(item.getValor());

            mList.add(extrato);
        }

        ordenarExtrato(mList);

        mAdapter = new ExtratoAdapter(ViewExtratoActivity.this, mList);
        mRecycler.setAdapter(mAdapter);
    }

    private static void ordenarExtrato(List<Extrato> listExt) {
        Collections.sort(listExt, new Comparator<Extrato>() {
            @Override
            public int compare(Extrato extrato1, Extrato extrato2) {
                return extrato1.getData().compareTo(extrato2.getData());
            }
        });
    }*/
    }
}
