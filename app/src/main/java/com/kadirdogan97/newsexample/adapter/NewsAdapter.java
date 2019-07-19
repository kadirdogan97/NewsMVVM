package com.kadirdogan97.newsexample.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;


import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.sqlite.db.SupportSQLiteOpenHelper;

import com.kadirdogan97.newsexample.R;
import com.kadirdogan97.newsexample.database.News;
import com.kadirdogan97.newsexample.database.NewsDao;
import com.kadirdogan97.newsexample.database.NewsDatabase;
import com.kadirdogan97.newsexample.database.NewsDbRepository;
import com.kadirdogan97.newsexample.databinding.NewsItemBinding;
import com.kadirdogan97.newsexample.model.Articles;
import com.kadirdogan97.newsexample.viewmodel.VMNewsActivity;

import java.util.ArrayList;


public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.NewsViewHolder> {
    Context context;
    ArrayList<Articles> articles;
    OnNewsListener mOnNewsListener;
    VMNewsActivity vmNewsActivity;
    public NewsAdapter(Context context, ArrayList<Articles> articles, OnNewsListener onNewsListener) {
        this.context = context;
        this.articles = articles;
        this.mOnNewsListener = onNewsListener;

    }

    @NonNull
    @Override
    public NewsAdapter.NewsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        NewsItemBinding newsBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.news_item, parent, false);
        return new NewsViewHolder(newsBinding, mOnNewsListener);
    }

    @Override
    public void onBindViewHolder(@NonNull NewsViewHolder holder, int position) {
        Articles article = articles.get(position);
        String sUrl = holder.sharedPref.getString(articles.get(position).getPublishedAt(), "Kayıt Yok");
        if(!sUrl.equals("Kayıt Yok")){
            holder.newsItemBinding.tvReading.setText(context.getResources().getString(R.string.remove_reading));
            holder.clickReading=true;
        }else{
            holder.newsItemBinding.tvReading.setText(context.getResources().getString(R.string.add_reading));
            holder.clickReading=false;
        }
        holder.newsItemBinding.setNews(article);
        holder.newsItemBinding.setImageUrl(articles.get(position).getUrlToImage());
    }

    @Override
    public int getItemCount() {
        return articles.size();
    }
    public class NewsViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        NewsItemBinding newsItemBinding;
        Boolean clickReading = false;
        OnNewsListener onNewsListener;
        News news;

        //NewsDbRepository newsDbRepository = new NewsDbRepository(context);
        String id,sourceName,author,title,description,url,urlToImage;
        SharedPreferences sharedPref = context.getSharedPreferences(context.getString(R.string.preference_enable), context.MODE_PRIVATE);

        public NewsViewHolder(@NonNull NewsItemBinding newsItemBinding, OnNewsListener onNewsListener) {
            super(newsItemBinding.getRoot());
            this.newsItemBinding = newsItemBinding;
            this.onNewsListener = onNewsListener;
            newsItemBinding.getRoot().setOnClickListener(this::onClick);
            newsItemBinding.tvReading.setOnClickListener(this::onReadClick);
        }

        @Override
        public void onClick(View view) {
            String sUrl = sharedPref.getString(articles.get(getAdapterPosition()).getPublishedAt(), "Kayıt Yok");
            AlertDialog.Builder builder = new AlertDialog.Builder(context);
            builder.setTitle("Listedeki Url");
            if(sUrl.equals("Kayıt Yok")){
                builder.setMessage("Önce Kayıt Yapınız");
            }else{ //Kayıtlı değerler yazdırılıyor
                builder.setMessage("Kayıtlı String : "+sUrl);
            }
            builder.show();
            onNewsListener.onNewsClick(getAdapterPosition());
        }
        public void onReadClick(View view) {
            int position = getAdapterPosition();

            SharedPreferences.Editor editor = sharedPref.edit();
            if(clickReading==false){
                newsItemBinding.tvReading.setText(context.getResources().getString(R.string.remove_reading));
                editor.putString(articles.get(position).getPublishedAt(), articles.get(position).getUrl());
                id = articles.get(position).getPublishedAt();
                sourceName = articles.get(position).getSource().getName();
                author = articles.get(position).getAuthor();
                title = articles.get(position).getTitle();
                description = articles.get(position).getDescription();
                url = articles.get(position).getUrl();
                urlToImage = articles.get(position).getUrlToImage();
                //news = new News(id,sourceName,author,title,description,url,urlToImage);
                //addReadingNews(newsDbRepository, news);
                clickReading=true;
                Toast.makeText(context, "Added", Toast.LENGTH_SHORT).show();
                Log.d("Test", "onReadClick: Added");
            }else{
                newsItemBinding.tvReading.setText(context.getResources().getString(R.string.add_reading));
                editor.remove(articles.get(position).getPublishedAt());
                //newsDbRepository.delete(articles.get(position).getPublishedAt());
                clickReading=false;
                Toast.makeText(context, "Removed", Toast.LENGTH_SHORT).show();
                Log.d("Test", "onReadClick: Remove");
            }
            editor.commit();
            onNewsListener.onReadClick(getAdapterPosition());
        }
    }
    public interface OnNewsListener{
        void onNewsClick(int position);
        void onReadClick(int position);
    }
    private static void addReadingNews(final NewsDbRepository dbRepo, News news) {
        dbRepo.insert(news);
    }
}
