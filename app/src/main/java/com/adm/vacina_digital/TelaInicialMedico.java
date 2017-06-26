package com.adm.vacina_digital;

import android.app.AlertDialog;
import android.content.Intent;
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

        lista = (ListView) findViewById(R.id.lista);

        vacina = new ArrayList<>();
        vacina.add("Brasileiro");
        vacina.add("Libertadores");
        vacina.add("Mineiro");
        vacina.add("Espanhol");
        vacina.add("Paulista");

        ListaAdapter adapter = new ListaAdapter(vacina, this);

        lista.setAdapter(adapter);
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










}
