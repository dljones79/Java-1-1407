// David Jones
// Full Sail University
// Java 1 1407
// Fundamentals App

package com.fullsail.djones.android.fundamentalsapp;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import static android.util.Log.*;
import static com.fullsail.djones.android.fundamentalsapp.R.id.listView;


public class MainActivity extends Activity {

    // Declare variables
    final String TAG = "Fundamentals App";
    private TextView mEditText;
    private ListView mListView;
    private TextView mEntriesText;
    private TextView mLengthText;
    private ArrayAdapter arrayAdapter;
    private ArrayList arrList;
    private int sizeOfArray;
    private String sizeString;

    // Create a HashSet to store entered items
    private HashSet<String> foodSet = new HashSet<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Instantiate variables
        mEditText = (TextView) findViewById(R.id.editText);
        mListView = (ListView) findViewById(listView);
        mEntriesText = (TextView) findViewById(R.id.entriesText);
        mLengthText = (TextView) findViewById(R.id.lengthText);

        // Create onClickListener for "Enter" button
        Button enterButton = (Button) findViewById(R.id.enterbutton);
        enterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i(TAG, "Button Clicked");

                // Add item entered to set
                foodSet.add(mEditText.getText().toString());

                // Clear text.
                mEditText.setText("");

                // convert hashset to array list
                arrList = new ArrayList<String>(foodSet);

                // Call custom method to populate listview from array
                popListView(arrList);

                // Get size of array and display to user
                sizeOfArray = arrList.size();
                sizeString = "" + sizeOfArray;
                mEntriesText.setText(sizeString);

                // Call custom method to calculate average length of strings in array
                calcAverage(arrList);
            }
        });

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Log.i(TAG, "List item clicked.");
                TextView itemSelected = (TextView) view;
                String selectedString = itemSelected.getText().toString();

                // Instantiate a Toast object
                Context context = getApplicationContext();
                CharSequence text = selectedString + " " + getText(R.string.alert_message) + ".";
                int duration = Toast.LENGTH_SHORT;

                Toast toast = Toast.makeText(context, text, duration);
                toast.show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

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
    }

    // Custom methods

    // Method to populate listview
    private void popListView(ArrayList currentList){
        arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, currentList);
        mListView.setAdapter(arrayAdapter);
    }

    // Method to calculate average string length in arraylist
    private void calcAverage(ArrayList currentList){
        int totalLength = 0;
        int average = 0;
        for (Object s : currentList)
        {
            totalLength = totalLength + s.toString().length();
        }
        average = totalLength/currentList.size();
        String averageString = "" + average;
        mLengthText.setText(averageString);
    }

}

