package com.example.tb.traineebanking.Activity;
import com.example.tb.traineebanking.R;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class ViewEmprestimo extends AppCompatActivity {

    private ImageView btnAcordo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_emprestimo);

        btnAcordo = findViewById(R.id.btnAcordo);

        btnAcordo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ViewEmprestimo.this, ViewAcordoActivity.class);
                startActivity(intent);
            }
        });
    }
}
