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

public class AcordoAdapter extends RecyclerView.Adapter<AcordoAdapter.viewHolder> {
    private Context mContext;

    public AcordoAdapter(Context context) {
        this.mContext = context;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View holder = inflater.inflate(R.layout.item_acordo, viewGroup, false);
        viewHolder viewHolder = new viewHolder(holder);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder viewHolder, int i) {
        viewHolder.lblTipo.setText("Emprestimo");
        viewHolder.lblValor.setText("1000");
        viewHolder.lblJuros.setText("10");
        viewHolder.lblValorAberto.setText("10000");
    }

    @Override
    public int getItemCount() {
        return 10;
    }

    public class viewHolder extends RecyclerView.ViewHolder {
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
        }
    }
}
