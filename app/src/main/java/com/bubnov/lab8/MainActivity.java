package com.bubnov.lab8;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.bubnov.lab8.services.Service1;
import com.bubnov.lab8.services.Service2;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //service1
        findViewById(R.id.btnService1).setOnClickListener(view -> {
            Intent service1_intent = new Intent(getApplicationContext(), Service1.class);
            service1_intent.putExtra("number", 100);
            startService(service1_intent);
        });
        //service2
        findViewById(R.id.btnService2).setOnClickListener(view -> {
            Intent service2_intent = new Intent(this, Service2.class);
            startService(service2_intent);
        });
        findViewById(R.id.btnService2Stop).setOnClickListener(view -> {
            Intent service2_stop = new Intent(this, Service2.class);
            stopService(service2_stop);
        });

        //takes to second activity
        findViewById(R.id.btn2Activity).setOnClickListener(view -> {
            startActivity(new Intent(MainActivity.this, SecondActivity.class));
        });
    }
}