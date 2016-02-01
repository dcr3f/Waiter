/*Main method.  Contains button implementations and methods for communicating between server
and secondary activity (search-accessed list page).  All method calls required for Main Activity
occur here.

Main Activity: App home page with logo, search text box and button to search for wait times at
various food venues.  Will eventually contain two more working buttons to locate closest venues
with shortest wait times and to pre-order food.*/

package com.example.jayempeesee.waiterapp;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.renderscript.ScriptGroup;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.JsonReader;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import static android.widget.Toast.*;

`
public class /MainActivity extends AppCompatActivity {

    EditText editText;
    //Button mButton;
    //String str = "";
    static String urlStr;
    static InputStream in;
    static ArrayList spotArray;
    Context mContext;
    SendDataTask dataTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        urlStr = "https://t1f3ktfmy8.execute-api.us-west-2.amazonaws.com/prod/getdata";
        in = null;
        spotArray = new ArrayList();
        mContext = this;

        dataTask = new SendDataTask();

        editText = (EditText) findViewById(R.id.searchView);
    }


    @Override
    protected void onStart() {
        super.onStart();

        Button goToSearch = (Button) findViewById(R.id.button2);
        goToSearch.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view2) {
                //Intent intentSearch = new Intent(MainActivity.this, search_waiter.class);
                //startActivity(intentSearch);
                String s = editText.getText().toString();
                try {
                    dataTask.execute(s);
                } catch (Exception e) {
                    //
                }
                //spotArray = parse(in);

                Intent intent = new Intent(MainActivity.this, WaiterList.class);
                intent.putStringArrayListExtra("spotArray", spotArray);
                startActivity(intent);
            }

        });
    }


    /**
     * SendDataTask is a background asynchronous task (AsyncTask) that (ideally)
     *  establishes a connection with a server / database and (supposedly) downloads
     *  information from the inputstream, which then (should) go into a JsonReader
     *  and give us a wonderful array of strings. Apparently we are too demanding,
     *  and Android doesn't want to help us, because it doesn't establish the connection and
     *
     * I had to consolidate the sendData and parse functions because we are not allowed
     *  to establish url connections on a main thread where the rest of the app is running.
     *  This is why I had to put everything in an AsyncTask so that it would all be executed
     *  in the background. But this makes debugging a bi....uh, horrible thing...
     */
    private class SendDataTask extends AsyncTask<String,String,ArrayList>{

        // doInBackground.....does stuff in the background.
        //  Establishes connection (actually, it doesn't),
        //  and parses through JSON retrieve from db... except
        //  we don't know if it works
        @Override
        protected ArrayList doInBackground(String... params){

            // Gets the string that was passed into task
            String str = params[0];

            // Construct the url with the query
            if (!str.equals(null) && str.length() > 1) {
                urlStr = urlStr + "?search=" + str;
            }

            // Initialize connection and url
            HttpURLConnection connection = null;
            URL url;

            // Maybe this will work, but probs not
            try {

                Log.e("Status", "Setting up URLConnection Request");

                // Setup request body
                url = new URL(urlStr);
                connection = (HttpURLConnection) url.openConnection();
                //connection.connect();
                //connection.setDoInput(true);
                //connection.setDoOutput(true);
                connection.setConnectTimeout(5000);

                Log.e("Status", "Making POST Request");

                // Gets input stream (in an ideal world)
                in = new BufferedInputStream(connection.getInputStream());

            } catch (Exception e) {
                // No surprise here
                Log.e("Error", "Unable to connect");
            } finally {
                // disconnect the connection
                if (connection != null) connection.disconnect();
            }

            if (in == null) Log.e("Status", "\'in\' is null");

            // Json parsing. Haven't gotten this far yet.
            try {
                JsonReader reader = new JsonReader(new InputStreamReader(in));

                reader.beginArray();

                while (reader.hasNext()) {
                    reader.beginObject();
                    Spot spot = new Spot();
                    while (reader.hasNext()) {
                        reader.nextName();
                        spot.name = reader.nextString();
                        reader.nextName();
                        spot.visitors = reader.nextString();
                        reader.nextName();
                        spot.waitTime = reader.nextString();
                    }

                    spotArray.add(spot);
                }

            } catch (Exception e) {
                System.out.println("IOException :)");
                Log.e("Exception", "The inputstream was probably null, which means the JSON Reader didn't get to do anything. Poor thing.");
            }
            return spotArray;
        }
    }


    /*
    public ArrayList parse(InputStream in) {

        JsonReader reader = new JsonReader(new InputStreamReader(in));

        try {

            reader.beginArray();


            while (reader.hasNext()) {
                reader.beginObject();
                Spot spot = new Spot();
                while (reader.hasNext()) {
                    reader.nextName();
                    spot.name = reader.nextString();
                    reader.nextName();
                    spot.visitors = reader.nextString();
                    reader.nextName();
                    spot.waitTime = reader.nextString();
                }

                spotArray.add(spot);
            }
        } catch (IOException e) {
            System.out.println("IOException :)");

        }
        return spotArray;

    }*/
}




