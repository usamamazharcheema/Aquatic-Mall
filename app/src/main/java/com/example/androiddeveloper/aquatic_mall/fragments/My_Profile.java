package com.example.androiddeveloper.aquatic_mall.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.androiddeveloper.aquatic_mall.Interfaces.ApiClient;
import com.example.androiddeveloper.aquatic_mall.Interfaces.ManageResponse;
import com.example.androiddeveloper.aquatic_mall.R;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * A simple {@link Fragment} subclass.
 */
public class My_Profile extends Fragment {
    ArrayList<MainViewResponse> mainlist=new ArrayList<>();
    private static final String ARG_SECTION_NUMBER = "section_number";


    public My_Profile() {
        // Required empty public constructor
    }
    public static My_Profile newInstance(int sectionNumber) {
//        checkfilter=filter;
//        catfilter=shops;
        My_Profile fragment = new My_Profile();
        Bundle args = new Bundle();

        args.putInt(ARG_SECTION_NUMBER, sectionNumber);

        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        recallmethod(1);
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_my__profile, container, false);
    }
    public void recallmethod(int j)
    {
        ManageResponse service = ApiClient.getClient().create( ManageResponse.class);

        Call<MainResponselist> userCall =service.ivestor_profile(j);

        userCall.enqueue(new Callback<MainResponselist>() {
            @Override
            public void onResponse(Call<MainResponselist> call, Response<MainResponselist> response) {
                ArrayList<MainViewResponse> mainview = (ArrayList<MainViewResponse>) response.body().getResults();

                if (mainview!=null) {
                    for (int i = 0; i < mainview.size(); i = i + 1) {
                        mainlist.add(mainview.get(i));
                        mainview.clear();
                    }
                }
            }

            @Override
            public void onFailure(Call<MainResponselist> call, Throwable t) {
                Toast.makeText(getActivity(), "Please Check Your Internet Connection", Toast.LENGTH_SHORT).show();
                Log.d("onFailure", t.toString());
            }
        });
    }

}
