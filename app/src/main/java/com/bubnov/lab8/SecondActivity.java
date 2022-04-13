package com.bubnov.lab8;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.PersistableBundle;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.bubnov.lab8.services.Service3;
import com.bubnov.lab8.services.Service4;

public class SecondActivity extends AppCompatActivity implements ServiceConnection {

    private TextView txtResult;
    private boolean isBound = false;
    private Service3 service3;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        txtResult = findViewById(R.id.txtResult);
        findViewById(R.id.btnTime).setOnClickListener(view -> {
            if(isBound) txtResult.setText(service3.getCurrentTime());
        });
        findViewById(R.id.btnDate).setOnClickListener(view -> {
            if(isBound) txtResult.setText(service3.getCurrentDate());
        });
        findViewById(R.id.btnConnect).setOnClickListener(view -> {
            if(!isBound){
                Intent boundIntent = new Intent(this, Service3.class);
                bindService(boundIntent, this, Context.BIND_AUTO_CREATE);
            }
        });

        findViewById(R.id.btnStartRinging).setOnClickListener(view -> {
            startService(new Intent(SecondActivity.this, Service4.class));
        });
        findViewById(R.id.btnStopRinging).setOnClickListener(view -> {
            stopService(new Intent(SecondActivity.this, Service4.class));

        });
    }

    @Override
    public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
        Service3.MyBinder myBinder = (Service3.MyBinder) iBinder;
        service3 = myBinder.getService();
        isBound = true;
        txtResult.setText("Connected");

    }

    @Override
    public void onServiceDisconnected(ComponentName componentName) {
        isBound = false;
        service3 = null;

    }
}
