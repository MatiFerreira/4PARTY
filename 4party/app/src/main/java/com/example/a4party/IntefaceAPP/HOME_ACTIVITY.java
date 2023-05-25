package com.example.a4party.IntefaceAPP;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import androidx.core.view.WindowCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import com.example.a4party.Fragment.OrderFragments;
import com.example.a4party.Fragment.PartySearchFragment;
import com.example.a4party.Fragment.ProfileFragment;
import com.example.a4party.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class HOME_ACTIVITY extends AppCompatActivity {
    BottomNavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        WindowCompat.setDecorFitsSystemWindows(getWindow(), false);
        navigationView = findViewById(R.id.bottomNavigationView);
        getSupportFragmentManager().beginTransaction().add(R.id.frameLayout, new PartySearchFragment()).commit();
        // Obtén el intent y verifica si contiene el extra "fragment"
        Intent intent = getIntent();
        if (intent != null && intent.hasExtra("fragment")) {
            String fragmentName = intent.getStringExtra("fragment");
            if (fragmentName.equals("carrito")) {
                // Reemplaza el fragmento actual con el fragmento "Carrito"
                Fragment carritoFragment = new OrderFragments();
                FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.frameLayout, carritoFragment);
                fragmentTransaction.commit();
            }if (fragmentName.equals("profile")) {
                // Reemplaza el fragmento actual con el fragmento "Carrito"
                Fragment carritoFragment = new ProfileFragment();
                FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.frameLayout, carritoFragment);
                fragmentTransaction.commit();
            }
        }

        navigationView.setOnItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.orderitem:
                    ReemplazadorFragments(new OrderFragments());
                    break;
                case R.id.profileitem:
                    ReemplazadorFragments(new ProfileFragment());
                    break;
                case R.id.partyitem:
                    ReemplazadorFragments(new PartySearchFragment());
                    break;
            }
            return false;
        });
    }

    private void ReemplazadorFragments(Fragment fragmentParams) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frameLayout, fragmentParams);
        fragmentTransaction.commit();
    }
}
