package com.example.a4party.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.a4party.Objetos.Empresario;
import com.example.a4party.R;
import com.example.a4party.RecyclerView.Adaptador;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.List;


public class PartySearchFragment extends Fragment {
    RecyclerView recyclerView;
    FirebaseFirestore db;
    ArrayList<Empresario> empresarioslist;
    Adaptador adaptador;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_party_search, container, false);
        db = FirebaseFirestore.getInstance();
        empresarioslist = new ArrayList<>();
        db.collection("Empresarios").get().addOnSuccessListener(queryDocumentSnapshots -> {
            List<DocumentSnapshot> list = queryDocumentSnapshots.getDocuments();
            for (DocumentSnapshot documentSnapshot : list) {
                Empresario objeto = documentSnapshot.toObject(Empresario.class);
                empresarioslist.add(objeto);
            }
            //ACTUALIZA ADAPTADOR
            adaptador.notifyDataSetChanged();
        });
        adaptador = new Adaptador(empresarioslist, getContext(), getActivity());
        recyclerView = view.findViewById(R.id.recyclerviewparty);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adaptador);
        return view;
    }
}
