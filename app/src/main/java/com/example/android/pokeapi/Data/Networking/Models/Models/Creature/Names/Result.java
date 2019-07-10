package com.example.android.pokeapi.Data.Networking.Models.Models.Creature.Names;

import com.google.gson.annotations.SerializedName;

public class Result {
    @SerializedName("name")
    private String mName;

    @SerializedName("url")
    private String mUrl;

    public Result(String mName, String mUrl) {
        this.mName = mName;
        this.mUrl = mUrl;
    }

    public String getmName() {
        return mName;
    }

    public void setmName(String mName) {
        this.mName = mName;
    }

    public String getmUrl() {
        return mUrl;
    }

    public void setmUrl(String mUrl) {
        this.mUrl = mUrl;
    }
}
