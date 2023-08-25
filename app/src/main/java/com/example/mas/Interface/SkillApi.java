package com.example.mas.Interface;

import com.example.mas.Model.SkillModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.*;

public interface SkillApi {

    @POST("/api/skill/")
    Call<Void> createSkill(@Body SkillModel skill);

    @GET("/api/skill/getall")
    Call<List<SkillModel>> getAllSkills();

    @PUT("/api/skill/{skill_id}")
    Call<Void> updateSkill(@Path("skill_id") String skillId, @Body SkillModel skill);

    @DELETE("/api/skill/{skill_id}")
    Call<Void> deleteSkill(@Path("skill_id") String skillId);
}