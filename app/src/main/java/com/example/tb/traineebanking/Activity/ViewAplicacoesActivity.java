package com.example.tb.traineebanking.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.tb.traineebanking.R;

public class ViewAplicacoesActivity extends AppCompatActivity {
    private Button btnSolicitar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_aplicacoes);

        btnSolicitar = findViewById(R.id.btnSolicitar);

        btnSolicitar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ViewAplicacoesActivity.this, ViewEfetuarAplicacao.class);
                startActivity(intent);
            }
        });
    }
}
