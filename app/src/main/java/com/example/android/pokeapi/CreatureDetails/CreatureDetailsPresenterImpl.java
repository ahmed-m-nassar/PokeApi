package com.example.android.pokeapi.CreatureDetails;

import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.pokeapi.Base.PresenterBase;
import com.example.android.pokeapi.Data.Networking.Models.Models.Creature.Creature.Abilities;
import com.example.android.pokeapi.Data.Networking.Models.Models.Creature.Creature.Creature;
import com.example.android.pokeapi.Data.Networking.Models.Models.Creature.Creature.Moves;
import com.example.android.pokeapi.Data.Networking.Models.Networking.Api.ApiClient;
import com.example.android.pokeapi.Data.Networking.Models.Networking.Api.ApiInterface;
import com.example.android.pokeapi.Data.Networking.Models.StaticData.CreatureUrl;
import com.example.android.pokeapi.R;
import com.example.android.pokeapi.Utils.UiUtils;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CreatureDetailsPresenterImpl extends PresenterBase implements CreatureDetailsContract.CreatureDetailsPresenter {

    CreatureDetailsContract.CreatureDetailsView creatureDetailsView ;

    public CreatureDetailsPresenterImpl(CreatureDetailsViewImpl creatureDetailsView) {
        this.creatureDetailsView = creatureDetailsView;
    }

    @Override
    public void requestCreatureDetails() {

        //showing progress bar
        creatureDetailsView.controlUi(0);

        ApiInterface apiService =
                ApiClient.getClient().create(ApiInterface.class);
        Call<Creature> call = apiService.getCreatureDetails(getCreatureNumFromUrl(CreatureUrl.creatureUrl));
        call.enqueue(new Callback<Creature>() {
            @Override
            public void onResponse(Call<Creature>call,
                                   Response<Creature> response) {
                //showing layout content
                creatureDetailsView.controlUi(2);

                //filling the content of the layout with creature details
                creatureDetailsView.fillCreatureDetails(response.body());
            }

            @Override
            public void onFailure(Call<Creature> call, Throwable t) {

                //showing reload button
                creatureDetailsView.controlUi(1);
            }
        });

    }

    @Override
    public void onReloadClicked() {
        requestCreatureDetails();
    }

    @Override
    public int getCreatureNumFromUrl(String url) {

        if (url.charAt(url.length() - 1) == '/') {
            url = url.substring(0, url.length() - 1);
        }

        int startIndex = url.lastIndexOf('/');
        return Integer.valueOf(url.substring(startIndex + 1, url.length()));

    }
}
