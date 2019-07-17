package com.kadirdogan97.newsexample.network;

import com.kadirdogan97.newsexample.model.ExampleNews;
import com.kadirdogan97.newsexample.model.ExampleSources;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface SourcesApi {
    @GET("sources")
    Call<ExampleSources> getSourcesList(@Query("apiKey") String apiKey);

    @GET("top-headlines")
    Call<ExampleNews> getNewsList(@Query("sources") String source, @Query("apiKey") String apiKey);
}
