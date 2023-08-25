package com.example.mas;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.mas.Adapter.EmployeeJobsAdapter;
import com.example.mas.Interface.EmployeeJobsApiService;
import com.example.mas.Model.EmployeeJobsModel;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EmployeeJobsActivity extends AppCompatActivity {
    private EmployeeJobsApiService empjobApi;
    private RecyclerView recyclerView;
    private EmployeeJobsAdapter EmployeeJobsAdapter;
    private List<EmployeeJobsModel> empjobList;
    //private TextView tvJobDetails;
    private EditText etEmpolyeeId, etEmployeeRole;
    private Button btnCreate ;
    private Button btnGetAll ;
    private Button btnUpdate ;
    private Button btnDelete ;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee_jobs);

        empjobApi = RetrofitInstance.getRetrofitInstance().create(EmployeeJobsApiService.class);
        recyclerView = findViewById(R.id.roleRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        empjobList = new ArrayList<>();
        EmployeeJobsAdapter = new EmployeeJobsAdapter(this, empjobList);
        recyclerView.setAdapter(EmployeeJobsAdapter);

        //tvJobDetails = findViewById(R.id.tvJobDetails);

        btnCreate = findViewById(R.id.role_addBtn);
        btnGetAll = findViewById(R.id.role_viewAllBtn);
        btnUpdate = findViewById(R.id.role_updateBtn);
        btnDelete = findViewById(R.id.role_deleteBtn);
        etEmpolyeeId = findViewById(R.id.empIdEditText);
        etEmployeeRole = findViewById(R.id.emp_roleEditText);

        btnCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createJob();
            }
        });

        btnGetAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getAllJobs();
            }
        });

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateJob();
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteJob();
            }

        });

    };





    private void createJob() {
        String EmployeeId = etEmpolyeeId.getText().toString().trim();
        String description = etEmployeeRole.getText().toString().trim();
        if (!EmployeeId.isEmpty() && !description.isEmpty()) {
            Call<Void> call = empjobApi.createJob(new EmployeeJobsModel(EmployeeId, description));

            call.enqueue(new Callback<Void>() {
                @Override
                public void onResponse(Call<Void> call, Response<Void> response) {
                    if (response.isSuccessful()) {
                        etEmpolyeeId.setText("");
                        etEmployeeRole.setText("");
                        getAllJobs(); // Refresh the list after creating the color
                    } else {
                        Log.e("API", "Failed to create Jobs: " + response.code());
                    }
                }

                @Override
                public void onFailure(Call<Void> call, Throwable t) {
                    Log.e("API", "Failed to create Jobs: " + t.getMessage());
                }
            });
        }
    }

    private void getAllJobs() {
        Call<List<EmployeeJobsModel>> call = empjobApi.getAllJobs();
        call.enqueue(new Callback<List<EmployeeJobsModel>>() {
            @Override
            public void onResponse(Call<List<EmployeeJobsModel>> call, Response<List<EmployeeJobsModel>> response) {
                if (response.isSuccessful()) {
                    List<EmployeeJobsModel> Jobs = response.body();
                    EmployeeJobsAdapter.setJobs(Jobs);
                } else {
                    Log.e("API", "Failed to fetch Jobs: " + response.code());
                }
            }

            @Override
            public void onFailure(Call<List<EmployeeJobsModel>> call, Throwable t) {
                Log.e("API", "Failed to fetch Jobs: " + t.getMessage());
            }
        });
    }

    private void updateJob() {
        String EmployeeId = etEmpolyeeId.getText().toString().trim();
        String description = etEmployeeRole.getText().toString().trim();
        if (!EmployeeId.isEmpty() && !description.isEmpty()) {
            Call<Void> call = empjobApi.updateJob(EmployeeId, new EmployeeJobsModel(EmployeeId, description));
            call.enqueue(new Callback<Void>() {
                @Override
                public void onResponse(Call<Void> call, Response<Void> response) {
                    if (response.isSuccessful()) {
                        etEmpolyeeId.setText("");
                        getAllJobs(); // Refresh the list after updating the color
                    } else {
                        Log.e("API", "Failed to update Jobs: " + response.code());
                    }
                }

                @Override
                public void onFailure(Call<Void> call, Throwable t) {
                    Log.e("API", "Failed to update Jobs: " + t.getMessage());
                }
            });
        }
    }


    private void deleteJob() {
        String EmployeeId = etEmpolyeeId.getText().toString().trim();
        if (!EmployeeId.isEmpty()) {
            Call<Void> call = empjobApi.deleteJob(EmployeeId);
            call.enqueue(new Callback<Void>() {
                @Override
                public void onResponse(Call<Void> call, Response<Void> response) {
                    if (response.isSuccessful()) {
                        etEmpolyeeId.setText("");
                        getAllJobs(); // Refresh the list after deleting the color
                    } else {
                        Log.e("API", "Failed to delete Jobs: " + response.code());
                    }
                }

                @Override
                public void onFailure(Call<Void> call, Throwable t) {
                    Log.e("API", "Failed to delete Jobs: " + t.getMessage());
                }
            });
        }
    }
}
