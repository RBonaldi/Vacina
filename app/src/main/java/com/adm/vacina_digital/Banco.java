package com.adm.vacina_digital;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by rafael on 05/06/17.
 */

public class Banco extends SQLiteOpenHelper {
    public Banco(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, "vacina.db", factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE Usuario (id INTEGER PRIMARY KEY AUTOINCREMENT, tipo VARCHAR, usuario TEXT, cpf VARCHAR, senha VARCHAR);");
        sqLiteDatabase.execSQL("CREATE TABLE Vacina (id INTEGER PRIMARY KEY AUTOINCREMENT, nome VARCHAR, descricao TEXT);");
        sqLiteDatabase.execSQL("CREATE TABLE CartaoVacina (id INTEGER PRIMARY KEY AUTOINCREMENT, cpf VARCHAR, idVacina VARCHAR, data VARCHAR);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS Usuario;");
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS Vacina;");
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS CartaoVacina;");

        onCreate(sqLiteDatabase);
    }

    public void insert_Usuario(String tipo, String usuario, String CPF, String Senha){
        ContentValues contentValues = new ContentValues();
        contentValues.put("tipo", tipo);
        contentValues.put("usuario", usuario);
        contentValues.put("cpf", CPF);
        contentValues.put("senha", Senha);
        this.getWritableDatabase().insertOrThrow("Usuario", "", contentValues);
    }

    public boolean validar_Usuario_Paciente(String usuario, String Senha) {
        String countQuery = "SELECT * FROM Usuario where cpf='"+usuario+"' and senha='"+Senha+"' and tipo='Paciente'";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        int cnt = cursor.getCount();
        cursor.close();
        if(cnt > 0)
            return true;
        else
            return false;
    }
    public boolean validar_Usuario_Medico(String usuario, String Senha) {
        String countQuery = "SELECT * FROM Usuario where cpf='"+usuario+"' and senha='"+Senha+"' and tipo='Medico'";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        int cnt = cursor.getCount();
        cursor.close();
        if(cnt > 0)
            return true;
        else
            return false;
    }

    public Cursor lista_cartao_vacina(String CPF){
        Cursor cursor = this.getReadableDatabase().rawQuery("SELECT a.id, b.nome, a.data FROM CartaoVacina a inner join Vacina b on a.idVacina = b.id where a.cpf= '"+CPF+"'", null);
        return cursor;
    }

    public void insert_Vacina(String nome, String descricao){
        ContentValues contentValues = new ContentValues();
        contentValues.put("nome", nome);
        contentValues.put("descricao", descricao);
        this.getWritableDatabase().insertOrThrow("Vacina", "", contentValues);
    }

    public Cursor lista_vacina(){
        Cursor cursor = this.getReadableDatabase().rawQuery("SELECT id, nome, descricao from Vacina", null);
        return cursor;
    }

    public void insert_CartaoVacina(String cpf, String idVacina, String data){
        ContentValues contentValues = new ContentValues();
        contentValues.put("cpf", cpf);
        contentValues.put("idVacina", idVacina);
        contentValues.put("data", data);
        this.getWritableDatabase().insertOrThrow("CartaoVacina", "", contentValues);
    }

}
