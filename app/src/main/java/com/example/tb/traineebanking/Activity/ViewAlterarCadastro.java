package com.example.tb.traineebanking.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.tb.traineebanking.API.API;
import com.example.tb.traineebanking.Models.Conta;
import com.example.tb.traineebanking.Models.LogarConta;
import com.example.tb.traineebanking.R;
import com.example.tb.traineebanking.Utils.ServiceGenerator;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ViewAlterarCadastro extends AppCompatActivity {

    private EditText txtEmail;
    private EditText txtTelefone;
    private EditText txtCelular;
    private EditText txtRua;
    private EditText txtNumero;
    private EditText txtComplemento;
    private EditText txtBairro;
    private EditText txtCep;
    private EditText txtCidade;
    private EditText txtEstado;
    private Button btnAlterar;
    private RelativeLayout pbLoading;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_alterar_cadastro);

        txtEmail = findViewById(R.id.txtEmail);
        txtTelefone = findViewById(R.id.txtTelefone);
        txtCelular = findViewById(R.id.txtCelular);

        txtRua = findViewById(R.id.txtRua);
        txtNumero = findViewById(R.id.txtNumero);
        txtComplemento = findViewById(R.id.txtComplemento);
        txtBairro = findViewById(R.id.txtBairro);
        txtCep = findViewById(R.id.txtCep);
        txtCidade = findViewById(R.id.txtCidade);
        txtEstado = findViewById(R.id.txtEstado);

        btnAlterar = findViewById(R.id.btnAlterar);
        pbLoading = findViewById(R.id.pbLoading);

        popularCampos();

        btnAlterar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pbLoading.setVisibility(ProgressBar.VISIBLE);
                verificarAcesso();
            }
        });
    }

    private void popularCampos() {
        txtEmail.setText(ServiceGenerator.CONTA.cliente.email);
        txtTelefone.setText(ServiceGenerator.CONTA.cliente.telefone);
        txtCelular.setText(ServiceGenerator.CONTA.cliente.celular);
        txtRua.setText(ServiceGenerator.CONTA.cliente.endereco.rua);
        txtNumero.setText(ServiceGenerator.CONTA.cliente.endereco.numero);
        txtComplemento.setText(ServiceGenerator.CONTA.cliente.endereco.complemento);
        txtBairro.setText(ServiceGenerator.CONTA.cliente.endereco.bairro);
        txtCep.setText(ServiceGenerator.CONTA.cliente.endereco.cep);
        txtCidade.setText(ServiceGenerator.CONTA.cliente.endereco.cidade);
        txtEstado.setText(ServiceGenerator.CONTA.cliente.endereco.estado);
    }

    private Conta alterarDados() {
        if ((validarCampos()) == false)
        {
            return null;
        }
        if (ValidarEmail(txtEmail.getText().toString()) == false)
        {
            Toast.makeText(
                    ViewAlterarCadastro.this,
                    "Email inválido",
                    Toast.LENGTH_LONG
            ).show();
            return null;
        }

        Conta contaComNovosDados = new Conta();

        contaComNovosDados = ServiceGenerator.CONTA;
        contaComNovosDados.cliente.email = txtEmail.getText().toString();
        contaComNovosDados.cliente.telefone = txtTelefone.getText().toString();
        contaComNovosDados.cliente.celular = txtCelular.getText().toString();

        contaComNovosDados.cliente.endereco.rua = txtRua.getText().toString();
        contaComNovosDados.cliente.endereco.numero = txtNumero.getText().toString();
        contaComNovosDados.cliente.endereco.complemento = txtComplemento.getText().toString();
        contaComNovosDados.cliente.endereco.bairro = txtBairro.getText().toString();
        contaComNovosDados.cliente.endereco.cep = txtCep.getText().toString();
        contaComNovosDados.cliente.endereco.cidade = txtCidade.getText().toString();
        contaComNovosDados.cliente.endereco.estado = txtEstado.getText().toString();



        return contaComNovosDados;
    }

    public boolean validarCampos()
    {
        boolean aux = true;

        if ((txtRua.getText().toString().equals("")) || (txtBairro.getText().toString().equals("")) || (txtNumero.getText().toString().equals(""))|| (txtComplemento.getText().toString().equals("")) || (txtCidade.getText().toString().equals("")) || (txtEstado.getText().toString().equals("")) || (txtCep.getText().toString().equals("")) || (txtEmail.getText().toString().equals("")) || (txtTelefone.getText().toString().equals("")) || (txtCelular.getText().toString().equals("")))
        {
            Toast.makeText(
                    ViewAlterarCadastro.this,
                    "É necessário preencher todos os campos para salvar",
                    Toast.LENGTH_LONG
            ).show();
            aux = false;
        }

        return aux;
    }

    private boolean ValidarEmail(final String email) {
        if (android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            return true;
        }
        return false;
    }

    public void verificarAcesso() {
        Conta contaAlterada = alterarDados();
        if(contaAlterada != null){
        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ss").create();

        Retrofit retrofit = new Retrofit
                .Builder()
                .baseUrl(getString(R.string.ip))
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        API api = retrofit.create(API.class);
        Call<Conta> call = api.alterarDadosCadastrais(contaAlterada.idConta, contaAlterada);
        call.enqueue(new Callback<Conta>() {

            @Override
            public void onResponse(Call<Conta> call, Response<Conta> response) {
                if (response.body() != null) {
                    ServiceGenerator.CONTA = response.body();
                    Toast.makeText(
                            ViewAlterarCadastro.this,
                            "Dados alterados com sucesso",
                            Toast.LENGTH_LONG
                    ).show();
                    Intent i = new Intent(ViewAlterarCadastro.this, ViewHome.class);
                    startActivity(i);
                    finish();

                } else {
                    pbLoading.setVisibility(ProgressBar.INVISIBLE);
                    Toast.makeText(
                            ViewAlterarCadastro.this,
                            "Não foi possível alterar os dados",
                            Toast.LENGTH_LONG
                    ).show();
                }
            }

            @Override
            public void onFailure(Call<Conta> call, Throwable t) {
                pbLoading.setVisibility(ProgressBar.INVISIBLE);
                Toast.makeText(
                        ViewAlterarCadastro.this,
                        "Erro, tente acessar mais tarde" + t.getMessage(),
                        Toast.LENGTH_LONG
                ).show();
            }
            });
        }
    }
}
