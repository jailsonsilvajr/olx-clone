package com.example.olxclone;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.olxclone.service.Service;

import java.util.ArrayList;
import java.util.List;

public class FilterActivity extends AppCompatActivity {

    private Spinner spinner_all_states;
    private Spinner spinner_all_regions;
    private Spinner spinner_all_city_zone;
    private Spinner spinner_all_location;

    private Service service;

    private List<String> states;

    private String state_selected;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filter);

        //Back button on the acationBar:
        getSupportActionBar().setDisplayHomeAsUpEnabled(true); //Show button
        getSupportActionBar().setHomeButtonEnabled(true); //Activate button

        this.service = new Service();
        this.states = this.service.getStates();

        this.state_selected = this.states.get(0);

        /* Spinners */

        this.spinner_all_states = findViewById(R.id.spinner_all_states);
        this.spinner_all_regions = findViewById(R.id.spinner_all_regions);
        this.spinner_all_city_zone = findViewById(R.id.spinner_all_citys_zone);
        this.spinner_all_location = findViewById(R.id.spinner_all_location);

        this.spinner_all_regions.setVisibility(View.GONE);
        this.spinner_all_city_zone.setVisibility(View.GONE);
        this.spinner_all_location.setVisibility(View.GONE);

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, this.states);
        this.spinner_all_states.setAdapter(dataAdapter);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner_all_states.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                if(position != 7) Toast.makeText(FilterActivity.this, "Select Pernambuco!", Toast.LENGTH_SHORT).show();
                else{


                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        /* Spinners */
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
