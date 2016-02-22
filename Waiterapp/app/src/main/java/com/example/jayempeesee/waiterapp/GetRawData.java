package com.example.jayempeesee.waiterapp;
import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Chase Rutledge on 2/19/2016.
 */
public class GetRawData {
    private String mData;
    private String mRawUrl;
    private String LOG_TAG = GetRawData.class.getSimpleName();

    public GetRawData(String mRawUrl){
        this.mRawUrl = mRawUrl;
    }

    public String getmData(){
        return mData;
    }

    public void setmRawUrl(String mRawUrl){
        this.mRawUrl = mRawUrl;
    }

    public void execute(){
        DownloadRawData downloadRawData = new DownloadRawData();
        downloadRawData.execute(mRawUrl);
    }


    public class DownloadRawData extends AsyncTask<String, Void, String> {

        // doInBackground.....does stuff in the background.
        //  Establishes connection (actually, it doesn't),
        //  and parses through JSON retrieve from db... except
        //  we don't know if it works

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            mData = result;
            Log.d("DownloadData", "Result was: " + result);
        }

        @Override
        protected String doInBackground(String... params) {
            HttpURLConnection urlConnection = null;
            BufferedReader reader = null;

            if (params == null) {
                return null;
            }

            try {
                URL url = new URL(params[0]);

                urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setRequestMethod("GET");
                urlConnection.connect();
                InputStream inputStream = urlConnection.getInputStream();
                if (inputStream == null) {
                    return null;
                }
                StringBuffer buffer = new StringBuffer();
                reader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
                reader.skip(1);
                char [] chars = new char[1] ;
                while (reader.read(chars,0,1) >= 0) {
                    if(chars[0] != '\\') {
                        buffer.append(chars);
                    }
                }
                buffer = buffer.deleteCharAt((buffer.length() - 1));
                return buffer.toString();
            } catch (IOException e) {
                Log.e(LOG_TAG, "Error" + e);
                return null;
            } finally {
                if (urlConnection != null) {
                    urlConnection.disconnect();
                }
                if (reader != null) {
                    try {
                        reader.close();
                    } catch (final IOException e) {
                        Log.e(LOG_TAG, "Error closing stream", e);
                    }
                }
            }
        }
    }

}
