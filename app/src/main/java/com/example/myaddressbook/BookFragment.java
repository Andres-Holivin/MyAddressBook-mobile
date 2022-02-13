package com.example.myaddressbook;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.myaddressbook.adapter.BookAdapter;
import com.example.myaddressbook.adapter.EmployeeAdapter;


public class BookFragment extends Fragment {
    public BookFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.fragment_book, container, false);
        RecyclerView recyclerView=view.findViewById(R.id.rv_books);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        BookAdapter bookAdapter=new BookAdapter(getContext());
        recyclerView.setAdapter(bookAdapter);
        return view;
    }
}