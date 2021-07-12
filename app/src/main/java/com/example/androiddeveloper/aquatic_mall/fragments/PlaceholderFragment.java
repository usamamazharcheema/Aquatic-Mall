package com.example.androiddeveloper.aquatic_mall.fragments;


import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.androiddeveloper.aquatic_mall.Activities.LookingToInvest;
import com.example.androiddeveloper.aquatic_mall.Adapters.LookinToInvestAdapter;
import com.example.androiddeveloper.aquatic_mall.R;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
@SuppressLint("ValidFragment")
public class PlaceholderFragment extends Fragment {
    /**
     * The fragment argument representing the section number for this
     * fragment.
     */
    private static final String ARG_SECTION_NUMBER = "section_number";
    GridView gv;
    TextView shops, floors;
    public  static String checkfilter=null;
    public  static String catfilter=null;
    public  int position;
    private RecyclerView recyclerView;
    LookinToInvestAdapter homeRecyclerViewAdapter;
    LinearLayout forward, backward;

    ArrayList<feature_model> moviees=new ArrayList<>();
    private final static String API_KEY = "https://cpecintel.com/aquatic/features/feature_select.php";

    @SuppressLint("ValidFragment")
    public PlaceholderFragment(int position1) {
        position =position1;
    }


    public static PlaceholderFragment newInstance(int sectionNumber, String filter, String shops) {

        checkfilter=filter;
        catfilter=shops;
        PlaceholderFragment fragment = new PlaceholderFragment(sectionNumber);
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);

        fragment.setArguments(args);
        return fragment;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_blank, container, false);
//        Toast.makeText(getActivity(), catfilter, Toast.LENGTH_SHORT).show();
        recyclerView = (RecyclerView)rootView.findViewById(R.id.favrecyclerview);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
//        homeRecyclerViewAdapter=new LookinToInvestAdapter(0, getActivity(),moviees);
//        recyclerView.setAdapter(homeRecyclerViewAdapter);

        shops= rootView.findViewById(R.id.shops);
        forward=(LinearLayout) rootView.findViewById(R.id.gforward);
        floors= rootView.findViewById(R.id.floors);
//        gv.setAdapter(new CustomAdapter(getContext(), moviees));
        forward.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
ArrayList<feature_model> feature_models=new ArrayList<>();

        if (LookingToInvest.featuremodellist!=null) {
            for (int i = 0; i < LookingToInvest.featuremodellist.size(); i = i + 1) {
                if (LookingToInvest.featuremodellist.get(i).getFeaturename().equals(LookingToInvest.shoplist.get(position)) && LookingToInvest.featuremodellist.get(i).getFloor().equals(LookingToInvest.arrayList.get(position))) {
                    feature_models.add(LookingToInvest.featuremodellist.get(i));
                    shops.setText(catfilter);
                    floors.setText(checkfilter);

                }
                if(LookingToInvest.featuremodellist.size()-i==1)
                {
                    recyclerView.setAdapter(new LookinToInvestAdapter(0,getActivity(), feature_models));
//                            LookingToInvest.mainlayoutcall();
                }
            }
            if (getActivity()!=null){

            }}




















        return rootView;
    };
}