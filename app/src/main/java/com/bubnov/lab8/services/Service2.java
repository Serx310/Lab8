package com.bubnov.lab8.services;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class Service2 extends Service {

    private static final String TAG = Service2.class.getName();
    private boolean running = false;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if(running) Toast.makeText(this, TAG+" : Service already running", Toast.LENGTH_LONG).show();
        else{
            final int currentId = startId;
            Runnable runnable = new Runnable(){
                @Override
                public void run(){
                    long timeOut = System.currentTimeMillis() + 30000;
                    running = true;
                    while(running && System.currentTimeMillis() < timeOut){
                        synchronized (this){
                            sendMessage(currentId);
                            try{
                                wait(5000);
                            }catch(Exception e){e.printStackTrace();}
                        }
                    }
                    stopSelf();
                }
            };
            Thread worker = new Thread(runnable);
            worker.start();
        }

        return Service.START_STICKY;
    }

    private void sendMessage(int id) {
        final int currentId = id;
        Handler handler = new Handler(Service2.this.getMainLooper());
        handler.post(()-> Toast.makeText(this, TAG+" : Service"+currentId+"running", Toast.LENGTH_LONG).show());
    }

    @Override
    public void onDestroy() {
        running = false;
        Toast.makeText(this, TAG+" : Service destroyed", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onCreate() {
        Toast.makeText(this, TAG+" : Service created", Toast.LENGTH_LONG).show();
    }
}
