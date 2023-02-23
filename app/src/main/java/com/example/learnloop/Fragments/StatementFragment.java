package com.example.learnloop.Fragments;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;

import com.example.learnloop.R;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.sql.Array;
import java.util.ArrayList;

import javax.xml.transform.Templates;

public class StatementFragment extends Fragment {

    private Context mContext;

    private com.github.mikephil.charting.charts.BarChart bar_chart;
    private com.github.mikephil.charting.charts.PieChart pie_chart;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_statement, container, false);

        mContext = getActivity();

        getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.FLAG_FULLSCREEN);

        // Inflate the layout for this fragment
        return rootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        findViews(view);
    }

    private void findViews(View v) {

        //MPChart
        bar_chart = v.findViewById(R.id.bar_chart);
        pie_chart = v.findViewById(R.id.pie_chart);

        initUI();

    }

    private void initUI() {

        initCharts();
    }

    private void initCharts() {
        ArrayList<BarEntry> barEntries = new ArrayList<>();
        ArrayList<PieEntry> pieEntries = new ArrayList<>();

        for (int i=1; i<13; i++){
            float value = (float) (i*10.0);
            BarEntry barEntry = new BarEntry(i, value);

            barEntries.add(barEntry);
        }

        for (int i=1; i<9; i++){
            float value = (float) (i*10.0);
            PieEntry pieEntry = new PieEntry(i, value);

            pieEntries.add(pieEntry);
        }

        BarDataSet barDataSet = new BarDataSet(barEntries, "Employees");
        barDataSet.setColors(ColorTemplate.COLORFUL_COLORS);
        barDataSet.setDrawValues(false);
        bar_chart.setData(new BarData(barDataSet));
        bar_chart.animateY(2000);
        bar_chart.getDescription().setText("Lolz");
        bar_chart.getDescription().setTextColor(Color.BLUE);

        PieDataSet pieDataSet = new PieDataSet(pieEntries, "Students");
        pieDataSet.setColors(ColorTemplate.COLORFUL_COLORS);
        pie_chart.setData(new PieData(pieDataSet));
        pie_chart.animateXY(2000, 2000);
        pie_chart.getDescription().setEnabled(false);
    }
}