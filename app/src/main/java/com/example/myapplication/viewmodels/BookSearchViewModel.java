package com.example.myapplication.viewmodels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.myapplication.models.Result;
import com.example.myapplication.respositories.BookRepository;

public class BookSearchViewModel extends AndroidViewModel {
    private BookRepository bookRepository;
    private LiveData<Result> resultLiveData;

    public BookSearchViewModel(@NonNull Application application) {
        super(application);
    }

    public LiveData<Result> getResultLiveData() {
        return resultLiveData;
    }

    public void init() {
        bookRepository = new BookRepository();
        resultLiveData = bookRepository.getResultLiveData();
    }

    public void searchResult(String keyword) {
        bookRepository.searchResult(keyword);
    }

}
