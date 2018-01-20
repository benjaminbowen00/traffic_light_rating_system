package com.example.benjaminbowen.trafficlightrating;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

/**
 * Created by benjaminbowen on 20/01/2018.
 */


@Entity(tableName = "ratings")
public class Rating {

    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "score")
    private int score;

    @ColumnInfo(name = "date_time")
    private String dateTime;

    public Rating(int score, String dateTime){
        this.score = score;
        this.dateTime = dateTime;
    }

    public int getId() {
        return id;
    }

    public int getScore() {
        return score;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }
}
