package com.example.android.pokeapi;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.android.pokeapi.Adapters.Adapters.NamesAdapter;
import com.example.android.pokeapi.Api.ApiClient;
import com.example.android.pokeapi.Api.ApiInterface;
import com.example.android.pokeapi.Models.Names.Names;
import com.example.android.pokeapi.Models.Names.Result;
import com.example.android.pokeapi.Utils.UiUtils;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        requestCreaturesNames();
        Button reload = (Button)findViewById(R.id.Main_Reload_Button);
        reload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                requestCreaturesNames();
            }
        });

    }

    void requestCreaturesNames() {
        UiUtils.controlUi(getParent()
                ,findViewById(R.id.Main_Progress)
                ,findViewById(R.id.Main_Reload_Parent)
                ,findViewById(R.id.Main_Content_Parent)
                ,0);
        ApiInterface apiService =
                ApiClient.getClient().create(ApiInterface.class);

        Call<Names> call = apiService.getPokemonNames();
        call.enqueue(new Callback<Names>() {
            @Override
            public void onResponse(Call<Names>call,
                                   Response<Names> response) {
                UiUtils.controlUi(getParent()
                        ,findViewById(R.id.Main_Progress)
                        ,findViewById(R.id.Main_Reload_Parent)
                        ,findViewById(R.id.Main_Content_Parent)
                        ,2);
                ArrayList<Result> results = (ArrayList<Result>) response.body().getResults();

                NamesAdapter adapter = new NamesAdapter(getBaseContext() , results);
                ListView list = (ListView) findViewById(R.id.main_ListView);
                list.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<Names> call, Throwable t) {
                UiUtils.controlUi(getParent()
                        ,findViewById(R.id.Main_Progress)
                        ,findViewById(R.id.Main_Reload_Parent)
                        ,findViewById(R.id.Main_Content_Parent)
                        ,1);
                Toast.makeText(MainActivity.this, "failed", Toast.LENGTH_SHORT).show();
            }


        });

    }
}
