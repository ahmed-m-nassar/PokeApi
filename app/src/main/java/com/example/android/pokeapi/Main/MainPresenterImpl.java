package com.example.android.pokeapi.Main;

import android.widget.Toast;

import com.example.android.pokeapi.Base.PresenterBase;
import com.example.android.pokeapi.Data.Networking.Models.Models.Creature.Names.Names;
import com.example.android.pokeapi.Data.Networking.Models.Models.Creature.Names.Result;
import com.example.android.pokeapi.Data.Networking.Models.Networking.Api.ApiClient;
import com.example.android.pokeapi.Data.Networking.Models.Networking.Api.ApiInterface;
import com.example.android.pokeapi.Data.Networking.Models.StaticData.CreatureUrl;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainPresenterImpl extends PresenterBase implements MainContract.MainPresenter {
    private MainContract.MainView mainView;

    public MainPresenterImpl(MainContract.MainView mainView) {
        this.mainView = mainView;
    }

    @Override
    public void requestCreaturesNames() {

       //showing progress bar
       mainView.controlUi(0);

        ApiInterface apiService =
                ApiClient.getClient().create(ApiInterface.class);

        Call<Names> call = apiService.getPokemonNames();
        call.enqueue(new Callback<Names>() {
            @Override
            public void onResponse(Call<Names>call,
                                   Response<Names> response) {
                //show content of the ui
                mainView.controlUi(2);
                //fill the content of the ui
                mainView.fillCreaturesNames((ArrayList<Result>) response.body().getResults());
            }

            @Override
            public void onFailure(Call<Names> call, Throwable t) {
                //show the reload button
                mainView.controlUi(1);
            }


        });


    }

    @Override
    public void onReloadClicked() {
        requestCreaturesNames();
    }

    @Override
    public void saveCreatureUrl(String url) {
        CreatureUrl.creatureUrl = url;
    }
}
