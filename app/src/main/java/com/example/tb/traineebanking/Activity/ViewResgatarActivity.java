package com.example.tb.traineebanking.Activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TableLayout;
import android.widget.Toast;

import com.example.tb.traineebanking.API.API;
import com.example.tb.traineebanking.Adapters.AcordoAdapter;
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

public class ViewResgatarActivity extends AppCompatActivity implements AdapterPositionOnClickListener {
    private RecyclerView mRecycler;
    private ResgateAdapter mAdapter;
    private List<Investimento> mList;
    private RelativeLayout pbLoading;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_resgatar);

        mRecycler = findViewById(R.id.rvResgate);

        LinearLayoutManager manager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        mRecycler.setLayoutManager(manager);
        mRecycler.setHasFixedSize(true);

        pbLoading = findViewById(R.id.pbLoading);

        getInvestimentosId();

    }

    public void getInvestimentosId() {
        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ss").create();

        Retrofit retrofit = new Retrofit
                .Builder()
                .baseUrl("http://10.0.2.2:49283")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        API api = retrofit.create(API.class);
        Call<List<Investimento>> call = api.getInvestimentosId(ServiceGenerator.CONTA.idConta);

        call.enqueue(new Callback<List<Investimento>>() {
            @Override
            public void onResponse(Call<List<Investimento>> call, Response<List<Investimento>> response) {
                if (response.body() != null) {
                    mList = response.body();

                    mAdapter = new ResgateAdapter(ViewResgatarActivity.this, mList);
                    mAdapter.setAdapterPositionOnClickListener(ViewResgatarActivity.this);
                    mRecycler.setAdapter(mAdapter);
                }
            }

            @Override
            public void onFailure(Call<List<Investimento>> call, Throwable t) {
                Toast.makeText(ViewResgatarActivity.this,
                        "Erro",
                        Toast.LENGTH_LONG
                ).show();
            }
        });
    }

    @Override
    public void setAdapterPositionOnClickListener(View view, int p) {
        AlertDialog.Builder warning = new AlertDialog.Builder(ViewResgatarActivity.this);
        warning.setTitle("Resgatar");
        warning.setMessage("Deseja resgatar o investimento selecionado?");
        final int position = p;

        warning.setNegativeButton("Não", null);
        AlertDialog.Builder sim = warning.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                pbLoading.setVisibility(ProgressBar.VISIBLE);
                resgatarInvestimentoAPI(mAdapter.getInvestimento(position));
                mAdapter.notifyDataSetChanged();
            }
        });
        warning.show();
    }

    public void resgatarInvestimentoAPI(Investimento investimento) {

        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ss").create();

        Retrofit retrofit = new Retrofit
                .Builder()
                .baseUrl("http://10.0.2.2:49283")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        API api = retrofit.create(API.class);
        Call<Investimento> call = api.resgatarInvestimento(investimento);

        call.enqueue(new Callback<Investimento>() {
            @Override
            public void onResponse(Call<Investimento> call, Response<Investimento> response) {
            }

            @Override
            public void onFailure(Call<Investimento> call, Throwable t) {
                pbLoading.setVisibility(ProgressBar.INVISIBLE);
            }
        });
    }

}
