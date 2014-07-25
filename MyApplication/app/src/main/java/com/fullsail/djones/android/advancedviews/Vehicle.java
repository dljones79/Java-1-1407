package com.fullsail.djones.android.advancedviews;

import android.media.Image;
import android.widget.ImageView;

/**
 * Created by David on 7/24/14.
 */
public class Vehicle {

    // Variable declarations
    private String vehicleBrand = "";
    private String vehicleModel = "";
    private String vehiclePrice = "";
    private int vehiclePic;

    // Set Brand
    public void setBrand (String vehicleBrand){
        this.vehicleBrand = vehicleBrand;
    }

    // Retrieve Brand
    public String getBrand(){
        return vehicleBrand;
    }

    // Set Model
    public void setModel(String vehicleModel){
        this.vehicleModel = vehicleModel;
    }

    // Retrieve Model
    public String getModel(){
        return vehicleModel;
    }

    // Set Price
    public void setPrice(String vehiclePrice){
        this.vehiclePrice = vehiclePrice;
    }

    // Retrieve Price
    public String getPrice(){
        return vehiclePrice;
    }

    // Set Image
    public void setPic (int vehiclePic) {
        this.vehiclePic = vehiclePic;
    }

    // Retrieve Image
    public int getPic(){
        return vehiclePic;
    }
}
