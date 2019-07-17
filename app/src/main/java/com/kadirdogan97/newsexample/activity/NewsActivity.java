package com.kadirdogan97.newsexample.activity;

import androidx.appcompat.app.AppCompatActivity;
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
import com.kadirdogan97.newsexample.model.Articles;
import com.kadirdogan97.newsexample.viewmodel.VMNewsActivity;

import java.util.ArrayList;
import java.util.List;


public class NewsActivity extends AppCompatActivity implements NewsAdapter.OnNewsListener{
    private ArrayList<Articles> newsArrayList = new ArrayList<>();
    private NewsAdapter newsAdapter;
    private RecyclerView rvNews;
    private VMNewsActivity newsViewModel;

    TextView tvReading;
    Boolean clickReading = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);
        rvNews = findViewById(R.id.rvNews);
        tvReading = findViewById(R.id.tvReading);
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
            rvNews.setLayoutManager(new LinearLayoutManager(this));
            rvNews.setAdapter(newsAdapter);
            rvNews.setItemAnimator(new DefaultItemAnimator());
            rvNews.setNestedScrollingEnabled(true);
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
}
