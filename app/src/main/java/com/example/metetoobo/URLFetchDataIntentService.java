package com.example.metetoobo;

import android.app.IntentService;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class URLFetchDataIntentService extends IntentService {
    /**
     * @param name
     * @deprecated
     */
    public URLFetchDataIntentService(String name) {
        super(name);
        Log.d("METEOACTIVITY", "entering constructor");
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {

    }


    String data = "";

    public String urlFetcher(String ville) {
        try {
            //URL url = new URL("https://api.meteo-concept.com/api/forecast/daily?token=3722d305e101385ebbccdecd7a878d85122bbdd79857766fcfbd2dce06650d2c&search=" + ville);
            URL url = new URL("https://api.meteo-concept.com/api/forecast/daily?token=3722d305e101385ebbccdecd7a878d85122bbdd79857766fcfbd2dce06650d2c&search=Rennes");
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            InputStream inputStream = httpURLConnection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            String line;

            while ((line = bufferedReader.readLine()) != null) {
                data = data + line;
            }

            if (!data.isEmpty()) {
                JSONObject jsonObject = new JSONObject(data);
                JSONArray temperatureMin = jsonObject.getJSONArray("tmin");
                String tempMinString = temperatureMin.toString();
                return tempMinString;
                /*
                TextView textView = findViewById(R.id.text_showTempMin);
                //textView.setText(tempMinString);
                textView.setText("test1");

                 */
            }
            else return "erreur";

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return "erreur";
    }
}
