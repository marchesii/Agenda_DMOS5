package com.example.agenda_dmos5.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.agenda_dmos5.R;
import com.example.agenda_dmos5.control.ContatoDAO;
import com.example.agenda_dmos5.model.Contato;
import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;

public class AdicionarContatoAcitivity extends AppCompatActivity implements View.OnClickListener{

    private EditText nomeEditText;
    private EditText telefone_FixoEditText;
    private EditText telefone_CelularEditText;
    private Button salvarButton;
    private ContatoDAO mContatoDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adicionar_contato_acitivity);

        nomeEditText = findViewById(R.id.edittext_nome);
        telefone_FixoEditText = findViewById(R.id.edittext_telefone_fixo);
        telefone_CelularEditText = findViewById(R.id.edittext_telefone_celular);
        salvarButton = findViewById(R.id.button_salvar);
        mContatoDAO = new ContatoDAO(this);

        salvarButton.setOnClickListener(this);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }


    @Override
    public void onClick(View v) {
        if(v == salvarButton){
            salvaContato();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == android.R.id.home){
            setResult(RESULT_CANCELED);
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    private void salvaContato(){
        String nome;
        String telefone_fixo;
        String telefone_celular;

        nome = nomeEditText.getText().toString();
        telefone_fixo = telefone_FixoEditText.getText().toString();
        telefone_celular = telefone_CelularEditText.getText().toString();

        if(nome.isEmpty() || telefone_fixo.isEmpty() || telefone_celular.isEmpty()){
            Toast.makeText(this, R.string.erro_empty_fields, Toast.LENGTH_SHORT);
        }else{
                Contato novoContato = new Contato(nome, telefone_fixo, telefone_celular);
                mContatoDAO.add(novoContato);
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