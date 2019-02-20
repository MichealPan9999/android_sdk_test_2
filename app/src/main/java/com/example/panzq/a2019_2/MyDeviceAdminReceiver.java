package com.example.panzq.a2019_2;

import android.app.admin.DeviceAdminReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.UserHandle;
import android.widget.Toast;

public class MyDeviceAdminReceiver extends DeviceAdminReceiver {
    private void showToast(Context context,CharSequence msg)
    {
        Toast.makeText(context,msg,Toast.LENGTH_LONG).show();
    }

    @Override
    public void onEnabled(Context context, Intent intent) {
        super.onEnabled(context, intent);
        showToast(context,"DeviceAdminReceiver 启动");
    }

    @Override
    public CharSequence onDisableRequested(Context context, Intent intent) {
        return "DeviceAdminReceiver 是否确定停用";
    }

    @Override
    public void onDisabled(Context context, Intent intent) {
        super.onDisabled(context, intent);
        showToast(context,"DeviceAdminReceiver 停用");
    }

    @Override
    public void onPasswordChanged(Context context, Intent intent, UserHandle user) {
        super.onPasswordChanged(context, intent, user);
        showToast(context,"DeviceAdminReceiver 修改密码");
    }

    @Override
    public void onPasswordFailed(Context context, Intent intent, UserHandle user) {
        super.onPasswordFailed(context, intent, user);
        showToast(context,"DeviceAdminReceiver 密码错误");
    }

    @Override
    public void onPasswordSucceeded(Context context, Intent intent, UserHandle user) {
        super.onPasswordSucceeded(context, intent, user);
        showToast(context,"DeviceAdminReceiver 密码正确");
    }
}
