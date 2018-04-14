package com.example.android.simplefeeder;

import android.util.Log;

import org.xmlpull.v1.XmlPullParserException;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.net.ssl.HttpsURLConnection;

import static android.R.id.list;

/**
 * Created by lenovo on 4/8/2018.
 */

public class fetchData {
    String result;

    public List<Details> makeConnection(String uri)throws XmlPullParserException {
        List<Details> array=new ArrayList<>();
        HttpURLConnection connection = null;
        InputStream is = null;
        Log.d("result", "after initialization line");
        try {
            URL url = new URL(uri);
            Log.d("result", "after url passed line");
            connection = (HttpURLConnection) url.openConnection();
            Log.d("result", "after making connection line");
            connection.setRequestMethod("GET");
            Log.d("result", "after set request line");

            Log.d("result", "after .connect() line");
            is = new BufferedInputStream(connection.getInputStream());
            Log.d("result", "in make connection method");
            array=ParseXML.parse(is);
            Log.d("result", "going to return result");

        } catch (MalformedURLException e) {
            Log.d("result", "i am caught up in an errror named malformed");
            e.printStackTrace();

        } catch (IOException e) {
            Log.d("result", "i am caught up in an errror named IOExcepton");
            e.printStackTrace();

        } finally {
            if (connection != null)
                connection.disconnect();
        }
        return array;

    }
}
