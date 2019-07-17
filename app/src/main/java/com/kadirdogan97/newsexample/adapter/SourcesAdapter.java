package com.kadirdogan97.newsexample.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.kadirdogan97.newsexample.R;
import com.kadirdogan97.newsexample.databinding.ActivitySourcesBinding;
import com.kadirdogan97.newsexample.databinding.SourceItemBinding;
import com.kadirdogan97.newsexample.model.Sources;

import java.util.ArrayList;

public class SourcesAdapter extends RecyclerView.Adapter<SourcesAdapter.SourcesViewHolder> {

    private Context context;
    private ArrayList<Sources> sources;
    private OnSourceListener mOnSourceListener;

    public SourcesAdapter(Context context, ArrayList<Sources> sources, OnSourceListener onSourceListener) {
        this.context = context;
        this.sources = sources;
        this.mOnSourceListener = onSourceListener;
    }

    @NonNull
    @Override
    public SourcesAdapter.SourcesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        SourceItemBinding sourceBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.source_item, parent, false);
        SourcesViewHolder viewHolder = new SourcesViewHolder(sourceBinding, mOnSourceListener);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull SourcesViewHolder holder, int position) {
        Sources source = sources.get(position);
        holder.sourceItemBinding.setSource(source);
    }

    @Override
    public int getItemCount() {
        return sources.size();
    }

    public class SourcesViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        public SourceItemBinding sourceItemBinding;
        OnSourceListener onSourceListener;
        public SourcesViewHolder(@NonNull SourceItemBinding sourceItemBinding, OnSourceListener onSourceListener) {
            super(sourceItemBinding.getRoot());
            this.sourceItemBinding = sourceItemBinding;
            sourceItemBinding.getRoot().setOnClickListener(this);
            this.onSourceListener = onSourceListener;
        }

        @Override
        public void onClick(View view) {
            onSourceListener.onSourceClick(getAdapterPosition());
        }
    }
    public interface OnSourceListener{
        void onSourceClick(int position);
    }
}