package com.example.metetoobo;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
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

import java.util.ArrayList;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {
    //declaration variables
    private EditText enteredCity;
    private ListView listView_showFoundCities;
    private RequestQueue mQueue;
    ArrayList<String> arrayList_Cities;
    ArrayList<String> arrayList_Insee;
    ArrayAdapter<String> adapter;
    String weatherStatus;

    public static String tempMin;
    public static int tempMinInt;
    public static String tempMax;
    public static String probaRain;
    public static int weather;
    public static String sunHours;
    public static String weatherForPicture;
    public static String weatherState;
    public static String city;

    MeteoIndex weatherHashmap = new MeteoIndex(); //appel de la classe contenant la Hashmap weather


    public MainActivity() {
    }

    @SuppressLint({"WrongViewCast", "ResourceAsColor"})
    @Override
    protected void onCreate(Bundle savedInstanceState) { //fonction lue au lancement de l'activity
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //initialisation des edittext/listview/bouton
        enteredCity = (EditText) findViewById(R.id.et_City);
        listView_showFoundCities = findViewById(R.id.lv_result);
        Button buttonCitySearch = findViewById(R.id.btn_citySearch);

        mQueue = Volley.newRequestQueue(this);




        buttonCitySearch.setOnClickListener(new View.OnClickListener() {  //lu quand on clique sur le bouton recherche
            @Override
            public void onClick(View view) {
                searchCity();
                closeKeyBoard();

            }
        });

        arrayList_Cities = new ArrayList<>();
        arrayList_Insee = new ArrayList<>();

        adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1,  /* android standard layout for a single entry from list: just some text and just a horizontal separator */
                arrayList_Cities /* the List<T> contents */);

    }

    private void jsonParse(String insee) { //fonction qui accède à l'api avec comme paramètre le code insee de la ville choisie

        String url = "https://api.meteo-concept.com/api/forecast/daily/0?token=3722d305e101385ebbccdecd7a878d85122bbdd79857766fcfbd2dce06650d2c&insee="+ insee;

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            //récupération des données contenues dans le JSON obtenu en reponse
                            JSONObject jsonObject2 = response.getJSONObject("city");
                            city = jsonObject2.getString("name");

                            JSONObject jsonObject1 = response.getJSONObject("forecast");
                            tempMin = jsonObject1.getString("tmin");
                            tempMinInt = Integer.valueOf(jsonObject1.getString("tmin"));
                            tempMax = jsonObject1.getString("tmax");
                            probaRain = jsonObject1.getString("probarain");
                            weather = Integer.valueOf(jsonObject1.getString("weather"));
                            sunHours = jsonObject1.getString("sun_hours");

                            if (weather < 9){ //si il pleut pas, voir MeteoIndex
                                weatherState = "soleil";
                            }
                            else {
                                weatherState = "pluie"; //si il pleut, voir MeteoIndex
                                weatherForPicture = "pluie";
                            }

                            if (weatherState == "soleil" && tempMinInt < 10){ //si il pleut pas mais qu'il fait froid
                                weatherForPicture = "froid";
                            }

                            if (weatherState == "soleil" && tempMinInt >= 10){ //si il pleut pas mais qu'il fait chaud
                                weatherForPicture = "soleil";
                            }


                            weatherStatus = weatherHashmap.getWeatherHashmap(weather); //recuperation de la string meteo grace à l'index

                            goToToobo();


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

    private void searchCity(){

        arrayList_Cities.clear(); //vide l'arraylist affichée dans la listview
        arrayList_Insee.clear(); //vide l'arraylist comportant les codes insee des villes affichées

        String data = enteredCity.getText().toString(); //acquisition du nom de la ville cliquée

        String url = "https://api.meteo-concept.com/api/location/cities?token=3722d305e101385ebbccdecd7a878d85122bbdd79857766fcfbd2dce06650d2c&search=" + data;

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray jsonArray = response.getJSONArray("cities");


                            if (jsonArray.length()==0){ //si le json recu est vide
                                arrayList_Cities.add("Aucun résultat !");
                                listView_showFoundCities.setOnItemClickListener(null); //rend les objets de la listview inclickables
                            }

                            else {
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    JSONObject city = jsonArray.getJSONObject(i);

                                    String cityName = city.getString("name");
                                    String cityCP = city.getString("cp");
                                    String cityInsee = city.getString("insee");

                                    arrayList_Cities.add(cityName + " - " + cityCP);
                                    arrayList_Insee.add(cityInsee);

                                    listView_showFoundCities.setOnItemClickListener(new AdapterView.OnItemClickListener() { //rend clickable les objets de la listview
                                        @Override
                                        public void onItemClick(AdapterView<?> adapterView, View view, int pos, long l) {
                                            // Show a Toast message on item  click
                                            //Toast.makeText(MeteoActivity.this, "You clicked : " + arrayList_Insee.get(pos), Toast.LENGTH_SHORT).show();

                                            jsonParse(arrayList_Insee.get(pos)); //appelle jsonParse avec le code insee de la ville selectionnee
                                            //l'indice de l'arraylist de l'insee correspond à l'indice cliqué dans la listview
                                        }
                                    });


                                }

                            }

                            listView_showFoundCities.setAdapter(adapter);

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

    private void closeKeyBoard(){ //ferme le clavier
        View view = this.getCurrentFocus();
        if (view != null){
            InputMethodManager imm = (InputMethodManager)
                    getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    public void goToToobo() {
        // Do something in response to button
        Intent intent = new Intent(this, TooboActivity.class); //creation de l'intent pour l'activity toobo (météo)

        Bundle bundle = new Bundle(); //mise en bundle des variables à transmettre à l'activity toobo
        bundle.putString("temp_max", tempMax);
        bundle.putString("temp_min", tempMin);
        bundle.putString("city", city);
        bundle.putString("proba_rain", probaRain);
        bundle.putString("weather", weatherStatus);
        bundle.putString("sun_hours", sunHours);
        bundle.putString("weather_for_picture", weatherForPicture);
        intent.putExtras(bundle); //envoi du bundle
        startActivity(intent); //demarrage de l'activity toobo
    }

}
