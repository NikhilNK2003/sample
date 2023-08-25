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

import com.example.mas.Adapter.SkillAdapter;
import com.example.mas.Interface.SkillApiService;
import com.example.mas.Model.Colourmodel;
import com.example.mas.Model.SkillModel;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Skill extends AppCompatActivity {
    private SkillApiService skillApi;
    private RecyclerView recyclerView;
    private SkillAdapter skillAdapter;
    private List<SkillModel> skillList;
    //private TextView tvSkillDetails;
    private EditText etSkillId, etSkillDescription;
    private Button btnCreate ;
    private Button btnGetAll ;
    private Button btnUpdate ;
    private Button btnDelete ;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_skill);

        skillApi = RetrofitInstance.getRetrofitInstance().create(SkillApiService.class);
        recyclerView = findViewById(R.id.rvSkills);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        skillList = new ArrayList<>();
        skillAdapter = new SkillAdapter(this, skillList);
        recyclerView.setAdapter(skillAdapter);

        //tvSkillDetails = findViewById(R.id.tvSkillDetails);

        btnCreate = findViewById(R.id.btnCreate);
        btnGetAll = findViewById(R.id.btnGetAll);
        btnUpdate = findViewById(R.id.btnUpdate);
        btnDelete = findViewById(R.id.btnDelete);
        etSkillId = findViewById(R.id.etSkillId);
        etSkillDescription = findViewById(R.id.etSkillDescription);

        btnCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createSkill();
            }
        });

        btnGetAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getAllSkills();
            }
        });

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateSkill();
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteSkill();
            }

        });

    };





    private void createSkill() {
        String skillId = etSkillId.getText().toString().trim();
        String description = etSkillDescription.getText().toString().trim();
        if (!skillId.isEmpty() && !description.isEmpty()) {
            Call<Void> call = skillApi.createSkill(new SkillModel(skillId, description));

            call.enqueue(new Callback<Void>() {
                @Override
                public void onResponse(Call<Void> call, Response<Void> response) {
                    if (response.isSuccessful()) {
                        etSkillId.setText("");
                        etSkillDescription.setText("");
                        getAllSkills(); // Refresh the list after creating the color
                    } else {
                        Log.e("API", "Failed to create skills: " + response.code());
                    }
                }

                @Override
                public void onFailure(Call<Void> call, Throwable t) {
                    Log.e("API", "Failed to create skills: " + t.getMessage());
                }
            });
        }
    }

    private void getAllSkills() {
        Call<List<SkillModel>> call = skillApi.getAllSkills();
        call.enqueue(new Callback<List<SkillModel>>() {
            @Override
            public void onResponse(Call<List<SkillModel>> call, Response<List<SkillModel>> response) {
                if (response.isSuccessful()) {
                    List<SkillModel> skills = response.body();
                    skillAdapter.setSkills(skills);
                } else {
                    Log.e("API", "Failed to fetch skills: " + response.code());
                }
            }

            @Override
            public void onFailure(Call<List<SkillModel>> call, Throwable t) {
                Log.e("API", "Failed to fetch skills: " + t.getMessage());
            }
        });
    }

    private void updateSkill() {
        String skillId = etSkillId.getText().toString().trim();
        String description = etSkillDescription.getText().toString().trim();
        if (!skillId.isEmpty() && !description.isEmpty()) {
            Call<Void> call = skillApi.updateSkill(skillId, new SkillModel(skillId, description));
            call.enqueue(new Callback<Void>() {
                @Override
                public void onResponse(Call<Void> call, Response<Void> response) {
                    if (response.isSuccessful()) {
                        etSkillId.setText("");
                        getAllSkills(); // Refresh the list after updating the color
                    } else {
                        Log.e("API", "Failed to update skills: " + response.code());
                    }
                }

                @Override
                public void onFailure(Call<Void> call, Throwable t) {
                    Log.e("API", "Failed to update skills: " + t.getMessage());
                }
            });
        }
    }


    private void deleteSkill() {
        String skillId = etSkillId.getText().toString().trim();
        if (!skillId.isEmpty()) {
            Call<Void> call = skillApi.deleteSkill(skillId);
            call.enqueue(new Callback<Void>() {
                @Override
                public void onResponse(Call<Void> call, Response<Void> response) {
                    if (response.isSuccessful()) {
                        etSkillId.setText("");
                        getAllSkills(); // Refresh the list after deleting the color
                    } else {
                        Log.e("API", "Failed to delete skills: " + response.code());
                    }
                }

                @Override
                public void onFailure(Call<Void> call, Throwable t) {
                    Log.e("API", "Failed to delete skills: " + t.getMessage());
                }
            });
        }
    }
}
