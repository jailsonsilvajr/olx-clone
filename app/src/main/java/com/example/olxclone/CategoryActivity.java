package com.example.olxclone;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.example.olxclone.service.Service;
import com.example.olxclone.util.AdapterListViewCategory;

public class CategoryActivity extends AppCompatActivity {

    private ListView listview_categories;

    private Service service;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);

        //Back button on the acationBar:
        getSupportActionBar().setDisplayHomeAsUpEnabled(true); //Show button
        getSupportActionBar().setHomeButtonEnabled(true); //Activate button

        this.service = new Service();

        this.listview_categories = findViewById(R.id.listView_categories);
        AdapterListViewCategory adapter = new AdapterListViewCategory(this, service.getListCategories());
        this.listview_categories.setAdapter(adapter);
    }
}
