package com.example.androiddeveloper.aquatic_mall.Activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.androiddeveloper.aquatic_mall.MainActivity;
import com.example.androiddeveloper.aquatic_mall.R;
import com.example.androiddeveloper.aquatic_mall.fragments.DrawerFragment;
import com.example.androiddeveloper.aquatic_mall.fragments.Features;
import com.example.androiddeveloper.aquatic_mall.fragments.Slider1;

/**
 * Created by ANDROID DEVELOPER on 07/02/2018.
 */

public class AboutMall extends Fragment {

    LinearLayout features,layoutback,ratelayout,floorplans;
    TextView ratetext;
    ImageView imgback;
    public static AboutMall newInstance(String movieTitle) {
        AboutMall fragmentAction = new AboutMall();
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
        return inflater.inflate(R.layout.about_mall, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        imgback=view.findViewById(R.id.backaboutmall);
        layoutback=view.findViewById(R.id.layoutbackaboutmall);
        features=view.findViewById(R.id.featuress);
        ratelayout=view.findViewById(R.id.ratestructurelayout);
        floorplans=view.findViewById(R.id.floorplans);
        ratetext=view.findViewById(R.id.ratestructuretet);
        features.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainActivity.backfragment=MainActivity.currentfragment;
                MainActivity.mNavigationManager.showFragmentFeatures("");
            }
        });
        imgback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainActivity.backpressed();
            }
        });
        layoutback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainActivity.backpressed();
            }
        });
        ratelayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainActivity.backfragment=MainActivity.currentfragment;
                MainActivity.mNavigationManager.showFragmentRateStructure("");
            }
        });
        ratetext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainActivity.backfragment=MainActivity.currentfragment;
                MainActivity.mNavigationManager.showFragmentRateStructure("");
            }
        });
        floorplans.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainActivity.backfragment=MainActivity.currentfragment;
                MainActivity.mNavigationManager.showFragmentFloorPlans("");
            }
        });
    }





}
