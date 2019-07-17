package com.kadirdogan97.newsexample.viewmodel;

import android.widget.ImageView;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.kadirdogan97.newsexample.BuildConfig;
import com.kadirdogan97.newsexample.model.ExampleNews;
import com.kadirdogan97.newsexample.network.NewsRepository;

public class VMNewsActivity extends ViewModel {
    private MutableLiveData<ExampleNews> mutableLiveData;
    private NewsRepository newsRepository;
    public void init(String sourceId){
        if (mutableLiveData != null){
            return;
        }
        newsRepository = NewsRepository.getInstance();
        mutableLiveData = newsRepository.getNews(sourceId , BuildConfig.API_KEY);
    }

    public LiveData<ExampleNews> getNewsRepository() {
        return mutableLiveData;
    }
}
