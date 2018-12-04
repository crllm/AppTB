package com.example.tb.traineebanking.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.tb.traineebanking.Adapters.ExtratoAdapter;
import com.example.tb.traineebanking.R;

public class ViewExtratoActivity extends AppCompatActivity {

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

        mAdapter = new ExtratoAdapter(ViewExtratoActivity.this);
        mRecycler.setAdapter(mAdapter);
    }
}
