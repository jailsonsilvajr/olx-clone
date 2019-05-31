package com.example.olxclone;

import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.olxclone.entity.CityZone;
import com.example.olxclone.entity.Location;
import com.example.olxclone.entity.Region;
import com.example.olxclone.service.Service;

import java.util.ArrayList;
import java.util.List;

public class FilterActivity extends AppCompatActivity {

    private Spinner spinner_all_states;
    private Spinner spinner_all_regions;
    private Spinner spinner_all_city_zone;
    private Spinner spinner_all_location;

    private TextView textView_date_ordenation;
    private TextView textView_price_ordenation;

    private Service service;

    private List<String> states;
    private List<Region> regions;
    private List<CityZone> cityZone;
    private List<Location> locations;

    private boolean ordenationData;
    private boolean ordenationPrice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filter);

        //Back button on the acationBar:
        getSupportActionBar().setDisplayHomeAsUpEnabled(true); //Show button
        getSupportActionBar().setHomeButtonEnabled(true); //Activate button

        this.service = new Service();
        this.states = this.service.getStates();

        //Critérios para ordenação
        this.ordenationData = false;
        this.ordenationPrice = false;

        /* Spinners */

        this.spinner_all_states = findViewById(R.id.spinner_all_states);
        this.spinner_all_regions = findViewById(R.id.spinner_all_regions);
        this.spinner_all_city_zone = findViewById(R.id.spinner_all_citys_zone);
        this.spinner_all_location = findViewById(R.id.spinner_all_location);

        this.spinner_all_regions.setVisibility(View.GONE); //Deixando o Spinner não visível.
        this.spinner_all_city_zone.setVisibility(View.GONE);
        this.spinner_all_location.setVisibility(View.GONE);

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, this.states);
        this.spinner_all_states.setAdapter(dataAdapter);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        addClickSpinners(); //Adicionando métodos que serão executados quando selecionar um item nos Spinner.

        /* Spinners */

        /* TextView Ordenation */

        this.textView_date_ordenation = findViewById(R.id.textView_data);
        this.textView_price_ordenation = findViewById(R.id.textView_preco);
        addClickTextViewOrdenation();

        /* TextView Ordenation */
    }

    private void showSpinnerRegions(int position_state){

        this.regions = service.getRegions(position_state);

        List<String> regionsName = new ArrayList<>();
        for(int i = 0; i < this.regions.size(); i++){

            regionsName.add(this.regions.get(i).getName());
        }

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, regionsName);
        this.spinner_all_regions.setAdapter(dataAdapter);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        this.spinner_all_regions.setVisibility(View.VISIBLE);
    }

    private void showSpinnerCityZone(int position_region){

        this.cityZone = service.getCityZone(this.regions.get(position_region).getId());

        List<String> cityZoneName = new ArrayList<>();
        for(int i = 0; i < this.cityZone.size(); i++){

            cityZoneName.add(this.cityZone.get(i).getName());
        }

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, cityZoneName);
        this.spinner_all_city_zone.setAdapter(dataAdapter);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        this.spinner_all_city_zone.setVisibility(View.VISIBLE);
    }

    private void showSpinnerLocation(int position_cityZone){

        Toast.makeText(this, position_cityZone + "", Toast.LENGTH_SHORT).show();

        this.locations = this.service.getLocation(this.cityZone.get(position_cityZone).getId());

        List<String> locationsName = new ArrayList<>();
        for(int i = 0; i < this.locations.size(); i++){

            locationsName.add(this.locations.get(i).getName());
        }

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, locationsName);
        this.spinner_all_location.setAdapter(dataAdapter);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        this.spinner_all_location.setVisibility(View.VISIBLE);
    }

    private void goneSpinnerRegions(){

        this.spinner_all_regions.setVisibility(View.GONE);
    }

    private void goneSpinnerCityZone(){

        this.spinner_all_city_zone.setVisibility(View.GONE);
    }

    private void goneSpinnerLocation(){

        this.spinner_all_location.setVisibility(View.GONE);
    }

    private void addClickSpinners(){

        this.spinner_all_states.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                if(position == 0){ //position == 0 -> Todos os estados

                    goneSpinnerRegions(); //Esconder Spinner
                    goneSpinnerCityZone();
                    goneSpinnerLocation();
                }else{

                    showSpinnerRegions(position);
                    goneSpinnerCityZone();
                    goneSpinnerLocation();
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        this.spinner_all_regions.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                if(position == 0){

                    goneSpinnerCityZone();
                    goneSpinnerLocation();
                }else{

                    showSpinnerCityZone(position);
                    goneSpinnerLocation();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        this.spinner_all_city_zone.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                if(position == 0){

                    goneSpinnerLocation();
                }else{

                    showSpinnerLocation(position);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void addClickTextViewOrdenation(){

        this.textView_date_ordenation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                textView_date_ordenation.setBackground(ContextCompat.getDrawable(FilterActivity.this, R.drawable.background_border));
                textView_date_ordenation.setTextColor(ContextCompat.getColor(FilterActivity.this, R.color.white));

                textView_price_ordenation.setBackground(ContextCompat.getDrawable(FilterActivity.this, R.drawable.background_border_line_30));
                textView_price_ordenation.setTextColor(ContextCompat.getColor(FilterActivity.this, R.color.black));

                ordenationData = true;
                ordenationPrice = false;
            }
        });

        this.textView_price_ordenation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                textView_date_ordenation.setBackground(ContextCompat.getDrawable(FilterActivity.this, R.drawable.background_border_line_30));
                textView_date_ordenation.setTextColor(ContextCompat.getColor(FilterActivity.this, R.color.black));

                textView_price_ordenation.setBackground(ContextCompat.getDrawable(FilterActivity.this, R.drawable.background_border));
                textView_price_ordenation.setTextColor(ContextCompat.getColor(FilterActivity.this, R.color.white));

                ordenationPrice = true;
                ordenationData = false;
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if(id == android.R.id.home){

            finish();
            return true;
        }
        return false;
    }
}
