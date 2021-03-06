package com.example.consumption_monitor.Fragments;

import android.content.Context;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.consumption_monitor.DataManagement.Biometrics;
import com.example.consumption_monitor.DataManagement.FileIO;
import com.example.consumption_monitor.DataManagement.User;
import com.example.course_project.R;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class BiometricsLog extends Fragment {
    static BiometricsLog biometricsLog = new BiometricsLog();

    private BiometricsLog() {
    }

    public static BiometricsLog getInstance() {
        return biometricsLog;
    }

    private TextView textLog;
    private LineChart chart;
    ArrayList<Biometrics> biometricsList = new ArrayList<>();
    FileIO fileIO = FileIO.getInstance();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_biometrics_log, container, false);
        Context context = getActivity().getApplicationContext();

        User profile = (User) getArguments().getSerializable("User");
        String fileName = profile.getFirstName() + profile.getLastName() + "BiometricsList.ser";

        textLog = view.findViewById(R.id.textViewLog);
        chart = (LineChart) view.findViewById(R.id.chart);
        biometricsList = (ArrayList<Biometrics>) fileIO.readObjects(context, fileName);

        textLog.setMovementMethod(new ScrollingMovementMethod());

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
        String sDate = "Date", sWeight = "Weight", sBmi = "BMI";
        textLog.setText(String.format("%-26s %-18s %-5s\n", sDate, sWeight, sBmi));
        for (Biometrics biometrics : biometricsList) {
            textLog.append(String.format("%-20s %-20s %-5.1f\n",
                    dateFormat.format(biometrics.getDate()), biometrics.getWeight(), biometrics.getBmi()));
        }

        List<Entry> entries = new ArrayList<Entry>();
        int i = 0;
        for (Biometrics data : biometricsList) {
            i++;
            entries.add(new Entry(i, (float) data.getWeight()));
            System.out.println(i);
        }

        LineDataSet dataSet = new LineDataSet(entries, "Weight");
        LineData lineData = new LineData(dataSet);
        chart.setData(lineData);
        chart.invalidate();

        return view;
    }
}
