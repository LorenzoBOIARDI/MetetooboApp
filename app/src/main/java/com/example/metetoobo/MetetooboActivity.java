package com.example.metetoobo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

public class MetetooboActivity extends AppCompatActivity {

    private ImageView imageView;
    private String temps_actuel;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_metetoobo);

        this.imageView = (ImageView) this.findViewById(R.id.imageView_toobo);

        Intent intent2 = getIntent();
        temps_actuel = intent2.getStringExtra("weather_for_picture");
        pictureDisplay(temps_actuel);
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
