package com.example.android.pokeapi.Utils;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

public class UiUtils {
    /**
     * this function controls the ui in the network call operation
     * to show loading page (progressBar)
     * or to show reload button in case of failure
     * or to show the content of the activity in case of success
     * @param activity
     * @param progressBar     the progress bar id to control ist visibility
     * @param reload          the reload button id to control ist visibility
     * @param content         the parent layout id to control ist visibility
     * @param operationType   the operation type -> 0 to view the loading bar
     *                                              1 to view the reload button
     *                                              else to view the content
     */
    public static void controlUi (Activity activity , View progressBar , View reload , View content , int operationType) {
       progressBar.setVisibility(View.GONE);

       reload.setVisibility(View.GONE);

       content.setVisibility(View.GONE);

        switch (operationType) {
            case 0 :
                progressBar.setVisibility(View.VISIBLE);
                break;
            case 1:
                reload.setVisibility(View.VISIBLE);
                break;
            default :
                content.setVisibility(View.VISIBLE);

        }

    }


}
