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
import android.widget.TextView;

import com.example.olxclone.entity.Category;
import com.example.olxclone.entity.Poster;
import com.example.olxclone.entity.Region;
import com.example.olxclone.entity.State;
import com.example.olxclone.entity.User;
import com.example.olxclone.service.Service;
import com.example.olxclone.util.AdapterListViewPoster;
import com.example.olxclone.util.Filter;

import java.util.List;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private NavigationView navigationView; //For get header after login

    private Button button_location;
    private Button button_category;
    private Button button_filters;

    private State state_actual;
    private Region region_actual;

    private Category category_actual;
    private Filter filter_actual;

    private List<Poster> posters;

    private ListView listView_items;
    private AdapterListViewPoster adapterListViewPoster;

    private static final int ACTIVITY_LOCATION_REQUEST = 1;
    private static final int ACTIVITY_CATEGORY_REQUEST = 2;
    private static final int ACTIVITY_FILTER_REQUEST = 3;
    private static final int ACTIVITY_LOGIN_REQUEST = 4;

    private Service service;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        this.navigationView = findViewById(R.id.nav_view);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        this.navigationView.setNavigationItemSelectedListener(this);

        getSupportActionBar().setTitle(""); //"Removendo" o título da activity... Procurar uma forma "mais correta" de fazer isso.

        this.service = new Service();

        //Usar SharedPreference para setar esses dados:
        this.state_actual = new State(7, "Pernambuco");
        this.region_actual = new Region(1, "DDD 81 - Grande Recife");

        this.category_actual = new Category(0, "Todas as categorias");
        this.filter_actual = null;

        //Carregar os Buttons:
        this.button_location = (Button) findViewById(R.id.button_location);
        this.button_category = (Button) findViewById(R.id.button_category);
        this.button_filters = (Button) findViewById(R.id.button_filters);

        this.listView_items = (ListView) findViewById(R.id.listView_items);
        changePosters();

        //Adicionar função de click aos Buttons e ListView:
        addClick();
    }

    public void login(View v){

        Intent intent = new Intent(MainActivity.this, LoginActivity.class);
        startActivityForResult(intent, ACTIVITY_LOGIN_REQUEST);
    }

    private void changeDataListView(){

        this.adapterListViewPoster = new AdapterListViewPoster(this, this.posters);
        this.listView_items.setAdapter(this.adapterListViewPoster);
    }

    private void changePosters(){

        this.posters = this.service.getPosters(this.state_actual.getId(), this.region_actual.getId(),
                this.category_actual.getId(), this.filter_actual);

        changeDataListView();
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

                this.state_actual = (State) data.getExtras().getSerializable("state");
                this.region_actual = (Region) data.getExtras().getSerializable("region");
                this.button_location.setText(this.region_actual.getName());
            }
        }else if(requestCode == ACTIVITY_CATEGORY_REQUEST){
            if(resultCode == RESULT_OK){
                String result = data.getStringExtra("result");
                this.button_category.setText(result);
            }
        } else if(requestCode == ACTIVITY_FILTER_REQUEST){
            if(resultCode == RESULT_OK){
                Filter filter = (Filter) data.getExtras().getSerializable("resultFilters");
            }
        }else if(requestCode == ACTIVITY_LOGIN_REQUEST){

            if(resultCode == RESULT_OK){

                User user = (User) data.getExtras().getSerializable("resultUser");

                View headerLayout = this.navigationView.getHeaderView(0);
                TextView tvMenuHeader_name = headerLayout.findViewById(R.id.textView_name);
                TextView tvMenuHeader_email = headerLayout.findViewById(R.id.textView_email);

                tvMenuHeader_name.setText(user.getName());
                tvMenuHeader_email.setText(user.getEmail());
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
