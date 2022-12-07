package com.example.metetoobo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

public class MetetooboActivity extends AppCompatActivity {

    private ImageView imageView;
    private String temps_actuel = "pluie";

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_metetoobo);

        this.imageView = (ImageView) this.findViewById(R.id.imageView_toobo);
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
