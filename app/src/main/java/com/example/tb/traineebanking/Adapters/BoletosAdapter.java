package com.example.tb.traineebanking.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.tb.traineebanking.Interface.AdapterPositionOnClickListener;
import com.example.tb.traineebanking.Models.Boleto;
import com.example.tb.traineebanking.R;

import java.util.List;

public class BoletosAdapter extends RecyclerView.Adapter<BoletosAdapter.viewHolder> {

    private Context mContext;
    private List<Boleto> mList;
    private AdapterPositionOnClickListener adapterPositionOnClickListener;

    public void setAdapterPositionOnClickListener(AdapterPositionOnClickListener click) {
        adapterPositionOnClickListener = click;
    }

    public BoletosAdapter(Context context, List<Boleto> list) {
        this.mContext = context;
        this.mList = list;
    }

    public Boleto getBoletos(int position) {
        return mList.get(position);
    }

    @Override
    public viewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View holder = inflater.inflate(R.layout.item_boletos, viewGroup, false);
        viewHolder viewHolder = new viewHolder(holder);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(viewHolder viewHolder, int i) {
        Boleto boleto = mList.get(i);

        viewHolder.lblTipoBoleto.setText(boleto.getEmprestimo().getTipo());
        viewHolder.lblCodBoleto.setText("Código boleto: " + String.valueOf(boleto.getIdBoleto()));
        viewHolder.lblValorParcela.setText("Valor: R$ " + Double.toString(boleto.getValor()));
        viewHolder.lblParcela.setText("Nº da Parcela: " + String.valueOf(boleto.getNumero()));
        viewHolder.lblStatusBoleto.setText("Status: " + String.valueOf(boleto.getStatus()));
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }


    public class viewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView lblTipoBoleto;
        public TextView lblValorParcela;
        public TextView lblParcela;
        public TextView lblStatusBoleto;
        public TextView lblCodBoleto;

        public viewHolder(View itemView) {
            super(itemView);

            lblCodBoleto = itemView.findViewById(R.id.lblCodBoleto);
            lblTipoBoleto = itemView.findViewById(R.id.lblTipoBoleto);
            lblValorParcela = itemView.findViewById(R.id.lblValorParcela);
            lblParcela = itemView.findViewById(R.id.lblParcela);
            lblStatusBoleto = itemView.findViewById(R.id.lblStatusBoleto);

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
