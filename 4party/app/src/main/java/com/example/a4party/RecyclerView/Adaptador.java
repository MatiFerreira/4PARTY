package com.example.a4party.RecyclerView;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.a4party.IntefaceAPP.ShowOffert;
import com.example.a4party.Objetos.Empresario;
import com.example.a4party.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class Adaptador extends RecyclerView.Adapter<Adaptador.AdaptadorViewHolder> {
    ArrayList<Empresario> empresarios;
    Context context;
    Activity activity;

    public Adaptador(ArrayList<Empresario> empresarios, Context context, Activity activity) {
        this.empresarios = empresarios;
        this.context = context;
        this.activity = activity;

    }

    @NonNull
    @Override
    public Adaptador.AdaptadorViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_container, parent, false);
        return new AdaptadorViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull Adaptador.AdaptadorViewHolder holder, int position) {
        //holder.imagen.setImageURI(Uri.parse(empresarios.get(position).getUrlimagen()));
        Picasso.get().load(empresarios.get(position).getUrlimagen()).fit().into(holder.imagen);
        holder.imagen.setOnClickListener(view -> {

            Intent i = new Intent(context, ShowOffert.class);
            i.putExtra("email", empresarios.get(position).getEmail());
            activity.startActivity(i);


        });
    }

    @Override
    public int getItemCount() {
        return empresarios.size();
    }

    public class AdaptadorViewHolder extends RecyclerView.ViewHolder {
        ImageView imagen;

        public AdaptadorViewHolder(@NonNull View itemView) {
            super(itemView);
            imagen = itemView.findViewById(R.id.imagenitemrecycler);

        }
    }

}
