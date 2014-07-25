package com.fullsail.djones.android.advancedviews;

import android.app.Activity;
import android.content.res.Configuration;
import android.media.Image;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Surface;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Iterator;

public class MainActivity extends Activity {
    ListView mListView;
    Spinner mSpinner;
    ArrayList<Vehicle> vehicles = new ArrayList<Vehicle>();
    final String TAG = "AdvancedViews";
    ArrayAdapter<String> mOSAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Create custom objects
        Vehicle vehicle1 = new Vehicle();
        vehicle1.setBrand("Porsche");
        vehicle1.setModel("911");
        vehicle1.setPrice("$148,300");
        vehicle1.setPic(R.drawable.porsche);
        vehicles.add(vehicle1);

        Vehicle vehicle2 = new Vehicle();
        vehicle2.setBrand("Ferrari");
        vehicle2.setModel("458 Italia");
        vehicle2.setPrice("$233,509");
        vehicle2.setPic(R.drawable.ferrari);
        vehicles.add(vehicle2);

        Vehicle vehicle3 = new Vehicle();
        vehicle3.setBrand("Lamborghini");
        vehicle3.setModel("Huracan");
        vehicle3.setPrice("$237,250");
        vehicle3.setPic(R.drawable.lambo);
        vehicles.add(vehicle3);

        Vehicle vehicle4 = new Vehicle();
        vehicle4.setBrand("Chevrolet");
        vehicle4.setModel("Corvette Z06");
        vehicle4.setPrice("$76,900");
        vehicle4.setPic(R.drawable.z06);
        vehicles.add(vehicle4);

        Vehicle vehicle5 = new Vehicle();
        vehicle5.setBrand("Dodge");
        vehicle5.setModel("Hellcat");
        vehicle5.setPrice("$60,990");
        vehicle5.setPic(R.drawable.hellcat);
        vehicles.add(vehicle5);

        // Call custom method to populate UI
        popUI(vehicles);
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

    // Custom Methods
    public void popUI(final ArrayList<Vehicle> vehicles){

        // Get screen orientation
        Display display = ((WindowManager)getSystemService(WINDOW_SERVICE)).getDefaultDisplay();
        int orientation = display.getRotation();

        // Variables
        final TextView mBrandText = (TextView) findViewById(R.id.brandText);
        final TextView mModelText = (TextView) findViewById(R.id.modelText);
        final TextView mPriceText = (TextView) findViewById(R.id.priceText);
        final TextView mBrandLabel = (TextView) findViewById(R.id.brandLabel);
        final TextView mModelLabel = (TextView) findViewById(R.id.modelLabel);
        final TextView mPriceLabel = (TextView) findViewById(R.id.priceLabel);
        final ImageView mImageView = (ImageView) findViewById(R.id.imageView);

        mListView = (ListView) findViewById(R.id.listView);
        mSpinner = (Spinner) findViewById(R.id.spinner);
        ArrayList<String> brands = new ArrayList<String>();

        // Create iterator to loop through vehicles array
        Iterator iterator = (Iterator) vehicles.iterator();

        // Check screen orientation
        if (orientation == Surface.ROTATION_90 || orientation == Surface.ROTATION_270){
            Log.i(TAG, "Landscape");

            while (iterator.hasNext()) {
                Vehicle vehicle = (Vehicle) iterator.next();
                brands.add(vehicle.getBrand());
            }

            // Create adapter for the listview
            mOSAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, brands);
            mListView.setAdapter(mOSAdapter);

            // ListView Event Listener
            mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    mBrandText.setText(vehicles.get(i).getBrand());
                    mModelText.setText(vehicles.get(i).getModel());
                    mPriceText.setText(vehicles.get(i).getPrice());
                    mImageView.setImageResource(vehicles.get(i).getPic());
                    mBrandLabel.setVisibility(TextView.VISIBLE);
                    mModelLabel.setVisibility(TextView.VISIBLE);
                    mPriceLabel.setVisibility(TextView.VISIBLE);
                    mImageView.setVisibility(ImageView.VISIBLE);
                }
            });

        } else {
            Log.i(TAG, "Portrait");

            while (iterator.hasNext()) {
                Vehicle vehicle = (Vehicle) iterator.next();
                brands.add(vehicle.getBrand());
            }

            // Create an adapter for the spinner
            mOSAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, brands);
            mSpinner.setAdapter(mOSAdapter);

            // Spinner Event Listener
            mSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                    mBrandText.setText(vehicles.get(i).getBrand());
                    mModelText.setText(vehicles.get(i).getModel());
                    mPriceText.setText(vehicles.get(i).getPrice());
                    mImageView.setImageResource(vehicles.get(i).getPic());
                }

                @Override
                public void onNothingSelected(AdapterView<?> adapterView) {
                    Log.i(TAG, "Nothing");
                }
            });
        }
    }
}
