package com.example.android.pokeapi.CreatureDetails.Data.Models.Creature;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Creature {

    @SerializedName("sprites")
    private Sprites sprites;
    @SerializedName("name")
    private String name;
    @SerializedName("moves")
    private List<Moves> moves;
    @SerializedName("abilities")
    private List<Abilities> abilities;

    public Creature(Sprites sprites, String name, List<Moves> moves, List<Abilities> abilities) {
        this.sprites = sprites;
        this.name = name;
        this.moves = moves;
        this.abilities = abilities;
    }
    public Creature() {}

    public Sprites getSprites() {
        return sprites;
    }

    public void setSprites(Sprites sprites) {
        this.sprites = sprites;
    }



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Moves> getMoves() {
        return moves;
    }

    public void setMoves(List<Moves> moves) {
        this.moves = moves;
    }

    public List<Abilities> getAbilities() {
        return abilities;
    }

    public void setAbilities(List<Abilities> abilities) {
        this.abilities = abilities;
    }
}
