package com.example.olxclone;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.GravityCompat;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.MenuItem;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.olxclone.entity.Poster;
import com.example.olxclone.util.AdapterListViewPoster;
import com.example.olxclone.util.ReturnFilters;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private Button button_location;
    private Button button_category;
    private Button button_filters;

    private List<Poster> posters;

    private ListView listView_items;
    private AdapterListViewPoster adapterListViewPoster;

    private static final int ACTIVITY_LOCATION_REQUEST = 1;
    private static final int ACTIVITY_CATEGORY_REQUEST = 2;
    private static final int ACTIVITY_FILTER_REQUEST = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);

        getSupportActionBar().setTitle(""); //"Removendo" o título da activity... Procurar uma forma "mais correta" de fazer isso.

        //Carregar os Buttons:
        this.button_location = (Button) findViewById(R.id.button_location);
        this.button_category = (Button) findViewById(R.id.button_category);
        this.button_filters = (Button) findViewById(R.id.button_filters);

        /*Dados apenas para teste do ListView:*/
        this.posters = new ArrayList<Poster>();
        popularList();

        this.listView_items = (ListView) findViewById(R.id.listView_items);
        this.adapterListViewPoster = new AdapterListViewPoster(this, this.posters);
        listView_items.setAdapter(adapterListViewPoster);

        //Adicionar função de click aos Buttons e ListView:
        addClick();
    }

    /* Método temporário: */
    private void popularList(){

        for(int i = 0; i < 20; i++){

            Poster poster = new Poster(i, "Pneu", 150, 15, 05, 8, 24, "Cabo");
            this.posters.add(poster);
        }
    }

    private void addClick(){

        this.button_location.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, LocationActivity.class);
                startActivityForResult(intent, ACTIVITY_LOCATION_REQUEST);
            }
        });

        this.button_category.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, CategoryActivity.class);
                startActivityForResult(intent, ACTIVITY_CATEGORY_REQUEST);
            }
        });

        this.button_filters.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, FilterActivity.class);
                startActivityForResult(intent, ACTIVITY_FILTER_REQUEST);
            }
        });

        this.listView_items.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(requestCode == ACTIVITY_LOCATION_REQUEST){
            if(resultCode == RESULT_OK){
                String result = data.getStringExtra("result");
                this.button_location.setText(result);
            }
        }else if(requestCode == ACTIVITY_CATEGORY_REQUEST){
            if(resultCode == RESULT_OK){
                String result = data.getStringExtra("result");
                this.button_category.setText(result);
            }
        } else if(requestCode == ACTIVITY_FILTER_REQUEST){
            if(resultCode == RESULT_OK){
                ReturnFilters returnFilters = (ReturnFilters) data.getExtras().getSerializable("resultFilters");
            }
        }
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_search) {
            return true;
        }
        if (id == R.id.action_favorite){
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_home) {
            // Handle the camera action
        } else if (id == R.id.nav_home) {

        } else if (id == R.id.nav_insert) {

        } else if (id == R.id.nav_chat) {

        } else if (id == R.id.nav_favorite) {

        } else if (id == R.id.nav_profile) {

        } else if (id == R.id.nav_help) {

        } else if (id == R.id.nav_questions) {

        } else if (id == R.id.nav_security) {

        } else if (id == R.id.nav_terms) {

        } else if (id == R.id.nav_evaluate) {

        } else if (id == R.id.nav_facebook) {

        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
