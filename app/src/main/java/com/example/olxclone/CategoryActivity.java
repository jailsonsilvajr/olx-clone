package com.example.olxclone;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

public class CategoryActivity extends AppCompatActivity {

    private ListView listview_categories;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);

        this.listview_categories = findViewById(R.id.listView_categories);
        
    }
}
