package com.example.mas.Interface;

import com.example.mas.Model.Colourmodel;
import com.example.mas.Model.CustomerModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface ColourApiservice {
    @POST("/api/clothcolors/create")
    Call<Void> createColour(@Body Colourmodel colour);

    @GET("/api/clothcolors/getall")
    Call<List<Colourmodel>> getAllColours();

    @PUT("/api/clothcolors/edit/{color_id}")
    Call<Void> updateColour(@Path("color_id") String colourId, @Body Colourmodel colour);

    @DELETE("/api/clothcolors/delete/{color_id}")
    Call<Void> deleteColour(@Path("color_id") String colourId);
}
