package com.example.course_project.Fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.course_project.DataManagement.FileIO;
import com.example.course_project.DataManagement.Meal;
import com.example.course_project.R;

import java.util.ArrayList;

public class MealLog extends Fragment {
    static MealLog mealLog = new MealLog();

    private TextView textLog;

    ArrayList<Meal> mealList = new ArrayList<>();
    private MealLog() {}
    public static MealLog getInstance() {
        return mealLog;
    }
    FileIO fileIO = FileIO.getInstance();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_meal_log, container, false);
        Context context = getActivity().getApplicationContext();

        textLog = view.findViewById(R.id.textViewLog);

        mealList = (ArrayList<Meal>) fileIO.readObjects(context, "mealList.ser");

        textLog.setText("Date ; co2amount\n");
        for (Meal meal : mealList){
            textLog.append(meal.getDate() + " ; " + meal.getCo2amount()+"\n");

        }

        return view;
    }
}
