package com.example.mas;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.util.Log;
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
    private RecyclerView colourrecyclerView;
    private Colouradapter colourAdapter;
    private List<Colourmodel> colourList;
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
        colourrecyclerView = findViewById(R.id.colourRecyclerView);
        colourrecyclerView.setLayoutManager(new LinearLayoutManager(this));
        colourAdapter = new Colouradapter(new ArrayList<>());
        colourrecyclerView.setAdapter(colourAdapter);
        colourApi = RetrofitInstance.getRetrofitInstance().create(ColourApiservice.class);

        btnCreate = findViewById(R.id.colouraddButton);
        btnGetAll = findViewById(R.id.colourviewAllButton);
        btnUpdate = findViewById(R.id.colourupdateButton);
        btnDelete = findViewById(R.id.colourdeleteButton);
        etColourId = findViewById(R.id.colourIdEditText);
        etDescription = findViewById(R.id.colourdescEditText);
        btnCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String colourId = etColourId.getText().toString().trim();
                String description = etDescription.getText().toString().trim();
                if (!colourId.isEmpty() && !description.isEmpty()) {
                    Colourmodel newColour = new Colourmodel(colourId, description);
                    createColour(newColour);
                }
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
                String colourId = etColourId.getText().toString().trim();
                String description = etDescription.getText().toString().trim();
                if (!colourId.isEmpty() && !description.isEmpty()) {
                    Colourmodel updatedColour = new Colourmodel(colourId, description);
                    updateColour(colourId, updatedColour);
                }
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String colourId = etColourId.getText().toString().trim();
                if (!colourId.isEmpty()) {
                    deleteColour(colourId);
                }
            }
        });

    }

    private void createColour(Colourmodel colour) {
        Call<Void> call = colourApi.createColour(colour);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    getAllColours(); // Refresh the list after creating the color
                } else {
                    // Handle error
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                // Handle failure
            }
        });
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
                    // Handle error
                }
            }

            @Override
            public void onFailure(Call<List<Colourmodel>> call, Throwable t) {
                // Handle failure
            }
        });
    }

    private void updateColour(String colourId, Colourmodel updatedColour) {
        Call<Void> call = colourApi.updateColour(colourId, updatedColour);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    getAllColours(); // Refresh the list after updating the color
                } else {
                    // Handle error
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                // Handle failure
            }
        });
    }

    private void deleteColour(String colourId) {
        Call<Void> call = colourApi.deleteColour(colourId);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    getAllColours(); // Refresh the list after deleting the color
                } else {
                    // Handle error
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                // Handle failure
            }
        });
    }
}
