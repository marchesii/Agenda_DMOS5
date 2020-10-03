package com.example.agenda_dmos5.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.example.agenda_dmos5.R;
import com.example.agenda_dmos5.constants.Constantes;
import com.example.agenda_dmos5.control.ContatoDAO;
import com.example.agenda_dmos5.model.Contato;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class Agenda_DMOS5 extends AppCompatActivity implements View.OnClickListener{

    public static final int REQUESTCODE_NOVO_CONTATO = 99;

    private ListView contatosListView;
    private TextView semDadosTextView;
    private FloatingActionButton adicionarActionButton;

    private List<Contato> mContatoList;
    private ArrayList<Contato> contatos;
    private ArrayAdapter<Contato> arrayAdapter;
    private ContatoDAO mContatoDAO;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agenda_dmos5);

        contatosListView = findViewById(R.id.listview_contatos);
        semDadosTextView = findViewById(R.id.textview_sem_dados);
        adicionarActionButton = findViewById(R.id.add_contato);
        adicionarActionButton.setOnClickListener(this);

        mContatoDAO = new ContatoDAO(this);
        mContatoList = mContatoDAO.all();

        contatos = preencherDados();
        arrayAdapter = new ItemContatoAdapter(this, contatos);
        contatosListView.setAdapter(arrayAdapter);

        contatosListView.setOnItemClickListener(new AdapterView.OnItemClickListener(){

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int i, long id) {
                Intent detalhes_contato = new Intent(parent.getContext(), Detalhe_Contato.class);
                detalhes_contato.putExtra("contato", contatos.get(i).toString());
                startActivity(detalhes_contato);
            }
        });

        updateUI();
    }



    @Override
    protected void onResume() {
        super.onResume();
        updateUI();
    }

    private void updateUI(){
        if(mContatoList.size() == 0){
            contatosListView.setVisibility(View.GONE);
            semDadosTextView.setVisibility(View.VISIBLE);
        } else {
            contatosListView.setVisibility(View.VISIBLE);
            semDadosTextView.setVisibility(View.GONE);
        }
    }

    private ArrayList<Contato> preencherDados() {
        ArrayList<Contato> dados = new ArrayList<>(mContatoList);
        return dados;
    }

    @Override
    public void onClick(View view) {
        if(view == adicionarActionButton){
            Intent novoContato = new Intent(this, AdicionarContatoAcitivity.class);
            startActivityForResult(novoContato, Constantes.NEW_CONTATO_REQUEST_CODE);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode){
            case Constantes.NEW_CONTATO_REQUEST_CODE:
                if(resultCode == RESULT_OK){
                    updateDataSet();
                }else{
                    if(resultCode == RESULT_CANCELED){
                        Toast.makeText(this, "Nenhum contato adicionado.", Toast.LENGTH_SHORT).show();
                    }
                }

                break;
        }
        updateUI();
    }

    private void updateDataSet(){
        mContatoList.clear();
        mContatoList.addAll(mContatoDAO.all());

        contatos = preencherDados();
        arrayAdapter = new ItemContatoAdapter(this, contatos);
        contatosListView.setAdapter(arrayAdapter);
        arrayAdapter.notifyDataSetChanged();
    }


}