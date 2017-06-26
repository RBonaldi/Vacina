package com.adm.vacina_digital;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rafael on 25/06/17.
 */

public class CadastroMarcacao extends AppCompatActivity implements View.OnClickListener {

    private EditText txtCpf;
    private EditText txtData;
    private Spinner spVacina;
    private Button btnCadastro;
    List<String> vacina;

    private Banco banco;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cadastro_marcacao);

        txtCpf = (EditText) findViewById(R.id.txt_cpf);
        txtData = (EditText) findViewById(R.id.txt_data);
        spVacina = (Spinner) findViewById(R.id.sp_vacina);

        btnCadastro = (Button) findViewById(R.id.bt_cadastro);
        btnCadastro.setOnClickListener(this);

        banco = new Banco(this, "", null, 5);

        Cursor cursor = banco.lista_vacina();

        vacina = new ArrayList<>();

        while (cursor.moveToNext()){
            vacina.add(cursor.getString(1));
        }

        ListaAdapter adapter = new ListaAdapter(vacina, this);

        spVacina.setAdapter(adapter);
    }

    @Override
    public void onClick(View v) {

        Spinner spinner = (Spinner)findViewById(R.id.sp_vacina);
        String valspinner = spinner.getSelectedItem().toString();

        banco.insert_CartaoVacina(txtCpf.getText().toString(), valspinner, txtData.getText().toString());
        Intent intent = new Intent(CadastroMarcacao.this, TelaInicialMedico.class);
        startActivity(intent);
        finish();
    }
}
