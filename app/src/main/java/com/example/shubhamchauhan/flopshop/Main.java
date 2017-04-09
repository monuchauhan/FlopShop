package com.example.shubhamchauhan.flopshop;

import android.app.FragmentManager;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

public class Main extends AppCompatActivity {

    Toolbar toolbar;
    DrawerLayout drawerLayout;
    ActionBarDrawerToggle actionBarDrawerToggle;
    NavigationView navigationView;
    home_fragment homeFrag;
    settings_fragment settingFrag;
    cartFragment cartFrag;
    Work wipFrag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        homeFrag = new home_fragment();
        settingFrag = new settings_fragment();
        cartFrag = new cartFragment();
        wipFrag = new Work();

        toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawerLayout = (DrawerLayout)findViewById(R.id.drawerlayout);
        actionBarDrawerToggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.drawerOpen,R.string.drawerClose);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);

        Intent i = getIntent();
        int id = i.getIntExtra("id",R.id.home);


        navigationView = (NavigationView)findViewById(R.id.navigation_drawer);

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                FragmentManager fragmentManager = getFragmentManager();
                android.app.FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                switch(item.getItemId()){
                    case R.id.home:
                        //load fragment
                        fragmentTransaction.replace(R.id.frame,homeFrag);
                        fragmentTransaction.commit();
                        drawerLayout.closeDrawer(navigationView);
                        break;
                    case R.id.cart:
                        //load fragment
                        fragmentTransaction.replace(R.id.frame,cartFrag);
                        fragmentTransaction.commit();
                        drawerLayout.closeDrawer(navigationView);
                        break;
                    case R.id.wishlist:
                        //load fragment
                        fragmentTransaction.replace(R.id.frame,wipFrag);
                        fragmentTransaction.commit();
                        drawerLayout.closeDrawer(navigationView);
                        break;
                    case R.id.account:
                        //load fragment
                        fragmentTransaction.replace(R.id.frame,wipFrag);
                        fragmentTransaction.commit();
                        drawerLayout.closeDrawer(navigationView);
                        break;
                    case R.id.aboutus:
                        //load fragment
                        fragmentTransaction.replace(R.id.frame,wipFrag);
                        fragmentTransaction.commit();
                        drawerLayout.closeDrawer(navigationView);
                        break;
                    case R.id.settings:
                        //load fragment
                        fragmentTransaction.replace(R.id.frame,settingFrag);
                        fragmentTransaction.commit();
                        drawerLayout.closeDrawer(navigationView);
                        break;
                    default:
                        return false;
                }
                //fragmentTransaction.commit();
                return true;
            }
        });

        if(id == R.id.cart){
            FragmentManager fragmentManager = getFragmentManager();
            android.app.FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.frame,cartFrag);
            fragmentTransaction.commit();
        }
        else{
            FragmentManager fragmentManager = getFragmentManager();
            android.app.FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.frame,homeFrag);
            fragmentTransaction.commit();
        }

    }

    @Override
    public void onPostCreate(Bundle savedInstanceState){
        super.onPostCreate(savedInstanceState);
        actionBarDrawerToggle.syncState();
    }
}
