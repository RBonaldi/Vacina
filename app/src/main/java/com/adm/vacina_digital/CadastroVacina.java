package com.adm.vacina_digital;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by rafael on 25/06/17.
 */

public class CadastroVacina extends AppCompatActivity implements View.OnClickListener {

    private EditText txtNomeVacina;
    private EditText txtDescricao;
    private Button btnCadastro;

    private Banco banco;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cadastro_vacina);

        txtNomeVacina = (EditText) findViewById(R.id.txt_nomeVacina);
        txtDescricao = (EditText) findViewById(R.id.txt_descricao);

        btnCadastro = (Button) findViewById(R.id.bt_cadastro);
        btnCadastro.setOnClickListener(this);

        banco = new Banco(this, "", null, 5);
    }

    @Override
    public void onClick(View v) {
        banco.insert_Vacina(txtNomeVacina.getText().toString(), txtDescricao.getText().toString());
        Intent intent = new Intent(CadastroVacina.this, TelaInicialMedico.class);
        startActivity(intent);
        finish();
    }
}
