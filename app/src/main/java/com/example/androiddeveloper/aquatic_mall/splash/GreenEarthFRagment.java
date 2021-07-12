package com.example.androiddeveloper.aquatic_mall.splash;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.androiddeveloper.aquatic_mall.R;

/**
 * Created by ANDROID DEVELOPER on 16/02/2018.
 */

public class GreenEarthFRagment extends Fragment {


    public static GreenEarthFRagment newInstance(String movieTitle) {
        GreenEarthFRagment fragmentAction = new GreenEarthFRagment();
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
        return inflater.inflate(R.layout.greenearthlayout, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
}

