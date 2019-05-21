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

import java.util.ArrayList;
import java.util.List;

public class LocationActivity extends AppCompatActivity {

    private ListView listView_states;
    private List<String> list_states;
    private List<String> list_regions;

    private boolean see_list_states = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location);

        //Back button on the acationBar:
        getSupportActionBar().setDisplayHomeAsUpEnabled(true); //Show button
        getSupportActionBar().setHomeButtonEnabled(true); //Activate button

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
                switch (position){
                    case 6: addRegionsInArray("Pernambuco"); //Insert regions to ArrayList.
                        break;
                }
                if(position == 6) changeAdapter(); // REMOVER !!
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
        resultIntent.putExtra("result", this.list_regions.get(position));
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
        String[] array = {"São Paulo", "Minas Gerais", "Rio de Janeiro", "Bahia",
                            "Rio Grande do Sul", "Paraná", "Pernambuco", "Ceará", "Pará",
                            "Maranhão", "Santa Catarina", "Goiás", "Paraíba", "Espírito Santo",
                            "Amazonas", "Alagoas", "Piauí", "Rio Grande do Norte", "Mato Grosso",
                            "Distrito Federal", "Mato Grosso do Sul", "Sergipe", "Rondônia",
                            "Tocantins", "Acre", "Amapá", "Roraima"};

        for(int i = 0; i < array.length; i++){
            this.list_states.add(array[i]);
        }
    }

    private void addRegionsInArray(String state){
        this.list_regions = new ArrayList<>();
        if(state.equals("Pernambuco")){
            String[] array = {"Todas as regiões", "DDD 81 - Grande Recife", "DDD 87 - Petrolina, Garanhuns e região"};
            for(int i = 0; i < array.length; i++){
                this.list_regions.add(array[i]);
            }
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
