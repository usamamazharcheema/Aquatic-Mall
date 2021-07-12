package com.example.androiddeveloper.aquatic_mall.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.androiddeveloper.aquatic_mall.R;
import com.example.androiddeveloper.aquatic_mall.ResponseLists.Getchatresponse;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by ANDROID DEVELOPER on 14/03/2018.
 */




public class ChatAdapter extends RecyclerView.Adapter<ChatAdapter.MyViewHolder> {



    Context context;
ArrayList<Getchatresponse> getchatresponses;


    public ChatAdapter( Context context,ArrayList<Getchatresponse> getchatresponses) {

        this.context = context;
        this.getchatresponses=getchatresponses;

    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

TextView self,sender;

        public MyViewHolder(View view) {
            super(view);
self=view.findViewById(R.id.sender);
sender=view.findViewById(R.id.receiver);

        }
    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.chatrecyclerlayout, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {

if(getchatresponses.get(position).getSender().equals("user"))
{
    holder.self.setText(getchatresponses.get(position).getMessage().toString());
    holder.sender.setVisibility(View.GONE);
}
else
{
    holder.self.setVisibility(View.GONE);
    holder.sender.setText(getchatresponses.get(position).getMessage().toString());
}
    }

    @Override
    public int getItemCount() {
        return getchatresponses.size();
    }


}