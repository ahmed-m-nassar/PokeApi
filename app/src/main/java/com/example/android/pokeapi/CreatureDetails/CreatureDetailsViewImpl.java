package com.example.android.pokeapi.CreatureDetails;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.pokeapi.Data.Networking.Models.Models.Creature.Creature.Abilities;
import com.example.android.pokeapi.Data.Networking.Models.Networking.Api.ApiClient;
import com.example.android.pokeapi.Data.Networking.Models.Networking.Api.ApiInterface;
import com.example.android.pokeapi.Base.BaseActivity;
import com.example.android.pokeapi.Data.Networking.Models.Models.Creature.Creature.Creature;
import com.example.android.pokeapi.Data.Networking.Models.Models.Creature.Creature.Moves;
import com.example.android.pokeapi.R;
import com.example.android.pokeapi.Utils.UiUtils;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CreatureDetailsViewImpl extends BaseActivity implements CreatureDetailsContract.CreatureDetailsView {

    private String mUrl;
    private CreatureDetailsPresenterImpl presenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_creature_details);

        presenter = new CreatureDetailsPresenterImpl(this);

        //making a network request to get creature details
        presenter.requestCreatureDetails();

        //setting reload button click listener
        /////////////////////////////////////////////////////////////////////////
        Button reload = (Button)findViewById(R.id.Creature_Details_Reload_Button);
        reload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.onReloadClicked();
            }
        });
        /////////////////////////////////////////////////////////////////////////


    }


    @Override
    public void fillCreatureDetails(Creature creature) {
        // setting creature name
        //////////////////////////////////////////////////////////////////////
        TextView name = (TextView) findViewById(R.id.Creature_Details_name_textView);
        name.setText(creature.getName());
        /////////////////////////////////////////////////////////////////////

        //setting image
        //////////////////////////////////////////////////////////////////
        Picasso.with(getBaseContext()).load(creature.getSprites().getBackDefault()).
                into((ImageView)findViewById(R.id.Creature_Details_Image));
        /////////////////////////////////////////////////////////////////


        //setting abilities
        /////////////////////////////////////////////////////////////
        List<Abilities> abilities = creature.getAbilities();
        ArrayList<String> abilitiesNames = new ArrayList<>();
        for(int i = 0 ; i < abilities.size() ; i++) {
            abilitiesNames.add(abilities.get(i).getAbility().getName());
        }
        AbilitiesAdapter abilitiesAdapter = new AbilitiesAdapter(getBaseContext(),abilitiesNames);
        ListView abilitiesList = (ListView)findViewById(R.id.Creature_Details_abilities_List);
        abilitiesList.setAdapter(abilitiesAdapter);
        //////////////////////////////////////////////////////////////

        //setting abilities
        /////////////////////////////////////////////////////////////
        List<Moves> moves = creature.getMoves();
        ArrayList<String> movesNames = new ArrayList<>();
        for(int i = 0 ; i < moves.size() ; i++) {
            movesNames.add(moves.get(i).getMove().getName());
        }
        AbilitiesAdapter movesAdapter = new AbilitiesAdapter(getBaseContext(),movesNames);
        ListView movesList = (ListView)findViewById(R.id.Creature_Details_Moves_ListView);
        movesList.setAdapter(movesAdapter);
        //////////////////////////////////////////////////////////////
    }

    @Override
    public void controlUi(int operation) {
        controlUi(findViewById(R.id.Creature_Details_ProgressBar)
                , findViewById(R.id.Creature_Details_ReloadParent)
                , findViewById(R.id.Creature_Details_Content_Parent)
                , operation);
    }

}
