package com.example.food_family;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

public class descfragment extends Fragment {


    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";



    String description, name, prise, purl;
    int Intprise;
    private String mParam1;
    private String mParam2;

    public descfragment() {

    }
    public descfragment(String description, String name, String prise, String purl) {

        this.description= description;
        this.name = name;
        this.prise = prise;
        this.purl = purl;


    }


    public static descfragment newInstance(String param1, String param2) {
        descfragment fragment = new descfragment();
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


        View view= inflater.inflate(R.layout.fragment_descfragment, container, false);

       ImageView imageHolder = view.findViewById(R.id.imageHolder);
       TextView nameHolder = view.findViewById(R.id.nameHolder);
       TextView priseHolder = view.findViewById(R.id.priseHolder);
       TextView descriptionHolder = view. findViewById(R.id.descriptionHolder);
       ImageView like_btn = view.findViewById(R.id.like_btm);
        Button buyNow = view.findViewById(R.id.dec_buyNow);
        Button addCart = view.findViewById(R.id.addToCart);



       nameHolder.setText(name);
       priseHolder.setText(prise);
       descriptionHolder.setText(description);
        Glide.with(getContext()).load(purl).into(imageHolder);


        addCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                prossesInsert(name.toString(), prise.toString(), purl.toString());


            }

            private void prossesInsert(String name,  String prise, String purl) {
                String res = new myDatabaseHandler(getContext()).addCart(name,prise, purl );

//                Toast.makeText(getContext(), res, Toast.LENGTH_SHORT).show();

            }


        });

        return view;

    }




}