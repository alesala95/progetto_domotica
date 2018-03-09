package com.example.lorealerick.smartfridge2.Activity.Main;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.lorealerick.smartfridge2.Activity.Main.Fragments.FragRicette;
import com.example.lorealerick.smartfridge2.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportFragmentManager().beginTransaction().replace(R.id.contenitore,new FragRicette()).commit();
    }
}
