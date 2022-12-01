package com.example.adventureapp;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.adventureapp.activities.PhotoSelectorActivity;
import com.example.adventureapp.activities.TaskPhotoActivity;
import com.example.adventureapp.dao.DAOAdventure;
import com.example.adventureapp.model.Adventure;

import java.util.ArrayList;

public class AdventureAdapter extends RecyclerView.Adapter<AdventureAdapter.AdventureViewHolder> {

    Context context;
    ArrayList<Adventure> adventures;
    private String id;

    public AdventureAdapter(Context context, ArrayList<Adventure> list){
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

        holder.taskOne.setText(adventure.getTaskOne());
        holder.taskTwo.setText(adventure.getTaskTwo());
        holder.taskThree.setText(adventure.getTaskThree());
        holder.idValue.setText(adventure.getId());

        //Toast.makeText(context, "id:" + adventure.getId(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public int getItemCount() {
        return adventures.size();
    }

    public class AdventureViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        Button editButton, viewButton, deleteButton, saveEditButton;
        EditText newNameEditText;
        TextView adventureName, taskOne, taskTwo, taskThree, idValue;

        DAOAdventure dao;
        public AdventureViewHolder(@NonNull View itemView){
            super(itemView);
            context = itemView.getContext();

            adventureName = itemView.findViewById(R.id.adventureNameTextView);
            editButton = itemView.findViewById(R.id.editButton);
            viewButton = itemView.findViewById(R.id.viewButton);
            deleteButton = itemView.findViewById(R.id.deleteButton);
            saveEditButton = itemView.findViewById(R.id.saveEditButton);
            newNameEditText = itemView.findViewById(R.id.newNameEditText);
            taskOne = itemView.findViewById(R.id.taskOneTextView);
            taskTwo = itemView.findViewById(R.id.taskTwoTextView);
            taskThree = itemView.findViewById(R.id.taskThreeTextView);
            idValue = itemView.findViewById(R.id.idHolder);

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

                    newNameEditText.setVisibility(View.VISIBLE);
                    saveEditButton.setVisibility(View.VISIBLE);
                    break;
                case R.id.viewButton:
                    newNameEditText.setVisibility(View.GONE);
                    saveEditButton.setVisibility(View.GONE);

                    Intent intent = new Intent(context, PhotoSelectorActivity.class);
                    intent.putExtra("ADVENTURE_ID", idValue.getText());
                    intent.putExtra("ADVENTURE_NAME", adventureName.getText().toString());
                    intent.putExtra("TASK_ONE", taskOne.getText().toString());
                    intent.putExtra("TASK_TWO", taskTwo.getText().toString());
                    intent.putExtra("TASK_THREE", taskThree.getText().toString());
                    context.startActivity(intent);

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

            newNameEditText.setVisibility(View.GONE);
            saveEditButton.setVisibility(View.GONE);
            taskOne.setVisibility(View.GONE);
            taskTwo.setVisibility(View.GONE);
            taskThree.setVisibility(View.GONE);
            notifyItemRangeChanged(position, adventures.size());
        }

        public void saveEdit(int position){

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
