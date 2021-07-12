package com.example.androiddeveloper.aquatic_mall.fragments;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.androiddeveloper.aquatic_mall.R;

import java.util.ArrayList;

/**
 * Created by fm on 1/1/2018.
 */

public class Installments_Adapter extends RecyclerView.Adapter<Installments_Adapter.MovieViewHolder> {

    private ArrayList<MainViewResponse> movies;
    private int rowLayout;
    private Context context;


    public static class MovieViewHolder extends RecyclerView.ViewHolder {
        TextView in_installments, in_amount,in_duedate,in_status;

        public MovieViewHolder(View v) {
            super(v);
            in_installments = (TextView) v.findViewById(R.id.in_install);
            in_amount = (TextView) v.findViewById(R.id.in_amount);
            in_duedate = (TextView) v.findViewById(R.id.in_duedate);
            in_status = (TextView) v.findViewById(R.id.in_status);
        }
    }

    public Installments_Adapter(ArrayList<MainViewResponse> movies, int rowLayout, Context context) {
        this.movies = movies;
        this.rowLayout = rowLayout;
        this.context = context;
    }

    @Override
    public Installments_Adapter.MovieViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(rowLayout, parent, false);
        return new MovieViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MovieViewHolder holder, final int position) {
        holder.in_installments.setText(movies.get(position).getInstallments());
        holder.in_amount.setText(movies.get(position).getAmount());
        holder.in_duedate.setText(movies.get(position).getDuedate());
        holder.in_status.setText(movies.get(position).getStatus());

    }

    @Override
    public int getItemCount() {
        return movies.size();
    }
}