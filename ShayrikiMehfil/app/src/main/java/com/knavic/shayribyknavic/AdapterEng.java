package com.knavic.shayribyknavic;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class AdapterEng extends RecyclerView.Adapter<AdapterEng.MyViewHolder> {

    private Context context;
    private Activity activity;
    private ArrayList joke_id, joke_title;

    public AdapterEng(Activity activity, Context context, ArrayList joke_id, ArrayList joke_title){
        this.activity = activity;
        this.context = context;
        this.joke_id = joke_id;
        this.joke_title = joke_title;


    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.raw_layout, parent, false);
        return new MyViewHolder(view);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, final int position) {
        holder.joke_id_txt.setText(String.valueOf(joke_id.get(position)));
        holder.joke_title_txt.setText(String.valueOf(joke_title.get(position)));

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



                Intent modify_intent = new Intent(context.getApplicationContext(), ShowJokeEng.class);
                modify_intent.putExtra("title", String.valueOf(joke_title.get(position)));
                modify_intent.putExtra("category","english");
                modify_intent.putExtra("id", String.valueOf(joke_id.get(position)));
                modify_intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(modify_intent);
            }
        });



    }

    @Override
    public int getItemCount() {
        return joke_id.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        TextView joke_id_txt, joke_title_txt;
        CardView cardView;

        MyViewHolder(@NonNull View itemView) {
            super(itemView);
            joke_id_txt = itemView.findViewById(R.id.joke_id_txt);
            joke_title_txt = itemView.findViewById(R.id.joke_title_txt);
            cardView = itemView.findViewById(R.id.cardview);


        }

    }

}

