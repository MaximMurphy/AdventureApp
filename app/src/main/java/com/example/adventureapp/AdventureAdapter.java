package com.example.adventureapp;

import android.content.Context;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.adventureapp.model.Adventure;

import java.util.ArrayList;

public class AdventureAdapter extends RecyclerView.Adapter<AdventureAdapter.AdventureViewHolder> {

    Context context;
    ArrayList<Adventure> adventures;

    public AdventureAdapter(Context context, ArrayList<Adventure> list) {
        this.context = context;
        this.adventures = list;
    }

    @NonNull
    @Override
    public AdventureViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.fragment_past_adventure, parent, false);
        return new AdventureViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull AdventureViewHolder holder, int position) {
        Adventure adventure = adventures.get(position);
        holder.adventureName.setText(adventure.getAdventureName());

    }

    @Override
    public int getItemCount() {
        return adventures.size();
    }

    public static class AdventureViewHolder extends RecyclerView.ViewHolder{
        TextView adventureName;
        Button editButton, deleteButton;
        public AdventureViewHolder(@NonNull View itemView){
            super(itemView);

            adventureName = itemView.findViewById(R.id.adventureNameTextView);
            editButton = itemView.findViewById(R.id.editButton);
            deleteButton = itemView.findViewById(R.id.deleteButton);
        }
    }

    public void setAdventuresList(ArrayList<Adventure> adventures) {
        this.adventures = adventures;
        notifyDataSetChanged();
    }

}
