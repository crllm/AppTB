package com.example.tb.traineebanking.Activity;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;

import com.example.tb.traineebanking.R;

public class ViewEfetuarAplicacao extends AppCompatActivity {
    private EditText txtValorInvestimento;
    private RadioButton rdbPoupanca;
    private RadioButton rdbPreFixado;
    private Button btnInvestir;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.actibity_view_efetuar_investimento);

        txtValorInvestimento = findViewById(R.id.txtValorInvestimento);
        rdbPoupanca = findViewById(R.id.rdbPoupanca);
        rdbPreFixado = findViewById(R.id.rdbPreFixado);
        btnInvestir = findViewById(R.id.btnInvestir);


    }
}
