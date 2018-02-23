package com.example.itsadmin.smartfridge_fragment.Main;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.internal.BottomNavigationItemView;
import android.support.design.internal.BottomNavigationMenuView;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextSwitcher;
import android.widget.TextView;

import com.example.itsadmin.smartfridge_fragment.Main.Fragments.CreateRecipeFragment;
import com.example.itsadmin.smartfridge_fragment.Main.Fragments.FrigoFragment;
import com.example.itsadmin.smartfridge_fragment.Main.Fragments.HomeFragment;
import com.example.itsadmin.smartfridge_fragment.R;
import com.example.itsadmin.smartfridge_fragment.Main.Fragments.RecipeFragment;
import com.example.itsadmin.smartfridge_fragment.Setting.SettingActivity;
import com.example.itsadmin.smartfridge_fragment.Main.Fragments.ShopFragment;

import java.lang.reflect.Field;

public class MainActivity extends AppCompatActivity {

    Toolbar toolbar;
    BottomNavigationView navigation;
    Fragment frag [];


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        frag = new Fragment[3];
        setUpViews();

        inizializzaFragment(0);
        cambiaFragment(0);
    }

    private void inizializzaFragment (int i){

        switch (i){
            case 0:
                frag [0] = new HomeFragment();
                cambiaFragment(0);
                break;

            case 1:
                frag [1] = new FrigoFragment();
                cambiaFragment(1);
                break;

            case 2:
                frag [2] = new RecipeFragment();
                cambiaFragment(2);
                break;
        }
    }

    private void cambiaFragment (int nFrag){

        if(frag[nFrag]!=null) {
            if(nFrag!=0){

                FragmentTransaction fragmentTransactionH = getSupportFragmentManager().beginTransaction();
                fragmentTransactionH.add(R.id.fram, new HomeFragment(),"home");
                fragmentTransactionH.replace(R.id.fram, frag[nFrag]);
<<<<<<< HEAD
                fragmentTransactionH.addToBackStack("home");
=======
>>>>>>> 9dc77ffeaf83125af027669fb3ed3e5b1a3841e4
                fragmentTransactionH.commit();
            }else{
                FragmentTransaction fragmentTransactionH = getSupportFragmentManager().beginTransaction();
                fragmentTransactionH.replace(R.id.fram, frag[nFrag]);
                fragmentTransactionH.commit();
            }

        }else{

            inizializzaFragment(nFrag);
        }

    }

    private void setUpViews (){

        setContentView(R.layout.activity_main);

        // toolbar
        toolbar = findViewById(R.id.barra);
        setSupportActionBar(toolbar);
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {

                Intent intent=new Intent(getApplicationContext(),SettingActivity.class);
                startActivity(intent);

                return false;
            }
        });

        // bottom navigation
        navigation = findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {

            @Override
            public boolean onNavigationItemSelected(MenuItem item) {

                switch (item.getItemId()) {
                    case R.id.navigation_home:

                        cambiaFragment(0);
                        return true;
                    case R.id.navigation_frigo:

                        cambiaFragment(1);
                        return true;
                    case R.id.navigation_recipe:

                        cambiaFragment(2);
                        return true;

                    case R.id.navigation_shop:

                        return true;
                }

                return false;
            }
        });

        removeShiftMode(navigation);
    }

    //metodo per la rimozione dell'animazione del menu
    @SuppressLint("RestrictedApi")
    static void removeShiftMode(BottomNavigationView view) {
        BottomNavigationMenuView menuView = (BottomNavigationMenuView) view.getChildAt(0);
        try {
            Field shiftingMode = menuView.getClass().getDeclaredField("mShiftingMode");
            shiftingMode.setAccessible(true);
            shiftingMode.setBoolean(menuView, false);
            shiftingMode.setAccessible(false);
            for (int i = 0; i < menuView.getChildCount(); i++) {
                BottomNavigationItemView item = (BottomNavigationItemView) menuView.getChildAt(i);
                item.setShiftingMode(false);
                // set once again checked value, so view will be updated
                item.setChecked(item.getItemData().isChecked());
            }
        } catch (NoSuchFieldException e) {
            Log.e("ERROR NO SUCH FIELD", "Unable to get shift mode field");
        } catch (IllegalAccessException e) {
            Log.e("ERROR ILLEGAL ALG", "Unable to change value of shift mode");
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.tool_bar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        switch(id) {
            case R.id.navigation_setting:

                startActivity(new Intent(MainActivity.this,SettingActivity.class));
                break;
        }

        return false;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        navigation.setSelectedItemId(R.id.navigation_home);

    }
}
