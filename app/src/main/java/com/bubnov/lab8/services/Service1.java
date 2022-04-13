package com.bubnov.lab8.services;

import android.app.IntentService;
import android.content.Intent;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.util.Log;

import androidx.annotation.Nullable;

public class Service1 extends IntentService {

    private static final String TAG = Service1.class.getName();
    public Service1(){
        super(TAG);
    }


    @Override
    protected void onHandleIntent(@Nullable Intent intent) {

        if(intent != null){
            int number = intent.getIntExtra("number", 0);
            for(int i=0; i<number; i++){
                Log.i(TAG, "onHandleIntent: "+Math.sqrt(i));
            }
            Uri notification = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
            Ringtone ringtone = RingtoneManager.getRingtone(getApplicationContext(), notification);
            ringtone.play();
        }

    }
}
