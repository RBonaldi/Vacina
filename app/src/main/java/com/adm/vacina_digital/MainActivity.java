package com.adm.vacina_digital;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private EditText txtUsuario;
    private EditText txtSenha;
    private CPF cpf;

    private Banco banco;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        banco = new Banco(this, "", null, 5);

        Button btLogin = (Button)findViewById(R.id.bt_login);
        btLogin.setOnClickListener(this);

        Button btCadastro = (Button)findViewById(R.id.bt_cadastro);
        btCadastro.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.bt_login:

                logar();
                break;

            case R.id.bt_cadastro:

                NovoUsuario();
                break;
        }
    }

    private void logar(){
        EditText txtUsuario = (EditText)findViewById(R.id.txt_usuario);
        String usuario = txtUsuario.getText().toString();
        EditText txtSenha = (EditText)findViewById(R.id.txt_senha);
        String senha = txtSenha.getText().toString();

        if(banco.validar_Usuario_Paciente(usuario, senha)){
            cpf = new CPF(this);
            cpf.setusename(txtUsuario.getText().toString());

            Intent intent = new Intent(MainActivity.this, TelaInicialPaciente.class);
            startActivity(intent);
            finish();
        }
        else if(banco.validar_Usuario_Medico(usuario, senha)){
            cpf = new CPF(this);
            cpf.setusename(txtUsuario.getText().toString());

            Intent intent = new Intent(MainActivity.this, TelaInicialMedico.class);
            startActivity(intent);
            finish();
        }
        else{
            AlertDialog.Builder dlgAlert  = new AlertDialog.Builder(this);
            dlgAlert.setMessage("Usuário ou senha incorreta.");
            dlgAlert.setTitle("Atenção");
            dlgAlert.setPositiveButton("OK", null);
            dlgAlert.setCancelable(true);
            dlgAlert.create().show();
        }
    }

    private void NovoUsuario(){
        Intent intent = new Intent(MainActivity.this, Cadastro.class);
        startActivity(intent);
        finish();
    }










}
