package com.example.android.pokeapi.Main;

import com.example.android.pokeapi.Base.PresenterBase;
import com.example.android.pokeapi.Main.Data.Cloud.MainService;
import com.example.android.pokeapi.Main.Data.Models.Names.Names;
import com.example.android.pokeapi.Main.Data.Models.Names.Result;
import com.example.android.pokeapi.Base.ApiClient;

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

        MainService apiService =
                ApiClient.getClient().create(MainService.class);

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



}
