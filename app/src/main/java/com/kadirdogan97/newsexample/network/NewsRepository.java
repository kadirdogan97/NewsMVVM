package com.kadirdogan97.newsexample.network;

import androidx.lifecycle.MutableLiveData;

import com.kadirdogan97.newsexample.model.ExampleNews;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NewsRepository {
    private static NewsRepository newsRepository;
    public static NewsRepository getInstance(){
        if (newsRepository == null){
            newsRepository = new NewsRepository();
        }
        return newsRepository;
    }
    private SourcesApi newsApi;
    public NewsRepository(){
        newsApi = RetrofitService.createService(SourcesApi.class);
    }
    public MutableLiveData<ExampleNews> getNews(String newsSource, String key){
        final MutableLiveData<ExampleNews> newsData = new MutableLiveData<>();
        newsApi.getNewsList(newsSource,key).enqueue(new Callback<ExampleNews>() {
            @Override
            public void onResponse(Call<ExampleNews> call,
                                   Response<ExampleNews> response) {
                if (response.isSuccessful()){
                    newsData.setValue(response.body());
                }
            }
            @Override
            public void onFailure(Call<ExampleNews> call, Throwable t) {
                newsData.setValue(null);
            }
        });
        return newsData;
    }
}
