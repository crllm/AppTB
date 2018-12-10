package com.example.tb.traineebanking.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.tb.traineebanking.Models.Investimento;
import com.example.tb.traineebanking.R;

import java.util.List;

/**
 * Created by rafa_ on 04/12/2018.
 */

public class ResgateAdapter extends RecyclerView.Adapter<ResgateAdapter.viewHolder> {
    private Context mContext;
    private List<Investimento> mList;


    public ResgateAdapter(Context context) {
        this.mContext = context;
    }

    @NonNull
    @Override
    public ResgateAdapter.viewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View holder = inflater.inflate(R.layout.item_resgate, viewGroup, false);
        ResgateAdapter.viewHolder viewHolder = new ResgateAdapter.viewHolder(holder);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ResgateAdapter.viewHolder viewHolder, int i) {
        viewHolder.lblInvestimento.setText("Poupança");
        viewHolder.lblValor.setText("1000");
        viewHolder.lblJuros.setText("10");
        viewHolder.lblDataAplicacao.setText("30/11/2018 15:30:39");
        viewHolder.lblDataVencimento.setText("30/11/2019 15:30:39");
    }

    @Override
    public int getItemCount() {
        return 10;
    }

    public class viewHolder extends RecyclerView.ViewHolder {
        public TextView lblInvestimento;
        public TextView lblValor;
        public TextView lblJuros;
        public TextView lblDataAplicacao;
        public TextView lblDataVencimento;

        public viewHolder(View itemView) {
            super(itemView);

            lblInvestimento = itemView.findViewById(R.id.lblInvestimento);
            lblValor = itemView.findViewById(R.id.lblValor);
            lblJuros = itemView.findViewById(R.id.lblJuros);
            lblDataAplicacao = itemView.findViewById(R.id.lblDataAplicacao);
            lblDataVencimento = itemView.findViewById(R.id.lblDataVencimento);
        }
    }
}
