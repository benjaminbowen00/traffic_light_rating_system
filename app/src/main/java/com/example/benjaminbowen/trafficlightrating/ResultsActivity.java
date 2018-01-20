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

public class ResultsActivity extends AppCompatActivity {

    BarChart barChart;
    BarDataSet barDataSet;
    ArrayList<String> labels;
    ArrayList<BarEntry> values;
    BarData data;
    AppDatabase db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);

        db = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "ratings")
                .fallbackToDestructiveMigration()
                .allowMainThreadQueries()
                .build();

        barChart = findViewById(R.id.barchart);


        labels = new ArrayList<>();
        labels.add("1");
        labels.add("2");
        labels.add("3");

        values = new ArrayList<>();
        for(int i=1; i <=3; i++){
            int frequency = db.ratingDao().getFrequencyOfScore(i);
            float freqFloat = frequency;
            values.add(new BarEntry(freqFloat, i-1));
        }

        barDataSet = new BarDataSet(values, "score");
        barDataSet.setColors(new int[] {Color.RED, Color.YELLOW, Color.GREEN});
        barChart.getLegend().setEnabled(false);
        barChart.setDescription("");

        data = new BarData(labels, barDataSet);
        barChart.setData(data);

    }
}
