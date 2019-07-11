package com.example.android.pokeapi.CreatureDetails.Data.Models.Creature;

import com.google.gson.annotations.SerializedName;

public class Abilities {
    @SerializedName("ability")
    private Ability ability;

    public Abilities(Ability ability) {
        this.ability = ability;
    }

    public Ability getAbility() {
        return ability;
    }

    public void setAbility(Ability ability) {
        this.ability = ability;
    }
}
