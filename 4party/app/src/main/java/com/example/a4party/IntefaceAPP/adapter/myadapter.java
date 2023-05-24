package com.example.a4party.IntefaceAPP.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class myadapter extends RecyclerView.Adapter<myadapter.ViewHolder> {

    private int layout;

    @NonNull
    @Override
    public myadapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(layout, parent, false);
        // ViewHolder viewHolder = new viewHolder(view);
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull myadapter.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(View view) {
            super(view);

        }

        public void bind() {
            //Donde se va a procesar la imagen de carga de la lista de
        }
    }
}
