package com.kadirdogan97.newsexample.database;

import android.app.Application;
import android.content.Context;
import android.os.AsyncTask;
import androidx.lifecycle.LiveData;

import com.kadirdogan97.newsexample.model.Articles;

import java.util.List;

public class NewsDbRepository {
    private NewsDao mNewsDao;
    private LiveData<List<Articles>> mAllNews;
    public NewsDbRepository(Application application){
        NewsDatabase db = NewsDatabase.getDatabase(application);
        mNewsDao = db.newsDao();
        mAllNews = mNewsDao.getAllNews();
    }
    public LiveData<List<Articles>> getmAllNews(){
        return mAllNews;
    }

    public void insert(Articles news){
        new insertAsyncTask(mNewsDao).execute(news);
    }

    public void delete(String newsId){
        new deleteAsyncTask(mNewsDao).execute(newsId);
    }

    private static class insertAsyncTask extends AsyncTask<Articles, Void, Void>{
        private NewsDao mAsyncTaskDao;

        insertAsyncTask(NewsDao newsDao){
            mAsyncTaskDao = newsDao;
        }

        @Override
        protected Void doInBackground(Articles... news) {
            mAsyncTaskDao.insert(news[0]);
            return null;
        }

    }
    private static class deleteAsyncTask extends AsyncTask<String, Void, Void>{
        private NewsDao mAsyncTaskDao;

        deleteAsyncTask(NewsDao newsDao){
            mAsyncTaskDao = newsDao;
        }
        @Override
        protected Void doInBackground(String... strings) {
            mAsyncTaskDao.deleteById(strings[0]);
            return null;
        }
    }

}
