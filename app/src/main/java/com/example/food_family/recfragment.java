package com.example.food_family;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.food_family.Adapters.EnteranceAdapter;
import com.example.food_family.Models.EnteranceModel;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;


public class recfragment extends Fragment {


    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";


    private String mParam1;
    private String mParam2;
    RecyclerView recyclerView;
    EnteranceAdapter adapter;

    public recfragment() {

    }



    public static recfragment newInstance(String param1, String param2) {
        recfragment fragment = new recfragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_recfragment, container, false);
         recyclerView =(RecyclerView)view.findViewById(R.id.recyclerview);
         recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));

        FirebaseRecyclerOptions<EnteranceModel> options =
                new FirebaseRecyclerOptions.Builder<EnteranceModel>()
                        .setQuery(FirebaseDatabase.getInstance().getReference("foods").child("foodlist"), EnteranceModel.class)
                        .build();


        adapter = new EnteranceAdapter(options);
        recyclerView.setAdapter(adapter);





        return view;
    }
    @Override
    public void onStart()
    {
        super.onStart();
        adapter.startListening();
    }
    @Override
    public void onStop()
    {
        super.onStop();
        adapter.stopListening();
    }






}
