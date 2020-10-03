package com.example.agenda_dmos5.activities;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.agenda_dmos5.R;
import com.example.agenda_dmos5.model.Contato;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class ItemContatoAdapter extends ArrayAdapter<Contato> {

    private ArrayList<Contato> contatos;
    private Context context;

    public ItemContatoAdapter(@NonNull Context context, ArrayList<Contato> contatos) {
        super(context, android.R.layout.simple_list_item_1, contatos);
        this.context = context;
        this.contatos = contatos;
    }

    @NonNull
    @Override
    public View getView(int i, View view, ViewGroup parent) {
        View v = view;


        if (v == null) {
            v = LayoutInflater.from(context).inflate(R.layout.list_contatos, parent, false);
        }

        Contato contatoAtual = contatos.get(i);


        TextView nome = v.findViewById(R.id.edittext_nome_lista);
        nome.setText(contatoAtual.getNome());

        return v;
    }

}
