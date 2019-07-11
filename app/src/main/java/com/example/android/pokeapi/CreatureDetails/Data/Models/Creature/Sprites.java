package com.example.android.pokeapi.CreatureDetails.Data.Models.Creature;

import com.google.gson.annotations.SerializedName;

public class Sprites {
    @SerializedName("front_shiny")
    private String frontShiny;
    @SerializedName("front_default")
    private String frontDefault;
    @SerializedName("back_shiny")
    private String backShiny;
    @SerializedName("back_default")
    private String backDefault;

    public Sprites(String frontShiny, String frontDefault, String backShiny, String backDefault) {
        this.frontShiny = frontShiny;
        this.frontDefault = frontDefault;
        this.backShiny = backShiny;
        this.backDefault = backDefault;
    }

    public String getFrontShiny() {
        return frontShiny;
    }

    public void setFrontShiny(String frontShiny) {
        this.frontShiny = frontShiny;
    }

    public String getFrontDefault() {
        return frontDefault;
    }

    public void setFrontDefault(String frontDefault) {
        this.frontDefault = frontDefault;
    }

    public String getBackShiny() {
        return backShiny;
    }

    public void setBackShiny(String backShiny) {
        this.backShiny = backShiny;
    }

    public String getBackDefault() {
        return backDefault;
    }

    public void setBackDefault(String backDefault) {
        this.backDefault = backDefault;
    }
}
