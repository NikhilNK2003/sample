package com.example.mas.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mas.Model.SkillModel;
import com.example.mas.R;

import java.util.List;

public class SkillAdapter extends RecyclerView.Adapter<SkillAdapter.ViewHolder> {

    private List<SkillModel> skills;
    private OnItemClickListener listener;

    public SkillAdapter(List<SkillModel> skills) {
        this.skills = skills;
    }

    public void setSkills(List<SkillModel> skills) {
        this.skills = skills;
        notifyDataSetChanged();
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    public interface OnItemClickListener {
        void onItemClick(SkillModel skill);
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvSkill;

        ViewHolder(View itemView) {
            super(itemView);
            tvSkill = itemView.findViewById(R.id.tvSkill);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION && listener != null) {
                        listener.onItemClick(skills.get(position));
                    }
                }
            });
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_skill, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        SkillModel skill = skills.get(position);
        holder.tvSkill.setText(skill.getSkill_id() + "         " + skill.getDescription());
    }

    @Override
    public int getItemCount() {
        return skills.size();
    }
}
