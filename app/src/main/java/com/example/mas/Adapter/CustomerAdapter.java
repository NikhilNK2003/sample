package com.example.mas.Adapter;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.mas.Model.CustomerModel;
import com.example.mas.R;
import java.util.List;
public class CustomerAdapter extends RecyclerView.Adapter<CustomerAdapter.ViewHolder> {
    private Context context;
    private List<CustomerModel> customers;

    public CustomerAdapter(Context context, List<CustomerModel> customers) {
        this.context = context;
        this.customers = customers;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_customer, parent, false);
        return new ViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        CustomerModel customer = customers.get(position);
        holder.customerNameTextView.setText(customer.getCustomer_name());
        holder.customerPhoneTextView.setText(customer.getCustomer_phone());
        holder.customerIdTextView.setText(customer.getCustomer_id());
    }
    @Override
    public int getItemCount() {
        return customers.size();
    }
    public void setCustomers(List<CustomerModel> customers) {
        this.customers = customers;
        notifyDataSetChanged();
    }
    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView customerNameTextView;
        TextView customerPhoneTextView;
        TextView customerIdTextView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            customerNameTextView = itemView.findViewById(R.id.customerNameTextView);
            customerPhoneTextView = itemView.findViewById(R.id.customerPhoneTextView);
            customerIdTextView = itemView.findViewById((R.id.customerIdTextView));
        }
    }
}

