package com.example.agenda_dmos5.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.agenda_dmos5.R;
import com.example.agenda_dmos5.control.UsuarioDAO;
import com.example.agenda_dmos5.model.Usuario;
import com.google.android.material.snackbar.Snackbar;

public class AdicionarUsuarioActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText senha_editText;
    private EditText email_editText;
    private EditText confirma_senha_editText;
    private Button salvar_usuario_button;
    private UsuarioDAO mUsuarioDAO;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adicionar_usuario);

        senha_editText = findViewById(R.id.edittext_senha_usuario);
        confirma_senha_editText = findViewById(R.id.edittext_confirma_senha_usuario);
        email_editText = findViewById(R.id.edittext_email_usuario);
        salvar_usuario_button = findViewById(R.id.button_salvar_usuario);

        mUsuarioDAO = new UsuarioDAO(this);

        salvar_usuario_button.setOnClickListener(this);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }

    @Override
    public void onClick(View v) {
        if(v == salvar_usuario_button) {
            if (senha_editText.getText().toString().equals(confirma_senha_editText.getText().toString())) {
                salvaUsuario();
            } else {
                if(senha_editText.getText().toString().isEmpty() || senha_editText.getText().toString().isEmpty()){

                } else {
                    showSnackbar( "Senha não confirmada");
                }
            }
        }
    }

    private void showSnackbar(String mensagem){
        Snackbar snackbar;
        RelativeLayout relativeLayout = findViewById(R.id.novo_usuario_activity);
        snackbar = Snackbar.make(relativeLayout, mensagem, Snackbar.LENGTH_SHORT);
        snackbar.show();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == android.R.id.home){
            setResult(RESULT_CANCELED);
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    private void salvaUsuario(){
        String email;
        String senha;

        email = email_editText.getText().toString();
        senha = senha_editText.getText().toString();

        if(email.isEmpty() || senha.isEmpty()){
            Toast.makeText(this, R.string.erro_preenchimento, Toast.LENGTH_SHORT);
        }else{
            Usuario novoUsuario = new Usuario(email, senha);
            mUsuarioDAO.add(novoUsuario);
            finalizar(true);
        }
    }

    private void finalizar(boolean sucesso){
        if(sucesso){
            setResult(Activity.RESULT_OK);
        } else {
            setResult(Activity.RESULT_CANCELED);
        }
        finish();
    }
}