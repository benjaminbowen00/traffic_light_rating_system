package com.example.benjaminbowen.trafficlightrating;

import android.arch.persistence.room.Room;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;

import java.util.ArrayList;

public class ResultsByTImeActivity extends AppCompatActivity {

    AppDatabase db;

    BarChart morningBarChart;
    BarChart afternoonBarChart;
    ArrayList<String> labels;
    ArrayList<BarEntry> morningValues;
    ArrayList<BarEntry> afternoonValues;
    BarDataSet morningBarDataSet;
    BarDataSet afternoonBarDataSet;
    BarData morningData;
    BarData afternoonData;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results_by_time);

        db = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "ratings")
                .fallbackToDestructiveMigration()
                .allowMainThreadQueries()
                .build();

        morningBarChart = findViewById(R.id.bar_chart_morning);
        afternoonBarChart = findViewById(R.id.bar_chart_afternoon);


        labels = new ArrayList<>();
        labels.add("Sad");
        labels.add("OK");
        labels.add("Happy");

        morningValues = new ArrayList<>();
        for(int i=1; i <=3; i++){
            int frequency = db.ratingDao().getMorningFrequencyOfScore(i);
            float freqFloat = frequency;
            morningValues.add(new BarEntry(freqFloat, i-1));
        }

        afternoonValues = new ArrayList<>();
        for(int i=1; i <=3; i++){
            int frequency = db.ratingDao().getAfternoonFrequencyOfScore(i);
            float freqFloat = frequency;
            afternoonValues.add(new BarEntry(freqFloat, i-1));
        }

        morningBarDataSet = new BarDataSet(morningValues, "score");
        afternoonBarDataSet = new BarDataSet(afternoonValues, "score");
        morningBarDataSet.setColors(new int[] {Color.RED, Color.YELLOW, Color.GREEN});
        afternoonBarDataSet.setColors(new int[] {Color.RED, Color.YELLOW, Color.GREEN});

        morningBarChart.getLegend().setEnabled(false);
        afternoonBarChart.getLegend().setEnabled(false);
        morningBarChart.setDescription("");
        afternoonBarChart.setDescription("");
        morningBarDataSet.setDrawValues(false);
        afternoonBarDataSet.setDrawValues(false);

        morningData = new BarData(labels, morningBarDataSet);
        afternoonData = new BarData(labels, afternoonBarDataSet);
        morningBarChart.setData(morningData);
        afternoonBarChart.setData(afternoonData);






    }
}
