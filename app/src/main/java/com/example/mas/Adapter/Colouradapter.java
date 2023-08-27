package com.example.mas.Adapter;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.mas.Model.Colourmodel;
import com.example.mas.R;
import java.util.List;
public class Colouradapter extends RecyclerView.Adapter<Colouradapter.ViewHolder> {
    private Context context;
    private List<Colourmodel> colours;
    public Colouradapter(List<Colourmodel> colours) {
        this.colours = colours;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_colour, parent, false);
        return new ViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Colourmodel colour = colours.get(position);
        holder.colourIdTextView.setText(colour.getColor_id());
        holder.colourdescriTextView.setText(colour.getDescription());
    }
    @Override
    public int getItemCount() {
        return colours.size();
    }
    public void setColours(List<Colourmodel> colours) {
        this.colours = colours;
        notifyDataSetChanged();
    }
    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView colourdescriTextView;
        TextView colourIdTextView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            colourIdTextView = itemView.findViewById((R.id.colourIdTextView));
            colourdescriTextView = itemView.findViewById(R.id.colourdescTextView);
        }
    }
}

