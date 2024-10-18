package com.example.broadcast;

import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.Manifest;


import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "TAG_MainActivity";
    private static final int PHONE_STATUS_REQUEST_CODE = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
    }

    protected void onStart(){
        super.onStart();
        askPhonePermission();
    }

    private void askPhonePermission() {
        // Verifica permissão de estado do telefone
        int hasPhonePermission = ContextCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE);
        if (hasPhonePermission != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_PHONE_STATE}, PHONE_STATUS_REQUEST_CODE);
        }
        hasPhonePermission = ContextCompat.checkSelfPermission(this, Manifest.permission.READ_CALL_LOG);
        if(hasPhonePermission != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this, new String[] {Manifest.permission.READ_CALL_LOG}, PHONE_STATUS_REQUEST_CODE);
        }
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case PHONE_STATUS_REQUEST_CODE: {

                // Se o pedido está cancelado, os vectores resultantes
                // são vazios
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Log.i(TAG, "permissão de estado do telefone concedida");

                    // a permissão foi concedida! Faça a
                    // tarefa relacionada a contactos que precisa fazer.

                } else {
                    Log.i(TAG, "permissão de estado do telefone não concedida");

                    // permissão negada! Desabilite a
                    // funcionalidade que depende dessa permissão.
                }
                return;
            }

        }

    }
}