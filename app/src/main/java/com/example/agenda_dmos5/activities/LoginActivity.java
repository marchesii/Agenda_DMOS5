package com.example.agenda_dmos5.activities;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RelativeLayout;

import com.example.agenda_dmos5.R;
import com.example.agenda_dmos5.constants.Constantes;
import com.example.agenda_dmos5.control.UsuarioDAO;
import com.example.agenda_dmos5.model.Usuario;
import com.google.android.material.snackbar.Snackbar;

import java.util.List;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText email_editText;
    private EditText senha_editText;
    private Button logar_button;
    private Button registrar_button;
    private Agenda_DMOS5 agenda;
    private UsuarioDAO mUsuarioDAO;
    private Usuario user;
    private List<Usuario> mUsuarioList;
    private CheckBox lembrar_check;
    private SharedPreferences msharedPreferences;
    private SharedPreferences.Editor meditor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        msharedPreferences = this.getPreferences(MODE_PRIVATE);
        meditor = msharedPreferences.edit();


        email_editText = findViewById(R.id.email_login_usuario);
        senha_editText = findViewById(R.id.senha_login_usuario);
        logar_button = findViewById(R.id.logar_button);
        registrar_button = findViewById(R.id.registrar_button);
        lembrar_check = findViewById(R.id.lembrar_check);

        email_editText.setText(msharedPreferences.getString(getString(R.string.pref_login), ""));
        senha_editText.setText(msharedPreferences.getString(getString(R.string.pref_senha), ""));


        logar_button.setOnClickListener(this);
        registrar_button.setOnClickListener(this);
        lembrar_check.setOnClickListener(this);

        mUsuarioDAO = new UsuarioDAO(this);
        mUsuarioList = mUsuarioDAO.all();

    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    @Override
    public void onClick(View view) {
        if(view == logar_button){
            mUsuarioDAO = new UsuarioDAO(this);
            user = mUsuarioDAO.verificaUser(email_editText.getText().toString(), senha_editText.getText().toString());

            if(lembrar_check.isChecked()){
                meditor.putString(getString(R.string.pref_login), email_editText.toString());
                meditor.putString(getString(R.string.pref_senha), senha_editText.toString());
                meditor.commit();
            } else {
                meditor.putString(getString(R.string.pref_login), "");
                meditor.putString(getString(R.string.pref_senha), "");
                meditor.commit();
            }

            if(user != null){
                Intent intent = new Intent (getApplicationContext(), Agenda_DMOS5.class);
                intent.putExtra(Constantes.KEY_USER_OBJECT, user.getEmail());
                startActivity(intent);
            } else {
                showSnackbar("Usuário inválido");
            }

        }
        if(view == registrar_button){
            Intent novoUsuario = new Intent(this, AdicionarUsuarioActivity.class);
            startActivityForResult(novoUsuario, Constantes.NEW_USUARIO_REQUEST_CODE);
        }

    }

    private void showSnackbar(String mensagem){
        Snackbar snackbar;
        RelativeLayout relativeLayout = findViewById(R.id.login_activity);
        snackbar = Snackbar.make(relativeLayout, mensagem, Snackbar.LENGTH_SHORT);
        snackbar.show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode,@Nullable Intent data) {
        switch (requestCode){
            case Constantes.NEW_USUARIO_REQUEST_CODE:
                if(resultCode == RESULT_OK){
                    updateDataSet();
                    showSnackbar("Usuario adicionado.");
                }else{
                    if(resultCode == RESULT_CANCELED){
                        showSnackbar("Nenhum Usuario adicionado.");
                    }
                }

                break;
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    private void updateDataSet() {
        mUsuarioList.clear();
        mUsuarioList.addAll(mUsuarioDAO.all());
    }
}