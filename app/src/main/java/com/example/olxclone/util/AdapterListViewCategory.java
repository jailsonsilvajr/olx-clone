package com.example.olxclone.util;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.olxclone.R;

import java.util.List;

public class AdapterListViewCategory extends BaseAdapter {

    private List<String> categories;
    private Activity activity;

    public AdapterListViewCategory(Activity activity, List<String> categories){

        this.categories = categories;
        this.activity = activity;
    }

    @Override
    public int getCount() {

        return this.categories.size();
    }

    @Override
    public Object getItem(int position) {

        return this.categories.get(position);
    }

    @Override
    public long getItemId(int position) {

        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View view = this.activity.getLayoutInflater().inflate(R.layout.layout_listview_category, parent, false);

        TextView textView = view.findViewById(R.id.textView_category);
        ImageView imageView = view.findViewById(R.id.imageView_category);

        textView.setText(this.categories.get(position));
        switch (position){

            case 0: imageView.setImageResource(R.mipmap.ic_category_all);
            break;

            case 1: imageView.setImageResource(R.mipmap.ic_category_car);
            break;

            case 2: imageView.setImageResource(R.mipmap.ic_category_car);
            break;

            case 3: imageView.setImageResource(R.mipmap.ic_category_car);
            break;

            case 4: imageView.setImageResource(R.mipmap.ic_category_car);
            break;

            case 5: imageView.setImageResource(R.mipmap.ic_category_car);
            break;

            case 6: imageView.setImageResource(R.mipmap.ic_category_car);
            break;

            case 7: imageView.setImageResource(R.mipmap.ic_category_car);
            break;

            case 8: imageView.setImageResource(R.mipmap.ic_category_car);
            break;

            case 9: imageView.setImageResource(R.mipmap.ic_category_car);
            break;

            case 10: imageView.setImageResource(R.mipmap.ic_category_car);
            break;

            case 11: imageView.setImageResource(R.mipmap.ic_category_car);
            break;

            case 12: imageView.setImageResource(R.mipmap.ic_category_car);
            break;

            case 13: imageView.setImageResource(R.mipmap.ic_category_car);
            break;
        }

        return view;
    }
}
