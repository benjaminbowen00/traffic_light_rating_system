package com.example.benjaminbowen.trafficlightrating;

import android.arch.persistence.room.Room;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

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
    TextView meanScoreText;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);


        db = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "ratings")
                .fallbackToDestructiveMigration()
                .allowMainThreadQueries()
                .build();

        barChart = findViewById(R.id.barchart);
        meanScoreText = findViewById(R.id.average_text);

        labels = new ArrayList<>();
        labels.add("Sad");
        labels.add("OK");
        labels.add("Happy");

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
        barDataSet.setDrawValues(false);

        data = new BarData(labels, barDataSet);
        barChart.setData(data);


        float meanScore = getAverage();

        meanScoreText.setText("The average score is "+String.format("%.2f", meanScore)+"\n (sad = 1, OK = 2, happy = 3)");







    }


    public float getAverage(){

        db = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "ratings")
                .fallbackToDestructiveMigration()
                .allowMainThreadQueries()
                .build();

        float totalScore = 0;
        float totalFreq = 0;
        for(int i = 1; i<=3; i++){
            int scoreFreq = db.ratingDao().getFrequencyOfScore(i);
            totalFreq += scoreFreq;
            totalScore += (scoreFreq * i);
        }
        if (totalFreq != 0){
        return totalScore / totalFreq;}
        else{return 0;}

    }




}
