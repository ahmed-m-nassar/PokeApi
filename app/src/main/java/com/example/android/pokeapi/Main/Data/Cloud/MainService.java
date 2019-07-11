package com.example.android.pokeapi.Main.Data.Cloud;

import com.example.android.pokeapi.Main.Data.Models.Names.Names;

import retrofit2.Call;
import retrofit2.http.GET;

public interface MainService {
    @GET("pokemon/")
    Call<Names> getPokemonNames();
}
