package com.bubnov.lab8.services;


import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

import androidx.annotation.Nullable;

import java.util.Calendar;
import java.util.Locale;

public class Service3 extends Service {

    private final IBinder binder = new MyBinder();

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return binder;
    }

    public class MyBinder extends Binder {
        public Service3 getService() {return Service3.this; }
    }

    public String getCurrentTime(){
        Calendar cal = Calendar.getInstance();
        return String.format(Locale.getDefault(), "%02d:%02d:%02d:%03d"
        , cal.get(Calendar.HOUR_OF_DAY), cal.get(Calendar.MINUTE)
        , cal.get(Calendar.SECOND), cal.get(Calendar.MILLISECOND));
    }

    public String getCurrentDate(){
        Calendar cal = Calendar.getInstance();
        return String.format(Locale.getDefault(), "%04d-%02d-%02d"
                , cal.get(Calendar.YEAR), cal.get(Calendar.MONTH)+1
                , cal.get(Calendar.DAY_OF_MONTH));
    }

}
