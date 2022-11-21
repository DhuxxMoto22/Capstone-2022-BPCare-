package com.example.bloodcare;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.ViewHolder> {

    private Context context;
    private List<HomeString> list;

    public HomeAdapter(Context context, List<HomeString> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.single_item, parent, false);

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        HomeString homeString = list.get(position);

        holder.textResult.setText(homeString.getValue());
        holder.textResult2.setText(homeString.getValue2());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        public TextView textResult;
        public TextView textResult2;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            textResult = itemView.findViewById(R.id.main_value);
            textResult2 = itemView.findViewById(R.id.main_value2);

        }
    }
}
