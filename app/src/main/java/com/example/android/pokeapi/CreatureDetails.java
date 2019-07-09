package com.example.android.pokeapi;

import android.graphics.Movie;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.pokeapi.Adapters.Adapters.AbilitiesAdapter;
import com.example.android.pokeapi.Api.ApiClient;
import com.example.android.pokeapi.Api.ApiInterface;
import com.example.android.pokeapi.Models.Creature.Abilities;
import com.example.android.pokeapi.Models.Creature.Ability;
import com.example.android.pokeapi.Models.Creature.Creature;
import com.example.android.pokeapi.Models.Creature.Moves;
import com.example.android.pokeapi.Utils.UiUtils;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CreatureDetails extends AppCompatActivity {

    private String mUrl;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_creature_details);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            mUrl = extras.getString("url");
        }

        final int num = getCreatureNumberFromUrl(mUrl);

        requestCreatureDetails(num);

        Button reload = (Button)findViewById(R.id.Creature_Details_Reload_Button);
        reload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                requestCreatureDetails(num);
            }
        });


    }

    void requestCreatureDetails(int creatureNum) {
        UiUtils.controlUi(getParent()
                ,findViewById(R.id.Creature_Details_ProgressBar)
                ,findViewById(R.id.Creature_Details_ReloadParent)
                ,findViewById(R.id.Creature_Details_Content_Parent)
                ,0);
        ApiInterface apiService =
                ApiClient.getClient().create(ApiInterface.class);

        Call<Creature> call = apiService.getCreatureDetails(creatureNum);
        call.enqueue(new Callback<Creature>() {
            @Override
            public void onResponse(Call<Creature>call,
                                   Response<Creature> response) {
                UiUtils.controlUi(getParent()
                        ,findViewById(R.id.Creature_Details_ProgressBar)
                        ,findViewById(R.id.Creature_Details_ReloadParent)
                        ,findViewById(R.id.Creature_Details_Content_Parent)
                        ,2);
                // setting creature name
                //////////////////////////////////////////////////////////////////////
                TextView name = (TextView) findViewById(R.id.Creature_Details_name_textView);
                name.setText(response.body().getName());
                /////////////////////////////////////////////////////////////////////

                //setting image
                //////////////////////////////////////////////////////////////////
                Picasso.with(getBaseContext()).load(response.body().getSprites().getBackDefault()).
                        into((ImageView)findViewById(R.id.Creature_Details_Image));
                /////////////////////////////////////////////////////////////////


                //setting abilities
                /////////////////////////////////////////////////////////////
                List<Abilities> abilities = response.body().getAbilities();
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
                List<Moves> moves = response.body().getMoves();
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
            public void onFailure(Call<Creature>call, Throwable t) {
                Toast.makeText(CreatureDetails.this, "fail", Toast.LENGTH_SHORT).show();

                UiUtils.controlUi(getParent()
                        ,findViewById(R.id.Creature_Details_ProgressBar)
                        ,findViewById(R.id.Creature_Details_ReloadParent)
                        ,findViewById(R.id.Creature_Details_Content_Parent)
                        ,1);
            }
        });

    }

    /**
     * this method gets the creature number from the url given
     * @param url the url of the creature
     * @return the number of the creature
     */
    private int getCreatureNumberFromUrl(String url) {
        if(url.charAt(url.length() - 1 ) == '/') {
            url = url.substring(0,url.length() - 1 );
        }

        int startIndex = url.lastIndexOf('/');
        return Integer.valueOf(url.substring(startIndex + 1, url.length() ));
    }
}
