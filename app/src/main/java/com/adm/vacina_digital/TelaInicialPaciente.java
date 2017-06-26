package com.adm.vacina_digital;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rafael on 25/06/17.
 */

public class TelaInicialPaciente extends AppCompatActivity {

    private Banco banco;
    ListView lista;
    List<String> vacina;
    List<String> idVacina;
    private CPF cpf;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tela_inicial_paciente);

        lista = (ListView) findViewById(R.id.lista);

        banco = new Banco(this, "", null, 5);

        cpf = new CPF(this);

        lista = (ListView) findViewById(R.id.lista);

        vacina = new ArrayList<>();

        Cursor cursor = banco.lista_cartao_vacina(cpf.getusename());

        while (cursor.moveToNext()){
            vacina.add(cursor.getString(1) + " - " + cursor.getString(2));
        }

        ListaAdapter adapter = new ListaAdapter(vacina, this);

        lista.setAdapter(adapter);
    }
}
