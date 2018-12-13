package com.example.tb.traineebanking.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.tb.traineebanking.Interface.AdapterPositionOnClickListener;
import com.example.tb.traineebanking.Models.Acordo;
import com.example.tb.traineebanking.R;

import java.util.List;

public class AcordoAdapter extends RecyclerView.Adapter<AcordoAdapter.viewHolder> {
    private Context mContext;
    private List<Acordo> mList;
    private AdapterPositionOnClickListener adapterPositionOnClickListener;

    public void setAdapterPositionOnClickListener(AdapterPositionOnClickListener click) {
        adapterPositionOnClickListener = click;
    }

    public AcordoAdapter(Context context, List<Acordo> list) {
        this.mContext = context;
        this.mList = list;
    }

    public Acordo getAcordo(int position) {
        return mList.get(position);
    }

    @Override
    public viewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View holder = inflater.inflate(R.layout.item_acordo, viewGroup, false);
        viewHolder viewHolder = new viewHolder(holder);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(viewHolder viewHolder, int i) {
        Acordo acordo = mList.get(i);

        viewHolder.lblTipo.setText(acordo.getTipo());
        viewHolder.lblValor.setText(Double.toString(acordo.getValor()));
        viewHolder.lblJuros.setText(Double.toString(acordo.getJuros()));
        viewHolder.lblValorAberto.setText(Double.toString(acordo.getSaldo()));
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView lblTipo;
        public TextView lblValor;
        public TextView lblJuros;
        public TextView lblValorAberto;

        public viewHolder(View itemView) {
            super(itemView);

            lblTipo = itemView.findViewById(R.id.lblTipo);
            lblValor = itemView.findViewById(R.id.lblValor);
            lblJuros = itemView.findViewById(R.id.lblJuros);
            lblValorAberto = itemView.findViewById(R.id.lblValorAberto);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if(adapterPositionOnClickListener != null) {
                adapterPositionOnClickListener.setAdapterPositionOnClickListener(v, getPosition());
            }
        }
    }
}
