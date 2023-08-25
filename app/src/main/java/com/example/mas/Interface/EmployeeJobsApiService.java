package com.example.mas.Interface;

import com.example.mas.Model.Colourmodel;
import com.example.mas.Model.EmployeeJobsModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface EmployeeJobsApiService {
    @POST("/api/employeejobs/")
    Call<Void> createJob(@Body EmployeeJobsModel job);

    @GET("/api/employeejobs/getAll")
    Call<List<EmployeeJobsModel>> getAllJobs();

    @PUT("/api/employeejobs/put/{emp_id}")
    Call<Void> updateJob(@Path("emp_id") String colourId, @Body EmployeeJobsModel job);

    @DELETE("/api/employeejobs/delete/{emp_id}")
    Call<Void> deleteJob(@Path("emp_id") String empId);
}
