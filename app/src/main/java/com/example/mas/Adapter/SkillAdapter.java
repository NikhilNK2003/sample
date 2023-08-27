package com.example.mas.Adapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.mas.Model.SkillModel;
import com.example.mas.R;
import com.example.mas.Skill;
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
        TextView SkillID;
        TextView skilldesc;
        ViewHolder(View itemView) {
            super(itemView);
            SkillID = itemView.findViewById(R.id.skillIdTextView);
            skilldesc=itemView.findViewById(R.id.skilldescTextView);
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
        holder.SkillID.setText(skill.getSkill_id());
        holder.skilldesc.setText(skill.getDescription());
    }

    @Override
    public int getItemCount() {
        return skills.size();
    }

}
