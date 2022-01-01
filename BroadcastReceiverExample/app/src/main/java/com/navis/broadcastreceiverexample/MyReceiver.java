package com.navis.broadcastreceiverexample;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class MyReceiver extends BroadcastReceiver {
    private final String MY_ACTION = BuildConfig.APPLICATION_ID + ".MY_ACTION";
    Context appContext = null;
    public MyReceiver(Context context)
    {
        appContext = context;
    }
    @Override
    public void onReceive(Context context, Intent intent) {
        String msg = "";
        switch (intent.getAction())
        {
            case MY_ACTION:
                msg = "My Action";
                break;
            case Intent.ACTION_SCREEN_ON:
                msg = "Screen On";
                break;
            case Intent.ACTION_SCREEN_OFF:
                msg = "Screen Off";
                break;
        }
        Toast t = Toast.makeText(appContext, msg, Toast.LENGTH_LONG);
        t.show();
    }
}
