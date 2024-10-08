package com.example.listview;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.AdapterView;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ArrayList<String> items;
    private ArrayAdapter<String> itensAdapter;
    private ListView listView;
    private Button button;
    private EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = findViewById(R.id.listView);
        button = findViewById(R.id.button);
        editText = findViewById(R.id.editTextText);  // Adicionando a referência ao EditText

        items = new ArrayList<>();
        itensAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, items);
        listView.setAdapter(itensAdapter);

        // Adicionar o texto do EditText na lista ao clicar no botão
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addItem();
            }
        });
    }

    // Método para adicionar o item na lista
    private void addItem() {
        String itemText = editText.getText().toString();  // Obtém o texto inserido
        if (!itemText.isEmpty()) {  // Verifica se o texto não está vazio
            items.add(itemText);  // Adiciona o item à lista
            itensAdapter.notifyDataSetChanged();  // Notifica o adaptador sobre a mudança
            editText.setText("");  // Limpa o campo de texto
        }
    }


}
