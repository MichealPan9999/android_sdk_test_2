package com.example.panzq.a2019_2;

import android.app.Activity;
import android.app.admin.DevicePolicyManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class SaveDeviceActivity extends Activity{

    private DevicePolicyManager myDPM;
    private ComponentName myDeviceAdminReceiver;
    private Button btn_startDevice;
    private Button btn_setDevice;
    private Button btn_stopDevice;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_save_device);
        myDPM = (DevicePolicyManager) getSystemService(Context.DEVICE_POLICY_SERVICE);
        myDeviceAdminReceiver = new ComponentName(this,MyDeviceAdminReceiver.class);
        findViews();
        setListener();
    }
    private void findViews()
    {
        btn_startDevice = findViewById(R.id.btn_startDevice);
        btn_setDevice = findViewById(R.id.btn_setDevice);
        btn_stopDevice = findViewById(R.id.btn_stopDevice);
    }

    private void setListener()
    {
        btn_startDevice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("panzqww","start Device+++++++++++++++++++++");
                Intent intent = new Intent(DevicePolicyManager.ACTION_ADD_DEVICE_ADMIN);
                intent.putExtra(DevicePolicyManager.EXTRA_DEVICE_ADMIN,myDeviceAdminReceiver);
                intent.putExtra(DevicePolicyManager.EXTRA_ADD_EXPLANATION,"提别说明:此画面为启动设备管理画面");
                startActivityForResult(intent,1);
            }
        });
        btn_setDevice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("panzqww","set Device+++++++++++++++++++++");
                Intent intent2 = new Intent();
                intent2.setClass(SaveDeviceActivity.this,DeviceSettingActivity.class);
                intent2.putExtra(DevicePolicyManager.EXTRA_DEVICE_ADMIN,myDeviceAdminReceiver);
                startActivity(intent2);
            }
        });
        btn_stopDevice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("panzqww","stop Device+++++++++++++++++++++");
                myDPM.removeActiveAdmin(myDeviceAdminReceiver);
                updateButtonStatus();
            }
        });
    }

    private void updateButtonStatus() {
        boolean active = myDPM.isAdminActive(myDeviceAdminReceiver);
        if (active)
        {
            btn_startDevice.setEnabled(false);
            btn_setDevice.setEnabled(true);
            btn_stopDevice.setEnabled(true);
        }else{
            btn_startDevice.setEnabled(true);
            btn_setDevice.setEnabled(false);
            btn_stopDevice.setEnabled(false);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        updateButtonStatus();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (resultCode)
        {
            case RESULT_OK:
                Toast.makeText(SaveDeviceActivity.this,"启动成功",Toast.LENGTH_LONG).show();
                updateButtonStatus();
                break;
            default:
                Toast.makeText(SaveDeviceActivity.this,"取消启动",Toast.LENGTH_LONG).show();
                updateButtonStatus();
                break;
        }
    }

}
