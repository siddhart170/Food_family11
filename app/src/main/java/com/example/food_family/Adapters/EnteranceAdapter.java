package com.example.food_family.Adapters;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.food_family.Models.EnteranceModel;
import com.example.food_family.R;
import com.example.food_family.descfragment;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

public class EnteranceAdapter extends FirebaseRecyclerAdapter<EnteranceModel,EnteranceAdapter.myviewholder> {



    public EnteranceAdapter(@NonNull FirebaseRecyclerOptions<EnteranceModel> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull myviewholder holder, int position, @NonNull EnteranceModel model) {

        holder.foodDescription.setText(model.getDescription());
        holder.foodName.setText(model.getName());
        holder.foodPrise.setText(model.getPrise());
        Glide.with(holder.foodImage.getContext()).load(model.getPurl()).into(holder.foodImage);

          holder.cardView.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View view) {
                 AppCompatActivity activity = (AppCompatActivity)view.getContext();
                 activity.getSupportFragmentManager().beginTransaction().replace(R.id.wrapper, new descfragment(model.getDescription(),model.getName(), model.getPrise(), model.getPurl())).addToBackStack(null).commit();
              }
          });

    }

    @NonNull
    @Override
    public myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.singlerowdesign, parent, false);
       return new myviewholder(view);
    }

    public class myviewholder extends RecyclerView.ViewHolder {


        CardView cardView;
        ImageView foodImage;
        TextView foodName, foodPrise, foodDescription;
        public myviewholder(@NonNull View itemView) {
            super(itemView);
            foodImage = (ImageView) itemView.findViewById(R.id.foodImage);
            foodName = (TextView) itemView.findViewById(R.id.foodName);
            foodPrise = (TextView) itemView.findViewById(R.id.foodPrise);
            foodDescription = (TextView) itemView.findViewById(R.id.foodDescription);
            cardView = (CardView) itemView.findViewById(R.id.cardView);


        }
    }

}
