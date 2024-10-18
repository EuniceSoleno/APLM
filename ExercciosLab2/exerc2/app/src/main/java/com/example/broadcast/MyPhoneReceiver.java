package com.example.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.telecom.TelecomManager;
import android.telephony.TelephonyManager;
import android.util.Log;

public class MyPhoneReceiver extends BroadcastReceiver {
    private static final String TAG = "TAG_MyPhoneReceiver";

    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO: This method is called when the BroadcastReceiver is receiving
        // an Intent broadcast.
        if(intent.getAction().equals( TelephonyManager.ACTION_PHONE_STATE_CHANGED))
        {
            String state = intent.getStringExtra(TelephonyManager.EXTRA_STATE);
            String number = intent.getStringExtra(TelephonyManager.EXTRA_INCOMING_NUMBER);

            // Loga o estado da chamada e o número, se disponível
            if (state != null) {
                Log.i(TAG, "Estado da chamada: " + state);
            }

            if (number != null) {
                Log.i(TAG, "Número de telefone: " + number);
            } else {
                Log.i(TAG, "Número de telefone não disponível.");
            }

            Log.i(TAG,"State: " + state + ", Number: "+number);
        }
    }
}