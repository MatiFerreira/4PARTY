package com.example.a4party.Fragment;

import android.content.Intent;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;
import com.example.a4party.LogInOutUser.Login_activity;
import com.example.a4party.R;
import com.google.firebase.auth.FirebaseAuth;


public class ProfileFragment extends Fragment {

    Button botoncerrarsesion;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View vista= inflater.inflate(R.layout.fragment_profile, container, false);
        botoncerrarsesion=vista.findViewById(R.id.logoutbutton_profileact);
        // Inflate the layout for this fragment

        botoncerrarsesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                Toast.makeText(getActivity(), "Sesion cerrada", Toast.LENGTH_SHORT).show();
                gologin();
            }
        });
        return vista;

    }
    private void gologin() {
        Intent i = new Intent(getActivity(), Login_activity.class);
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(i);
    }

}
