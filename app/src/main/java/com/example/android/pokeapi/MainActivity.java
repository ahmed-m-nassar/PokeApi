package com.example.android.pokeapi;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import com.example.android.pokeapi.Adapters.Adapters.NamesAdapter;
import com.example.android.pokeapi.Api.ApiClient;
import com.example.android.pokeapi.Api.ApiInterface;
import com.example.android.pokeapi.Models.Names.Names;
import com.example.android.pokeapi.Models.Names.Result;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ApiInterface apiService =
                ApiClient.getClient().create(ApiInterface.class);

        Call<Names> call = apiService.getPokemonNames();
        call.enqueue(new Callback<Names>() {
            @Override
            public void onResponse(Call<Names>call,
                                   Response<Names> response) {
                ArrayList<Result> results = (ArrayList<Result>) response.body().getResults();

                NamesAdapter adapter = new NamesAdapter(getBaseContext() , results);
                ListView list = (ListView) findViewById(R.id.main_ListView);
                list.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<Names> call, Throwable t) {
                Toast.makeText(MainActivity.this, "failed", Toast.LENGTH_SHORT).show();
            }


        });

    }
}
