package com.example.android.pokeapi.CreatureDetails.Data.Cloud;


import com.example.android.pokeapi.CreatureDetails.Data.Models.Creature.Creature;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface CreatureDetailsService {

    @GET("pokemon/{id}")
    Call<Creature> getCreatureDetails(@Path("id") int id);

}
