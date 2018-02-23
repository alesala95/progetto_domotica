package com.example.itsadmin.smartfridge_fragment.Main.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;

import com.example.itsadmin.smartfridge_fragment.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class ShopFragment extends Fragment {

    WebView webViewShop;


    public ShopFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view=inflater.inflate(R.layout.fragment_shop, container, false);
        webViewShop=(WebView)view.findViewById(R.id.webShop);

        webViewShop.loadUrl("https://www.amazon.it/Amazon-Pantry/b?ie=UTF8&node=10547410031");
        webViewShop.getSettings().setJavaScriptEnabled(true);


        return view;
    }

    @Override
    public void onPause() {
        super.onPause();

        webViewShop.destroy();
    }
}
