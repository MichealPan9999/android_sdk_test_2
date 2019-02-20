package com.example.panzq.a2019_2;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.LocationManager;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private Button btn_set_wifigps,btn_set_saveDevice;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViews();
        setListener();
    }
    private void findViews()
    {
        btn_set_wifigps = findViewById(R.id.btn_set_wifigps);
        btn_set_saveDevice = findViewById(R.id.btn_set_saveDevice);
    }
    private void setListener()
    {
        btn_set_wifigps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,Wifi_LocationActivity.class);
                startActivityForResult(intent,0);
            }

        });
        btn_set_saveDevice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,SaveDeviceActivity.class);
                startActivityForResult(intent,0);
            }

        });
    }

}
