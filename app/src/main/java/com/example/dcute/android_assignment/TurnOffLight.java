package com.example.dcute.android_assignment;

import android.app.Activity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.WindowManager;

/**
 * Created by Dcute on 2/25/2017.
 */

public class TurnOffLight extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.turn_off_light);


        WindowManager.LayoutParams WMLP = getWindow().getAttributes();

        WMLP.height = 1030;
        WMLP.gravity= Gravity.TOP;
        WMLP.width = WindowManager.LayoutParams.MATCH_PARENT;

        getWindow().setLayout(WMLP.width, WMLP.height);
        //setting the margin to replace the screen (Popup features)
    }
}
