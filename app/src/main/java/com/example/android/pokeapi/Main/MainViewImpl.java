package com.example.android.pokeapi.Main;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.android.pokeapi.Data.Networking.Models.Networking.Api.ApiClient;
import com.example.android.pokeapi.Data.Networking.Models.Networking.Api.ApiInterface;
import com.example.android.pokeapi.Base.BaseActivity;
import com.example.android.pokeapi.Data.Networking.Models.Models.Creature.Names.Names;
import com.example.android.pokeapi.Data.Networking.Models.Models.Creature.Names.Result;
import com.example.android.pokeapi.R;
import com.example.android.pokeapi.Utils.UiUtils;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainViewImpl extends BaseActivity implements MainContract.MainView {

    MainPresenterImpl mainPresenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mainPresenter = new MainPresenterImpl(this);

        // reload button click listener
        ////////////////////////////////////////////////////////////////////////
        Button reload = (Button)findViewById(R.id.Main_Reload_Button);
        reload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mainPresenter.onReloadClicked();
            }
        });
        //////////////////////////////////////////////////////////////////////////

        //make a network request to get creature names
        mainPresenter.requestCreaturesNames();


    }

    @Override
    public void fillCreaturesNames(ArrayList<Result> results) {
        NamesAdapter adapter = new NamesAdapter(getBaseContext() , results);
        ListView list = (ListView) findViewById(R.id.main_ListView);
        list.setAdapter(adapter);
    }

    @Override
    public void controlUi(int operation) {
        controlUi(findViewById(R.id.Main_Progress)
                , findViewById(R.id.Main_Reload_Parent)
                , findViewById(R.id.Main_Content_Parent)
                , operation);
    }
}
