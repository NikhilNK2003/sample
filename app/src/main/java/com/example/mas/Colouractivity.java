package com.example.mas;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.mas.Adapter.Colouradapter;
import com.example.mas.Interface.ColourApiservice;
import com.example.mas.Model.Colourmodel;
import java.util.ArrayList;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Colouractivity extends AppCompatActivity {
    private ColourApiservice colourApi;
    private RecyclerView recyclerView;
    private Colouradapter colourAdapter;
    private Button btnCreate;
    private Button btnGetAll;
    private Button btnUpdate;
    private Button btnDelete;
    private EditText etColourId;
    private EditText etDescription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_colouractivity);

        colourApi = RetrofitInstance.getRetrofitInstance().create(ColourApiservice.class);
        recyclerView = findViewById(R.id.colourRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        colourAdapter = new Colouradapter(this, new ArrayList<>());
        recyclerView.setAdapter(colourAdapter);

        btnCreate = findViewById(R.id.colouraddButton);
        btnGetAll = findViewById(R.id.colourviewAllButton);
        btnUpdate = findViewById(R.id.colourupdateButton);
        btnDelete = findViewById(R.id.colourdeleteButton);
        etColourId = findViewById(R.id.colourIdEditText);
        etDescription = findViewById(R.id.colourdescEditText);

        btnCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    createColour();
                }
            });

                btnGetAll.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        getAllColours();
                    }
                });

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateColour();
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteColour();
            }

        });

    };





    private void createColour() {
        String colourId = etColourId.getText().toString().trim();
        String description = etDescription.getText().toString().trim();
        if (!colourId.isEmpty() && !description.isEmpty()) {
        Call<Void> call = colourApi.createColour(new Colourmodel(colourId, description));

        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    etColourId.setText("");
                    etDescription.setText("");
                    getAllColours(); // Refresh the list after creating the color
                } else {
                    Log.e("API", "Failed to create colours: " + response.code());
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Log.e("API", "Failed to create colours: " + t.getMessage());
            }
        });
    }
    }

    private void getAllColours() {
        Call<List<Colourmodel>> call = colourApi.getAllColours();
        call.enqueue(new Callback<List<Colourmodel>>() {
            @Override
            public void onResponse(Call<List<Colourmodel>> call, Response<List<Colourmodel>> response) {
                if (response.isSuccessful()) {
                    List<Colourmodel> colours = response.body();
                   colourAdapter.setColours(colours);
                } else {
                    Log.e("API", "Failed to fetch colours: " + response.code());
                }
            }

            @Override
            public void onFailure(Call<List<Colourmodel>> call, Throwable t) {
                Log.e("API", "Failed to fetch colours: " + t.getMessage());
            }
        });
    }

    private void updateColour() {
        String colourId = etColourId.getText().toString().trim();
        String description = etDescription.getText().toString().trim();
        if (!colourId.isEmpty() && !description.isEmpty()) {
            Call<Void> call = colourApi.updateColour(colourId, new Colourmodel(colourId, description));
            call.enqueue(new Callback<Void>() {
                @Override
                public void onResponse(Call<Void> call, Response<Void> response) {
                    if (response.isSuccessful()) {
                        etColourId.setText("");
                        etDescription.setText("");
                        getAllColours(); // Refresh the list after updating the color
                    } else {
                        Log.e("API", "Failed to update colours: " + response.code());
                    }
                }

                @Override
                public void onFailure(Call<Void> call, Throwable t) {
                    Log.e("API", "Failed to update colours: " + t.getMessage());
                }
            });
        }
    }


    private void deleteColour() {
        String colourId = etColourId.getText().toString().trim();
        if (!colourId.isEmpty()) {
            Call<Void> call = colourApi.deleteColour(colourId);
            call.enqueue(new Callback<Void>() {
                @Override
                public void onResponse(Call<Void> call, Response<Void> response) {
                    if (response.isSuccessful()) {
                        etColourId.setText("");
                        getAllColours(); // Refresh the list after deleting the color
                    } else {
                        Log.e("API", "Failed to delete colours: " + response.code());
                    }
                }

                @Override
                public void onFailure(Call<Void> call, Throwable t) {
                    Log.e("API", "Failed to delete colours: " + t.getMessage());
                }
            });
        }
    }
}
