package com.fullsail.djones.android.connectedapp;

import android.content.Context;
import android.net.NetworkInfo;
import android.net.ConnectivityManager;

/**
 * Created by David on 7/31/14.
 */
public class ConnectionDetection {
    private Context context;

    public ConnectionDetection(Context context){
        this.context = context;
    }

    public boolean connected(){
        ConnectivityManager connectionStatus = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectionStatus != null){
            NetworkInfo[] info = connectionStatus.getAllNetworkInfo();
            if (info != null)
                for (int i = 0; i < info.length; i++)
                    if (info[i].getState() == NetworkInfo.State.CONNECTED){
                        return true;
                    }
        }return false;
    }

}
