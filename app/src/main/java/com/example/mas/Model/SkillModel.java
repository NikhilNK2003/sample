package com.example.mas.Model;

public class SkillModel {
    private String skill_id;
    private String skill_description;

    public SkillModel(String skill_id, String skill_description) {
        this.skill_id = skill_id;
        this.skill_description = skill_description;
    }

    public String getSkill_id() {
        return skill_id;
    }

    public void setSkill_id(String skill_id) {
        this.skill_id = skill_id;
    }

    public String getSkill_description() {
        return skill_description;
    }

    public void setSkill_description(String skill_description) {
        this.skill_description = skill_description;
    }

}
