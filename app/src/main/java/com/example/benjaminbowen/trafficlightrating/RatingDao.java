package com.example.benjaminbowen.trafficlightrating;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

/**
 * Created by benjaminbowen on 20/01/2018.
 */

@Dao
public interface RatingDao {

    @Insert
    void insertAll(Rating... rating);

    @Query("SELECT count(*) FROM ratings WHERE score = :score;")
    int getFrequencyOfScore(int score);

    @Query("DELETE FROM ratings")
    void resetData();





}
