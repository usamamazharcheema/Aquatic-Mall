package com.example.androiddeveloper.aquatic_mall.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import com.example.androiddeveloper.aquatic_mall.Adapters.Admin_Guest_List_Adapter;
import com.example.androiddeveloper.aquatic_mall.Adapters.ChatAdapter;
import com.example.androiddeveloper.aquatic_mall.Interfaces.ApiClient;
import com.example.androiddeveloper.aquatic_mall.Interfaces.ManageResponse;
import com.example.androiddeveloper.aquatic_mall.MainActivity;
import com.example.androiddeveloper.aquatic_mall.R;
import com.example.androiddeveloper.aquatic_mall.ResponseLists.AdminGuestLists;
import com.example.androiddeveloper.aquatic_mall.ResponseLists.AdminResponseLists;
import com.example.androiddeveloper.aquatic_mall.ResponseLists.Adminslist;
import com.example.androiddeveloper.aquatic_mall.ResponseLists.Adminsslist;
import com.google.firebase.iid.FirebaseInstanceId;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by ANDROID DEVELOPER on 16/03/2018.
 */


public class Admin_Guest_List extends Fragment {
public static String guestemail=null;
    public static String guestname=null;
    public static String guesttoken=null;
    RecyclerView chatrecycler;
    Admin_Guest_List_Adapter admin_guest_list_adapter;
    ArrayList<Adminsslist> adminsslists=new ArrayList<>();
    LinearLayout mainlayout;
    ProgressBar progressBar;
    LinearLayout chatbacklayout;
    public static Admin_Guest_List newInstance(String movieTitle) {
        Admin_Guest_List fragmentAction = new Admin_Guest_List();
        Bundle args = new Bundle();
        args.putString(" ", movieTitle);
        fragmentAction.setArguments(args);

        return fragmentAction;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.adminchatlist, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        chatrecycler = (RecyclerView) view.findViewById(R.id.chatlists);
        progressBar=view.findViewById(R.id.progressBarloading);
        chatbacklayout=view.findViewById(R.id.chatlistback);
        mainlayout=view.findViewById(R.id.chatlistslayout);
        admin_guest_list_adapter=new Admin_Guest_List_Adapter(getActivity(),adminsslists);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        chatrecycler.setLayoutManager(mLayoutManager);
        chatrecycler.setItemAnimator(new DefaultItemAnimator());
        chatrecycler.setAdapter(admin_guest_list_adapter);
        admin_guest_list_adapter.notifyDataSetChanged();
        chatbacklayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.backpressed();
            }
        });
        adminguestlists();
    }
    public void adminguestlists()
    {
        ManageResponse apiService = ApiClient.getClient().create(ManageResponse.class);
        Call<AdminGuestLists> call = apiService.adminguestchatlists("https://cpecintel.com/aquatic/chat/getusrlist.php");
        call.enqueue(new Callback<AdminGuestLists>() {
            @Override
            public void onResponse(Call<AdminGuestLists> call, Response<AdminGuestLists> response) {
                int statusCode = response.code();

                ArrayList<Adminsslist> adminchatlists = (ArrayList<Adminsslist>) response.body().getAdminsslist();
                if (adminchatlists!=null) {

                    for(int c=0;c<adminchatlists.size();c++)
                    {
                        adminsslists.add(adminchatlists.get(c));
                        if(adminchatlists.size()-c==1)
                        {
                            admin_guest_list_adapter.notifyDataSetChanged();
                            progressBar.setVisibility(View.GONE);
                            mainlayout.setVisibility(View.VISIBLE);
                        }

                    }




                }
            }
            @Override
            public void onFailure(Call<AdminGuestLists> call, Throwable t) {
                adminguestlists();
            }
        });
    }
}
