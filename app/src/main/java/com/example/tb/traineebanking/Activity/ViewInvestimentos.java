package com.example.tb.traineebanking.Activity;

import com.example.tb.traineebanking.Models.Investimento;
import com.example.tb.traineebanking.R;
import com.example.tb.traineebanking.Utils.ServiceGenerator;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.NumberFormat;

public class ViewInvestimentos extends AppCompatActivity {
    private TextView saldo;
    private TextView totalInvestido;
    private ImageView btnVisibleSaldo;
    private ImageView btnVisibleInvestimento;
    private ImageView btnResgatar;
    private ImageView btnDetalhes;
    private ImageView btnAplicar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_investimentos);

        saldo = findViewById(R.id.txtSaldo);
        totalInvestido = findViewById(R.id.txtTotalInvestido);
        btnVisibleSaldo = findViewById(R.id.btnVisibleSaldo);
        btnVisibleInvestimento = findViewById(R.id.btnVisibleInvestimento);
        btnResgatar = findViewById(R.id.btnResgatar);
        btnDetalhes = findViewById(R.id.btnDetalhes);
        btnAplicar = findViewById(R.id.btnAplicar);

        btnVisibleSaldo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saldo.setVisibility(v.VISIBLE);
                saldo.setText("R$ " + String.valueOf(ServiceGenerator.CONTA.saldo));

            }
        });

        btnVisibleInvestimento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                totalInvestido.setVisibility(v.VISIBLE);

                totalInvestido.setText("R$" + String.valueOf(ServiceGenerator.INVESTIMENTO.valor));

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
}