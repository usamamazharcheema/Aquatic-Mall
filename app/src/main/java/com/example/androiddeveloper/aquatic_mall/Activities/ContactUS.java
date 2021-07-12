package com.example.androiddeveloper.aquatic_mall.Activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;


import com.dev.sacot41.scviewpager.DotsView;
import com.example.androiddeveloper.aquatic_mall.Adapters.ContactUsAdapter;
import com.example.androiddeveloper.aquatic_mall.Adapters.TabsPagerAdapter;
import com.example.androiddeveloper.aquatic_mall.MainActivity;
import com.example.androiddeveloper.aquatic_mall.R;
import com.example.androiddeveloper.aquatic_mall.fragments.Slider1;

public class ContactUS extends Fragment {


    public static ContactUS newInstance(String movieTitle) {
        ContactUS fragmentAction = new ContactUS();
        Bundle args = new Bundle();
        args.putString(" ", movieTitle);
        fragmentAction.setArguments(args);

        return fragmentAction;
    }
    private static final int NUM_PAGES = 3;

    private int status;
    private ViewPager viewPager;
   ContactUsAdapter myAdapter;
    private DotsView mDotsView;
    private ImageView farward,back;
    LinearLayout layback,laynext,backlayout;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.contactus_main, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewPager = (ViewPager) view.findViewById(R.id.viewpager);
        farward = (ImageView) view.findViewById(R.id.farward);
        backlayout = (LinearLayout) view.findViewById(R.id.backlayout);
        back = (ImageView) view.findViewById(R.id.back);
        layback = view.findViewById(R.id.layoutback);
        laynext =  view.findViewById(R.id.layoutnext);
        myAdapter = new ContactUsAdapter(getActivity());
        viewPager.setAdapter(myAdapter);
        mDotsView = (DotsView) view.findViewById(R.id.dotsview_main);
        mDotsView.setDotRessource(R.drawable.dot_selected, R.drawable.dot_unselected);
        mDotsView.setNumberOfPage(NUM_PAGES);

        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                if(position==0)
                {
                    MainActivity.homeiconset(R.drawable.newicon);
                }
                else if(position==1)
                {
                    MainActivity.homeiconset(R.drawable.bizventure_icon);
                }
                else if(position==2)
                {
                    MainActivity.homeiconset(R.drawable.greenearth_icon);
                }
            }

            @Override
            public void onPageSelected(int position) {
                mDotsView.selectDot(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
backlayout.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        MainActivity.backpressed();
    }
});
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                status=viewPager.getCurrentItem();
                status = status-1;
                if(status==-1){
                    status=2;
                    viewPager.setCurrentItem(status);

                }
                else {
                    viewPager.setCurrentItem(status);
                }
            }
        });

        farward.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                status=viewPager.getCurrentItem();
                status = status+1;
                if(status==3){
                    status=0;
                    viewPager.setCurrentItem(status);

                }
                else {
                    viewPager.setCurrentItem(status);
                }
            }
        });
        layback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                status=viewPager.getCurrentItem();
                status = status-1;
                if(status==-1){
                    status=2;
                    viewPager.setCurrentItem(status);

                }
                else {
                    viewPager.setCurrentItem(status);
                }
            }
        });

        laynext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                status=viewPager.getCurrentItem();
                status = status+1;
                if(status==3){
                    status=0;
                    viewPager.setCurrentItem(status);

                }
                else {
                    viewPager.setCurrentItem(status);
                }
            }
        });
    }




}
