package com.example.tb.traineebanking.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tb.traineebanking.API.API;
import com.example.tb.traineebanking.Adapters.AcordoAdapter;
import com.example.tb.traineebanking.Adapters.ExtratoAdapter;
import com.example.tb.traineebanking.Interface.AdapterPositionOnClickListener;
import com.example.tb.traineebanking.Models.Acordo;
import com.example.tb.traineebanking.Models.Emprestimo;
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

public class ViewAcordoActivity extends AppCompatActivity implements AdapterPositionOnClickListener {

    private List<Acordo> mList = new ArrayList<>();
    private RecyclerView mRecycler;
    private AcordoAdapter mAdapter;

    private Acordo mAcordo;

    private LinearLayout acordo;
    private LinearLayout solicitarAcordo;

    private TextView lblTipoEmprestimo;
    private TextView lblDesconto;
    private TextView lblValorAcordo;

    private Button btnSolicitarAcordo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_acordo);

        acordo = findViewById(R.id.acordo);
        solicitarAcordo = findViewById(R.id.solicitarAcordo);

        lblTipoEmprestimo = findViewById(R.id.lblTipoEmprestimo);
        lblDesconto = findViewById(R.id.lblDesconto);
        lblValorAcordo = findViewById(R.id.lblValorAcordo);

        btnSolicitarAcordo = findViewById(R.id.btnSolicitarAcordo);

        mRecycler = findViewById(R.id.rvAcordo);

        LinearLayoutManager manager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);

        mRecycler.setLayoutManager(manager);
        mRecycler.setHasFixedSize(true);

        loadData();

        btnSolicitarAcordo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                solicitaAcordo();
            }
        });
    }

    private void loadData() {
        Retrofit retrofit = new Retrofit
                .Builder()
                .baseUrl("http://10.0.2.2:49283")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        API api = retrofit.create(API.class);

        Call<List<Acordo>> call = api.getAcordos(ServiceGenerator.CONTA.idConta);
        call.enqueue(new Callback<List<Acordo>>() {
            @Override
            public void onResponse(Call<List<Acordo>> call, Response<List<Acordo>> response) {
                if (response.isSuccessful()) {

                    mList = response.body();

                    mAdapter = new AcordoAdapter(ViewAcordoActivity.this, mList);
                    mAdapter.setAdapterPositionOnClickListener(ViewAcordoActivity.this);
                    mRecycler.setAdapter(mAdapter);

                } else {
                    Toast.makeText(
                            ViewAcordoActivity.this,
                            "Retornou vazio",
                            Toast.LENGTH_LONG
                    ).show();
                }
            }

            @Override
            public void onFailure(Call<List<Acordo>> call, Throwable t) {
                Toast.makeText(
                        ViewAcordoActivity.this,
                        "Deu ruim: " + t.getMessage(),
                        Toast.LENGTH_LONG
                ).show();
            }
        });
    }

    public void mostrarAcordo() {

        double juros = ((mAcordo.getJuros() / 5) * 2) / 100;
        double valorAcordo = (mAcordo.getSaldo() - (mAcordo.getSaldo() * juros));

        lblTipoEmprestimo.setText(mAcordo.getTipo());
        lblDesconto.setText(Double.toString(juros) + "%");
        lblValorAcordo.setText("R$ " + Double.toString(valorAcordo));
    }

    public void solicitaAcordo() {
        Retrofit retrofit = new Retrofit
                .Builder()
                .baseUrl(getString(R.string.ip))
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        API api = retrofit.create(API.class);

        Call<Emprestimo> call = api.gerarAcordo(mAcordo);
        call.enqueue(new Callback<Emprestimo>() {
            @Override
            public void onResponse(Call<Emprestimo> call, Response<Emprestimo> response) {
                if (response.isSuccessful()) {

                    Toast.makeText(
                            ViewAcordoActivity.this,
                            "Acordo realizado com sucesso!",
                            Toast.LENGTH_LONG
                    ).show();

                } else {
                    Toast.makeText(
                            ViewAcordoActivity.this,
                            "Retornou vazio",
                            Toast.LENGTH_LONG
                    ).show();
                }
            }

            @Override
            public void onFailure(Call<Emprestimo> call, Throwable t) {
                Toast.makeText(
                        ViewAcordoActivity.this,
                        "Deu ruim: " + t.getMessage(),
                        Toast.LENGTH_LONG
                ).show();
            }
        });

        Intent intent = new Intent(ViewAcordoActivity.this, ViewEmprestimo.class);
        startActivity(intent);
    }

    @Override
    public void setAdapterPositionOnClickListener(View view, int position) {
        acordo.setVisibility(view.INVISIBLE);
        solicitarAcordo.setVisibility(view.VISIBLE);

        mAcordo = mAdapter.getAcordo(position);

        mostrarAcordo();
    }
}
