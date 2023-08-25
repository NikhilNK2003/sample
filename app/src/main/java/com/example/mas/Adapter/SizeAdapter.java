package com.example.mas.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mas.Model.SizeModel;
import com.example.mas.R;

import java.util.List;

public class SizeAdapter extends RecyclerView.Adapter<SizeAdapter.ViewHolder> {

    private Context context;
    private List<SizeModel> sizes;

    public SizeAdapter(Context context, List<SizeModel> sizes) {
        this.context = context;
        this.sizes = sizes;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_size, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        SizeModel size = sizes.get(position);
        holder.sizedescriTextView.setText(size.getSize_description());
        holder.sizeIdTextView.setText(size.getSize_id());
    }

    @Override
    public int getItemCount() {
        return sizes.size();
    }

    public void setsizes(List<SizeModel> sizes) {
        this.sizes = sizes;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView sizedescriTextView;

        TextView sizeIdTextView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            sizedescriTextView = itemView.findViewById(R.id.sizedescTextView);

            sizeIdTextView = itemView.findViewById((R.id.sizeIdTextView));
        }
    }
}