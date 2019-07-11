package com.example.android.pokeapi.Main;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.example.android.pokeapi.Base.BaseActivity;
import com.example.android.pokeapi.Main.Adapters.NamesAdapter;
import com.example.android.pokeapi.Main.Data.Models.Names.Result;
import com.example.android.pokeapi.R;

import java.util.ArrayList;

public class MainActivityImpl extends BaseActivity implements MainContract.MainView {

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
