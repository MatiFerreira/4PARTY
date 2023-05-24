package com.example.a4party.Fragment;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.a4party.IntefaceAPP.EditProfile;
import com.example.a4party.LogInOutUser.Login_activity;
import com.example.a4party.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

public class ProfileFragment extends Fragment {

    Button botoncerrarsesion;
    FirebaseUser currentUser;
    TextView viewemail;
    ProgressDialog progressDialog;
    LinearLayout lcarrito,lhcompras,ledit;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View vista = inflater.inflate(R.layout.fragment_profile, container, false);
        botoncerrarsesion = vista.findViewById(R.id.logoutbutton_profileact);
        ledit = vista.findViewById(R.id.linearedit);
        lcarrito =vista.findViewById(R.id.linearcarrito);
        lhcompras =vista.findViewById(R.id.linearhcompras);

        // Inicializar ProgressDialog
        progressDialog = new ProgressDialog(getActivity());
        progressDialog.setTitle("Cargando...");
        progressDialog.setCancelable(false);
        progressDialog.show();

        // Obtén la instancia de autenticación de Firebase
        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();

        // Verifica si hay un usuario actualmente autenticado
        FirebaseUser currentUser = firebaseAuth.getCurrentUser();

        if (currentUser != null) {
            // El usuario está autenticado, obtén su email de usuario
            String userEmail = currentUser.getEmail();

            // Obtén una referencia a la colección "usuarios" en Firestore
            CollectionReference usersCollection = FirebaseFirestore.getInstance().collection("Usuarios");
            // Crea una consulta para obtener el documento del usuario actual según su email
            Query query = usersCollection.whereEqualTo("email", userEmail);

            // Realiza la consulta a Firestore
            query.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                @Override
                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                    if (task.isSuccessful()) {
                        // La consulta se completó exitosamente
                        QuerySnapshot querySnapshot = task.getResult();
                        if (querySnapshot != null && !querySnapshot.isEmpty()) {
                            // Obtiene el primer documento (suponiendo que solo hay uno por usuario)
                            DocumentSnapshot document = querySnapshot.getDocuments().get(0);

                            // Obtén los datos del documento
                            String email = document.getString("email");
                            String nombre = document.getString("nombre");
                            String apellidos = document.getString("apellido");
                            String dni = document.getString("dni");

                            // Obtén las referencias a los TextViews en tu diseño de fragmento
                            TextView profilepasswordTextView = vista.findViewById(R.id.profilepasswordlistvalue);
                            TextView profileMailTextView = vista.findViewById(R.id.editprofilemail);
                            TextView profileNameTextView = vista.findViewById(R.id.editprofilename);
                            TextView profileNameTextView2 = vista.findViewById(R.id.profilenamelistvalue);
                            TextView profileDniListValueTextView = vista.findViewById(R.id.profilednilistvalue);
                            TextView profileApellidosListValueTextView = vista.findViewById(R.id.profilelastnamelistvalue);

                            // Establece los valores en los TextViews correspondientes
                            profilepasswordTextView.setText(apellidos);
                            profileMailTextView.setText(email);
                            profileNameTextView.setText(nombre);
                            profileNameTextView2.setText(nombre);
                            profileDniListValueTextView.setText(dni);
                            profileApellidosListValueTextView.setText(apellidos);

                            // Cierra el ProgressDialog
                            progressDialog.dismiss();
                        }
                    } else {
                        // Error al realizar la consulta a Firestore
                        Log.e("ProfileFragment", "Error al obtener los datos del perfil: " + task.getException());
                    }
                }
            });
        }

        botoncerrarsesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                Toast.makeText(getActivity(), "Sesión cerrada", Toast.LENGTH_SHORT).show();
                gologin();
            }
        });

        ledit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goEditprofile();
            }
        });
        lcarrito.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ReemplazadorFragments(new PartySearchFragment());
            }
        });

        return vista;
    }

    private void gologin() {
        Intent i = new Intent(getActivity(), Login_activity.class);
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(i);
    }
    private void goEditprofile() {
        Intent i = new Intent(getActivity(), EditProfile.class);
        //i.putExtra("mail", et_mail.getText().toString());
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(i);
    }

    private void ReemplazadorFragments(Fragment fragmentParams) {
        FragmentManager fragmentManager = getChildFragmentManager();  // Utiliza getChildFragmentManager() en lugar de getSupportFragmentManager()
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frameLayout, fragmentParams);
        fragmentTransaction.addToBackStack(null);  // Agrega este método para poder regresar al fragmento anterior
        fragmentTransaction.commit();
    }
}
