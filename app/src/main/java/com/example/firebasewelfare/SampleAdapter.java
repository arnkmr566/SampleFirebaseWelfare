package com.example.firebasewelfare;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class SampleAdapter extends RecyclerView.Adapter<SampleAdapter.ViewHolder> {

    ArrayList<SampleModel> list;
    Context context;

    public SampleAdapter(ArrayList<SampleModel> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.newsample, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        SampleModel model = list.get(position);
        holder.tvName.setText(model.getName());
        holder.tvAddress.setText(model.getAddress());
        holder.tvPhoneNumber.setText(model.getPhone());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvName, tvAddress, tvPhoneNumber;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvName = itemView.findViewById(R.id.tvName);
            tvAddress = itemView.findViewById(R.id.tvAddress);
            tvPhoneNumber = itemView.findViewById(R.id.tvPhoneNumber);
        }
    }


}
