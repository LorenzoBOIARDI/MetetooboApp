package com.example.metetoobo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class TooboActivity extends AppCompatActivity {

    public static String tempMin;
    public static String tempMax;
    public static String probaRain;
    public static String weather;
    public static String sunHours;
    public static String city;
    public static String weatherForPicture;

    private TextView text_showTempMax;
    private TextView text_showTempMin;
    private TextView text_showWeather;
    private TextView text_showRain;
    private TextView text_showSun;
    private TextView text_showCity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_toobo);
        text_showTempMax = findViewById(R.id.textView_tempMax);
        text_showTempMin = findViewById(R.id.textView_tempMin);
        text_showWeather = findViewById(R.id.textView_weather);
        text_showRain = findViewById(R.id.textView_rain);
        text_showSun = findViewById(R.id.textView_sunHours);
        text_showCity = findViewById(R.id.textView_city);


        Intent intent = getIntent();
        //recuperation des données de l'activity main
        Bundle bundle = getIntent().getExtras();
        tempMax = bundle.getString("temp_max");
        tempMin = bundle.getString("temp_min");
        probaRain = bundle.getString("proba_rain");
        weather = bundle.getString("weather");
        sunHours = bundle.getString("sun_hours");
        city = bundle.getString("city");
        weatherForPicture = bundle.getString("weather_for_picture");

        //affichage des données
        text_showTempMax.setText("Température maximale : " + tempMax + " °C");
        text_showTempMin.setText("Température minimale : " + tempMin + " °C");
        text_showRain.setText("Probabilité de pluie : " + probaRain + " %");
        text_showWeather.setText(weather);
        text_showSun.setText("Ensoleillement : " + sunHours + "h");
        text_showCity.setText(city);


        Button buttonGoToToobo = findViewById(R.id.button_metetoboo);

        buttonGoToToobo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { //lancé quand on clique sur le bouton
                goToMetetoobo();
            }
        });
    }

    public void goToMetetoobo() {
        // Do something in response to button
        Intent intent2 = new Intent(this, MetetooboActivity.class); //creation de l'intent pour l'activity metetoobo
        //envoi des données à l'activity metetoobo
        intent2.putExtra("weather_for_picture",weatherForPicture);
        intent2.putExtra("weather_status", weather);
        intent2.putExtra("temp_min", tempMin);
        intent2.putExtra("city", city);
        startActivity(intent2); //demarrage de l'activity metetoobo
    }


}

