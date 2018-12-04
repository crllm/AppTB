package com.example.tb.traineebanking.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.tb.traineebanking.Adapters.AcordoAdapter;
import com.example.tb.traineebanking.Adapters.ResgateAdapter;
import com.example.tb.traineebanking.R;

public class ViewResgatarActivity extends AppCompatActivity {

    private RecyclerView mRecycler;
    private ResgateAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_resgatar);

        mRecycler = findViewById(R.id.rvResgate);

        LinearLayoutManager manager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);

        mRecycler.setLayoutManager(manager);
        mRecycler.setHasFixedSize(true);

        mAdapter = new ResgateAdapter(ViewResgatarActivity.this);
        mRecycler.setAdapter(mAdapter);
    }
}
