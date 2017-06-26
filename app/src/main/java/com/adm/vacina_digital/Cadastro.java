package com.adm.vacina_digital;

import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.text.TextUtils;
import android.widget.RadioButton;

import com.adm.vacina_digital.Banco;

/**
 * Created by rafael on 25/06/17.
 */

public class Cadastro extends AppCompatActivity implements View.OnClickListener {

    private RadioButton rbPaciente;
    private RadioButton rbMedico;
    private EditText txtUsuario;
    private EditText txtCpf;
    private EditText txtSenha;
    private Button btnCadastro;
    private String tipoUsuario;

    private Banco banco;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cadastro);

        rbPaciente = (RadioButton) findViewById(R.id.rb_paciente);
        rbMedico = (RadioButton) findViewById(R.id.rb_medico);
        txtUsuario = (EditText) findViewById(R.id.txt_usuario);
        txtCpf = (EditText) findViewById(R.id.txt_cpf);
        txtSenha = (EditText) findViewById(R.id.txt_senha);

        btnCadastro = (Button) findViewById(R.id.bt_cadastro);
        btnCadastro.setOnClickListener(this);

        banco = new Banco(this, "", null, 5);
    }

    @Override
    public void onClick(View v) {
        banco.insert_Usuario(tipoUsuario, txtUsuario.getText().toString(), txtCpf.getText().toString(), txtSenha.getText().toString());
        Intent intent = new Intent(Cadastro.this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    public void onRadioButtonClicked(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.rb_paciente:
                if (checked){
                    tipoUsuario = "Paciente";
                }
                    break;
            case R.id.rb_medico:
                if (checked){
                    tipoUsuario = "Medico";
                }
                    break;
        }
    }
}
