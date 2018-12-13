package com.example.tb.traineebanking.Activity;
import com.example.tb.traineebanking.R;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ViewCreditoComGarantia extends AppCompatActivity {

    private Button btnSolicitarGarantia;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_credito_com_garantia);

        btnSolicitarGarantia = findViewById(R.id.btnSolicitarGarantia);

        btnSolicitarGarantia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ViewCreditoComGarantia.this, ViewFinanciamento.class);
                intent.putExtra("garantia", 1);

                startActivity(intent);
            }
        });
    }
}
