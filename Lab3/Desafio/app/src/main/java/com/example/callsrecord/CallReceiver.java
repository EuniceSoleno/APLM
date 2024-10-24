package com.example.callsrecord;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.telephony.TelephonyManager;
import android.util.Log;

import java.io.FileOutputStream;
import java.io.IOException;

public class CallReceiver extends BroadcastReceiver {
    private static final String FILE_NAME = "call_log.txt";

    @Override
    public void onReceive(Context context, Intent intent) {
        String logEntry = "";

        // Verificar se o intent é de mudança no estado de telefone
        if (intent.getAction().equals("android.intent.action.PHONE_STATE")) {
            String state = intent.getStringExtra(TelephonyManager.EXTRA_STATE);

            if (TelephonyManager.EXTRA_STATE_RINGING.equals(state)) {
                // O telefone está tocando
                String incomingNumber = intent.getStringExtra(TelephonyManager.EXTRA_INCOMING_NUMBER);
                logEntry = "Chamada recebida de: " + incomingNumber;  // Define logEntry com o número da chamada recebida
                Log.d("CallReceiver", logEntry);
                // Salvar a chamada recebida no arquivo
                logCall(context, logEntry);

            } else if (TelephonyManager.EXTRA_STATE_OFFHOOK.equals(state)) {
                // Chamada atendida ou efetuada
                logEntry = "Chamada atendida ou efetuada.";  // Atualiza logEntry
                Log.d("CallReceiver", logEntry);
                // Registrar a chamada atendida ou efetuada
                logCall(context, logEntry);

            } else if (TelephonyManager.EXTRA_STATE_IDLE.equals(state)) {
                // Chamada encerrada
                logEntry = "Chamada encerrada.";  // Atualiza logEntry
                Log.d("CallReceiver", logEntry);
                // Registrar a chamada encerrada
                logCall(context, logEntry);
            }

        } else if (intent.getAction().equals("android.intent.action.NEW_OUTGOING_CALL")) {
            // Verifica se a ação é de chamadas efetuadas
            String outgoingNumber = intent.getStringExtra(Intent.EXTRA_PHONE_NUMBER);
            logEntry = "Chamada efetuada para: " + outgoingNumber;  // Define logEntry com o número da chamada efetuada
            Log.d("CallReceiver", logEntry);
            // Registrar a chamada efetuada
            logCall(context, logEntry);
        }
    }

    public void logCall(Context context, String logEntry) {
        FileOutputStream fos = null;
        try {
            fos = context.openFileOutput(FILE_NAME, Context.MODE_APPEND);
            fos.write((logEntry + "\n").getBytes());  // Escreve logEntry no arquivo
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
