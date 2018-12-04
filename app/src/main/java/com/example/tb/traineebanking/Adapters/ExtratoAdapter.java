package com.example.tb.traineebanking.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.tb.traineebanking.R;

/**
 * Created by rafa_ on 04/12/2018.
 */

public class ExtratoAdapter extends RecyclerView.Adapter<ExtratoAdapter.viewHolder> {

    private Context mContext;

    public ExtratoAdapter(Context context) {
        this.mContext = context;
    }

    @NonNull
    @Override
    public ExtratoAdapter.viewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View holder = inflater.inflate(R.layout.item_extrato, viewGroup, false);
        ExtratoAdapter.viewHolder viewHolder = new ExtratoAdapter.viewHolder(holder);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ExtratoAdapter.viewHolder viewHolder, int i) {
        viewHolder.lblData.setText("30/11/2018 08:36:22");
        viewHolder.lblHistorico.setText("Empréstimo - Financiamento");
        viewHolder.lblValor.setText("1000");
    }

    @Override
    public int getItemCount() {
        return 10;
    }

    public class viewHolder extends RecyclerView.ViewHolder {
        public TextView lblData;
        public TextView lblHistorico;
        public TextView lblValor;

        public viewHolder(View itemView) {
            super(itemView);

            lblData = itemView.findViewById(R.id.lblData);
            lblHistorico = itemView.findViewById(R.id.lblHistorico);
            lblValor = itemView.findViewById(R.id.lblValor);
        }
    }
}
