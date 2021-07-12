package com.example.androiddeveloper.aquatic_mall.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.androiddeveloper.aquatic_mall.Adapters.FloorPLansAdapter;
import com.example.androiddeveloper.aquatic_mall.MainActivity;
import com.example.androiddeveloper.aquatic_mall.R;

/**
 * Created by ANDROID DEVELOPER on 23/02/2018.
 */

public class FloorPlans extends Fragment {

    ViewPager graphpager;
    ImageView back,next;
    TextView itemstrending;
    LinearLayout backlayout;
    public static FloorPlans newInstance(String movieTitle) {
        FloorPlans fragmentAction = new FloorPlans();
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
        return inflater.inflate(R.layout.floorplan, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        backlayout=view.findViewById(R.id.trendingbacklayout);
graphpager=view.findViewById(R.id.floorplanpager);
itemstrending=view.findViewById(R.id.floortrending);
back=view.findViewById(R.id.backtrend);
        next=view.findViewById(R.id.nexttrend);
        graphpager.setOffscreenPageLimit(4);
        graphpager.setAdapter(new FloorPLansAdapter(getActivity()));
        graphpager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                if(position==0)
                {
                    itemstrending.setText("Ground Floor");
                }
                else if(position==1)
                {
                    itemstrending.setText("Lower Ground Floor");
                }
                else if(position==2)
                {
                    itemstrending.setText("Food Court");
                }
                else if(position==3)
                {
                    itemstrending.setText("Master Plan");
                }
            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
back.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
//        next.setVisibility(View.VISIBLE);
        if(graphpager.getCurrentItem()==0)
        {
//            back.setVisibility(View.GONE);
        }
        else
        {
            graphpager.setCurrentItem(graphpager.getCurrentItem()-1,true);
        }
    }
});
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                back.setVisibility(View.VISIBLE);
                if(graphpager.getCurrentItem()==3)
                {
//next.setVisibility(View.GONE);
                }
                else
                {
                    graphpager.setCurrentItem(graphpager.getCurrentItem()+1,true);
                }
            }
        });
        backlayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.backpressed();
            }
        });
    }
}
