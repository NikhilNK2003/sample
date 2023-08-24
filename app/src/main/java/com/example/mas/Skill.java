package com.example.mas;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.mas.Adapter.SkillAdapter;
import com.example.mas.Interface.SkillApi;
import com.example.mas.Model.SkillModel;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Skill extends AppCompatActivity {
    private SkillApi skillApi;
    private RecyclerView recyclerView;
    private SkillAdapter skillAdapter;
    private List<SkillModel> skillList;
    //private TextView tvSkillDetails;
    private EditText etSkillId, etDescription;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_skill);

        skillApi = RetrofitInstance.getRetrofitInstance().create(SkillApi.class);
        recyclerView = findViewById(R.id.rvSkills);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        skillList = new ArrayList<>();
        skillAdapter = new SkillAdapter(skillList);
        recyclerView.setAdapter(skillAdapter);

        //tvSkillDetails = findViewById(R.id.tvSkillDetails);
        Button btnCreate = findViewById(R.id.btnCreate);
        Button btnGetAll = findViewById(R.id.btnGetAll);
        Button btnUpdate = findViewById(R.id.btnUpdate);
        Button btnDelete = findViewById(R.id.btnDelete);
        btnCreate = findViewById(R.id.btnCreate);
        btnGetAll = findViewById(R.id.btnGetAll);
        btnUpdate = findViewById(R.id.btnUpdate);
        btnDelete = findViewById(R.id.btnDelete);
        etSkillId = findViewById(R.id.etSkillId);
        etDescription = findViewById(R.id.etDescription);

        btnCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String skillId = etSkillId.getText().toString().trim();
                String description = etDescription.getText().toString().trim();
                if (!skillId.isEmpty() && !description.isEmpty()) {
                    SkillModel newSkill = new SkillModel(skillId, description);
                    createSkill(newSkill);
                }
            }
        });

        // Button for getting all skills

        btnGetAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getAllSkills();
            }
        });

        // Button for updating a skill

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String skillId = etSkillId.getText().toString().trim();
                String description = etDescription.getText().toString().trim();
                if (!skillId.isEmpty() && !description.isEmpty()) {
                    SkillModel updatedSkill = new SkillModel(skillId, description);
                    updateSkill(skillId, updatedSkill);
                }
            }
        });

        // Button for deleting a skill

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String skillId = etSkillId.getText().toString().trim();
                if (!skillId.isEmpty()) {
                    deleteSkill(skillId);
                }
            }
        });

        // For demonstration purposes, let's add some dummy data to the skillList


        // Show the list of skills in the RecyclerView
        skillAdapter.setSkills(skillList);

        // Set click listener on the RecyclerView items to show details of a selected skill

    }

    private void createSkill(SkillModel skill) {
        Call<Void> call = skillApi.createSkill(skill);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    // Skill created successfully, update the RecyclerView or handle as needed
                    getAllSkills(); // Refresh the list after creating the skill
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

    private void getAllSkills() {
        Call<List<SkillModel>> call = skillApi.getAllSkills();
        call.enqueue(new Callback<List<SkillModel>>() {
            @Override
            public void onResponse(Call<List<SkillModel>> call, Response<List<SkillModel>> response) {
                if (response.isSuccessful()) {
                    List<SkillModel> skills = response.body();
                    if (skills != null) {
                        skillList.clear();
                        skillList.addAll(skills);
                        skillAdapter.notifyDataSetChanged();
                    }
                } else {
                    // Handle error
                }
            }

            @Override
            public void onFailure(Call<List<SkillModel>> call, Throwable t) {
                // Handle failure
            }
        });
    }

    private void updateSkill(String skillId, SkillModel updatedSkill) {
        Call<Void> call = skillApi.updateSkill(skillId, updatedSkill);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    // Skill updated successfully, update the RecyclerView or handle as needed
                    getAllSkills(); // Refresh the list after updating the skill
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

    private void deleteSkill(String skillId) {
        Call<Void> call = skillApi.deleteSkill(skillId);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    // Skill deleted successfully, update the RecyclerView or handle as needed
                    getAllSkills(); // Refresh the list after deleting the skill
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

