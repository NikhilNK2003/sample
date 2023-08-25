package com.example.mas.Interface;

import com.example.mas.Model.SizeMode;
import com.example.mas.Model.SkillModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface SizeApiservice {
    @POST("/api/clothsizes/create")
    Call<Void> createSize(@Body SizeMode size);

    @GET("/api/clothsizes/getall")
    Call<List<SizeMode>> getAllSizes();

    @PUT("/api/clothsizes/edit/{size_id}")
    Call<Void> updateSize(@Path("size_id") String sizeId, @Body SizeMode size);

    @DELETE("/api/clothsizes/delete/{size_id}")
    Call<Void> deleteSize(@Path("size_id") String sizeId);
}
