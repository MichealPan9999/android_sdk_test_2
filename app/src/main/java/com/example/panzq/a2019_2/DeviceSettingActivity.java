package com.example.panzq.a2019_2;

import android.app.Activity;
import android.app.admin.DevicePolicyManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class DeviceSettingActivity extends Activity implements View.OnClickListener{
    private DevicePolicyManager myDPM;
    private Button btn_lock,btn_resetPassword,btn_wipeData;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_device_setting);
        myDPM=(DevicePolicyManager) getSystemService(DEVICE_POLICY_SERVICE);
        findViews();
    }
    private void findViews()
    {
        btn_lock = findViewById(R.id.btn_lock);
        btn_lock.setOnClickListener(this);
        btn_resetPassword = findViewById(R.id.btn_resetPassword);
        btn_resetPassword.setOnClickListener(this);
        btn_wipeData = findViewById(R.id.btn_wipeData);
        btn_wipeData.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId())
        {
            case R.id.btn_lock:
                myDPM.lockNow();
                break;
            case R.id.btn_resetPassword:
                /**
                 *当设置此flag时，resetPassword在锁屏状态下失去重置作用，即任何admin用户都必须先进入系统才能重置密码
                 public static final int RESET_PASSWORD_REQUIRE_ENTRY = 0x0001;
                 使用此flag必须是device owner，可在不需要密码的情况下启动设备，暂不清楚用法
                 public static final int RESET_PASSWORD_DO_NOT_ASK_CREDENTIALS_ON_BOOT = 0x0002;
                 设为0表示可任意重置密码（无论是否解锁）
                 public static final int PASSWORD_QUALITY_UNSPECIFIED = 0;
                 */
                myDPM.resetPassword("",0);//如果要清除锁屏密码直接传入空字符。可能会报错，需要重启一下手机就可以
                break;
            case R.id.btn_wipeData:
                /**
                 //双清存储数据（包括外置sd卡），wipeData后重启
                 public static final int WIPE_EXTERNAL_STORAGE = 0x0001;
                 //恢复出厂设置，使用此flag必须是device owner，否则将抛出SecurityException异常，而setDeviceOwner为隐藏API
                 public static final int WIPE_RESET_PROTECTION_DATA = 0x0002;
                 */
                myDPM.wipeData(1);
                break;
        }
    }
}
