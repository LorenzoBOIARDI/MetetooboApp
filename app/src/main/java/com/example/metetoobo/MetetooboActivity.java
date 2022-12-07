package com.example.metetoobo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

public class MetetooboActivity extends AppCompatActivity {

    private ImageView imageView;
    private String tempsActuel;
    private String weatherStatus;
    private String tempMin;

    private TextView textView;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_metetoobo);

        this.imageView = (ImageView) this.findViewById(R.id.imageView_toobo);
        textView = findViewById(R.id.textView_toobo);

        Intent intent2 = getIntent();
        tempsActuel = intent2.getStringExtra("weather_for_picture");
        weatherStatus = intent2.getStringExtra("weather_status");
        tempMin = intent2.getStringExtra("temp_min");

        textView.setText(weatherStatus + ", température minimale de " + tempMin + " °C... \nVoici la tenue que nous te recommandons !");

        pictureDisplay(tempsActuel);
    }

    public void pictureDisplay (String temps){

        switch (temps) {
            case "soleil":
                this.imageView.setImageResource(R.drawable.toobo_bg);
        }

        switch (temps) {
            case "pluie":
                this.imageView.setImageResource(R.drawable.toobo_pluie);
        }

        switch (temps) {
            case "froid":
                this.imageView.setImageResource(R.drawable.toobo_froid);
        }
    }






}
