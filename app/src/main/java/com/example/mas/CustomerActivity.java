package com.example.mas;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import com.example.mas.Adapter.CustomerAdapter;
import com.example.mas.Interface.CustomerApiService;
import com.example.mas.Model.CustomerModel;
import java.util.ArrayList;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
public class CustomerActivity extends AppCompatActivity {

    private RecyclerView customerRecyclerView;
    private CustomerAdapter customerAdapter;
    private CustomerApiService apiService;
    private EditText customerIdEditText, nameEditText, phoneEditText;
    private Button addButton, updateButton, deleteButton, viewAllButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer);
        customerRecyclerView = findViewById(R.id.RecyclerView);
        customerRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        customerAdapter = new CustomerAdapter(this, new ArrayList<>());
        customerRecyclerView.setAdapter(customerAdapter);
        apiService = RetrofitInstance.getRetrofitInstance().create(CustomerApiService.class);

        customerIdEditText = findViewById(R.id.customerIdEditText);
        nameEditText = findViewById(R.id.nameEditText);
        phoneEditText = findViewById(R.id.phoneEditText);
        addButton = findViewById(R.id.addButton);
        updateButton = findViewById(R.id.updateButton);
        deleteButton = findViewById(R.id.deleteButton);
        viewAllButton = findViewById(R.id.viewAllButton);

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createCustomer();
            }
        });

        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateCustomer();
            }
        });

        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deleteCustomer();
            }
        });

        viewAllButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fetchCustomers();
            }
        });
    }

    private void fetchCustomers() {
        Call<List<CustomerModel>> call = apiService.getAllCustomers();
        call.enqueue(new Callback<List<CustomerModel>>() {
            @Override
            public void onResponse(Call<List<CustomerModel>> call, Response<List<CustomerModel>> response) {
                if (response.isSuccessful()) {
                    List<CustomerModel> customers = response.body();
                    customerAdapter.setCustomers(customers);
                } else {
                    Log.e("API", "Failed to fetch customers: " + response.code());
                }
            }

            @Override
            public void onFailure(Call<List<CustomerModel>> call, Throwable t) {
                Log.e("API", "Failed to fetch customers: " + t.getMessage());
            }
        });
    }

    private void createCustomer() {
        String id = customerIdEditText.getText().toString().trim();
        String name = nameEditText.getText().toString().trim();
        String phone = phoneEditText.getText().toString().trim();

        if (!name.isEmpty() && !phone.isEmpty()) {
            Call<Void> call = apiService.createCustomer(new CustomerModel(id, name, phone));
            call.enqueue(new Callback<Void>() {
                @Override
                public void onResponse(Call<Void> call, Response<Void> response) {
                    if (response.isSuccessful()) {
                        customerIdEditText.setText("");
                        nameEditText.setText("");
                        phoneEditText.setText("");
                        fetchCustomers(); // Refresh the list after adding a new customer
                    } else {
                        Log.e("API", "Failed to create customer: " + response.code());
                    }
                }

                @Override
                public void onFailure(Call<Void> call, Throwable t) {
                    Log.e("API", "Failed to create customer: " + t.getMessage());
                }
            });
        }
    }

    private void updateCustomer() {
        String id = customerIdEditText.getText().toString().trim();
        String name = nameEditText.getText().toString().trim();
        String phone = phoneEditText.getText().toString().trim();

        if (!id.isEmpty() && !name.isEmpty() && !phone.isEmpty()) {
            Call<Void> call = apiService.updateCustomer(id, new CustomerModel(id, name, phone));
            call.enqueue(new Callback<Void>() {
                @Override
                public void onResponse(Call<Void> call, Response<Void> response) {
                    if (response.isSuccessful()) {
                        customerIdEditText.setText("");
                        nameEditText.setText("");
                        phoneEditText.setText("");
                        fetchCustomers(); // Refresh the list after updating a customer
                    } else {
                        Log.e("API", "Failed to update customer: " + response.code());
                    }
                }

                @Override
                public void onFailure(Call<Void> call, Throwable t) {
                    Log.e("API", "Failed to update customer: " + t.getMessage());
                }
            });
        }
    }

    private void deleteCustomer() {
        String id = customerIdEditText.getText().toString().trim();

        if (!id.isEmpty()) {
            Call<Void> call = apiService.deleteCustomer(id);
            call.enqueue(new Callback<Void>() {
                @Override
                public void onResponse(Call<Void> call, Response<Void> response) {
                    if (response.isSuccessful()) {
                        customerIdEditText.setText("");
                        fetchCustomers(); // Refresh the list after deleting a customer
                    } else {
                        Log.e("API", "Failed to delete customer: " + response.code());
                    }
                }

                @Override
                public void onFailure(Call<Void> call, Throwable t) {
                    Log.e("API", "Failed to delete customer: " + t.getMessage());
                }
            });
        }
    }
}
