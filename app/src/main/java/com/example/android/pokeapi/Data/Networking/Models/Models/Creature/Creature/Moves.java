package com.example.android.pokeapi.Data.Networking.Models.Models.Creature.Creature;

import com.google.gson.annotations.SerializedName;

public class Moves {

    @SerializedName("move")
    private Move move;

    public Moves(Move move) {
        this.move = move;
    }

    public Move getMove() {
        return move;
    }

    public void setMove(Move move) {
        this.move = move;
    }
}
