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
    private List<Extrato> mList = new ArrayList<>();
    private RecyclerView mRecycler;
    private ExtratoAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_extrato);

        mRecycler = findViewById(R.id.rvExtrato);

        LinearLayoutManager manager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);

        mRecycler.setLayoutManager(manager);
        mRecycler.setHasFixedSize(true);

        loadData();
    }

    private void loadData() {
        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ss").create();

        Retrofit retrofit = new Retrofit
                .Builder()
                .baseUrl(getString(R.string.ip))
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        API api = retrofit.create(API.class);

        Call<List<Extrato>> call = api.getExtrato(ServiceGenerator.CONTA.idConta);
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
    }
}
