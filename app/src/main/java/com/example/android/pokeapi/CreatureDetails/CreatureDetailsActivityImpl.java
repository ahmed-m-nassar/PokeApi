package com.example.android.pokeapi.CreatureDetails;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;


import com.example.android.pokeapi.Base.BaseActivity;

import com.example.android.pokeapi.CreatureDetails.Adapters.AbilitiesAdapter;
import com.example.android.pokeapi.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class CreatureDetailsActivityImpl extends BaseActivity implements CreatureDetailsContract.CreatureDetailsView {
    private TextView mCreatureName;
    private ImageView mCreatureImage;
    private ListView mCreatureAbilities;
    private ListView mCreatureMoves;
    private String mUrl;
    private CreatureDetailsPresenterImpl presenter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_creature_details);

        //getting views and presenter
        ///////////////////////////////////////////////
        mCreatureName = (TextView) findViewById(R.id.Creature_Details_name_textView);
        mCreatureImage = (ImageView) findViewById(R.id.Creature_Details_Image);
        mCreatureAbilities = (ListView) findViewById(R.id.Creature_Details_abilities_List);
        mCreatureMoves = (ListView) findViewById(R.id.Creature_Details_Moves_ListView);
        presenter = new CreatureDetailsPresenterImpl(this);
        /////////////////////////////////////////////

        //getting the url of the creature choosen from previous screen
        /////////////////////////////////////////////////////////
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            mUrl = bundle.getString("url");
        }
        /////////////////////////////////////////////////////////

        //making a network request to get creature details
        presenter.requestCreatureDetails(mUrl);

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
    public String getCreatureUrl() {
        return mUrl;
    }

    @Override
    public void viewCreatureName(String name) {
        mCreatureName.setText(name);
    }

    @Override
    public void viewCreatureAbilities(ArrayList<String> abilities) {
        AbilitiesAdapter adapter = new AbilitiesAdapter(this,abilities);
        mCreatureAbilities.setAdapter(adapter);
    }

    @Override
    public void viewCreatureMoves(ArrayList<String> moves) {
        AbilitiesAdapter adapter = new AbilitiesAdapter(this , moves);
        mCreatureMoves.setAdapter(adapter);
    }


    @Override
    public void viewCreatureImage(String imageUrl) {
        Picasso.with(this).load(imageUrl).into(mCreatureImage);
    }

    @Override
    public void controlUi(int operation) {
        controlUi(findViewById(R.id.Creature_Details_ProgressBar)
                , findViewById(R.id.Creature_Details_ReloadParent)
                , findViewById(R.id.Creature_Details_Content_Parent)
                , operation);
    }

}
