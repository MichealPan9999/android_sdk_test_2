package com.example.panzq.a2019_2;

import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.LocationManager;
import android.net.wifi.WifiManager;
import android.provider.Settings;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Wifi_LocationActivity extends AppCompatActivity {

    private TextView tv_wifiStatus,tv_gpsStatus;
    private Button btn_startWifi,btn_startGps,btn_startGame;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wifi__location);
        findViews();
        setListener();
        checkWifiAndGpsStatus();
    }
    private void findViews()
    {
        tv_wifiStatus = findViewById(R.id.tv_wifistatus);
        tv_gpsStatus = findViewById(R.id.tv_gpsstatus);
        btn_startWifi = findViewById(R.id.btn_startwifi);
        btn_startGps = findViewById(R.id.btn_startGPS);
        btn_startGame = findViewById(R.id.btn_startGame);
    }
    private void setListener()
    {
        btn_startWifi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                if(android.os.Build.VERSION.SDK_INT <= 11){
                    intent .setClassName("com.android.settings", "com.android.settings.Settings$WifiSettingsActivity");
                }else{
                    intent .setClassName("com.android.settings" ,"com.android.settings.wifi.WifiSettings");
                }
                startActivityForResult( intent,0);
            }

        });
        btn_startGps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                startActivityForResult(intent,0);
            }
        });
        btn_startGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new AlertDialog.Builder(Wifi_LocationActivity.this)
                        .setTitle("准备")
                        .setMessage("准备进入游戏")
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                            }
                        }).show();
            }
        });
    }
    private void checkWifiAndGpsStatus()
    {
        boolean result = true;
        WifiManager wifiManager = (WifiManager)getApplicationContext().getSystemService(Context.WIFI_SERVICE);
        LocationManager locationManager = (LocationManager) getApplicationContext().getSystemService(Context.LOCATION_SERVICE);
        if (!wifiManager.isWifiEnabled() && wifiManager.getWifiState()!=WifiManager.WIFI_STATE_ENABLING)
        {
            tv_wifiStatus.setText("未启动");
            result =false;
        }else
        {
            tv_wifiStatus.setText("已启动");
        }

        if(!locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)
                || !locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER))
        {
            tv_gpsStatus.setText("未启动");
            result = false;
        }else{
            tv_gpsStatus.setText("已启动");
        }
        if(result)
        {
            btn_startGame.setEnabled(true);
        }else{
            btn_startGame.setEnabled(false);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        checkWifiAndGpsStatus();
    }
}
