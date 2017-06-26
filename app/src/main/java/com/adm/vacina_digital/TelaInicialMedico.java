package com.adm.vacina_digital;

import android.app.AlertDialog;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class TelaInicialMedico extends AppCompatActivity implements View.OnClickListener{

    ListView lista;
    List<String> vacina;
    private CPF cpf;
    EditText txtCPF;

    private Banco banco;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tela_inicial_medico);

        banco = new Banco(this, "", null, 5);

        Button btVacina = (Button)findViewById(R.id.bt_vacina);
        btVacina.setOnClickListener(this);

        Button btMarcacao = (Button)findViewById(R.id.bt_marcacao);
        btMarcacao.setOnClickListener(this);

        Button btPesquisar = (Button)findViewById(R.id.bt_Pesquisar);
        btPesquisar.setOnClickListener(this);

        txtCPF = (EditText)findViewById(R.id.txt_pesquisarCPF);

        cpf = new CPF(this);

        lista = (ListView) findViewById(R.id.lista);

        vacina = new ArrayList<>();

        Cursor cursor = banco.lista_cartao_vacina(cpf.getusename());

        while (cursor.moveToNext()){
            vacina.add(cursor.getString(1) + " - " + cursor.getString(2));
        }

        ListaAdapter adapter = new ListaAdapter(vacina, this);

        lista.setAdapter(adapter);

        txtCPF.setText(cpf.getusename());
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.bt_vacina:
                vacina();
                break;

            case R.id.bt_marcacao:
                marcacao();
                break;

            case R.id.bt_Pesquisar:
                pesquisa();
                break;
        }
    }

    private void vacina(){
            Intent intent = new Intent(TelaInicialMedico.this, CadastroVacina.class);
            startActivity(intent);
    }

    private void marcacao(){
        Intent intent = new Intent(TelaInicialMedico.this, CadastroMarcacao.class);
        startActivity(intent);
    }

    private  void pesquisa(){
        cpf = new CPF(this);
        cpf.setusename(txtCPF.getText().toString());

        Intent intent = new Intent(TelaInicialMedico.this, TelaInicialMedico.class);
        startActivity(intent);
        finish();
    }








}
