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

    @ColumnInfo(name = "date")
    private String date;

    @ColumnInfo(name = "time")
    private String time;

    public Rating(int score, String date, String time){
        this.score = score;
        this.date = date;
        this.time = time;
    }

    public int getId() {
        return id;
    }

    public int getScore() {
        return score;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setScore(int score) {
        this.score = score;
    }


}
