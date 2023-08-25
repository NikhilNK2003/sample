package com.example.mas.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mas.Model.Colourmodel;
//import com.example.mas.Model.CustomerModel;
import com.example.mas.Model.SkillModel;
import com.example.mas.R;

import java.util.List;

public class SkillAdapter extends RecyclerView.Adapter<SkillAdapter.ViewHolder> {

    private Context context;
    private List<SkillModel> skills;

    public SkillAdapter(Context context,List<SkillModel> skills) {
        this.context = context;
        this.skills = skills;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_skill, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        SkillModel colour = skills.get(position);
        holder.skilldescriTextView.setText(colour.getSkill_description());
        holder.skillIdTextView.setText(colour.getSkill_id());
    }

    @Override
    public int getItemCount() {
        return skills.size();
    }

    public void setSkills(List<SkillModel> skills) {
        this.skills = skills;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView skilldescriTextView;

        TextView skillIdTextView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            skilldescriTextView = itemView.findViewById(R.id.skilldescTextView);

            skillIdTextView = itemView.findViewById((R.id.skillIdTextView));
        }
    }
}

