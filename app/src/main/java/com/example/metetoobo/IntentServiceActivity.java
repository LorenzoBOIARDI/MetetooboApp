package com.example.metetoobo;

import android.os.Bundle;
import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class IntentServiceActivity extends Activity {

    public class MyReceiver extends BroadcastReceiver {
        public static final String ACTION_RESP = "com.myapp.intent.action.TEXT_TO_DISPLAY";

        @Override
        public void onReceive(Context context, Intent intent) {
            String text = intent.getStringExtra(URLFetchDataIntentService.SOURCE_URL);
            // send text to display
            /*
            TextView result = (TextView) findViewById(R.id.text_showTempMin);
            result.setText(text);
            */
            if (!text.isEmpty()){
                JSONObject jsonObject = null;
                try {
                    jsonObject = new JSONObject(text);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                JSONArray temperatureMin = null;
                try {
                    temperatureMin = jsonObject.getJSONArray("tmin");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                String tempMinString = temperatureMin.toString();
                TextView textView = findViewById(R.id.text_showTempMin);
                textView.setText(tempMinString);
            }


        }
    }

    private MyReceiver receiver;

    public void onCreate(Bundle savedInstanceState, String ville) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meteo);

        // on initialise notre broadcast
        receiver = new MyReceiver();
        // on lance le service
        Intent msgIntent = new Intent(this, URLFetchDataIntentService.class);
        msgIntent.putExtra(URLFetchDataIntentService.URL, "https://api.meteo-concept.com/api/forecast/daily?token=3722d305e101385ebbccdecd7a878d85122bbdd79857766fcfbd2dce06650d2c&search=" + ville);
        startService(msgIntent);
    }

    @Override
    protected void onResume() {
        super.onResume();
        // on déclare notre Broadcast Receiver
        IntentFilter filter = new IntentFilter(MyReceiver.ACTION_RESP);
        filter.addCategory(Intent.CATEGORY_DEFAULT);
        registerReceiver(receiver, filter);
    }

    @Override
    protected void onPause() {
        super.onPause();
        // on désenregistre notre broadcast
        unregisterReceiver(receiver);
    }
}