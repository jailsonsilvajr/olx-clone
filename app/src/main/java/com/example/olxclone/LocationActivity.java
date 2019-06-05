package com.example.olxclone;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.olxclone.entity.Region;
import com.example.olxclone.entity.State;
import com.example.olxclone.service.Service;

import java.util.ArrayList;
import java.util.List;

public class LocationActivity extends AppCompatActivity {

    private ListView listView_states;
    private List<String> list_states;
    private List<String> list_regions;

    private List<State> states;
    private List<Region> regions;

    private int id_state_actual;

    private boolean see_list_states = true;

    private Service service;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location);

        //Back button on the acationBar:
        getSupportActionBar().setDisplayHomeAsUpEnabled(true); //Show button
        getSupportActionBar().setHomeButtonEnabled(true); //Activate button

        this.regions = new ArrayList<>();

        this.service = new Service();

        //ListView:
        this.listView_states = findViewById(R.id.listView_states);
        this.list_states = new ArrayList<>();
        addStatesInArray(); //Insert states to ArrayList.
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, this.list_states);
        this.listView_states.setAdapter(adapter);

        this.list_regions = new ArrayList<>();

        statesSetOnItemClickListener();
    }

    private void statesSetOnItemClickListener(){
        this.listView_states.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                id_state_actual = position;
                switch (position){
                    case 7: addRegionsInArray(position); //Insert regions to ArrayList.
                        break;
                }
                if(position == 7) changeAdapter(); // REMOVER !!
                else Toast.makeText(getApplicationContext(), "ESCOLHA PERNAMBUCO.", Toast.LENGTH_SHORT).show(); //REMOVER !!!
            }
        });
    }

    private void regionsSetOnItemClickListener(){
        this.listView_states.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                returnResult(position);
            }
        });
    }

    private void returnResult(int position){

        Intent resultIntent = new Intent();
        resultIntent.putExtra("state", this.states.get(id_state_actual));
        resultIntent.putExtra("region", this.regions.get(position));
        setResult(RESULT_OK, resultIntent);
        finish();
    }

    private void changeAdapter(){
        if(this.see_list_states){
            ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, this.list_regions);
            this.listView_states.setAdapter(adapter);
            regionsSetOnItemClickListener();
            this.see_list_states = false;
        }else{
            ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, this.list_states);
            this.listView_states.setAdapter(adapter);
            statesSetOnItemClickListener();
            this.see_list_states = true;
        }
    }

    private void addStatesInArray(){

        this.states = this.service.getStates();
        for(int i = 0; i < this.states.size(); i++){

            this.list_states.add(this.states.get(i).getName());
        }
    }

    private void addRegionsInArray(int id_state){

        this.regions = this.service.getRegions(id_state);
        this.list_regions = new ArrayList<>();

        for(int i = 0; i < this.regions.size(); i++){

            this.list_regions.add(this.regions.get(i).getName());
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if(id == android.R.id.home){
            if(this.see_list_states){
                finish();
                return true;
            }else{
                changeAdapter();
            }

        }
        return false;
    }

    @Override
    public void onBackPressed() {
        if(this.see_list_states){
            super.onBackPressed();
        }else{
            changeAdapter();
        }
    }
}
