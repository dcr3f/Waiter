package com.example.jayempeesee.waiterapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

//import org.json.simple.parser.ParseException;
//import org.json.simple.parser.JSONParser;
//import Spot;

import java.util.ArrayList;

import android.widget.ListView;

public class WaiterList extends AppCompatActivity {
    ArrayList<Spot> spotArray;
    SpotAdapter adapter;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.waiter_list);
        Intent i = getIntent();
       spotArray = new ArrayList<Spot>();
        spotArray.add(new Spot("Taco Bell", "22", "8 min" ));
        adapter = new SpotAdapter(this, spotArray);
        listView = (ListView) findViewById(R.id.actualList);

      // spotArray = i.getStringArrayListExtra("spotArray"); not a string array?
    }




    @Override
    protected void onStart(){
        super.onStart();
        listView.setAdapter(adapter);

       // Spot newSpot = new Spot("")
    }





}







