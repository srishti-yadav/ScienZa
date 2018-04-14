package com.example.android.simplefeeder;
import android.content.Context;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Debug;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.simplefeeder.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static android.R.attr.button;
import static android.R.attr.description;
import static android.R.attr.name;

public class MainActivity extends AppCompatActivity {
    private String variable;
    private TextView display_url;
    RecyclerView recyclerView;
    RecyclerView.Adapter adapter;
    public List<Details> detailValues;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView=(RecyclerView)findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        detailValues=new ArrayList<>();

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        new asynTask().execute();
        adapter=new AdapterClass(detailValues,getApplicationContext());
        recyclerView.setAdapter(adapter);
        //display_url.setText(variable);



    }
    public class asynTask extends AsyncTask<Void,Void,Void>{

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(Void aVoid) {

            super.onPostExecute(aVoid);



        }

        @Override
        protected Void doInBackground(Void... params) {
            //   https://api.androidhive.info/contacts/
            //   https://newsapi.org/v2/top-headlines?sources=new-scientist&apiKey=6e6c6d58b20d403096b8e023979d9431


            fetchData obj = new fetchData();
          /*  Uri.Builder uri=new Uri.Builder();
            uri.scheme("https").authority("newsapi.org").appendPath("v2")
                    .appendPath("top-headlines").appendQueryParameter("sources","new-scientist")
            .appendQueryParameter("apiKey","6e6c6d58b20d403096b8e023979d9431").build();
            //variable=uri.toString();
            */
            //variable="https://newsapi.org/v2/top-headlines?sources=new-scientist&apiKey=76f922a6d60f4134b05d998a5a74a7f2";
            variable = "http://www.sciencemag.org/rss/news_current.xml";
            Log.d("result", variable);

           try{
               detailValues= obj.makeConnection(variable);}catch (org.xmlpull.v1.XmlPullParserException e)
           {e.printStackTrace();}
            return null;
        }

        }

    /**
     * Created by lenovo on 4/8/2018.
     */


}