package com.example.exerc2;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Tela2 extends AppCompatActivity {

    private TextView textViewRecebido;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_tela2);

        textViewRecebido = findViewById(R.id.textViewRecebido);

        //Recebe o Intent e o texto Enviado pela Tela1
        String textoRecebido = getIntent().getStringExtra("textoEnviado");

        //exibe o texto no TextView
        textViewRecebido.setText(textoRecebido);
    }
}