package com.example.androiddeveloper.aquatic_mall.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.androiddeveloper.aquatic_mall.MainActivity;
import com.example.androiddeveloper.aquatic_mall.R;
import com.example.androiddeveloper.aquatic_mall.ResponseLists.Adminsslist;
import com.example.androiddeveloper.aquatic_mall.ResponseLists.Getchatresponse;
import com.example.androiddeveloper.aquatic_mall.fragments.Admin_Guest_List;

import java.util.ArrayList;

/**
 * Created by ANDROID DEVELOPER on 16/03/2018.
 */


public class Admin_Guest_List_Adapter extends RecyclerView.Adapter<Admin_Guest_List_Adapter.MyViewHolder> {



    Context context;
    ArrayList<Adminsslist> adminsslists;


    public Admin_Guest_List_Adapter( Context context, ArrayList<Adminsslist> adminsslists) {

        this.context = context;
        this.adminsslists=adminsslists;


    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView name,text;
        LinearLayout mainview;

        public MyViewHolder(View view) {
            super(view);
            text=view.findViewById(R.id.message);
            name=view.findViewById(R.id.name);
            mainview=view.findViewById(R.id.mainview);

        }
    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.gustslistrecycler, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {

holder.name.setText(adminsslists.get(position).getName().toString());
holder.text.setText(adminsslists.get(position).getMessage().toString());
holder.mainview.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        Admin_Guest_List.guestemail=adminsslists.get(position).getEmail().toString();
        Admin_Guest_List.guestname=adminsslists.get(position).getName().toString();
        Admin_Guest_List.guesttoken=adminsslists.get(position).getToken().toString();

        MainActivity.t.cancel();
        MainActivity.backfragment=MainActivity.currentfragment;
        MainActivity.mNavigationManager.showFragmentAdminChatFragment("");
    }
});
    }

    @Override
    public int getItemCount() {
        return adminsslists.size();
    }


}