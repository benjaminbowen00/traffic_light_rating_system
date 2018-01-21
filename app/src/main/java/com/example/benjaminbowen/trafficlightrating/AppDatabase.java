package com.example.benjaminbowen.trafficlightrating;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

/**
 * Created by benjaminbowen on 20/01/2018.
 */

@Database(entities = {Rating.class}, version = 2)
public abstract class AppDatabase extends RoomDatabase {
    public abstract RatingDao ratingDao();
}
