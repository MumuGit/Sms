package com.mu.example.sms;

import android.Manifest;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.mu.example.sms.sms.SmsObserver;
import com.mu.example.sms.sms.SmsResponseCallback;

public class MainActivity extends AppCompatActivity implements SmsResponseCallback {
    private SmsObserver smsObserver;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestPermission();
        SMSInterceptReceiver dynamicReceiver = new SMSInterceptReceiver();

        IntentFilter intentFilter = new IntentFilter();
        intentFilter.setPriority(1000);//优先级设置

        intentFilter.addAction("android.provider.Telephony.SMS_RECEIVED");
         registerReceiver(dynamicReceiver, intentFilter);
//        smsObserver = new SmsObserver(getActivity(), this, new VerificationCodeSmsFilter("10690"));
//        smsObserver.registerSMSObserver();
        setContentView(R.layout.activity_main);
    }
    public  void requestPermission(){
        String[] ps = {Manifest.permission.READ_SMS};

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M
                && (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_SMS)
                != PackageManager.PERMISSION_GRANTED )) {

            requestPermissions(ps, 0);

        }
    }

    @Override
    public void onCallbackSmsContent(String smsContent) {

    }
}
