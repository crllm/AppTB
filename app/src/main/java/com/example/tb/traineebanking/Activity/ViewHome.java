package com.example.tb.traineebanking.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.tb.traineebanking.R;
import com.example.tb.traineebanking.Utils.ServiceGenerator;

public class ViewHome extends AppCompatActivity {

    private TextView Nome;
    private TextView Valor;
    private TextView Endereco;
    private TextView Cidade;

    private ImageView AlterarDados;
    private ImageView Pagamentos;
    private ImageView Emprestimos;
    private ImageView Investimentos;
    private ImageView Extrato;
    private ImageView Saldo;
    private int i=0;

    private View.OnClickListener vAlterarCadastro = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent i = new Intent(ViewHome.this, ViewAlterarCadastro.class);
            startActivity(i);

        }
    };

    private View.OnClickListener vPagamento = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent i = new Intent(ViewHome.this, ViewPagamento.class);
            startActivity(i);

        }
    };

    private View.OnClickListener vEmprestimo = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent i = new Intent(ViewHome.this, ViewEmprestimo.class);
            startActivity(i);

        }
    };

    private View.OnClickListener vInvestimentos = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent i = new Intent(ViewHome.this, ViewInvestimentos.class);
            startActivity(i);

        }
    };

    private View.OnClickListener vExtrato = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent i = new Intent(ViewHome.this, ViewExtratoActivity.class);
            startActivity(i);

        }
    };

    private View.OnClickListener vSaldo = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if(i == 0){
                Valor.setText(String.valueOf(("R$ ") + (ServiceGenerator.CONTA.saldo)));
                i=1;}
            else
            if(i == 1){
                Valor.setText(String.valueOf("R$ *****"));
                i=0;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_home);

        Nome = findViewById(R.id.txtNome);
        Valor = findViewById(R.id.txtValor);
        Endereco = findViewById(R.id.txtEndereco);
        Cidade = findViewById(R.id.txtCidade);
        AlterarDados = findViewById(R.id.imgAlterarDados);
        Pagamentos = findViewById(R.id.imgPagamentos);
        Emprestimos = findViewById(R.id.imgEmprestimos);
        Investimentos = findViewById(R.id.imgInvestimentos);
        Extrato = findViewById(R.id.imgExtrato);
        Saldo = findViewById(R.id.imgSaldo);

        String str = ServiceGenerator.CONTA.cliente.nome;
        String[] splited = str.split("\\s+");

        Nome.setText(("Olá, ")+(splited[0])+(" ")+(splited[splited.length-1]));
        Valor.setText(String.valueOf("R$ *****"));
        Endereco.setText((ServiceGenerator.CONTA.agencia.endereco.rua)+(" ,")+(ServiceGenerator.CONTA.agencia.endereco.numero));
        Cidade.setText((ServiceGenerator.CONTA.agencia.endereco.cidade)+(" ,")+(ServiceGenerator.CONTA.agencia.endereco.estado));

        AlterarDados.setOnClickListener(vAlterarCadastro);
        Pagamentos.setOnClickListener(vPagamento);
        Emprestimos.setOnClickListener(vEmprestimo);
        Investimentos.setOnClickListener(vInvestimentos);
        Extrato.setOnClickListener(vExtrato);
        Saldo.setOnClickListener(vSaldo);


    }


}
