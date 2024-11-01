package com.example.client;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface EmployeeService {
    @GET("/employees")  // Endpoint da lista de empregados
    Call<List<Employee>> getEmployees();
}
