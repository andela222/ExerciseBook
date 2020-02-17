package com.example.exercisebook;

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

public class MeasurementDayAdapter extends RecyclerView.Adapter<MeasurementDayAdapter.DayHolder> {
    private List<MeasurementDay> days = new ArrayList<MeasurementDay>();
    private  OnMeasurementCardClickListener measurementCardListener;
    private OnDeleteButtonClickListener deleteButtonListener;
    @NonNull
    @Override
    public MeasurementDayAdapter.DayHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.measurement_day_item, parent, false);
        return new MeasurementDayAdapter.DayHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MeasurementDayAdapter.DayHolder holder, int position) {
        MeasurementDay currentDay = days.get(position);
        Date date = currentDay.getDate();
        holder.dateContainer.setText(DateFormat.getDateInstance(DateFormat.LONG).format(date));
    }

    @Override
    public int getItemCount() {
        return days.size();
    }

    public void setDays(List<MeasurementDay> days) {
        this.days = days;
        notifyDataSetChanged();
    }

    class DayHolder extends RecyclerView.ViewHolder {
        private TextView dateContainer;

        public DayHolder(@NonNull View itemView) {
            super(itemView);
            dateContainer = itemView.findViewById(R.id.dateContainer);

            CardView cardView = itemView.findViewById(R.id.measurementDayCard);

            cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if (measurementCardListener != null && position != RecyclerView.NO_POSITION) {
                        measurementCardListener.OnMeasurementCardClick(days.get(position));
                    }
                }
            });

            Button deleteButton = itemView.findViewById(R.id.deleteMeasurementsButton);
            deleteButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if(deleteButtonListener!= null && position != RecyclerView.NO_POSITION){
                        deleteButtonListener.onDeleteButtonClick(days.get(position));
                    }
                }
            });
        }
    }

    public interface OnMeasurementCardClickListener{
        void OnMeasurementCardClick(MeasurementDay day);
    }

    public void setOnMeasurementCardClickListener(MeasurementDayAdapter.OnMeasurementCardClickListener listener){
        this.measurementCardListener = listener;
    }

    public interface OnDeleteButtonClickListener{
        void onDeleteButtonClick(MeasurementDay day);
    }

    public void setOnDeleteButtonClickListener(MeasurementDayAdapter.OnDeleteButtonClickListener listener){
        this.deleteButtonListener = listener;
    }
}
