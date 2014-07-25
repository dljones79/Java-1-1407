package com.fullsail.djones.android.advancedviews;

/**
 * Created by David on 7/24/14.
 */
public class Vehicle {
    private String vehicleBrand = "";
    private String vehicleModel = "";
    private String vehiclePrice = "";

    public void setBrand (String vehicleBrand){
        this.vehicleBrand = vehicleBrand;
    }

    public String getBrand(){
        return vehicleBrand;
    }

    public void setModel(String vehicleModel){
        this.vehicleModel = vehicleModel;
    }

    public String getModel(){
        return vehicleModel;
    }

    public void setPrice(String vehiclePrice){
        this.vehiclePrice = vehiclePrice;
    }

    public String getPrice(){
        return vehiclePrice;
    }
}
