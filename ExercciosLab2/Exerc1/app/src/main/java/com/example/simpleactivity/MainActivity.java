package com.example.simpleactivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        Log.i("MainActivity","Oncreate");


    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i("MainActivity","Inicializando");
    }
    @Override
    protected  void onResume(){
        super.onResume();
        Toast.makeText(this, "onResume", Toast.LENGTH_SHORT).show();
        Log.i("MainActivity","onResume");

    }

    @Override
    protected void onPause(){
        super.onPause();
       // Toast.makeText(this,"onDestroy",Toast.LENGTH_SHORT).show();
        Log.i("MainActivity","Pausado");


    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i("MainActivity", "onStop");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.i("MainActivity", "onRestart");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i("MainActivity", "onDestroy");
    }

    public void finishButtonPressed(View view) {
        finish();
    }


}