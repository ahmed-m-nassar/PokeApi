package com.example.android.pokeapi.Main;

import com.example.android.pokeapi.Data.Networking.Models.Models.Creature.Names.Result;

import java.util.ArrayList;

public interface MainContract {

    interface MainView {

        /**
         * Fills the ist of names with the names in the passed array list
         * @param names contains the names that the list will be filled with
         */
        void fillCreaturesNames(ArrayList<Result> names);

        /**
         * controls the ui according to the operation
         * @param operation 0    --> show progress bar
         *                  1    --> show the reload button
         *                  else --> show the content
         */
        void controlUi(int operation);
    }

    interface MainPresenter {

        /**
         * make a network request using retrofit to get the names of the creatures
         */
        void requestCreaturesNames();


        /**
         * handles the reload button click and calls requestCreaturesNames function
         *
         */
        void onReloadClicked();


        /**
         * saves the clicked creature url in CreatureUrl class
         * @param url url to be saved
         */
        void saveCreatureUrl(String url);
    }


}
