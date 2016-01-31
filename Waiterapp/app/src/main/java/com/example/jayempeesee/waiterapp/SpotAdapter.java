package com.example.jayempeesee.waiterapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by John on 1/30/2016.
 */
public class SpotAdapter extends ArrayAdapter<Spot> {

    public SpotAdapter(Context context, ArrayList<Spot> spots) {

        super(context, 0, spots);


    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Spot spot = getItem(position);
        if(convertView == null) {
        convertView = LayoutInflater.from(getContext()).inflate(R.layout.waiter_list_item, parent);
        }
        TextView name = (TextView) convertView.findViewById(R.id.name);
        TextView visitors = (TextView) convertView.findViewById(R.id.visitors);
        TextView waitTime = (TextView) convertView.findViewById(R.id.waitTime);
        name.setText(spot.name);
        visitors.setText(spot.visitors);
        waitTime.setText(spot.waitTime);
        return convertView;
    }
}
