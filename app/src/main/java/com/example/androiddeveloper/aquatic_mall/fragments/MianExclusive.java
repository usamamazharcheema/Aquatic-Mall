package com.example.androiddeveloper.aquatic_mall.fragments;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.example.androiddeveloper.aquatic_mall.R;

/**
 * Created by ANDROID DEVELOPER on 08/02/2018.
 */

public class MianExclusive  extends Fragment {


    public static MianExclusive newInstance(String movieTitle) {
        MianExclusive fragmentAction = new MianExclusive();
        Bundle args = new Bundle();
        args.putString(" ", movieTitle);
        fragmentAction.setArguments(args);

        return fragmentAction;
    }
public static LinearLayout featuredetaillayout;
  public static   FragmentManager fm;
  public static FrameLayout frameLayout;
  public static String exclusivetitle=null;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.exclusivemain, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        MianExclusive.featuredetaillayout=view.findViewById(R.id.exclusivemainupper);
        MianExclusive.frameLayout=view.findViewById(R.id.exlusivemainfragment);
        MianExclusive.fm = getActivity().getSupportFragmentManager();


        MianExclusive.showFragment(Exclusive.newInstance(""), true);
    }

    public static void showFragment(Fragment fragment, boolean allowStateLoss) {



            @SuppressLint("CommitTransaction")
            FragmentTransaction ft = fm.beginTransaction();
            ft.setCustomAnimations(R.anim.fadin, R.anim.fadeout);

            ft.replace(R.id.exlusivemainfragment, fragment);

            ft.commit();



        /*if (allowStateLoss || !BuildConfig.DEBUG) {
            ft.commitAllowingStateLoss();
        } else {
            ft.commit();
        }*/

//        fm.executePendingTransactions();
    }
}
