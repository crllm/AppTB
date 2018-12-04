package com.example.tb.traineebanking.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.tb.traineebanking.Adapters.AcordoAdapter;
import com.example.tb.traineebanking.R;

public class ViewAcordoActivity extends AppCompatActivity {

    private RecyclerView mRecycler;
    private AcordoAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_acordo);

        mRecycler = findViewById(R.id.rvAcordo);

        LinearLayoutManager manager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);

        mRecycler.setLayoutManager(manager);
        mRecycler.setHasFixedSize(true);

        mAdapter = new AcordoAdapter(ViewAcordoActivity.this);
        mRecycler.setAdapter(mAdapter);
    }
}
