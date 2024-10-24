package com.example.thread;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    private Thread timeThread;
    private boolean conatando = false;
    private int contador = 0;
    private static final String TAG = "TimeCounter";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        Button buttonStart = findViewById(R.id.start);
        Button buttonStop = findViewById(R.id.stop);

        buttonStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startCouting();
            }
        });

        buttonStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stopCounting();
            }
        });
    }

    private void startCouting() {
        if (conatando) return;
        contador = 0;
        conatando = true;

        //criação de uma nova thread
        timeThread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (conatando) {
                    Log.d(TAG, "Contador: " + contador);
                    contador++;
                    try {
                        Thread.sleep(1000); //pausa por um segundo
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }

        });
    }
    private void stopCounting(){
        if(conatando) return ;
        conatando = false;

        if(timeThread != null){
            timeThread.interrupt();//interrompe a thread
            timeThread = null;
        }

    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
        stopCounting();
        // Garante que a thread seja interrompida ao destruir a atividade
    }


}