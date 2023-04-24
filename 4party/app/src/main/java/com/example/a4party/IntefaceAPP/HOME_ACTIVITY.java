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
import com.example.a4party.databinding.ActivityHomeBinding;

public class HOME_ACTIVITY extends AppCompatActivity {
    ActivityHomeBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityHomeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        ReemplazadorFragments(new Fragment());
        binding.bottomnavigationview.setOnItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.partyitem:
                    ReemplazadorFragments(new PartySearchFragment());
                    break;
                case R.id.homeitem:
                    ReemplazadorFragments(new ProfileFragment());
                    break;
                case R.id.profileitem:
                    ReemplazadorFragments(new OrderFragments());
                    break;
                default:
                    return false;
            }
            return false;
        });
    }

    private void ReemplazadorFragments(Fragment fragmentParams) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.framelayout, fragmentParams);
        fragmentTransaction.commit();

    }
}
