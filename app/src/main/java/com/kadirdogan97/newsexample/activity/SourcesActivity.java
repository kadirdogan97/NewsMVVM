package com.kadirdogan97.newsexample.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.kadirdogan97.newsexample.R;
import com.kadirdogan97.newsexample.adapter.SourcesAdapter;
import com.kadirdogan97.newsexample.databinding.ActivitySourcesBinding;
import com.kadirdogan97.newsexample.model.Sources;
import com.kadirdogan97.newsexample.viewmodel.VMSourcesActivity;

import java.util.ArrayList;
import java.util.List;

public class SourcesActivity extends AppCompatActivity implements SourcesAdapter.OnSourceListener {

    private ActivitySourcesBinding mBinding;
    private ArrayList<Sources> sourcesArrayList = new ArrayList<>();
    private SourcesAdapter sourcesAdapter;
    private VMSourcesActivity sourcesViewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_sources);
        sourcesViewModel = ViewModelProviders.of(this).get(VMSourcesActivity.class);
        sourcesViewModel.init();
        sourcesViewModel.getSourcesRepository().observe(this, sourcesResponse -> {
            List<Sources> sourcesList = sourcesResponse.getSources();
            sourcesArrayList.addAll(sourcesList);
            sourcesAdapter.notifyDataSetChanged();
        });
        setupRecyclerView();
    }
    private void setupRecyclerView() {
        if (sourcesAdapter == null) {
            sourcesAdapter = new SourcesAdapter(SourcesActivity.this, sourcesArrayList,this);
            mBinding.rvSources.setLayoutManager(new LinearLayoutManager(this));
            mBinding.rvSources.setAdapter(sourcesAdapter);
            mBinding.rvSources.setItemAnimator(new DefaultItemAnimator());
            mBinding.rvSources.setNestedScrollingEnabled(true);
        } else {
            sourcesAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onSourceClick(int position) {
        Log.d("SourcesActivity: ", "onSourceClick: "+position);
        Intent intent = new Intent(this, NewsActivity.class);
        intent.putExtra("source", sourcesArrayList.get(position).getId().toString());
        startActivity(intent);
    }
}