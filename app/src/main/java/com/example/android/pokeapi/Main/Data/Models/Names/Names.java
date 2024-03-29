package com.example.android.pokeapi.Main.Data.Models.Names;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Names {
    @SerializedName("results")
    private List<Result> results;

    public Names(List<Result> results) {
        this.results = results;
    }

    public List<Result> getResults() {
        return results;
    }

    public void setResults(List<Result> results) {
        this.results = results;
    }
}
