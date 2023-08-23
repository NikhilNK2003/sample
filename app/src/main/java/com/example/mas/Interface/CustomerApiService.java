package com.example.mas.Interface;

import com.example.mas.Model.CustomerModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.*;

public interface CustomerApiService {
    @POST("/api/customers/create")
    Call<Void> createCustomer(@Body CustomerModel customer);

    @GET("/api/customers/getAll")
    Call<List<CustomerModel>> getAllCustomers();

    @PUT("/api/customers/{customer_id}")
    Call<Void> updateCustomer(@Path("customer_id") String customerId, @Body CustomerModel customer);

    @DELETE("/api/customers/{customer_id}")
    Call<Void> deleteCustomer(@Path("customer_id") String customerId);
}

