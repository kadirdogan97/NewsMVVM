package com.kadirdogan97.newsexample.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import androidx.annotation.NonNull;
import androidx.databinding.BindingAdapter;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.kadirdogan97.newsexample.R;
import com.kadirdogan97.newsexample.databinding.NewsItemBinding;
import com.kadirdogan97.newsexample.model.Articles;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;


public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.NewsViewHolder> {
    Context context;
    ArrayList<Articles> articles;
    OnNewsListener mOnNewsListener;
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
        String sUrl = holder.sharedPref.getString(String.valueOf(position), "Kayıt Yok");
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
            onNewsListener.onNewsClick(getAdapterPosition());
            String sUrl = sharedPref.getString(String.valueOf(getAdapterPosition()), "Kayıt Yok");
            AlertDialog.Builder builder = new AlertDialog.Builder(context);
            builder.setTitle("Listedeki Url");
            if(sUrl.equals("Kayıt Yok")){
                builder.setMessage("Önce Kayıt Yapınız");
            }else{ //Kayıtlı değerler yazdırılıyor
                builder.setMessage("Kayıtlı String : "+sUrl);
            }
            builder.show();
        }

        public void onReadClick(View view) {
            int position = getAdapterPosition();
            SharedPreferences.Editor editor = sharedPref.edit();
            if(clickReading==false){
                newsItemBinding.tvReading.setText(context.getResources().getString(R.string.remove_reading));
                editor.putString(String.valueOf(position), articles.get(position).getUrl());
                clickReading=true;
                Toast.makeText(context, "Added", Toast.LENGTH_SHORT).show();
            }else{
                newsItemBinding.tvReading.setText(context.getResources().getString(R.string.add_reading));
                editor.remove(String.valueOf(position));
                clickReading=false;
                Toast.makeText(context, "Removed", Toast.LENGTH_SHORT).show();
            }
            editor.commit();


        }
    }
    public interface OnNewsListener{
        void onNewsClick(int position);
    }
}
