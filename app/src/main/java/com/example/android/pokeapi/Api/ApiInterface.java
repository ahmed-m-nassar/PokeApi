package com.example.android.pokeapi.Api;

import com.example.android.pokeapi.Models.Creature.Creature;
import com.example.android.pokeapi.Models.Names.Names;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiInterface {
    @GET("pokemon/")
    Call<Names> getPokemonNames();

    @GET("pokemon/{id}") //{id} means you will put a value here
    Call<Creature> getCreatureDetails(@Path("id") int id);

}

