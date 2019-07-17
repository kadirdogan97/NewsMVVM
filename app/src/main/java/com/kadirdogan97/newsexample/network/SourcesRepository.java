package com.kadirdogan97.newsexample.network;

import androidx.lifecycle.MutableLiveData;

import com.kadirdogan97.newsexample.model.ExampleSources;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SourcesRepository {
    private static SourcesRepository sourcesRepository;
    public static SourcesRepository getInstance(){
        if (sourcesRepository == null){
            sourcesRepository = new SourcesRepository();
        }
        return sourcesRepository;
    }
    private SourcesApi sourcesApi;

    public SourcesRepository(){
        sourcesApi = RetrofitService.createService(SourcesApi.class);
    }
    public MutableLiveData<ExampleSources> getSources(String key){
        final MutableLiveData<ExampleSources> sourcesData = new MutableLiveData<>();
        sourcesApi.getSourcesList(key).enqueue(new Callback<ExampleSources>() {
            @Override
            public void onResponse(Call<ExampleSources> call,
                                   Response<ExampleSources> response) {
                if (response.isSuccessful()){
                    sourcesData.setValue(response.body());
                }
            }
            @Override
            public void onFailure(Call<ExampleSources> call, Throwable t) {
                // TODO: 17.07.2019 tüm error ihtimallerinde erroru çekerek bi toast bas
                sourcesData.setValue(null);
            }
        });
        return sourcesData;
    }
}
