package com.kadirdogan97.newsexample.viewmodel;
import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.kadirdogan97.newsexample.BuildConfig;
import com.kadirdogan97.newsexample.database.News;
import com.kadirdogan97.newsexample.database.NewsDbRepository;
import com.kadirdogan97.newsexample.model.ExampleNews;
import com.kadirdogan97.newsexample.network.NewsRepository;

import java.util.List;

public class VMNewsActivity extends AndroidViewModel {
    private MutableLiveData<ExampleNews> mutableLiveData;
    private NewsRepository newsRepository;

    private LiveData<List<News>> mAllNews;
    private NewsDbRepository newsDbRepository;
    public VMNewsActivity(@NonNull Application application) {
        super(application);
        newsDbRepository = new NewsDbRepository(application);
        mAllNews = newsDbRepository.getmAllNews();
    }
    public LiveData<List<News>> getAllNews() {
        return mAllNews;
    }
    public void insert(News news){
        newsDbRepository.insert(news);
    }
    public void deleteById(String deleteId){
        newsDbRepository.delete(deleteId);
    }

    /*
        private LiveData<List<News>> mAllNews;
        private NewsDbRepository newsDbRepository;

        public VMNewsActivity(Application application){
            super(application);
            newsDbRepository = new NewsDbRepository(application);

        }

        LiveData<List<News>> getAllNews(){
            return mAllNews;
        }

        public void insert(News news){
            newsDbRepository.insert(news);
        }
    */
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
