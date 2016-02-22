package com.example.jayempeesee.waiterapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class WaiterList extends AppCompatActivity {
    private ListView listView;
    private TextView message;
    ParseSpotJson adapter;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.waiter_list);


        Intent intent = getIntent();
        String searchText = intent.getExtras().getString("extra");
        message = (TextView) findViewById(R.id.textView2);
        message.setText("The Searched String was: " + searchText);
        listView = (ListView) findViewById(R.id.actualList);

        ArrayAdapter<Spot> arrayAdapter = new ArrayAdapter<Spot>(WaiterList.this,R.layout.waiter_list_item, adapter.getSpots());
        listView.setAdapter(arrayAdapter);





    }


}







