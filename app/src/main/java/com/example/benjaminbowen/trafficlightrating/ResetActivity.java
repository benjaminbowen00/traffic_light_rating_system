package com.example.benjaminbowen.trafficlightrating;

import android.app.AlertDialog;
import android.arch.persistence.room.Room;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class ResetActivity extends AppCompatActivity {

    Button resetButton;
    AppDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset);

        resetButton = findViewById(R.id.reset_button);

        db = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "ratings")
                .fallbackToDestructiveMigration()
                .allowMainThreadQueries()
                .build();
    }

    public void clearAllData(View v) {


        DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which) {
                    case DialogInterface.BUTTON_POSITIVE:
                        db.ratingDao().resetData();


                        Toast.makeText(ResetActivity.this, "All data was reset", Toast.LENGTH_SHORT).show();

                    case DialogInterface.BUTTON_NEGATIVE:

                        break;
                }
            }
        };

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Once deleted, data can not be recovered. Are you sure you want to continue?").setNegativeButton("No", dialogClickListener).setPositiveButton("Yes", dialogClickListener).show();

    }
}
