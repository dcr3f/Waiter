/*Main method.  Contains button implementations and methods for communicating between server
and secondary activity (search-accessed list page).  All method calls required for Main Activity
occur here.

Main Activity: App home page with logo, search text box and button to search for wait times at
various food venues.  Will eventually contain two more working buttons to locate closest venues
with shortest wait times and to pre-order food.*/

package com.example.jayempeesee.waiterapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private EditText txtSearch;
    private Button btnSearch;
    private ParseSpotJson downloadData;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        btnSearch = (Button) findViewById(R.id.search_button);
        txtSearch = (EditText) findViewById(R.id.search_edit);
        downloadData = new ParseSpotJson("https://t1f3ktfmy8.execute-api.us-west-2.amazonaws.com/prod/getdata");
        downloadData.execute();


        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String searchText = txtSearch.getText().toString();
                Intent intent = new Intent(MainActivity.this, WaiterList.class);
                intent.putExtra("searchtext", searchText);
                intent.putExtra("object", downloadData);
                setResult(RESULT_OK, intent);
                startActivity(intent);
            }
        });
    }


    /**
     * DownloadRawData is a background asynchronous task (AsyncTask) that (ideally)
     * establishes a connection with a server / database and (supposedly) downloads
     * information from the inputstream, which then (should) go into a JsonReader
     * and give us a wonderful array of strings. Apparently we are too demanding,
     * and Android doesn't want to help us, because it doesn't establish the connection and
     * <p/>
     * I had to consolidate the sendData and parse functions because we are not allowed
     * to establish url connections on a main thread where the rest of the app is running.
     * This is why I had to put everything in an AsyncTask so that it would all be executed
     * in the background. But this makes debugging a bi....uh, horrible thing...
     */
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




