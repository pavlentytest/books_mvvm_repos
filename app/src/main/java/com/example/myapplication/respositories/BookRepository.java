package com.example.myapplication.respositories;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.myapplication.apis.BookSearchService;
import com.example.myapplication.models.Result;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class BookRepository {
    private static final String BASE_URL = "https://www.googleapis.com/";
    private BookSearchService bookSearchService;
    private MutableLiveData<Result> resultLiveData;

    public BookRepository() {
        resultLiveData = new MutableLiveData<>();
        bookSearchService = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(BookSearchService.class);
    }

    public LiveData<Result> getResultLiveData() {
        return resultLiveData;
    }

    public void searchResult(String keyword) {
        bookSearchService.searchBooks(keyword).enqueue(new Callback<Result>() {
            @Override
            public void onResponse(Call<Result> call, Response<Result> response) {
                if(response.body() != null) {
                    resultLiveData.postValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<Result> call, Throwable t) {
                Log.d("RRR", t.getMessage());
                resultLiveData.postValue(null);
            }
        });
    }
}
