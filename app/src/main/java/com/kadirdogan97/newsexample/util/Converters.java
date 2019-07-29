package com.kadirdogan97.newsexample.util;

import androidx.room.TypeConverter;

import com.google.gson.Gson;
import com.kadirdogan97.newsexample.model.ArticleSource;

public class Converters {
    private static Gson gson = new Gson();
    @TypeConverter
    public static String convertArticleSource(ArticleSource articleSource) {
        return articleSource.getName();
    }
}
