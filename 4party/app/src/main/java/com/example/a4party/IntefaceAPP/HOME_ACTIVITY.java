package com.example.a4party.IntefaceAPP;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
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
        navigationView = findViewById(R.id.bottomNavigationView);
        getSupportFragmentManager().beginTransaction().add(R.id.frameLayout, new PartySearchFragment()).commit();
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
