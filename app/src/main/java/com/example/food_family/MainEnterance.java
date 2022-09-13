package com.example.food_family;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class MainEnterance extends AppCompatActivity {



    BottomNavigationView bottomNavigationView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main_enterance);

        FragmentTransaction homeTrans = getSupportFragmentManager().beginTransaction();
        homeTrans.replace(R.id.wrapper, new recfragment());
        homeTrans.commit();

        getSupportActionBar().hide();

        bottomNavigationView =(BottomNavigationView)findViewById(R.id.bottomNavigation);

       bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
           @Override
           public boolean onNavigationItemSelected(@NonNull MenuItem item) {

               Fragment temp = null;

               switch (item.getItemId()){
                   case R.id.menu_Home: temp = new recfragment();
                   break;
                   case R.id.menu_AddtoCard: temp = new AddtoCardFragment();
                   break;
                   case  R.id.menu_shoped: temp = new ShopedFragment();
                   break;
               }

               getSupportFragmentManager().beginTransaction().replace(R.id.wrapper, temp).commit();


               return true;
           }
       });




    }






}