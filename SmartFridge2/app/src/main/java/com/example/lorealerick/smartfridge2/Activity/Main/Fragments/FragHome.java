package com.example.lorealerick.smartfridge2.Activity.Main.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.lorealerick.smartfridge2.R;

/**
 * Created by LoreAleRick on 09/03/2018.
 */

public class FragHome extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.frag_ricettario, container, false);

        return view;
    }
}
