package com.example.myapplication.views;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.myapplication.R;
import com.example.myapplication.adapters.BookSearchResultAdapter;
import com.example.myapplication.models.Result;
import com.example.myapplication.viewmodels.BookSearchViewModel;


public class BookSearchFragment extends Fragment {

    private BookSearchViewModel viewModel;
    private BookSearchResultAdapter adapter;

    private EditText keyword;
    private Button button;

    public BookSearchFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        adapter = new BookSearchResultAdapter();
        viewModel = new ViewModelProvider(this).get(BookSearchViewModel.class);
        viewModel.init();
        viewModel.getResultLiveData().observe(this, new Observer<Result>() {
            @Override
            public void onChanged(Result result) {
                if(result != null) {
                    adapter.setResults(result.getItems());
                }
            }
        });


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_book_search, container, false);
        RecyclerView recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);
        keyword = view.findViewById(R.id.editKeyWord);
        button = view.findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String query = keyword.getText().toString();
                viewModel.searchResult(query);
            }
        });

        return view;
    }
}