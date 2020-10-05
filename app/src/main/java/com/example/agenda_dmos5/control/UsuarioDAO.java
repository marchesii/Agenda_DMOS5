package com.example.agenda_dmos5.control;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.agenda_dmos5.model.Usuario;

import java.util.ArrayList;
import java.util.List;

public class UsuarioDAO {

    private SQLiteDatabase mSqLiteDatabase;
    private SQLiteHelper mHelper;

    public UsuarioDAO(Context context){
        mHelper = new SQLiteHelper(context);
    }


    public void add(Usuario usuario) throws NullPointerException{
        if(usuario == null){
            throw new NullPointerException("Usuario inválido");
        }
        ContentValues valores = new ContentValues();
        valores.put(SQLiteHelper.COLUMN_EMAIL_USUARIO, usuario.getEmail());
        valores.put(SQLiteHelper.COLUMN_SENHA_USUARIO, usuario.getSenha());

        mSqLiteDatabase = mHelper.getWritableDatabase();
        mSqLiteDatabase.insert(SQLiteHelper.TABLE_NAME_USUARIO, null, valores);
        mSqLiteDatabase.close();
    }

    public Usuario verificaUser(String nome, String senha) {
        Usuario result = null;
        Cursor mCursor;
        if (nome.isEmpty() || senha.isEmpty()) {

        } else {

            String sql = "SELECT " + SQLiteHelper.COLUMN_EMAIL_USUARIO + ", " + SQLiteHelper.COLUMN_SENHA_USUARIO + " FROM " + SQLiteHelper.TABLE_NAME_USUARIO + " WHERE " + SQLiteHelper.COLUMN_EMAIL_USUARIO + " = ? AND " + SQLiteHelper.COLUMN_SENHA_USUARIO + " = ?";

            String argumentos[] = new String[]{
              nome, senha
            };

            mSqLiteDatabase = mHelper.getReadableDatabase();


            mCursor = mSqLiteDatabase.rawQuery(sql, argumentos);

            if (mCursor.moveToNext()) {
                result = new Usuario(
                        mCursor.getString(0),
                        mCursor.getString(1)
                );
            }
            mCursor.close();
            mSqLiteDatabase.close();
        }

            return result;
        }

    public List<Usuario> all(){
        List<Usuario> mUsuarioList;
        Usuario mUsuario;
        Cursor mCursor;

        mUsuarioList = new ArrayList<>();

        String colunas[] = new String[]{
                SQLiteHelper.COLUMN_EMAIL_USUARIO,
                SQLiteHelper.COLUMN_SENHA_USUARIO

        };

        mSqLiteDatabase = mHelper.getReadableDatabase();

        mCursor = mSqLiteDatabase.query(
                SQLiteHelper.TABLE_NAME_USUARIO,
                colunas,
                null,
                null,
                null,
                null,
                SQLiteHelper.COLUMN_EMAIL_USUARIO
        );

        while (mCursor.moveToNext()){
            mUsuario = new Usuario(
                    mCursor.getString(0),
                    mCursor.getString(1)
            );
            mUsuarioList.add(mUsuario);
        }


        mCursor.close();
        mSqLiteDatabase.close();
        return mUsuarioList;
    }
}
