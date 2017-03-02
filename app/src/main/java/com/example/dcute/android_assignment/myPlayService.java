package com.example.dcute.android_assignment;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.support.annotation.Nullable;


public class myPlayService extends Service {

    private MediaPlayer mediaPlayer;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
       return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        mediaPlayer =  MediaPlayer.create(this, R.raw.music_a); //Get the music from the raw Folder
        mediaPlayer.setLooping(true); //Set Loop, After the music play finish, it will replay again
        mediaPlayer.start();
        return START_STICKY;
    }

    @Override
    public void onDestroy(){
        super.onDestroy();
        mediaPlayer.stop();
        //Completely Stop the music
    }

}
