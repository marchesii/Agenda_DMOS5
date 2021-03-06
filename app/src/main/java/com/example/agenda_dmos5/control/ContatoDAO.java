package com.example.agenda_dmos5.control;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.agenda_dmos5.model.Contato;

import java.util.ArrayList;
import java.util.List;

public class ContatoDAO {

    private SQLiteDatabase mSqLiteDatabase;
    private SQLiteHelper mHelper;

    public ContatoDAO(Context context){
        mHelper = new SQLiteHelper(context);
    }


    public void add(Contato contato) throws NullPointerException{
        if(contato == null){
            throw new NullPointerException("Contato inválido");
        }
        ContentValues valores = new ContentValues();
        valores.put(SQLiteHelper.COLUMN_NOME, contato.getNome());
        valores.put(SQLiteHelper.COLUMN_TELEFONE_FIXO, contato.getTelefone_fixo());
        valores.put(SQLiteHelper.COLUMN_TELEFONE_CEL, contato.getTelefone_celular());
        valores.put(SQLiteHelper.COLUMN_USUARIO_CONTATO, contato.getUsuario());

        mSqLiteDatabase = mHelper.getWritableDatabase();
        mSqLiteDatabase.insert(SQLiteHelper.TABLE_NAME_CONTATOS, null, valores);
        mSqLiteDatabase.close();
    }

    public List<Contato> all(String usuario){
        List<Contato> mContatoList;
        Contato mContato;
        Cursor mCursor;

        mContatoList = new ArrayList<>();

        String sql= "SELECT " + SQLiteHelper.COLUMN_NOME + ", " + SQLiteHelper.COLUMN_TELEFONE_FIXO + ", " + SQLiteHelper.COLUMN_TELEFONE_CEL + " FROM " + SQLiteHelper.TABLE_NAME_CONTATOS+ " WHERE " + SQLiteHelper.COLUMN_USUARIO_CONTATO + " = ?";

        String argumentos[] = new String[]{
                usuario
        };



        mSqLiteDatabase = mHelper.getReadableDatabase();

        mCursor = mSqLiteDatabase.rawQuery(sql, argumentos);

        while (mCursor.moveToNext()){
            mContato = new Contato(
                    mCursor.getString(0),
                    mCursor.getString(1),
                    mCursor.getString(2)
            );
            mContatoList.add(mContato);
        }


        mCursor.close();
        mSqLiteDatabase.close();
        return mContatoList;
    }


    public List<Contato> all(){
        List<Contato> mContatoList;
        Contato mContato;
        Cursor mCursor;

        mContatoList = new ArrayList<>();

        String colunas[] = new String[]{
                SQLiteHelper.COLUMN_NOME,
                SQLiteHelper.COLUMN_TELEFONE_FIXO,
                SQLiteHelper.COLUMN_TELEFONE_CEL,
                SQLiteHelper.COLUMN_USUARIO_CONTATO

        };

        mSqLiteDatabase = mHelper.getReadableDatabase();

        mCursor = mSqLiteDatabase.query(
          SQLiteHelper.TABLE_NAME_CONTATOS,
                colunas,
                null,
                null,
                null,
                null,
                SQLiteHelper.COLUMN_NOME
        );

        while (mCursor.moveToNext()){
            mContato = new Contato(
                mCursor.getString(0),
                mCursor.getString(1),
                mCursor.getString(2)
            );
            mContatoList.add(mContato);
        }


        mCursor.close();
        mSqLiteDatabase.close();
        return mContatoList;
    }
}
