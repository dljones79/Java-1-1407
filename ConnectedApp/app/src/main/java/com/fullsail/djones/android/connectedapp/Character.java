package com.fullsail.djones.android.connectedapp;

import android.util.Log;

import org.json.JSONObject;

/**
 * Created by David Jones on 7/30/14.
 */
public class Character {

    final String TAG = "Character Class";

    private String mName;
    private String mCharClass;
    private String mLevel;
    private String mServer;
    private String mAAS;

    public Character(){}

    public Character(String name, String charClass, String level, String server, String aas){
        mName = name;
        mCharClass = charClass;
        mLevel = level;
        mServer = server;
        mAAS = aas;
    }

    public Character(JSONObject characterData){
        try{
            mName = characterData.getJSONObject("name").getString("first");
            mCharClass = characterData.getJSONObject("type").getString("class");
            mLevel = characterData.getJSONObject("type").getString("level");
            mServer = characterData.getJSONObject("locationdata").getString("world");
            //mAAS = characterData.getJSONObject("alternateadvancements").getString("availablepoints");
        } catch (Exception e){
            Log.e(TAG, "Failure");
        }
    }

    public String getName() {
        return mName;
    }

    public void setName(String mName){
        this.mName = mName;
    }

    public String getCharClass() {
        return mCharClass;
    }

    public void setCharClass(String mCharClass){
        this.mCharClass = mCharClass;
    }

    public String getLevel(){
        return mLevel;
    }

    public void setLevel(String mLevel){
        this.mLevel = mLevel;
    }

    public String getServer() {
        return mServer;
    }

    public void setServer(String mServer){
        this.mServer = mServer;
    }

    public String getAAS(){
        return mAAS;
    }

    public void setAAS(String mAAS){
        this.mAAS = mAAS;
    }
}
