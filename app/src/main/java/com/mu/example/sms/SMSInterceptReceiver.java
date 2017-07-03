package com.mu.example.sms;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;

/**
 * Created by mu on 2017/6/30.
 */

public class SMSInterceptReceiver extends BroadcastReceiver {
    final String ACTION_SMS_RECEIVED="android.provider.Telephony.SMS_RECEIVED";
    //有新短信时系统发出的广播，有序，可拦截。
    public SMSInterceptReceiver() {
    }
    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO: This method is called when the BroadcastReceiver is receiving
        // an Intent broadcast.
        String contact;
        String content;
        //发送短信的号码
        Boolean intercept = false;
        //布尔变量，表示是否拦截
        if (intent.getAction().equals(ACTION_SMS_RECEIVED)) {
            StringBuilder builder = new StringBuilder();
            Bundle bundle = intent.getExtras();
            if (bundle != null) {
                Object[] pdus = (Object[]) bundle.get("pdus");
                //从Intent中获取bundle对象，此对象包含了所有的信息，短信是以“pdus”字段存储的 。得到的是一个object数组，
                // 每个object都包含一条短信，（可能会获取到多条信息）。
                SmsMessage[] messages = new SmsMessage[pdus.length];
                //新建SmsMessage数组对象存储短信，每个SmsMessage对应一条短信类。
                for (int i = 0; i < messages.length; i++) {
                    messages[i] = SmsMessage.createFromPdu((byte[]) pdus[i]);
                }
                contact = messages[pdus.length - 1].getDisplayOriginatingAddress();
                content = messages[pdus.length - 1].getMessageBody();


               
            }
        } else {
            throw new UnsupportedOperationException("Not yet implemented");
        }
    }
}
