package com.example.a4party.BBDD;

import android.widget.TextView;
import androidx.annotation.NonNull;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.firebase.firestore.FirebaseFirestore;
import org.jetbrains.annotations.NotNull;

public class GetData implements getDataBBDD {
    private FirebaseFirestore db;

    public GetData() {

    }

    @Override
    public void IntaciaBD() {
        db = FirebaseFirestore.getInstance();
    }

    @Override
    public void setNameEstablecimiento(String email, TextView textView) {
        IntaciaBD();
        db.collection("Empresarios").document(email).get().addOnSuccessListener(documentSnapshot -> {
            if (documentSnapshot.exists()) {
                String nombre = documentSnapshot.getString("nombreEstablecimiento");
                textView.setText("BIENVENIDO ".concat(nombre.toUpperCase()));
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull @NotNull Exception e) {
                textView.setText("BIENVENIDO ANONIMO!");
            }
        });
    }
}
