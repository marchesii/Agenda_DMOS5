package com.example.agenda_dmos5.activities;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.agenda_dmos5.R;
import com.example.agenda_dmos5.model.Contato;

import java.util.List;

public class ItemContatoAdapter extends RecyclerView.Adapter<ItemContatoAdapter.ViewHolder> {

    private List<Contato> contatos;
    private static DetalhesContatoClickListener clickListener;

    public ItemContatoAdapter(@NonNull List<Contato> contatos) {
        this.contatos = contatos;
    }

    public void setClickListener(DetalhesContatoClickListener clickListener){
        ItemContatoAdapter.clickListener = clickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder (@NonNull ViewGroup parent, int viewType){
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_contatos, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int i) {
        holder.nomeContatoTextView.setText(contatos.get(i).getNome());
    }

    @Override
    public int getItemCount() {
        return contatos.size();

    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        public TextView nomeContatoTextView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            nomeContatoTextView = itemView.findViewById(R.id.textview_nome_contato);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if(clickListener != null){
                clickListener.onContatoClick(getAdapterPosition());
            }

        }
    }

}
