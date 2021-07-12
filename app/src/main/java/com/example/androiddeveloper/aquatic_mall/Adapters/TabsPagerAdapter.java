package com.example.androiddeveloper.aquatic_mall.Adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.androiddeveloper.aquatic_mall.MainActivity;
import com.example.androiddeveloper.aquatic_mall.R;
import com.example.androiddeveloper.aquatic_mall.fragments.Aquatic_Mall;
import com.example.androiddeveloper.aquatic_mall.fragments.Bizventure;
import com.example.androiddeveloper.aquatic_mall.fragments.Green_Earth;

public class TabsPagerAdapter extends FragmentPagerAdapter {

    private int NUM_ITEMS = 3;

    public TabsPagerAdapter(FragmentManager fm) {
        super(fm);
    }


    @Override
    public int getCount() {
        return  NUM_ITEMS ;
    }


    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
//                MainActivity.homeiconset(R.drawable.homeicon);
                return new Aquatic_Mall();
            case 1:
//                MainActivity.homeiconset(R.drawable.bizventure_icon);
                return new Bizventure();
            case 2:
//                MainActivity.homeiconset(R.drawable.greenearth_icon);
                return new Green_Earth();
            default:
                return null;
        }
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return "";
    }

}