package com.example.android.pokeapi.CreatureDetails;

import com.example.android.pokeapi.Main.Data.Models.Names.Result;

import java.util.ArrayList;

public interface CreatureDetailsContract {

    interface CreatureDetailsView {

        /**
         * views the creature name received from the network request
         * @param name
         */
        void viewCreatureName(String name);


        /**
         * views the creature abilities received from the network request
         * @param abilities
         */
        void viewCreatureAbilities(ArrayList<String> abilities);


        /**
         * views the creature moves received from the network request
         * @param moves
         */
        void viewCreatureMoves(ArrayList<String> moves);


        /**
         * views the creature image received from the network request
         * @param imageUrl
         */
        void viewCreatureImage(String imageUrl);

        /**
         *this function returns the url of the picked creature from last screen
         * @return
         */
        String getCreatureUrl();

        /**
         * controls the ui according to the operation
         * @param operation 0    --> show progress bar
         *                  1    --> show the reload button
         *                  else --> show the content
         */
        void controlUi(int operation);
    }

    interface CreatureDetailsPresenter {



        /**
         * make a network request using retrofit to get the details of the creatures
         */
        void requestCreatureDetails(String url);


        /**
         * handles the reload button click and calls requestCreatureDetails function
         */
        void onReloadClicked();

    }
}
