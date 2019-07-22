package com.kadirdogan97.newsexample.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.kadirdogan97.newsexample.BuildConfig;
import com.kadirdogan97.newsexample.model.ExampleSources;
import com.kadirdogan97.newsexample.network.SourcesRepository;

public class VMSourcesActivity extends ViewModel {
    private MutableLiveData<ExampleSources> mutableLiveData;
    private SourcesRepository sourcesRepository;
    public void init(){
        if (mutableLiveData != null){
            return;
        }
        sourcesRepository = SourcesRepository.getInstance();
        mutableLiveData = sourcesRepository.getSources(BuildConfig.API_KEY);
    }
    public LiveData<ExampleSources> getSourcesRepository() {
        return mutableLiveData;
    }
}
