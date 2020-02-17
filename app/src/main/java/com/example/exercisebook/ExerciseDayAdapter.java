package com.example.exercisebook;

import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ExerciseDayAdapter extends RecyclerView.Adapter<ExerciseDayAdapter.DayHolder> {
    private List<ExerciseDay> days = new ArrayList<ExerciseDay>();
    private OnWorkoutCardClickListener workoutCardListener;
    private OnWorkoutEditClickListener workoutEditClickListener;
    private OnWorkoutDeleteClickListener workoutDeleteClickListener;
    @NonNull
    @Override
    public ExerciseDayAdapter.DayHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.exercise_day_item, parent, false);
        return new ExerciseDayAdapter.DayHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ExerciseDayAdapter.DayHolder holder, int position) {
        ExerciseDay currentDay = days.get(position);
        Date date = currentDay.getDate();
        holder.dateContainer.setText(DateFormat.getDateInstance(DateFormat.LONG).format(date));
    }

    @Override
    public int getItemCount() {
        return days.size();
    }

    public void setDays(List<ExerciseDay> days) {
        this.days = days;
        notifyDataSetChanged();
    }

    class DayHolder extends RecyclerView.ViewHolder {
        private TextView dateContainer;

        public DayHolder(@NonNull View itemView) {
            super(itemView);
            dateContainer = itemView.findViewById(R.id.dateContainer);

            CardView cardView = itemView.findViewById(R.id.exerciseDayCard);

            cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if (workoutCardListener != null && position != RecyclerView.NO_POSITION) {
                        workoutCardListener.OnWorkoutCardClick(days.get(position));
                    }
                }
            });

            Button editButton = itemView.findViewById(R.id.editExerciseDayButton);
            editButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if(workoutEditClickListener != null && position != RecyclerView.NO_POSITION){
                        workoutEditClickListener.OnWorkoutEditClick(days.get(position));
                    }
                }
            });

            Button deleteButton = itemView.findViewById(R.id.deleteExerciseDayButton);
            deleteButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if(workoutDeleteClickListener != null && position != RecyclerView.NO_POSITION){
                        workoutDeleteClickListener.OnWorkoutDeleteClick(days.get(position));
                    }
                }
            });
        }
    }

    public interface OnWorkoutCardClickListener{
        void OnWorkoutCardClick(ExerciseDay day);
    }

    public void setOnWorkoutCardClickListener(ExerciseDayAdapter.OnWorkoutCardClickListener listener){
        this.workoutCardListener = listener;
    }

    public interface OnWorkoutEditClickListener{
        void OnWorkoutEditClick(ExerciseDay day);
    }

    public void setOnWorkoutEditClickListener(ExerciseDayAdapter.OnWorkoutEditClickListener listener){
        this.workoutEditClickListener = listener;
    }

    public interface OnWorkoutDeleteClickListener{
        void OnWorkoutDeleteClick(ExerciseDay day);
    }

    public void setOnWorkoutDeleteClickListener(ExerciseDayAdapter.OnWorkoutDeleteClickListener listener){
        this.workoutDeleteClickListener = listener;
    }

}
