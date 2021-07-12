package com.example.androiddeveloper.aquatic_mall.Activities;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.support.v4.app.Fragment;

import com.dev.sacot41.scviewpager.DotsView;
import com.example.androiddeveloper.aquatic_mall.Adapters.AboutAdapter;
import com.example.androiddeveloper.aquatic_mall.Adapters.AboutPagerAdapter;
import com.example.androiddeveloper.aquatic_mall.R;
import com.example.androiddeveloper.aquatic_mall.fragments.Slider1;

public class About extends Fragment {

    private ViewPager viewPager3;
    AboutAdapter myAdapter3;
    private DotsView mDotsView3;
    private static final int NUM_PAGES = 4;
    public static About newInstance(String movieTitle) {
        About fragmentAction = new About();
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
        return inflater.inflate(R.layout.activity_about, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewPager3 = (ViewPager) view.findViewById(R.id.viewpager3);
        myAdapter3 = new AboutAdapter(getActivity());
        viewPager3.setAdapter(myAdapter3);
        mDotsView3 = (DotsView) view.findViewById(R.id.dotsview_main3);
        mDotsView3.setDotRessource(R.drawable.dotaboutselected, R.drawable.dotaboutunselected);
        mDotsView3.setNumberOfPage(NUM_PAGES);
viewPager3.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        mDotsView3.selectDot(position);
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
});
       /* viewPager3.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                mDotsView3.selectDot(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });*/
    }
}




