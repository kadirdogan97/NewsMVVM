package com.kadirdogan97.newsexample.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcel;
import android.util.Log;

import com.kadirdogan97.newsexample.R;
import com.kadirdogan97.newsexample.adapter.NewsAdapter;
import com.kadirdogan97.newsexample.databinding.ActivityNewsBinding;
import com.kadirdogan97.newsexample.model.ArticleSource;
import com.kadirdogan97.newsexample.model.Articles;
import com.kadirdogan97.newsexample.model.Sources;
import com.kadirdogan97.newsexample.viewmodel.VMNewsActivity;

import java.util.ArrayList;
import java.util.List;


public class NewsActivity extends AppCompatActivity implements NewsAdapter.OnNewsListener{
    private ActivityNewsBinding mBinding;
    private ArrayList<Articles> newsArrayList = new ArrayList<>();
    private NewsAdapter newsAdapter;
    private VMNewsActivity newsViewModel;
    private String id,sourceName,author,title,description,url,urlToImage;
    private Articles news;
    private Sources sources;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_news);
        newsViewModel = ViewModelProviders.of(this).get(VMNewsActivity.class);
        sources = getIntent().getParcelableExtra("source");
        String a = sources.getId();
        newsViewModel.init(a);
        newsViewModel.getNewsRepository().observe(this, newsResponse -> {
            List<Articles> sourcesList = newsResponse.getArticles();
            newsArrayList.addAll(sourcesList);
            newsAdapter.notifyDataSetChanged();
        });
        news=new Articles();

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
        if(newsArrayList.get(position).getAuthor()!= null)
            author = newsArrayList.get(position).getAuthor();
        else
            author = "";
        title = newsArrayList.get(position).getTitle();
        description = newsArrayList.get(position).getDescription();
        url = newsArrayList.get(position).getUrl();
        urlToImage = newsArrayList.get(position).getUrlToImage();
        this.news.setUid(id);
        this.news.setSourceName(sourceName);
        this.news.setAuthor(author);
        this.news.setTitle(title);
        this.news.setDescription(description);
        this.news.setUrl(url);
        this.news.setUrlToImage(urlToImage);
        String sUrl = sharedPref.getString(newsArrayList.get(position).getPublishedAt(),"Kayıt Yok");
        if(!sUrl.equals("Kayıt Yok")){
            insert(news);
            getAllNews();
        }else{
            deleteById(id);
        }
    }

    private void getAllNews() {
        newsViewModel.getAllNews().observe(this, new Observer<List<Articles>>() {
            @Override
            public void onChanged(List<Articles> newsS) {

                Log.d("DBSIZE", "dbSize:"+newsS.size());
                for (int i = 0; i<newsS.size();i++){
                    Log.d("DatabaseItems", "databaseItem"+(i+1)+": "+newsS.get(i).getUid());
                }
            }
        });
    }

    private void insert(Articles news) {
        newsViewModel.insert(news);
    }

    private void deleteById(String id){
        newsViewModel.deleteById(id);

    }

}
