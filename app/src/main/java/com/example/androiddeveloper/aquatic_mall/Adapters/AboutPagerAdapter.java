package com.example.androiddeveloper.aquatic_mall.Adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.androiddeveloper.aquatic_mall.fragments.Albari_slide;
import com.example.androiddeveloper.aquatic_mall.fragments.Aquatic_slide;
import com.example.androiddeveloper.aquatic_mall.fragments.Bizventure_slide;
import com.example.androiddeveloper.aquatic_mall.fragments.GreenEarth_slide;

public class AboutPagerAdapter extends FragmentPagerAdapter {

    private int NUM_ITEMS = 4;

    public AboutPagerAdapter(FragmentManager fm) {
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
                return new Aquatic_slide();
            case 1:
                return new Bizventure_slide();
            case 2:
                return new GreenEarth_slide();
            case 3:
                return new Albari_slide();
            default:
                return null;
        }
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return "";
    }

}