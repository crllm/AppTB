package com.example.tb.traineebanking.Activity;

import android.app.Service;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.example.tb.traineebanking.API.API;
import com.example.tb.traineebanking.Adapters.BoletosAdapter;
import com.example.tb.traineebanking.Adapters.ExtratoAdapter;
import com.example.tb.traineebanking.Interface.AdapterPositionOnClickListener;
import com.example.tb.traineebanking.Models.Boleto;
import com.example.tb.traineebanking.Models.Extrato;
import com.example.tb.traineebanking.R;
import com.example.tb.traineebanking.Utils.ServiceGenerator;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ViewBoletos extends AppCompatActivity {

    private List<Boleto> mListBoleto = new ArrayList<>();
    private RecyclerView mRecyclerBoleto;
    private BoletosAdapter mBoletoAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_boletos);

        mRecyclerBoleto = findViewById(R.id.rvBoletos);

        LinearLayoutManager manager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);

        mRecyclerBoleto.setLayoutManager(manager);
        mRecyclerBoleto.setHasFixedSize(true);

        buscarBoletos();
    }

    private void buscarBoletos() {
        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ss").create();

        Retrofit retrofit = new Retrofit
                .Builder()
                .baseUrl(getString(R.string.ip))
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        API api = retrofit.create(API.class);
        Call<List<Boleto>> call = api.getListBoletos(ServiceGenerator.CONTA.idConta);
        call.enqueue(new Callback<List<Boleto>>() {
            @Override
            public void onResponse(Call<List<Boleto>> call, Response<List<Boleto>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    mListBoleto = response.body();
                    mBoletoAdapter = new BoletosAdapter(ViewBoletos.this, mListBoleto);
                    mRecyclerBoleto.setAdapter(mBoletoAdapter);
                }else {
                    Toast.makeText(
                            ViewBoletos.this,
                            "Não há boletos associados a esta conta",
                            Toast.LENGTH_LONG
                    ).show();
                }
            }

            @Override
            public void onFailure(Call<List<Boleto>> call, Throwable t) {
                Toast.makeText(
                        ViewBoletos.this,
                        "Erro, tente mais tarde\n" + t.getMessage(),
                        Toast.LENGTH_LONG
                ).show();
            }
        });
    }
}
