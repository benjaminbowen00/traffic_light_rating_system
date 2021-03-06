package com.example.benjaminbowen.trafficlightrating;

import android.arch.persistence.room.Room;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    ImageButton happyButton;
    ImageButton okButton;
    ImageButton sadButton;
    AppDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        happyButton = findViewById(R.id.happy_button);
        okButton = findViewById(R.id.ok_button);
        sadButton = findViewById(R.id.sad_button);

        db = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "ratings")
                .fallbackToDestructiveMigration()
                .allowMainThreadQueries()
                .build();
    }

    public int getCorrectScore(View view){
        switch(view.getId()){
            case R.id.happy_button:
                return 3;
            case R.id.ok_button:
                return 2;
            case R.id.sad_button:
                return  1;
        }
        return 2;
    }

    public String getFeelingString(View view){
        switch(view.getId()){
            case R.id.happy_button:
                return "happy";
            case R.id.ok_button:
                return "OK";
            case R.id.sad_button:
                return  "sad";
        }
        return "OK";
    }

    public void onButtonClicked(View view){

        int score = getCorrectScore(view);

        Date date = new Date();
        SimpleDateFormat yMDFormat = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat hhmmFormat = new SimpleDateFormat("HH:mm");
        String dateAsString = yMDFormat.format(date);
        String timeAsString = hhmmFormat.format(date);


        Rating rating = new Rating(score, dateAsString, timeAsString);

        db.ratingDao().insertAll(rating);

        Toast.makeText(this, "Rating of '"+ getFeelingString(view) +"' saved.", Toast.LENGTH_SHORT).show();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.activity_results, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){

        if(item.getItemId() == R.id.menu_item_results){
            Intent intent = new Intent (this, ResultsActivity.class);
            startActivity(intent);
        }

        if(item.getItemId() == R.id.menu_item_reset){
            Intent intent = new Intent (this, ResetActivity.class);
            startActivity(intent);
        }

        if(item.getItemId() == R.id.menu_item_result_by_time){
            Intent intent = new Intent (this, ResultsByTImeActivity.class);
            startActivity(intent);
        }




        return super.onOptionsItemSelected(item);
    }
}
