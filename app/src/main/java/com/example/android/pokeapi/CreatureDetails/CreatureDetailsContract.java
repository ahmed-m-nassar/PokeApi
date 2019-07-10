package com.example.android.pokeapi.CreatureDetails;

import com.example.android.pokeapi.Data.Networking.Models.Models.Creature.Creature.Creature;

public interface CreatureDetailsContract {

    interface CreatureDetailsView {

        /**
         * fills the layout with the creature details passed in creature parameters
         * @param creature contains the details to be filled in the layout
         */
        void fillCreatureDetails(Creature creature);

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
        void requestCreatureDetails();


        /**
         * handles the reload button click and calls requestCreatureDetails function
         */
        void onReloadClicked();

        /**
         * this method gets the creature number from the url given
         * @param url the url of the creature
         * @return the number of the creature
         */
         int getCreatureNumFromUrl(String url);
    }
}
