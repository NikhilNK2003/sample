package com.example.mas;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import com.example.mas.Adapter.SizeAdapter;
import com.example.mas.Interface.SizeApiService;
import com.example.mas.Model.SizeModel;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Size extends AppCompatActivity {

    private SizeApiService sizeApi;
    private RecyclerView recyclerView;
    private com.example.mas.Adapter.SizeAdapter SizeAdapter;
    private Button btnCreate;
    private Button btnGetAll;
    private Button btnUpdate;
    private Button btnDelete;
    private EditText etSizeId;
    private EditText etDescription;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_size);
        sizeApi = RetrofitInstance.getRetrofitInstance().create(SizeApiService.class);
        recyclerView = findViewById(R.id.sizeRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        SizeAdapter = new SizeAdapter(this, new ArrayList<>());
        recyclerView.setAdapter(SizeAdapter);
        btnCreate = findViewById(R.id.size_addBtn);
        btnGetAll = findViewById(R.id.size_viewAllBtn);
        btnUpdate = findViewById(R.id.size_updateBtn);
        btnDelete = findViewById(R.id.size_deleteBtn );
        etSizeId = findViewById(R.id.sizeIdEditText );
        etDescription = findViewById(R.id.sizedescEditText );
        btnCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createSize();
            }
        });
        btnGetAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getAllSizes();
            }
        });
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateSize();
            }
        });
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteSize();
            }
        });
    }
    private void createSize() {
        String SizeId = etSizeId.getText().toString().trim();
        String description = etDescription.getText().toString().trim();
        if (!SizeId.isEmpty() && !description.isEmpty()) {
            Call<Void> call = sizeApi.createSize(new SizeModel(SizeId, description));
            call.enqueue(new Callback<Void>() {
                @Override
                public void onResponse(Call<Void> call, Response<Void> response) {
                    if (response.isSuccessful()) {
                       //etSizeId.setText("");
                        //etDescription.setText("");
                        getAllSizes(); // Refresh the list after creating the color
                    } else {
                        Log.e("API", "Failed to create Sizes: " + response.code());
                    }
                }
                @Override
                public void onFailure(Call<Void> call, Throwable t) {
                    Log.e("API", "Failed to create Sizes: " + t.getMessage());
                }
            });
        }
    }
    private void getAllSizes() {
        Call<List<SizeModel>> call = sizeApi.getAllSizes();
        call.enqueue(new Callback<List<SizeModel>>() {
            @Override
            public void onResponse(Call<List<SizeModel>> call, Response<List<SizeModel>> response) {
                if (response.isSuccessful()) {
                    List<SizeModel> Sizes = response.body();
                    SizeAdapter.setsizes(Sizes);
                } else {
                    Log.e("API", "Failed to fetch Sizes: " + response.code());
                }
            }

            @Override
            public void onFailure(Call<List<SizeModel>> call, Throwable t) {
                Log.e("API", "Failed to fetch Sizes: " + t.getMessage());
            }
        });
    }
    private void updateSize() {
        String SizeId = etSizeId.getText().toString().trim();
        String description = etDescription.getText().toString().trim();
        if (!SizeId.isEmpty() && !description.isEmpty()) {
            Call<Void> call = sizeApi.updateSize(SizeId, new SizeModel(SizeId, description));
            call.enqueue(new Callback<Void>() {
                @Override
                public void onResponse(Call<Void> call, Response<Void> response) {
                    if (response.isSuccessful()) {
                        etSizeId.setText("");
                        etDescription.setText("");
                        getAllSizes(); // Refresh the list after updating the color
                    } else {
                        Log.e("API", "Failed to update Sizes: " + response.code());
                    }
                }
                @Override
                public void onFailure(Call<Void> call, Throwable t) {
                    Log.e("API", "Failed to update Sizes: " + t.getMessage());
                }
            });
        }
    }
    private void deleteSize() {
        String SizeId = etSizeId.getText().toString().trim();
        if (!SizeId.isEmpty()) {
            Call<Void> call = sizeApi.deleteSize(SizeId);
            call.enqueue(new Callback<Void>() {
                @Override
                public void onResponse(Call<Void> call, Response<Void> response) {
                    if (response.isSuccessful()) {
                        etSizeId.setText("");
                        getAllSizes(); // Refresh the list after deleting the color
                    } else {
                        Log.e("API", "Failed to delete Sizes: " + response.code());
                    }
                }

                @Override
                public void onFailure(Call<Void> call, Throwable t) {
                    Log.e("API", "Failed to delete Sizes: " + t.getMessage());
                }
            });
        }
    }
}