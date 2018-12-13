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

import java.util.List;

/**
 * Created by rafa_ on 04/12/2018.
 */

public class DetalhesAdapter extends RecyclerView.Adapter<DetalhesAdapter.viewHolder> {
    private Context mContext;
    private List<Investimento> mList;
    private AdapterPositionOnClickListener adapterPositionOnClickListener;


    public void setAdapterPositionOnClickListener(AdapterPositionOnClickListener click) {
        adapterPositionOnClickListener = click;

    }

    public DetalhesAdapter(Context mContext, List<Investimento> mList) {
        this.mContext = mContext;
        this.mList = mList;
    }

    public void getInvestimento(int position) {
        mList.get(position);
    }


    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View holder = inflater.inflate(R.layout.item_detalhes, viewGroup, false);
        viewHolder ViewHolder = new viewHolder(holder);

        return ViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder viewHolder, int i) {
        Investimento investimento = mList.get(i);

        viewHolder.txtInvestimento.setText(investimento.tipo);
        viewHolder.txtValor.setText("R$" + Double.toString((investimento.getValor())));
        viewHolder.txtJuros.setText(Double.toString((investimento.getJuros())) + "%");
        viewHolder.txtDataAplicacao.setText(investimento.dataInvestimento.toString());
        viewHolder.txtDataVencimento.setText(investimento.dataTermino.toString());
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
