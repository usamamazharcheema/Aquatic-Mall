package com.example.androiddeveloper.aquatic_mall.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.androiddeveloper.aquatic_mall.Activities.AboutMallFeature;
import com.example.androiddeveloper.aquatic_mall.Adapters.FeatureRecylerAdapter;
import com.example.androiddeveloper.aquatic_mall.Adapters.NewsAdapter;
import com.example.androiddeveloper.aquatic_mall.Interfaces.ApiClient;
import com.example.androiddeveloper.aquatic_mall.Interfaces.ManageResponse;
import com.example.androiddeveloper.aquatic_mall.R;
import com.example.androiddeveloper.aquatic_mall.ResponseLists.Featureimgresponse;
import com.example.androiddeveloper.aquatic_mall.ResponseLists.Featureresponse;
import com.example.androiddeveloper.aquatic_mall.ResponseLists.FeaturesResponseList;
import com.example.androiddeveloper.aquatic_mall.ResponseLists.PicResponse;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by ANDROID DEVELOPER on 07/02/2018.
 */

public class FeatureRcyclerlayout extends Fragment {
    public static FeatureRcyclerlayout newInstance(String movieTitle) {
        FeatureRcyclerlayout fragmentAction = new FeatureRcyclerlayout();
        Bundle args = new Bundle();
        args.putString(" ", movieTitle);
        fragmentAction.setArguments(args);

        return fragmentAction;
    }
    ArrayList<Featureimgresponse> featureimgresponses=new ArrayList<>();
    RecyclerView featurerecclerview;
    FeatureRecylerAdapter featureRecylerAdapter;
    ArrayList<Integer>  imglists=new ArrayList<>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.featurerycylerview, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
imglists.clear();
try {
    if (Features.itemname.equals("Business Centre")) {
        imglists.add(R.drawable.office1);
        imglists.add(R.drawable.office2);
        imglists.add(R.drawable.office2);
    } else if (Features.itemname.equals("Luxury Apartments")) {
        imglists.add(R.drawable.apartment1);
        imglists.add(R.drawable.apartment2);
        imglists.add(R.drawable.apartment3);
    } else if (Features.itemname.equals("Deluxe Suites")) {
        imglists.add(R.drawable.suite1);
        imglists.add(R.drawable.suite2);
        imglists.add(R.drawable.suite3);
        imglists.add(R.drawable.suite4);
    } else if (Features.itemname.equals("Shopping Area")) {
        imglists.add(R.drawable.shops1);
        imglists.add(R.drawable.shops2);
        imglists.add(R.drawable.shops3);
    } else if (Features.itemname.equals("Kids Area")) {
//            Toast.makeText(getActivity(), "adkj", Toast.LENGTH_SHORT).show();
        imglists.add(R.drawable.kidsarea1);
        imglists.add(R.drawable.kidsarea2);
        imglists.add(R.drawable.kidsarea3);
        imglists.add(R.drawable.kidsarea4);
    } else if (Features.itemname.equals("Food Court")) {
        imglists.add(R.drawable.foodcourt1);
        imglists.add(R.drawable.foodcourt2);
        imglists.add(R.drawable.foodcourt3);
    }
}
catch (Exception e)
{

}
       /* imglists.add(R.drawable.foodcourt1)  ;
        imglists.add(R.drawable.foodcourt2)  ;
        imglists.add(R.drawable.foodcourt3)  ;*/










        featurerecclerview = (RecyclerView) view.findViewById(R.id.featurerecclerview);

        featureRecylerAdapter=new FeatureRecylerAdapter(getActivity(),imglists);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        featurerecclerview.setLayoutManager(mLayoutManager);
        featurerecclerview.setItemAnimator(new DefaultItemAnimator());
        featurerecclerview.setAdapter(featureRecylerAdapter);
        featureRecylerAdapter.notifyDataSetChanged();



    }


}
