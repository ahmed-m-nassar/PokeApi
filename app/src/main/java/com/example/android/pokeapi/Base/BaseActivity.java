package com.example.android.pokeapi.Base;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.android.pokeapi.R;

public class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);
    }
    /**
     * this function controls the ui in the network call operation
     * to show loading page (progressBar)
     * or to show reload button in case of failure
     * or to show the content of the activity in case of success
     * @param progressBar     the progress bar id to control ist visibility
     * @param reload          the reload button id to control ist visibility
     * @param content         the parent layout id to control ist visibility
     * @param operationType   the operation type -> 0 to view the loading bar
     *                                              1 to view the reload button
     *                                              else to view the content
     */
    protected void controlUi ( View progressBar , View reload , View content , int operationType) {
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
