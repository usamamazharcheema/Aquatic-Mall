package com.example.androiddeveloper.aquatic_mall.Activities;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.androiddeveloper.aquatic_mall.MainActivity;
import com.example.androiddeveloper.aquatic_mall.R;
import com.example.androiddeveloper.aquatic_mall.fragments.DrawerFragment;
import com.example.androiddeveloper.aquatic_mall.fragments.Exclusive;
import com.example.androiddeveloper.aquatic_mall.fragments.FeatureRcyclerlayout;
import com.example.androiddeveloper.aquatic_mall.fragments.Features;
import com.example.androiddeveloper.aquatic_mall.fragments.MianExclusive;
import com.example.androiddeveloper.aquatic_mall.fragments.Slider1;

import java.util.ArrayList;

/**
 * Created by ANDROID DEVELOPER on 08/02/2018.
 */

public class AboutMallFeature extends Fragment {


    public static AboutMallFeature newInstance(String movieTitle) {
        AboutMallFeature fragmentAction = new AboutMallFeature();
        Bundle args = new Bundle();
        args.putString(" ", movieTitle);
        fragmentAction.setArguments(args);

        return fragmentAction;
    }
    LinearLayout featureslayout,exclusivelayout;
    TextView featuretext,exclusivetext;
    LinearLayout layoutback;
    ImageView imgback;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.aboutmall_features, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        imgback=view.findViewById(R.id.aboutfeaturebackimg);
        layoutback=view.findViewById(R.id.aboutfeatureback);
        featuretext=view.findViewById(R.id.featuretext);
        exclusivetext=view.findViewById(R.id.exclusivetext);
        featureslayout=view.findViewById(R.id.featurelayout);
        exclusivelayout=view.findViewById(R.id.exclusivelayout);
        featureslayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                replacefragmentfe();
            }
        });
        exclusivelayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
replacefragmentex();
            }
        });
        featuretext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                replacefragmentfe();
            }
        });
        exclusivetext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                replacefragmentex();
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
        showFragment(Features.newInstance(""), true);
    }


    private void showFragment(Fragment fragment, boolean allowStateLoss) {

            FragmentManager fm = getActivity().getSupportFragmentManager();

            @SuppressLint("CommitTransaction")
            FragmentTransaction ft = fm.beginTransaction();
            ft.setCustomAnimations(R.anim.fadin, R.anim.fadeout);

            ft.replace(R.id.aboutfeaturefragment1, fragment);

            ft.commit();




        /*if (allowStateLoss || !BuildConfig.DEBUG) {
            ft.commitAllowingStateLoss();
        } else {
            ft.commit();
        }*/

//        fm.executePendingTransactions();
    }
  /*  public void back (View v)
    {
        super.onBackPressed();
        overridePendingTransition( R.anim.backpressedlayout, R.anim.lefttoright );
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition( R.anim.backpressedlayout, R.anim.lefttoright );
    }*/
    public void replacefragmentex()
    {
        exclusivelayout.setBackgroundDrawable(getResources().getDrawable(R.drawable.featureselectedbar));
        featureslayout.setBackgroundDrawable(getResources().getDrawable(R.drawable.featurebar));
        showFragment(MianExclusive.newInstance(""), true);
    }
    public void replacefragmentfe()
    {
        exclusivelayout.setBackgroundDrawable(getResources().getDrawable(R.drawable.featurebar));
        featureslayout.setBackgroundDrawable(getResources().getDrawable(R.drawable.featureselectedbar));
        showFragment(Features.newInstance(""), true);
    }
    /*public void drawer(View v)
    {
        Intent i=new Intent(AboutMallFeature.this, DrawerFragment.class);
        startActivity(i);
        overridePendingTransition( R.anim.layoutup, R.anim.backpressedlayout );
    }*/
}
