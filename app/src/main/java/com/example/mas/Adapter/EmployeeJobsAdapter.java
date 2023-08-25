package com.example.mas.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mas.Model.EmployeeJobsModel;
import com.example.mas.R;

import java.util.List;

public class EmployeeJobsAdapter extends RecyclerView.Adapter<EmployeeJobsAdapter.ViewHolder> {

    private Context context;
    private List<EmployeeJobsModel> jobs;

    public EmployeeJobsAdapter(Context context, List<EmployeeJobsModel> jobs) {
        this.context = context;
        this.jobs = jobs;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_employee_jobs, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        EmployeeJobsModel job = jobs.get(position);
        holder.jobdescriTextView.setText(job.getEmployee_role());
        holder.jobIdTextView.setText(job.getEmployee_id());
    }

    @Override
    public int getItemCount() {
        return jobs.size();
    }

    public void setJobs(List<EmployeeJobsModel> jobs) {
        this.jobs = jobs;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView jobdescriTextView;

        TextView jobIdTextView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            jobdescriTextView = itemView.findViewById(R.id.emp_roleTextView);

            jobIdTextView = itemView.findViewById((R.id.empIdTextView));
        }
    }
}
