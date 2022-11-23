package com.example.metetoobo;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

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

public class MainActivity extends AppCompatActivity {

        private Button btnFetchData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        initView();
    }

    private void initView() {
        btnFetchData = findViewById(R.id.btn_fetchData);

        btnFetchData.setOnClickListener(view -> {
            fetchData test = new fetchData();
            test.runFetch("Rennes");
        });
    }


    class fetchData extends Thread{

        String data = "";

        public void runFetch(String ville) {
            try {
                URL url = new URL("https://api.meteo-concept.com/api/forecast/daily?token=3722d305e101385ebbccdecd7a878d85122bbdd79857766fcfbd2dce06650d2c&name=" + ville);
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
                    textView.setText(tempMinString);

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
}

