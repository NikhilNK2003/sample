package com.example.mas.Interface;

import com.example.mas.Model.SizeModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface SizeApiService {
    @POST("/api/clothsizes/create")
    Call<Void> createSize(@Body SizeModel size);

    @GET("/api/clothsizes/getall")
    Call<List<SizeModel>> getAllSizes();

    @PUT("/api/clothsizes/edit/{size_id}")
    Call<Void> updateSize(@Path("size_id") String sizeId, @Body SizeModel size);

    @DELETE("/api/clothsizes/delete/{size_id}")
    Call<Void> deleteSize(@Path("size_id") String sizeId);
}
