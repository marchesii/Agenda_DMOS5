package com.example.agenda_dmos5.activities;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.agenda_dmos5.R;
import com.example.agenda_dmos5.constants.Constantes;
import com.example.agenda_dmos5.control.ContatoDAO;
import com.example.agenda_dmos5.model.Contato;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class Agenda_DMOS5 extends AppCompatActivity implements View.OnClickListener{

    private RecyclerView contatosRecyclerView;
    private TextView semDadosTextView;
    private FloatingActionButton adicionarActionButton;
    private String user;

    private List<Contato> mContatoList;

    private ItemContatoAdapter mItemContatoAdapter;

    private ContatoDAO mContatoDAO;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agenda_dmos5);

        Bundle bundle = getIntent().getExtras();
        if(bundle != null){
            user = (bundle.get(Constantes.KEY_USER_OBJECT).toString());
        }

        contatosRecyclerView = findViewById(R.id.recyclerview_contatos);
        semDadosTextView = findViewById(R.id.textview_sem_dados);
        adicionarActionButton = findViewById(R.id.add_contato);
        adicionarActionButton.setOnClickListener(this);

        mContatoDAO = new ContatoDAO(this);
        mContatoList = mContatoDAO.all(user);

        //Recycler
        mItemContatoAdapter = new ItemContatoAdapter(mContatoList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        contatosRecyclerView.setLayoutManager(layoutManager);
        contatosRecyclerView.setAdapter(mItemContatoAdapter);


        mItemContatoAdapter.setClickListener(new DetalhesContatoClickListener() {
            @Override
            public void onContatoClick(int position) {
                Intent intent = new Intent (getApplicationContext(), Detalhe_Contato.class);
                intent.putExtra(Constantes.KEY_DETALHES, mContatoList.get(position).toString());
                startActivity(intent);
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
            contatosRecyclerView.setVisibility(View.GONE);
            semDadosTextView.setVisibility(View.VISIBLE);
        } else {
            contatosRecyclerView.setVisibility(View.VISIBLE);
            semDadosTextView.setVisibility(View.GONE);
        }
    }

    @Override
    public void onClick(View view) {
        if(view == adicionarActionButton){
            Intent novoContato = new Intent(this, AdicionarContatoAcitivity.class);
            novoContato.putExtra(Constantes.KEY_USER, user);
            startActivityForResult(novoContato, Constantes.NEW_CONTATO_REQUEST_CODE);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode,@Nullable Intent data) {
        switch (requestCode){
            case Constantes.NEW_CONTATO_REQUEST_CODE:
                if(resultCode == RESULT_OK){
                    updateDataSet();
                    contatosRecyclerView.getAdapter().notifyDataSetChanged();
                }else{
                    if(resultCode == RESULT_CANCELED){
                        Toast.makeText(this, "Nenhum contato adicionado.", Toast.LENGTH_SHORT).show();
                    }
                }

                break;
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    private void updateDataSet(){
        mContatoList.clear();
        mContatoList.addAll(mContatoDAO.all(user));
    }

}