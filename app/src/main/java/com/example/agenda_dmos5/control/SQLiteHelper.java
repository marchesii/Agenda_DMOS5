package com.example.agenda_dmos5.control;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class SQLiteHelper extends SQLiteOpenHelper {

    //constantes do bd
    public static final int DATABASE_VERSION = 2;
    public static final String DATABASE_NAME = "meus_contatos.db";

    //Constantes da tabela Contato
    public static final String TABLE_NAME_CONTATOS = "contatos";
    public static final String COLUMN_NOME = "nome";
    public static final String COLUMN_TELEFONE_FIXO = "telefone_fixo";
    public static final String COLUMN_TELEFONE_CEL = "telefone_celular";
    public static final String COLUMN_USUARIO_CONTATO = "usuario";

    //Constantes da tabela Usuário
    public static final String TABLE_NAME_USUARIO = "usuarios";
    public static final String COLUMN_EMAIL_USUARIO = "email";
    public static final String COLUMN_SENHA_USUARIO = "senha";


    public SQLiteHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.setVersion(1);
        String sql;

        sql = "CREATE TABLE " + TABLE_NAME_CONTATOS + "(";
        sql += COLUMN_NOME + " TEXT NOT NULL, ";
        sql += COLUMN_TELEFONE_FIXO + " TEXT NOT NULL, ";
        sql += COLUMN_TELEFONE_CEL + " TEXT NOT NULL);";

        sqLiteDatabase.execSQL(sql);


    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {

        if(oldVersion == 1) {
            String sql;
            sql = "AlTER TABLE " + TABLE_NAME_CONTATOS + " ADD COLUMN " + COLUMN_USUARIO_CONTATO + " TEXT";
            sqLiteDatabase.execSQL(sql);

            sql = "CREATE TABLE " + TABLE_NAME_USUARIO + "(";
            sql += COLUMN_EMAIL_USUARIO + " TEXT NOT NULL, ";
            sql += COLUMN_SENHA_USUARIO + " TEXT NOT NULL, ";
            sql += "PRIMARY KEY (" + COLUMN_EMAIL_USUARIO + ") );";
            sqLiteDatabase.execSQL(sql);

        }

    }

}
