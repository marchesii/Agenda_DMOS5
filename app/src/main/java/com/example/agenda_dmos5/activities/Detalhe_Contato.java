package com.example.agenda_dmos5.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.example.agenda_dmos5.R;

public class Detalhe_Contato extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhe__contato);
        TextView detalhes = findViewById(R.id.edittext_dados);
        Bundle bundle = getIntent().getExtras();
        if(bundle != null){
            detalhes.setText(bundle.get("contato").toString());
        }

    }
}