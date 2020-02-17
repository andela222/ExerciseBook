package com.example.exercisebook;


import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class MeasurementDayFragment extends Fragment {
    private long userId;
    private static MeasurementDayViewModel dayViewModel;
    public MeasurementDayFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        final MeasurementDayAdapter adapter = new MeasurementDayAdapter();

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.measurement_days_layout, container, false);
        RecyclerView recyclerView = view.findViewById(R.id.recyclerViewMeasurementDays);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);

        Bundle bundle = getArguments();
        userId = bundle.getLong("userId");


        dayViewModel = ViewModelProviders.of(this).get(MeasurementDayViewModel.class);
        dayViewModel.getAllDaysByUserId(userId).observe(this, new Observer<List<MeasurementDay>>() {
            @Override
            public void onChanged(List<MeasurementDay> days) {
                adapter.setDays(days);
            }
        });
        adapter.setOnMeasurementCardClickListener(new MeasurementDayAdapter.OnMeasurementCardClickListener() {
            @Override
            public void OnMeasurementCardClick(MeasurementDay day) {
                Intent intent = new Intent(getActivity(), AddEditMeasurementsActivity.class);
                intent.putExtra(AddEditUserActivity.EXTRA_USER_ID, userId);
                intent.putExtra(AddEditMeasurementsActivity.EXTRA_MEASUREMENT_DAY_ID, day.getId());
                startActivity(intent);
            }
        });

        adapter.setOnDeleteButtonClickListener(new MeasurementDayAdapter.OnDeleteButtonClickListener() {
            @Override
            public void onDeleteButtonClick(MeasurementDay day) {
                dayViewModel.delete(day);
            }
        });

        return view;
    }
}
