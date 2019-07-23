package com.kadirdogan97.newsexample.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Update;

import java.util.List;

import androidx.room.Query;

import com.kadirdogan97.newsexample.model.Articles;

@Dao
public interface NewsDao {
    @Query("SELECT * FROM news_reading_table")
    LiveData<List<Articles>> getAllNews();

    @Query("DELETE FROM news_reading_table WHERE uid = :newsId")
    void deleteById(String newsId);

    @Insert
    void insert(Articles news);

    @Delete
    void delete(Articles news);

    @Update
    void update(Articles news);

    @Query("DELETE FROM news_reading_table")
    void deleteAll();
}
