package com.example.metetoobo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

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

public class MeteoActivity extends AppCompatActivity {

    private TextView text_showTempMin;
    private RequestQueue mQueue;

    public MeteoActivity() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meteo);
        text_showTempMin = findViewById(R.id.text_showTempMin);
        Button buttonFetchURL = findViewById(R.id.btn_fetchData);

        mQueue = Volley.newRequestQueue(this);

        buttonFetchURL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                jsonParse();
            }
        });
    }

    private void jsonParse() {

        String url = "https://api.meteo-concept.com/api/forecast/daily/0?token=3722d305e101385ebbccdecd7a878d85122bbdd79857766fcfbd2dce06650d2c&search=Rennes";

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray jsonArray = response.getJSONArray("forecast");
                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject forecast = jsonArray.getJSONObject(i);

                                String tempMin = forecast.getString("tmin");

                                text_showTempMin.append(tempMin);
                            }


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });
        mQueue.add(request);
    }

/*
    class FetchData extends Thread{

        String data = "";

        FetchData() {
        Log.d("METEOACTIVITY", "entering constructor");
        }
        public void runFetch(String ville) {
            try {
                //URL url = new URL("https://api.meteo-concept.com/api/forecast/daily?token=3722d305e101385ebbccdecd7a878d85122bbdd79857766fcfbd2dce06650d2c&search=" + ville);
                URL url = new URL("https://api.meteo-concept.com/api/forecast/daily/0?token=3722d305e101385ebbccdecd7a878d85122bbdd79857766fcfbd2dce06650d2c&search=Rennes");
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                String line;

                while ((line = bufferedReader.readLine()) != null){
                    data = data + line;
                }

                if (!data.isEmpty()){
                    JSONObject jsonObject = new JSONObject(data);
                    JSONArray temperatureMin = jsonObject.getJSONArray("tmin");
                    String tempMinString = temperatureMin.toString();
                    TextView textView = findViewById(R.id.text_showTempMin);
                    //textView.setText(tempMinString);
                    textView.setText("test1");
                }

            } catch (MalformedURLException e){
                e.printStackTrace();
            } catch (IOException e){
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }



    }

 */
}
