package com.example.society_app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.List;

public class adminfeautres extends AppCompatActivity {
    private DrawerLayout dl;
    private ActionBarDrawerToggle abdt;


    List<items>  lsitems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
     setContentView(R.layout.activity_adminfeautres);
        Toolbar toolbar=findViewById(R.id.toolbar);
        toolbar.setTitle("FEATURES");
        setSupportActionBar(toolbar);
        dl=(DrawerLayout)findViewById(R.id.dl);
        abdt=new ActionBarDrawerToggle(this,dl,R.string.open,R.string.close);

        abdt.setDrawerIndicatorEnabled(true);

        dl.addDrawerListener(abdt);
        abdt.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        NavigationView nav=(NavigationView)findViewById(R.id.nav_view);
        nav.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

                int id=menuItem.getItemId();

                if(id==R.id.first) {

                  Intent intent=new Intent(adminfeautres.this,adminfeautres.class);
                  startActivity(intent);
                }
                else  if(id==R.id.society) {

                    Intent intent=new Intent(adminfeautres.this,aboutsociety.class);
                    startActivity(intent);
                }
                else  if(id==R.id.second) {

                    Intent intent=new Intent(adminfeautres.this,aboutus.class);
                    startActivity(intent);
                }
                else  if(id==R.id.third) {

                    Intent intent=new Intent(adminfeautres.this,contatcus.class);
                    startActivity(intent);
                }
                return true;
            }
        });



        lsitems=new ArrayList<>();
        lsitems.add(new items("MEMBER DETAILS",R.drawable.profile));
        lsitems.add(new items("MAINTENANCE DETAILS",R.drawable.maintenance));
        lsitems.add(new items("MEETING DETAILS",R.drawable.meeting));
        lsitems.add(new items("EXPENSES",R.drawable.expenses));
        lsitems.add(new items("NOTICE",R.drawable.notice));
        lsitems.add(new items("NOTIFICATION",R.drawable.notification));


        RecyclerView myrv=(RecyclerView) findViewById(R.id.recyclerview_id);
        RecycleViewAdapter myAdapter=new RecycleViewAdapter(this,lsitems);
        myrv.setLayoutManager(new GridLayoutManager(this,2));
        myrv.setAdapter(myAdapter);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.example,menu);
        return true;

    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {
            case R.id.item1:
                Intent intent=new Intent(adminfeautres.this,login.class);
                startActivity(intent);

        }

        return abdt.onOptionsItemSelected(item)|| super.onOptionsItemSelected(item);
    }

}
