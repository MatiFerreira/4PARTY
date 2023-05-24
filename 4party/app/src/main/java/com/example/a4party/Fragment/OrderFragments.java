package com.example.a4party.Fragment;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.a4party.R;


public class OrderFragments extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        //esto esta asi hasta que metan el fragment de order que no se pq no esta
        //return inflater.inflate(R.layout.fragment_order_fragments, container, false);
        return inflater.inflate(R.layout.fragment_party_search, container, false);
    }
}
