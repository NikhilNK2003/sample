package com.example.mas.Model;

public class SkillModel { private String skill_id;
    private String description;

    public SkillModel(String skill_id, String description) {
        this.skill_id = skill_id;
        this.description = description;
    }

    public String getSkill_id() {
        return skill_id;
    }

    public String getDescription() {
        return description;
    }

    public void setSkill_id(String skill_id) {
        this.skill_id = skill_id;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
