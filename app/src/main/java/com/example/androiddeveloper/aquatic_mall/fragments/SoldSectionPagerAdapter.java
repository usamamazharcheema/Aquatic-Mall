package com.example.androiddeveloper.aquatic_mall.fragments;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

/**
 * Created by fm on 2/6/2018.
 */

public class SoldSectionPagerAdapter extends FragmentStatePagerAdapter {


    public SoldSectionPagerAdapter(FragmentManager fragmentManager) {
        super(fragmentManager);
    }



    @Override
    public Fragment getItem(int position) {
        // getItem is called to instantiate the fragment for the given page.
        // Return a PlaceholderFragment (defined as a static inner class below).
        String gound=null;
        String cat=null;
        if(position==1)
        {
            gound="lower ground";
            cat="shops";
        }
        else if (position==2)
        {
            gound="Ground floor";
            cat="shops";
        }
        else if (position==3)
        {
            gound="upper ground";
            cat="shops";
        }
        else if (position==4)
        {
            gound="first floor";
            cat="shops";
        }
        else if (position==5)
        {
            gound="2nd floor";
            cat="shops";
        }
        else if (position==6)
        {
            gound="3rd floor";
            cat="foodcourt";
        }
        else if (position==7)
        {
            gound="5th floor";
            cat="offices";
        }
        else if (position==8)
        {
            gound="6th floor";
            cat="offices";
        }
        else if (position==9)
        {
            gound="7th floor";
            cat="apartments";
        }
        else if (position==10)
        {
            gound="8th floor";
            cat="apartments";
        }
        else if (position==11)
        {
            gound="9th floor";
            cat="sweets";
        }

        return SoldPlaceholderFragment.newInstance(position+1 ,gound, cat);
    }

    @Override
    public int getCount() {
        // Show 3 total pages.
        return 12;

    }

//        @Override
//        public CharSequence getPageTitle(int position) {
//            switch (position) {
//                case 0:
//                    return "SECTION 1";
//                case 1:
//                    return "SECTION 2";
//                case 2:
//                    return "SECTION 3";
//            }
//            return null;
//        }
}
