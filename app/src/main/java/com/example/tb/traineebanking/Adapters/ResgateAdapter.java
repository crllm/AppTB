package com.example.tb.traineebanking.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.tb.traineebanking.Interface.AdapterPositionOnClickListener;
import com.example.tb.traineebanking.Models.Investimento;
import com.example.tb.traineebanking.R;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by rafa_ on 04/12/2018.
 */

public class ResgateAdapter extends RecyclerView.Adapter<ResgateAdapter.viewHolder> {
    private Context mContext;
    private List<Investimento> mList;
    private AdapterPositionOnClickListener adapterPositionOnClickListener;


    public void setAdapterPositionOnClickListener(AdapterPositionOnClickListener click) {
        adapterPositionOnClickListener = click;

    }

    public ResgateAdapter(Context mContext, List<Investimento> mList) {
        this.mContext = mContext;
        this.mList = mList;
    }

    public Investimento getInvestimento(int position) {
        return mList.get(position);
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
        Investimento investimento = mList.get(i);
        DateFormat df = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");

        String valor = Double.toString(investimento.valor);
        String juros = Double.toString(investimento.juros);
        String dataInvestimento = df.format(investimento.dataInvestimento);
        String dataTermino = df.format(investimento.dataTermino);

        viewHolder.txtInvestimento.setText(investimento.tipo);
        viewHolder.txtValor.setText(valor);
        viewHolder.txtJuros.setText(juros);
        viewHolder.txtDataAplicacao.setText(dataInvestimento);
        viewHolder.txtDataVencimento.setText(dataTermino);
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView txtInvestimento;
        public TextView txtValor;
        public TextView txtJuros;
        public TextView txtDataAplicacao;
        public TextView txtDataVencimento;

        public viewHolder(View itemView) {
            super(itemView);

            txtInvestimento = itemView.findViewById(R.id.txtInvestimento);
            txtValor = itemView.findViewById(R.id.txtValor);
            txtJuros = itemView.findViewById(R.id.txtJuros);
            txtDataAplicacao = itemView.findViewById(R.id.txtDataAplicacao);
            txtDataVencimento = itemView.findViewById(R.id.txtDataVencimento);
        }

        @Override
        public void onClick(View v) {
            if (adapterPositionOnClickListener != null) {
                adapterPositionOnClickListener.setAdapterPositionOnClickListener(v, getPosition());

            }
        }
    }
}
