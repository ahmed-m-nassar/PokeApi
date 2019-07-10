package com.example.android.pokeapi.Data.Networking.Models.Networking.Api;

import com.example.android.pokeapi.Data.Networking.Models.Models.Creature.Creature.Creature;
import com.example.android.pokeapi.Data.Networking.Models.Models.Creature.Names.Names;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ApiInterface {
    @GET("pokemon/")
    Call<Names> getPokemonNames();

    @GET("pokemon/{id}")
    Call<Creature> getCreatureDetails(@Path("id") int id);

}

