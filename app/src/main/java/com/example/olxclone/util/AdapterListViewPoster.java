package com.example.olxclone.util;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.olxclone.R;
import com.example.olxclone.entity.Poster;

import java.util.List;

public class AdapterListViewPoster extends BaseAdapter {

    private final Activity activity;
    private final List<Poster> posters;

    public AdapterListViewPoster(Activity activity, List<Poster> posters){

        this.activity = activity;
        this.posters = posters;
    }

    @Override
    public int getCount() {
        return this.posters.size();
    }

    @Override
    public Object getItem(int position) {
        return this.posters.get(position);
    }

    @Override
    public long getItemId(int position) {
        return this.posters.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View view = this.activity.getLayoutInflater().inflate(R.layout.layout_listview, parent, false);

        Poster poster = posters.get(position);

        ImageView imageView = view.findViewById(R.id.imageView_poster);
        TextView textView_title = (TextView) view.findViewById(R.id.textView_title);
        TextView textView_price = (TextView) view.findViewById(R.id.textView_price);
        TextView textView_date = (TextView) view.findViewById(R.id.textView_date_time_location);

        imageView.setImageResource(R.drawable.img_anuncio);
        textView_title.setText(poster.getTitle());
        textView_price.setText("R$" + poster.getPrice());
        textView_date.setText(poster.getDay() + "/" + poster.getMonth() + " " + poster.getHour() + ":" + poster.getMinute() + ", " + poster.getLocation());


        return view;
    }
}
