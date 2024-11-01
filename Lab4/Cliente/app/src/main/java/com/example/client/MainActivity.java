// MainActivity.java
package com.example.client;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private EmployeeAdapter adapter;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Configuração do RecyclerView
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Inicialize o Retrofit
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.56.1:8080/employees/")  // Substitua pelo IP do servidor
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        // Crie o serviço
        EmployeeService service = retrofit.create(EmployeeService.class);

        // Faça a chamada para obter os dados
        Call<List<Employee>> call = service.getEmployees();
        call.enqueue(new Callback<List<Employee>>() {
            @Override
            public void onResponse(Call<List<Employee>> call, Response<List<Employee>> response) {
                if (response.isSuccessful()) {
                    List<Employee> employees = response.body();
                    // Configure o adapter com a lista de empregados
                    adapter = new EmployeeAdapter(employees);
                    recyclerView.setAdapter(adapter);
                }
            }

            @Override
            public void onFailure(Call<List<Employee>> call, Throwable t) {
                Log.e("RetrofitError", "Erro ao buscar empregados", t);
            }
        });
    }
}
