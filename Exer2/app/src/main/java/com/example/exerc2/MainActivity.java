package com.example.exerc2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private EditText editText;
    private Button buttonEnviar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        editText = findViewById(R.id.editText);
        buttonEnviar =  findViewById(R.id.buttonEnviar);

        buttonEnviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //captura o texto inserido no EditText
                String texto = editText.getText().toString();

                //cria um Intent para navegar para a tela2
                Intent intent = new Intent(MainActivity.this, Tela2.class);
                //Adicionar o texto como um extra
                intent.putExtra("textoEnviado",texto);
                //inicia a Tela2
                startActivity(intent);
            }
        });

    }
}