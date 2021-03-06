package com.example.tb.traineebanking.Activity;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.example.tb.traineebanking.API.API;
import com.example.tb.traineebanking.Adapters.DetalhesAdapter;
import com.example.tb.traineebanking.Adapters.ResgateAdapter;
import com.example.tb.traineebanking.Interface.AdapterPositionOnClickListener;
import com.example.tb.traineebanking.Models.Investimento;
import com.example.tb.traineebanking.R;
import com.example.tb.traineebanking.Utils.ServiceGenerator;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ViewDetalhesActivity extends AppCompatActivity {
    private RecyclerView mRecycler;
    private DetalhesAdapter mAdapter;
    private List<Investimento> mList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_detalhes);

        mRecycler = findViewById(R.id.rvDetalhes);

        LinearLayoutManager manager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        mRecycler.setLayoutManager(manager);
        mRecycler.setHasFixedSize(true);

        getTotosInvestimentos();

    }

    public void getTotosInvestimentos() {
        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ss").create();

        Retrofit retrofit = new Retrofit
                .Builder()
                .baseUrl(getString(R.string.ip))
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        API api = retrofit.create(API.class);
        Call<List<Investimento>> call = api.getTotosInvestimentos(ServiceGenerator.CONTA.idConta);

        call.enqueue(new Callback<List<Investimento>>() {
            @Override
            public void onResponse(Call<List<Investimento>> call, Response<List<Investimento>> response) {
                if (response.body() != null) {
                    mList = response.body();

                    mAdapter = new DetalhesAdapter(ViewDetalhesActivity.this, mList);
                    mRecycler.setAdapter(mAdapter);
                }
            }

            @Override
            public void onFailure(Call<List<Investimento>> call, Throwable t) {
                Toast.makeText(ViewDetalhesActivity.this,
                        "Erro",
                        Toast.LENGTH_LONG
                ).show();
            }
        });
    }

}
