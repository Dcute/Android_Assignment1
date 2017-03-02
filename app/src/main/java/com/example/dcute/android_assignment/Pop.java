package com.example.dcute.android_assignment;

import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;

/**
 * Created by Dcute on 2/24/2017.
 */

public class Pop extends AppCompatActivity implements View.OnClickListener {

    private Button PlayButton, StopButton;

    private SeekBar volumeSeekBar;
    //Adjust The volume
    private AudioManager audioManager;
    //AudioManager provides access to volume and ringer mode control.

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.pop_out_window);

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;

        getWindow().setLayout((int) (width * .9), (int) (height * .7));
        //setting the margin of the pop out page

        PlayButton = (Button) findViewById(R.id.PlayButton); //Get the reference from XML File
        StopButton = (Button) findViewById(R.id.StopButton); //Get the reference from XML File

        PlayButton.setOnClickListener(this);
        StopButton.setOnClickListener(this);

        setVolumeControlStream(AudioManager.STREAM_MUSIC);
        initControls();
    }


    @Override
    public void onClick(View v) {
        if(v == PlayButton){
            startService(new Intent(this, myPlayService.class));
            //Start Playing the Music
        }else if(v == StopButton){
            stopService((new Intent(this, myPlayService.class)));
            //Stop Playing the Music
        }
    }


    private void initControls(){
        try{
            volumeSeekBar = (SeekBar)findViewById(R.id.adjust_volume_button);   //Get reference from popwindow.xml
            audioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
            volumeSeekBar.setMax(audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC)); //Returns the maximum volume index for a particular stream.
            volumeSeekBar.setProgress(audioManager.getStreamVolume(AudioManager.STREAM_MUSIC)); //Sets the volume index for a particular stream.
            volumeSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                @Override
                public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                    audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, progress, 0);
                }

                @Override
                public void onStartTrackingTouch(SeekBar seekBar) {

                }

                @Override
                public void onStopTrackingTouch(SeekBar seekBar) {

                }
            });

        }catch (Exception e) {

        }
    }
}
