package com.example.jayempeesee.waiterapp;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by John on 1/30/2016.
 */
public class ParseSpotJson extends GetRawData implements Serializable {

    private ArrayList<Spot> spotArrayList;
    private String rawURL;
    private String mData;
    private String LOG_TAG = this.getClass().getSimpleName();


    public ParseSpotJson(String rawURL) {
        super(null);
        spotArrayList = new ArrayList<Spot>();
        this.rawURL = rawURL;


    }

    public void setRawURL(String URL){
        this.rawURL = URL;
    }

    public ArrayList<Spot> getSpots(){
        return this.spotArrayList;
    }

//    // 99.9% of the time you can just ignore this
//    @Override
//    public int describeContents() {
//        return 0;
//    }
//
//    // write your object's data to the passed-in Parcel
//    @Override
//    public void writeToParcel(Parcel out, int flags) {
//        out.writeString(this.rawURL);
//        out.writeString(this.mData);
//    }
//
//    // this is used to regenerate your object. All Parcelables must have a CREATOR that implements these two methods
//    public static final Parcelable.Creator<ParseSpotJson> CREATOR = new Parcelable.Creator<ParseSpotJson>() {
//        public ParseSpotJson createFromParcel(Parcel in) {
//            return new ParseSpotJson(in);
//        }
//
//        public ParseSpotJson[] newArray(int size) {
//            return new ParseSpotJson[size];
//        }
//    };

    public boolean Parse(){
        final String SPOT_ITEMS = "array";
        final String SPOT_NAME = "rName";
        final String SPOT_WAIT = "waitTime";
        final String SPOT_PEOPLE = "numPeople";
        final String FIXED_JSON = mData;
        try{
            if(mData == null){
                Log.v(LOG_TAG, "rawData is null");
            }
            else{
                Log.v(LOG_TAG, "rawData is not null");
                Log.v(LOG_TAG, FIXED_JSON);
            }
            JSONArray jsonArray = new JSONArray(FIXED_JSON);
            for(int i = 0; i < jsonArray.length(); i++){
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                String name = jsonObject.getString(SPOT_NAME);
                String waitTime = jsonObject.getString(SPOT_WAIT);
                String numPeople = jsonObject.getString(SPOT_PEOPLE);

                Spot parsedSpot = new Spot(name, numPeople, waitTime);
                this.spotArrayList.add(parsedSpot);
            }

            for(Spot test : spotArrayList){
                Log.v(LOG_TAG, test.toString());
            }

        }catch(JSONException jsone){
            jsone.printStackTrace();
            Log.e(LOG_TAG, "FIX YO JSON DATA BRUH");
            return false;
        }
        return true;
    }
    public void execute(){
        DownloadJsonData downloadJsonData = new DownloadJsonData();
        downloadJsonData.execute(rawURL);
    }

    public class DownloadJsonData extends DownloadRawData {
        protected void onPostExecute(String webData){
            mData = webData;
            super.onPostExecute(webData);
            Parse();

        }

        protected String doInBackground(String... params){
            String[] par = { rawURL.toString()};
            return super.doInBackground(par);
        }


    }
}

