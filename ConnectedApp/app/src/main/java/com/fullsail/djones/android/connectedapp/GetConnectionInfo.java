package com.fullsail.djones.android.connectedapp;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

/**
 * Created by David on 7/30/14.
 */
public class GetConnectionInfo extends Activity {
    String TAG = "ConnectedApp";

    public void testConnection(){
        // Get a connectivity manager
        ConnectivityManager manager = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);

        // Get active network info
        NetworkInfo networkInfo = manager.getActiveNetworkInfo();

        // Check connection
        if (networkInfo != null) {
            if (networkInfo.getType() == ConnectivityManager.TYPE_MOBILE) {
                // 3G/4G Data
            } else if (networkInfo.getType() == ConnectivityManager.TYPE_WIFI) {
                // WiFi Data
            }

            if (networkInfo.isConnected()) {
                // Valid Connection
                Log.i(TAG, "Connected");
            } else {
                Log.i(TAG, "Not Connected");
            }
        }
    }
}

