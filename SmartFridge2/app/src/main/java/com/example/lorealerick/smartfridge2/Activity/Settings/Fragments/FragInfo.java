package com.example.lorealerick.smartfridge2.Activity.Settings.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.lorealerick.smartfridge2.R;

/**
 * Created by LoreAleRick on 18/03/2018.
 */

public class FragInfo extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.frag_info, container,false);

        return view;
    }
}
