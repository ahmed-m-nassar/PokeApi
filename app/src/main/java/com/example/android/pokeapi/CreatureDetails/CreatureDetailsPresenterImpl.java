package com.example.android.pokeapi.CreatureDetails;

import com.example.android.pokeapi.Base.PresenterBase;
import com.example.android.pokeapi.CreatureDetails.Data.Cloud.CreatureDetailsService;
import com.example.android.pokeapi.CreatureDetails.Data.Models.Creature.Abilities;
import com.example.android.pokeapi.CreatureDetails.Data.Models.Creature.Creature;
import com.example.android.pokeapi.CreatureDetails.Data.Models.Creature.Moves;

import com.example.android.pokeapi.Base.ApiClient;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class CreatureDetailsPresenterImpl extends PresenterBase implements CreatureDetailsContract.CreatureDetailsPresenter {

    CreatureDetailsContract.CreatureDetailsView creatureDetailsView ;

    public CreatureDetailsPresenterImpl(CreatureDetailsActivityImpl creatureDetailsView) {
        this.creatureDetailsView = creatureDetailsView;
    }

    @Override
    public void requestCreatureDetails(String url) {


        //showing progress bar
        creatureDetailsView.controlUi(0);

        CreatureDetailsService apiService =
                ApiClient.getClient().create(CreatureDetailsService.class);
        Call<Creature> call = apiService.getCreatureDetails(getCreatureNumFromUrl(url));

        call.enqueue(new Callback<Creature>() {
            @Override
            public void onResponse(Call<Creature>call,
                                   Response<Creature> response) {
                //showing layout content
                    creatureDetailsView.controlUi(2);

                //filling the content of the layout with creature details
                //////////////////////////////////////////////////////////////////////
                    //adding the creature name
                    creatureDetailsView.viewCreatureName(response.body().getName());

                    //adding the creature image
                    creatureDetailsView.viewCreatureImage(response.body().getSprites().getBackDefault());

                    //adding the creature abilities
                    creatureDetailsView.viewCreatureAbilities(getAbilitiesNames((ArrayList<Abilities>) response.body().getAbilities()));

                    //adding the creature moves
                    creatureDetailsView.viewCreatureMoves(getMovesNames((ArrayList<Moves>) response.body().getMoves()));
                ////////////////////////////////////////////////////////////////////
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
        requestCreatureDetails(creatureDetailsView.getCreatureUrl());
    }


    //region private (utility) functions

    /**
     * this method gets the creature number from the url given
     * @param url the url of the creature
     * @return the number of the creature
     */
    private int getCreatureNumFromUrl(String url) {

        if (url.charAt(url.length() - 1) == '/') {
            url = url.substring(0, url.length() - 1);
        }

        int startIndex = url.lastIndexOf('/');
        return Integer.valueOf(url.substring(startIndex + 1, url.length()));

    }

    /**
     * this function extracts an array of abilities names (array of strings) from Array of abilities
     * @param abilities
     * @return
     */
    private ArrayList<String> getAbilitiesNames(ArrayList<Abilities> abilities) {
        ArrayList<String> abilitiesNames = new ArrayList<>();
        for(int i = 0 ; i < abilities.size() ; i++) {
            abilitiesNames.add(abilities.get(i).getAbility().getName());
        }

        return abilitiesNames;
    }

    /**
     * this function extracts an array of moves names (array of strings) from Array of moves
     * @param moves
     * @return
     */
    private ArrayList<String> getMovesNames(ArrayList<Moves> moves) {

        ArrayList<String> movesNames = new ArrayList<>();
        for(int i = 0 ; i < moves.size() ; i++) {
            movesNames.add(moves.get(i).getMove().getName());
        }
        return movesNames;
    }

    //endregion

}
