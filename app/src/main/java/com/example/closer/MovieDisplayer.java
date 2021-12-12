package com.example.closer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class MovieDisplayer extends AppCompatActivity {
    List<MovieModelClass> movieList;
    RecyclerView recyclerView;
    String apiURL="https://api.themoviedb.org/3/movie/popular?api_key=4a56b975740745ae46b79bb11a7271e4";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_displayer);
        movieList=new ArrayList<>();
        recyclerView=findViewById(R.id.recyclerView);

        GetData getData=new GetData();
        getData.execute();
    }

    public class GetData extends AsyncTask<String,String,String>{

        @Override
        protected String doInBackground(String... strings) {
            String current="";
            try{
                URL url;
                HttpURLConnection urlConnection=null;
                try{
                    url=new URL(apiURL);
                    urlConnection=(HttpURLConnection) url.openConnection();

                    InputStream is=urlConnection.getInputStream();
                    InputStreamReader isr=new InputStreamReader(is);

                    int data= isr.read();
                    while (data!=-1){
                        current+=(char)data;
                        data=isr.read();
                    }
                    return current;
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }finally {
                    if(urlConnection!=null){
                        urlConnection.disconnect();
                    }
                }
            }catch (Exception e){
                e.printStackTrace();
            }
            return current;
        }

        @Override
        protected void onPostExecute(String s) {
            try{
                JSONObject jsonObject=new JSONObject(s);
                JSONArray jsonArray=jsonObject.getJSONArray("results");
                for(int i=0;i<jsonArray.length();i++){
                    JSONObject jsonObject1=jsonArray.getJSONObject(i);
                    MovieModelClass model=new MovieModelClass();
                    model.setId(jsonObject1.getString("vote_average"));
                    model.setName(jsonObject1.getString("title"));
                    model.setImg(jsonObject1.getString("poster_path"));
                    model.setOverview(jsonObject1.getString("overview"));
                    model.setRelease_date(jsonObject1.getString("release_date"));
                    movieList.add(model);
                }

            }catch (JSONException e){
                e.printStackTrace();
            }
            putDataIntoRecyclerView( movieList);
        }
    }

    private void putDataIntoRecyclerView(List<MovieModelClass> movieList){
        MovieAdapter movieAdapter=new MovieAdapter(this,movieList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        recyclerView.setAdapter(movieAdapter);
    }
}