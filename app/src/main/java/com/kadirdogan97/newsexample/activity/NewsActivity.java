package com.kadirdogan97.newsexample.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.kadirdogan97.newsexample.R;
import com.kadirdogan97.newsexample.adapter.NewsAdapter;
import com.kadirdogan97.newsexample.database.News;
import com.kadirdogan97.newsexample.databinding.ActivityNewsBinding;
import com.kadirdogan97.newsexample.model.ArticleSource;
import com.kadirdogan97.newsexample.model.Articles;
import com.kadirdogan97.newsexample.viewmodel.VMNewsActivity;

import java.util.ArrayList;
import java.util.List;


public class NewsActivity extends AppCompatActivity implements NewsAdapter.OnNewsListener{
    private ActivityNewsBinding mBinding;
    private ArrayList<Articles> newsArrayList = new ArrayList<>();
    private NewsAdapter newsAdapter;
    private VMNewsActivity newsViewModel;
    private String id,sourceName,author,title,description,url,urlToImage;
    private News news;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_news);
        newsViewModel = ViewModelProviders.of(this).get(VMNewsActivity.class);
        newsViewModel.init(getIntent().getStringExtra("source"));
        newsViewModel.getNewsRepository().observe(this, newsResponse -> {
            List<Articles> sourcesList = newsResponse.getArticles();
            newsArrayList.addAll(sourcesList);
            newsAdapter.notifyDataSetChanged();
        });
        setupRecyclerView();
    }

    private void setupRecyclerView() {
        if (newsAdapter == null) {
            newsAdapter = new NewsAdapter(NewsActivity.this, newsArrayList,this);
            mBinding.rvNews.setLayoutManager(new LinearLayoutManager(this));
            mBinding.rvNews.setAdapter(newsAdapter);
            mBinding.rvNews.setItemAnimator(new DefaultItemAnimator());
            mBinding.rvNews.setNestedScrollingEnabled(true);
        } else {
            newsAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onNewsClick(int position) {
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(newsArrayList.get(position).getUrl()));
        startActivity(browserIntent);
        Log.d("NewsActivity: ", "onNewsClick: "+position);
    }

    @Override
    public void onReadClick(int position) {

        SharedPreferences sharedPref = getSharedPreferences(getString(R.string.preference_enable),MODE_PRIVATE);
        id = newsArrayList.get(position).getPublishedAt();
        sourceName = newsArrayList.get(position).getSource().getName();
        author = newsArrayList.get(position).getAuthor();
        title = newsArrayList.get(position).getTitle();
        description = newsArrayList.get(position).getDescription();
        url = newsArrayList.get(position).getUrl();
        urlToImage = newsArrayList.get(position).getUrlToImage();
        news = new News(id,sourceName,author,title,description,url,urlToImage);
        String sUrl = sharedPref.getString(newsArrayList.get(position).getPublishedAt(),"Kayıt Yok");
        if(!sUrl.equals("Kayıt Yok")){
            insert(news);
            getAllNews();
        }else{
            deleteById(newsArrayList.get(position).getPublishedAt());
        }

    }

    private void getAllNews() {
        newsViewModel.getAllNews().observe(this, new Observer<List<News>>() {
            @Override
            public void onChanged(List<News> newsS) {

                Log.d("DBSIZE", "dbSize:"+newsS.size());
                for (int i = 0; i<newsS.size();i++){
                    Log.d("DatabaseItems", "databaseItem"+(i+1)+": "+newsS.get(i).getId());
                }
            }
        });
    }

    private void insert(News news) {
        newsViewModel.insert(news);
    }

    private void deleteById(String id){
        newsViewModel.deleteById(id);

    }
}
