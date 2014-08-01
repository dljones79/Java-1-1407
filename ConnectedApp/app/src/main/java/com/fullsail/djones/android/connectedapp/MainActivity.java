/*
David Jones
Java 1 - 1407
Full Sail University
Connected App Assignment
*/

package com.fullsail.djones.android.connectedapp;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import org.apache.commons.io.IOUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import java.net.URL;
import java.net.URLConnection;

import butterknife.InjectView;

public class MainActivity extends Activity {

    final String TAG = "Connected App";
    Boolean isConnected = false;    // This is to test connection
    ConnectionDetection detector;   // ConnectionDetection Class

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Create a connection detector
        detector = new ConnectionDetection(getApplicationContext());

        Button enterButton = (Button) findViewById(R.id.enterButton);
        enterButton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {

                // Check network status
                isConnected = detector.connected();

                if (isConnected) {

                    EditText characterName = (EditText) findViewById(R.id.editText);
                    String name = characterName.getText().toString();
                    try {
                        String baseURL = "http://census.soe.com/get/eq2/character/?name.first_lower=";
                        URL queryURL = new URL(baseURL + name + "&c:show=name,type,locationdata,guild");
                        Log.i(TAG, queryURL.toString());
                        new GetCharacterData().execute(queryURL);
                    } catch (Exception e) {
                        Log.e(TAG, "Name not found." + name);
                    }
                    characterName.setText("");
                } else {
                    Log.i(TAG, "No Connection");
                } // End else
            } // End onClick
        }); // End setOnClickListener
    } // End onCreate


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    } // End of onCreateOptionsMenu

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    } // End of onOptionsItemSelected

    // Async Task Class
    private class GetCharacterData extends AsyncTask<URL, Integer, JSONObject> {

        final String TAG = "Connected App Async Task";

        protected void onPreExecute()
        {
            ((ProgressBar) findViewById(R.id.progressBar)).setVisibility(View.VISIBLE);
        }

        @TargetApi(Build.VERSION_CODES.KITKAT)
        @Override
        protected JSONObject doInBackground(URL... urls) {

            String jsonString = "";

            // Collect the string responses from the API
            for (URL queryURL : urls){
                try{
                    URLConnection connection = queryURL.openConnection();
                    jsonString = IOUtils.toString(connection.getInputStream());
                    break;
                } catch (Exception e){
                    Log.e(TAG, "No connection found.");
                }
            }

            Log.i(TAG, "Data Received: " + jsonString);

            // Convert the API String to a JSONObject

            JSONObject apiData;

            try {
                apiData = new JSONObject(jsonString);
                Log.i(TAG, apiData.toString());
            } catch (Exception e) {
                Log.e(TAG, "Can't convert API Response to JSON");
                apiData = null;
            }

            try {
                apiData = (apiData != null) ? apiData.getJSONArray("character_list").getJSONObject(0) : null;
                Log.i(TAG, "Character ID: " + apiData.toString());
            } catch (Exception e){
                Log.e(TAG, "Failure to parse data.");
            }

            return apiData;
        } // End of doInBackground

        protected void onPostExecute(JSONObject apiData) {
            Character displayCharacter = new Character(apiData);
            refreshUI(displayCharacter);
            ((ProgressBar) findViewById(R.id.progressBar)).setVisibility(View.INVISIBLE);
        } // End of onPostExecute


    } // End of GetCharacterData

    // Custom Method to Display Data
    private void refreshUI(Character character){
        ((TextView) findViewById(R.id.charName)).setText(character.getName());
        ((TextView) findViewById(R.id.charLevel)).setText(character.getLevel());
        ((TextView) findViewById(R.id.charClass)).setText(character.getCharClass());
        ((TextView) findViewById(R.id.charServer)).setText(character.getServer());
        //((TextView) findViewById(R.id.charAAS)).setText(character.getAAS());
    } // End of refreshUI
} // End of MainActivity



