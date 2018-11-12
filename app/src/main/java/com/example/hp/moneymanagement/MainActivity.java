package com.example.hp.moneymanagement;

import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MainActivity extends FragmentActivity implements MainFragment.OnFragmentInteractionListener,GraphFragment.OnFragmentInteractionListener,About.OnFragmentInteractionListener {
    private ActionBarDrawerToggle drawerToggle;
    private NavigationView nvDrawer;
    private DrawerLayout mDrawer;
    Fragment fragment;
    String income;
    FragmentManager fragmentManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Data.id = new ArrayList<Integer>();
        Data.category = new ArrayList<String>();
        Data.values = new ArrayList<Integer>();
        Data.date = new ArrayList<String>();
        fragment=null;
        try {
            fragment = MainFragment.newInstance();
        }
        catch (Exception e){
            Log.getStackTraceString(e);
        }
        fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().add(R.id.content_frame,fragment).commit();
        mDrawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawerToggle = new ActionBarDrawerToggle(this,mDrawer,R.string.Open,R.string.Close);
        mDrawer.addDrawerListener(drawerToggle);
        nvDrawer = (NavigationView) findViewById(R.id.nav_view);
        drawerToggle.syncState();
        nvDrawer.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                fragment = null;
                Class fragmentClass=MainFragment.class;
                switch (menuItem.getItemId()){
                    case R.id.nav_camera:
                        fragmentClass = MainFragment.class;
                        break;
                    case R.id.nav_gallery:
                        fragmentClass = GraphFragment.class;
                        break;
                    case R.id.nav_manage:
                        try {
                            fragmentClass = About.class;
                        }
                        catch (Exception e){
                            Log.getStackTraceString(e);
                        }
                        break;
                    default: fragmentClass = MainFragment.class;
                }
                    try {
                        fragment = (Fragment) fragmentClass.newInstance();
                    } catch (Exception e) {
                        Log.getStackTraceString(e);
                    }
                    fragmentManager.beginTransaction().replace(R.id.content_frame, fragment).commit();
                    menuItem.setChecked(true);
                    mDrawer.closeDrawer(Gravity.LEFT);
                return true;
            }
        });
    }
    public void onClick(View v){
        Intent intent = new Intent(this,Add.class);
        startActivity(intent);
    }

    @Override
    public void onBackPressed(){
        finish();
        moveTaskToBack(true);
    }

    @Override
    public void onFragmentInteraction(Uri uri) {
        Log.d("a","a");
    }
}
