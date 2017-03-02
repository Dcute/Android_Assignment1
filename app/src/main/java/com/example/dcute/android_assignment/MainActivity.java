package com.example.dcute.android_assignment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button b = (Button) findViewById(R.id.setting_button);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, Pop.class));
                //jump from an activity to another activity
            }
        });
    }

    public void start_button_click(View v) {
        if(v.getId() == R.id.start_game_button) {
            Intent i = new Intent(MainActivity.this, StartGame.class);
            startActivity(i);
            //jump from an activity to another activity
        }
    }
}
