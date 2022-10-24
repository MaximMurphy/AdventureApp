package com.example.adventureapp;

import android.content.Context;
import android.content.Intent;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.adventureapp.activities.EditAdventureActivity;
import com.example.adventureapp.dao.DAOAdventure;
import com.example.adventureapp.model.Adventure;

import java.util.ArrayList;

public class AdventureAdapter extends RecyclerView.Adapter<AdventureAdapter.AdventureViewHolder> {

    Context context;
    ArrayList<Adventure> adventures;

    private ClickListener editListener, deleteListener;

    public interface ClickListener {
        void onItemClicked(Adventure adventure);
    }

    public AdventureAdapter(Context context, ArrayList<Adventure> list){//, ClickListener editListener, ClickListener deleteListener) {
        this.context = context;
        this.adventures = list;
//        this.editListener = editListener;
//        this.deleteListener = deleteListener;
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

//        holder.editButton.setOnClickListener(v -> editListener.onItemClicked(adventure));
//        holder.deleteButton.setOnClickListener(v -> deleteListener.onItemClicked(adventure));
    }

    @Override
    public int getItemCount() {
        return adventures.size();
    }

    public class AdventureViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView adventureName;
        Button editButton, deleteButton;
        DAOAdventure dao;
        public AdventureViewHolder(@NonNull View itemView){
            super(itemView);

            adventureName = itemView.findViewById(R.id.adventureNameTextView);
            editButton = itemView.findViewById(R.id.editButton);
            deleteButton = itemView.findViewById(R.id.deleteButton);
            dao = new DAOAdventure();

            editButton.setOnClickListener(this);
            deleteButton.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            //Log.d("View: ", v.toString());
            //Toast.makeText(v.getContext(), mTextViewTitle.getText() + " position = " + getPosition(), Toast.LENGTH_SHORT).show();
            if(v.equals(deleteButton)){
                removeAt(getAdapterPosition());
            }
        }

        public void removeAt(int position) {

            Adventure removed = adventures.remove(position);
            dao.delete(removed.getAdventureName());
            setAdventuresList(adventures);
//            notifyItemRemoved(position);
            notifyItemRangeChanged(position, adventures.size());
        }
    }

    public void setAdventuresList(ArrayList<Adventure> adventures) {
        this.adventures = adventures;
        notifyDataSetChanged();
    }



}
