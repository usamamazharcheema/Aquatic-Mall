package com.example.androiddeveloper.aquatic_mall.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.androiddeveloper.aquatic_mall.MainActivity;
import com.example.androiddeveloper.aquatic_mall.R;

/**
 * Created by ANDROID DEVELOPER on 08/02/2018.
 */

public  class Exclusive  extends Fragment {


    public static Exclusive newInstance(String movieTitle) {
        Exclusive fragmentAction = new Exclusive();
        Bundle args = new Bundle();
        args.putString(" ", movieTitle);
        fragmentAction.setArguments(args);

        return fragmentAction;
    }
    Button underwater,apartments,suites,marine,pool,waterpark,auadam,helipad;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.exclusive_layout, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        underwater=view.findViewById(R.id.underwaterpark);
        apartments=view.findViewById(R.id.apartments);
        suites=view.findViewById(R.id.suites);
        marine=view.findViewById(R.id.marine);
        pool=view.findViewById(R.id.pool);
        waterpark=view.findViewById(R.id.waterpark);
        auadam=view.findViewById(R.id.aquadam);
        helipad=view.findViewById(R.id.helipad);
        underwater.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MianExclusive.exclusivetitle="UnderWater Tunnel";

                Features.itemname="OFFICES";
MainActivity.backfragment=MainActivity.currentfragment;
                MainActivity.mNavigationManager.showFragmentExclusiveDetail("");
            }
        });
        apartments.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MianExclusive.exclusivetitle="Apartments With Lawn";

                Features.itemname="OFFICES";
                MainActivity.backfragment=MainActivity.currentfragment;
                MainActivity.mNavigationManager.showFragmentExclusiveDetail("");
            }
        });
        suites.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MianExclusive.exclusivetitle="Deluxe Suites";

                Features.itemname="OFFICES";
                MainActivity.backfragment=MainActivity.currentfragment;
                MainActivity.mNavigationManager.showFragmentExclusiveDetail("");
            }
        });
        marine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MianExclusive.exclusivetitle="Marine Themed Restaurant";

                Features.itemname="OFFICES";
                MainActivity.backfragment=MainActivity.currentfragment;
                MainActivity.mNavigationManager.showFragmentExclusiveDetail("");
//                Toast.makeText(getActivity(), "asdjkh", Toast.LENGTH_SHORT).show();

            }
        });
        pool.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                MianExclusive.exclusivetitle="Infinity Pool";

                Features.itemname="OFFICES";
                MainActivity.backfragment=MainActivity.currentfragment;
                MainActivity.mNavigationManager.showFragmentExclusiveDetail("");
            }
        });
        waterpark.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MianExclusive.exclusivetitle="Indoor Water Park";

                Features.itemname="OFFICES";
                MainActivity.backfragment=MainActivity.currentfragment;
                MainActivity.mNavigationManager.showFragmentExclusiveDetail("");
            }
        });
        helipad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MianExclusive.exclusivetitle="Helipad";

                Features.itemname="OFFICES";
                MainActivity.backfragment=MainActivity.currentfragment;
                MainActivity.mNavigationManager.showFragmentExclusiveDetail("");
            }
        });
        auadam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MianExclusive.exclusivetitle="Centralized Aquarium";

                Features.itemname="OFFICES";
                MainActivity.backfragment=MainActivity.currentfragment;
                MainActivity.mNavigationManager.showFragmentExclusiveDetail("");
            }
        });
        LinearLayout layout=view.findViewById(R.id.featurelayout);
        layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.backfragment="AboutMall";
                MainActivity.mNavigationManager.showFragmentFeatures("");
            }
        });
    }
}
