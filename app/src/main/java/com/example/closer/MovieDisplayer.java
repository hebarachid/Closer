package com.example.closer;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class MovieDisplayer extends AppCompatActivity {
    List<String> MovieList = new ArrayList<String>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_displayer);

        String apiURL="https://api.themoviedb.org/3/movie/popular?api_key=4a56b975740745ae46b79bb11a7271e4";
        DownloadTask task=new DownloadTask();
        task.execute(apiURL);
    }
    public class DownloadTask extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... urls) {
            String result = "";
            URL url;
            HttpURLConnection urlConnection;
            try {
                url = new URL(urls[0]);
                urlConnection = (HttpURLConnection) url.openConnection();
                //read
                InputStream in = urlConnection.getInputStream();
                InputStreamReader reader = new InputStreamReader(in);
                int data = reader.read();
                while (data != -1) {
                    char current = (char) data;
                    result += current;
                    data = reader.read();
                }
                Log.i("result", "doInBackground: "+result);
                return result;
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }

        }

        @Override
        protected void onPostExecute(String s) {
            try{
                JSONObject jsonObject =new JSONObject(s);
                JSONArray jsonArray=jsonObject.getJSONArray("results");
                for(int i=0;i<jsonArray.length();i++){
                    JSONObject jsonObject1=jsonArray.getJSONObject(i);
                    String id= jsonObject1.getString("original_title");
                    Log.i("hehe", "onPostExecute: "+id);
              MovieList.add(id);
                }
                ListView list = (ListView) findViewById(R.id.listView);
                ArrayAdapter<String> arr=new ArrayAdapter<String>(getApplicationContext(),R.layout.support_simple_spinner_dropdown_item,MovieList);
                list.setAdapter(arr);
            }catch (Exception e) {
                e.printStackTrace();
        }
    }
}}