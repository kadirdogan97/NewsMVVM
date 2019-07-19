package com.kadirdogan97.newsexample.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Update;

import java.util.List;

import androidx.room.Query;

@Dao
public interface NewsDao {
    @Query("SELECT * FROM news_reading_table")
    LiveData<List<News>> getAllNews();

    @Query("DELETE FROM news_reading_table WHERE id = :newsId")
    void deleteById(String newsId);

    @Insert
    void insert(News news);

    @Delete
    void delete(News news);

    @Update
    void update(News news);

    @Query("DELETE FROM news_reading_table")
    void deleteAll();
}
