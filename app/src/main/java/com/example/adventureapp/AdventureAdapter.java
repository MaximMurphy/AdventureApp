package com.example.adventureapp;

import android.content.Context;
import android.content.Intent;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
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

        //holder.taskOne.setText(adventure.getTasks().get(0));
        //holder.taskTwo.setText(adventure.getTasks().get(1));
        //holder.taskThree.setText(adventure.getTasks().get(2));


//        holder.editButton.setOnClickListener(v -> editListener.onItemClicked(adventure));
//        holder.deleteButton.setOnClickListener(v -> deleteListener.onItemClicked(adventure));
    }

    @Override
    public int getItemCount() {
        return adventures.size();
    }

    public class AdventureViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView adventureName, taskOne, taskTwo, taskThree;
        Button editButton, viewButton, deleteButton, saveEditButton;
        EditText newNameEditText;
        //LinearLayout editAdventureContainer, viewTasksContainer;
        DAOAdventure dao;
        public AdventureViewHolder(@NonNull View itemView){
            super(itemView);

            adventureName = itemView.findViewById(R.id.adventureNameTextView);
            editButton = itemView.findViewById(R.id.editButton);
            viewButton = itemView.findViewById(R.id.viewButton);
            deleteButton = itemView.findViewById(R.id.deleteButton);
            saveEditButton = itemView.findViewById(R.id.saveEditButton);
            newNameEditText = itemView.findViewById(R.id.newNameEditText);
            taskOne = itemView.findViewById(R.id.taskOneTextView);
            taskTwo = itemView.findViewById(R.id.taskTwoTextView);
            taskThree = itemView.findViewById(R.id.taskThreeTextView);
            //editAdventureContainer = itemView.findViewById(R.id.editAdventureContainer);
            //viewTasksContainer = itemView.findViewById(R.id.viewTasksContainer);
            dao = new DAOAdventure();

            editButton.setOnClickListener(this);
            viewButton.setOnClickListener(this);
            deleteButton.setOnClickListener(this);
            saveEditButton.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.editButton:
                    //viewTasksContainer.setVisibility(View.GONE);
                    taskOne.setVisibility(View.GONE);
                    taskTwo.setVisibility(View.GONE);
                    taskThree.setVisibility(View.GONE);

                    //editAdventureContainer.setVisibility(View.VISIBLE);
                    newNameEditText.setVisibility(View.VISIBLE);
                    saveEditButton.setVisibility(View.VISIBLE);
                    break;
                case R.id.viewButton:
                    //editAdventureContainer.setVisibility(View.GONE);
                    newNameEditText.setVisibility(View.GONE);
                    saveEditButton.setVisibility(View.GONE);

                    //viewTasksContainer.setVisibility(View.VISIBLE);
                    taskOne.setVisibility(View.VISIBLE);
                    taskTwo.setVisibility(View.VISIBLE);
                    taskThree.setVisibility(View.VISIBLE);

                    break;
                case R.id.deleteButton:
                    removeAt(getAdapterPosition());
                    break;
                case R.id.saveEditButton:
                    saveEdit(getAdapterPosition());
                    break;
            }
        }

        public void removeAt(int position) {
            adventures.remove(position);
            dao.delete(position);
            setAdventuresList(adventures);
            //editAdventureContainer.setVisibility(View.GONE);
            newNameEditText.setVisibility(View.GONE);
            saveEditButton.setVisibility(View.GONE);
            taskOne.setVisibility(View.GONE);
            taskTwo.setVisibility(View.GONE);
            taskThree.setVisibility(View.GONE);
            notifyItemRangeChanged(position, adventures.size());
        }

        public void saveEdit(int position){
            //editAdventureContainer.setVisibility(View.GONE);
            newNameEditText.setVisibility(View.GONE);
            saveEditButton.setVisibility(View.GONE);
            dao.updateAdventure(newNameEditText.getText().toString(), position);
            ArrayList<Adventure> newList = adventures;
            newList.get(position).setAdventureName(newNameEditText.getText().toString());
            setAdventuresList(newList);
        }
    }

    public void setAdventuresList(ArrayList<Adventure> adventures) {
        this.adventures = adventures;
        notifyDataSetChanged();
    }

}
