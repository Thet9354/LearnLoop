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
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.sql.Array;
import java.util.ArrayList;

import javax.xml.transform.Templates;

public class StatementFragment extends Fragment {

    private Context mContext;

    private com.github.mikephil.charting.charts.BarChart bar_chart;
    private com.github.mikephil.charting.charts.PieChart pie_chart;

    FirebaseDatabase database = FirebaseDatabase.getInstance("https://learnloop-1673224439925-default-rtdb.asia-southeast1.firebasedatabase.app/");
    DatabaseReference databaseReference  = database.getReference().child("users");

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

        /**
        //Connect firebase to pie chart
        databaseReference.child("User Transaction").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot data : snapshot.getChildren()) {

                    String key = data.getKey();

                    float value = Float.parseFloat(data.child("value").getValue().toString());
                    String label = data.child("label").getValue().toString();

                    pieDataSet.addEntry(new PieEntry(value, label));
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        })
         */

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

        BarDataSet barDataSet = new BarDataSet(barEntries, "Monthly Expenditures");
        barDataSet.setColors(ColorTemplate.COLORFUL_COLORS);
        barDataSet.setValueTextColor(Color.WHITE);
        bar_chart.setData(new BarData(barDataSet));
        bar_chart.animateY(2000);

        PieDataSet pieDataSet = new PieDataSet(pieEntries, "Expenditure Category");
        pieDataSet.setColors(ColorTemplate.COLORFUL_COLORS);
        pieDataSet.setValueTextColor(Color.WHITE);
        pie_chart.setData(new PieData(pieDataSet));
        pie_chart.animateXY(2000, 2000);
        pie_chart.getDescription().setEnabled(false);


    }
}