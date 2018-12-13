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
    public viewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View holder = inflater.inflate(R.layout.item_resgate, viewGroup, false);
        viewHolder ViewHolder = new viewHolder(holder);

        return ViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder viewHolder, int i) {
        Investimento investimento = mList.get(i);

        viewHolder.lblInvestimento.setText(investimento.tipo);
        viewHolder.lblValor.setText("R$" + Double.toString((investimento.getValor())));
        viewHolder.lblJuros.setText(Double.toString((investimento.getJuros())) + "%");
        viewHolder.lblDataAplicacao.setText(investimento.dataInvestimento.toString());
        viewHolder.lblDataVencimento.setText(investimento.dataTermino.toString());
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
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

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (adapterPositionOnClickListener != null) {
                adapterPositionOnClickListener.setAdapterPositionOnClickListener(v, getPosition());

            }
        }
    }
}
