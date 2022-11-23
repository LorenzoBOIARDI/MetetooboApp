package com.example.metetoobo;

import android.app.IntentService;
import android.content.ContentValues;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.ParcelFileDescriptor;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class URLFetchDataIntentService extends IntentService {

    public static final String URL = "urlpath";
    public static final String SOURCE_URL = "destination_source";

    public URLFetchDataIntentService() {
        super("URLFetchDataIntentService");
    }



    @Override
    protected void onHandleIntent(Intent intent) {
        String urlPath = intent.getStringExtra(URL);
        InputStream is = null;
        BufferedReader r = null;
        StringBuilder result = null;
        // on récupère les données depuis l'url
        try {
            URL aURL = new URL(urlPath);
            URLConnection conn = aURL.openConnection();
            conn.connect();
            is = conn.getInputStream();
            r = new BufferedReader(new     InputStreamReader(is));
            result = new StringBuilder();
            String line;
            while ((line = r.readLine()) != null) {
                result.append(line);
            }

        } catch (IOException e) {
            // message d'erreur

        } finally {
            // on ferme bien tous les flux
            if ( r != null) {
                try {
                    r.close();
                } catch (IOException e) {
                    // message d'erreur
                }
            }
        }
        // maintenant on transmet le résultat
        // on pourrait avoir un Handler, BroadCast, Notification, etc.
        Intent broadcastIntent = new Intent();
        broadcastIntent.setAction(IntentServiceActivity.MyReceiver.ACTION_RESP);
        broadcastIntent.addCategory(Intent.CATEGORY_DEFAULT);
        broadcastIntent.putExtra(SOURCE_URL, result.toString());
        sendBroadcast(broadcastIntent);

    }
}