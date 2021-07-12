package com.example.androiddeveloper.aquatic_mall.fragments;

import android.annotation.SuppressLint;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.example.androiddeveloper.aquatic_mall.Activities.About;
import com.example.androiddeveloper.aquatic_mall.Activities.AboutMall;
import com.example.androiddeveloper.aquatic_mall.Activities.AboutMallFeature;
import com.example.androiddeveloper.aquatic_mall.Activities.ContactUS;
import com.example.androiddeveloper.aquatic_mall.Activities.HomeActivity;
import com.example.androiddeveloper.aquatic_mall.Activities.LatestNews;
import com.example.androiddeveloper.aquatic_mall.Activities.LogIn_Activity;
import com.example.androiddeveloper.aquatic_mall.Activities.LookingToInvest;
import com.example.androiddeveloper.aquatic_mall.Activities.RateStructure;
import com.example.androiddeveloper.aquatic_mall.Activities.Trendiing;
import com.example.androiddeveloper.aquatic_mall.BuildConfig;
import com.example.androiddeveloper.aquatic_mall.MainActivity;
import com.example.androiddeveloper.aquatic_mall.R;


/**
 * @author msahakyan
 */

public class FragmentNavigationManager implements NavigationManager {

    private static FragmentNavigationManager sInstance;

    private FragmentManager mFragmentManager;
    private MainActivity mActivity;

    public static FragmentNavigationManager obtain(MainActivity activity) {
        if (sInstance == null) {
            sInstance = new FragmentNavigationManager();
        }
        sInstance.configure(activity);
        return sInstance;
    }

    private void configure(MainActivity activity) {
        mActivity = activity;
        mFragmentManager = mActivity.getSupportFragmentManager();
    }

    @Override
    public void showFragmentHome(String title) {
        MainActivity.currentfragment="Home";
        if(MainActivity.drawercheck.equals("open")) {


            showFragment1(HomeActivity.newInstance(title), false);
            MainActivity.swipe = "home";
            MainActivity.drawerpicclose();

        }
        else
        {
            showFragment3(HomeActivity.newInstance(title), false);
            MainActivity.swipe = "home";
        }
        MainActivity.lookingtoinvest="Home";
        MainActivity.drawerpicclose();
    }

    @Override
    public void showFragmentDrawer(String title) {

        showFragment(Drawer.newInstance(title), false);
        MainActivity.drawerpicopen();
    }

    @Override
    public void showFragmentLatesnews(String title) {
        if(MainActivity.drawercheck.equals("open")) {
            MainActivity.currentfragment = "Latesnews";
            showFragment1(LatestNews.newInstance(title), false);
            MainActivity.drawerpicclose();
        }
        else
        {
            MainActivity.currentfragment = "Latesnews";
            showFragment2(LatestNews.newInstance(title), false);

        }
        MainActivity.lookingtoinvest="Home";
        MainActivity.drawerpicclose();

    }

    @Override
    public void showFragmentAboutMall(String title) {
        if(MainActivity.drawercheck.equals("open")) {
            MainActivity.currentfragment = "AboutMall";
            showFragment1(AboutMall.newInstance(title), false);
            MainActivity.drawerpicclose();
        }
        else
        {
            MainActivity.currentfragment = "AboutMall";
            showFragment2(AboutMall.newInstance(title), false);

        }
        MainActivity.lookingtoinvest="Home";
        MainActivity.drawerpicclose();
    }

    @Override
    public void showFragmentAboutMallFeatures(String title) {
        if(MainActivity.drawercheck.equals("open")) {
            MainActivity.currentfragment = "AboutMallFeatures";
            showFragment1(AboutMallFeature.newInstance(title), false);
            MainActivity.drawerpicclose();
        }
        else
        {
            MainActivity.currentfragment = "AboutMallFeatures";
            showFragment2(AboutMallFeature.newInstance(title), false);

        }
        MainActivity.lookingtoinvest="Home";
        MainActivity.drawerpicclose();
    }

    @Override
    public void showFragmentAboutTrending(String title) {
        if(MainActivity.drawercheck.equals("open")) {
            MainActivity.currentfragment = "Trending";
            showFragment1(Trendiing.newInstance(title), false);
            MainActivity.drawerpicclose();
        }
        else
        {
            MainActivity.currentfragment = "Trending";
            showFragment2(Trendiing.newInstance(title), false);

        }
        MainActivity.drawerpicclose();
    }

    @Override
    public void showFragmentContactUS(String title) {
        if(MainActivity.drawercheck.equals("open")) {
            MainActivity.currentfragment = "ContactUs";
            showFragment1(ContactUS.newInstance(title), false);
            MainActivity.drawerpicclose();
        }
        else
        {
            MainActivity.currentfragment = "ContactUs";
            showFragment2(ContactUS.newInstance(title), false);

        }
        MainActivity.lookingtoinvest="Home";
        MainActivity.drawerpicclose();
    }

    @Override
    public void showFragmentRateStructure(String title) {
        if(MainActivity.drawercheck.equals("open")) {
            MainActivity.currentfragment = "RateStructure";
            showFragment1(RateStructure.newInstance(title), false);
            MainActivity.drawerpicclose();
        }
        else
        {
            MainActivity.currentfragment = "RateStructure";
            showFragment2(RateStructure.newInstance(title), false);

        }
        MainActivity.lookingtoinvest="Home";
        MainActivity.drawerpicclose();
    }

    @Override
    public void showFragmentLookingToInvesment(String title) {
        if(MainActivity.drawercheck.equals("open")) {
            MainActivity.currentfragment = "LookingToInvest";
            showFragment1(LookingToInvest.newInstance(title), false);
            MainActivity.drawerpicclose();
        }
        else
        {
            MainActivity.currentfragment = "LookingToInvest";
            showFragment2(LookingToInvest.newInstance(title), false);

        }
        MainActivity.drawerpicclose();
    }

    @Override
    public void showFragmentLogIn(String title) {
        if(MainActivity.drawercheck.equals("open")) {
            MainActivity.currentfragment = "Login";
            showFragment1(LogIn_Activity.newInstance(title), false);
            MainActivity.drawerpicclose();
        }
        else
        {
            MainActivity.currentfragment = "Login";
            showFragment2(LogIn_Activity.newInstance(title), false);

        }
        MainActivity.lookingtoinvest="Home";
        MainActivity.drawerpicclose();
    }

    @Override
    public void showFragmentLogInasinvesor(String title) {
        if(MainActivity.drawercheck.equals("open")) {
            MainActivity.currentfragment = "LoginInvesor";
            showFragment1(LoginAsInvestor.newInstance(title), false);
            MainActivity.drawerpicclose();
        }
        else
        {
            MainActivity.currentfragment = "LoginInvesor";
            showFragment2(LoginAsInvestor.newInstance(title), false);

        }
        MainActivity.lookingtoinvest="Home";
        MainActivity.drawerpicclose();
    }

    @Override
    public void showFragmentLogInasGustRegistor(String title) {
        if(MainActivity.drawercheck.equals("open")) {
            MainActivity.currentfragment = "LoginGuest";
            showFragment1(LogIn_asGUest_Register.newInstance(title), false);
            MainActivity.drawerpicclose();
        }
        else
        {
            MainActivity.currentfragment = "LoginGuest";
            showFragment2(LogIn_asGUest_Register.newInstance(title), false);

        }
        MainActivity.lookingtoinvest="Home";
        MainActivity.drawerpicclose();
    }

    @Override
    public void showFragmentAboutHome(String title) {
        if(MainActivity.drawercheck.equals("open")) {
            MainActivity.currentfragment = "About";
            showFragment1(About.newInstance(title), false);
            MainActivity.drawerpicclose();
        }
        else
        {
            MainActivity.currentfragment = "About";
            showFragment2(About.newInstance(title), false);

        }
        MainActivity.lookingtoinvest="Home";
        MainActivity.drawerpicclose();
    }

    @Override
    public void showFragmentExclusive(String title) {
        if(MainActivity.drawercheck.equals("open")) {
            MainActivity.currentfragment = "Exclusive";
            showFragment1(Exclusive.newInstance(title), false);
            MainActivity.drawerpicclose();
        }
        else
        {
            MainActivity.currentfragment = "Exclusive";
            showFragment2(Exclusive.newInstance(title), false);

        }
        MainActivity.drawerpicclose();
    }

    @Override
    public void showFragmentFeatures(String title) {
        if(MainActivity.drawercheck.equals("open")) {
            MainActivity.currentfragment = "Features";
            showFragment1(Features.newInstance(title), false);
            MainActivity.drawerpicclose();
        }
        else
        {
            MainActivity.currentfragment = "Features";
            showFragment2(Features.newInstance(title), false);

        }
        MainActivity.lookingtoinvest="Home";
        MainActivity.drawerpicclose();
    }

    @Override
    public void showFragmentExclusiveDetail(String title) {
        if(MainActivity.drawercheck.equals("open")) {
            MainActivity.currentfragment = "ExclusiveDetail";
            showFragment1(ExclusiveDetail.newInstance(title), false);
            MainActivity.drawerpicclose();
        }
        else
        {
            MainActivity.currentfragment = "ExclusiveDetail";
            showFragment2(ExclusiveDetail.newInstance(title), false);

        }
        MainActivity.lookingtoinvest="Home";
        MainActivity.drawerpicclose();
    }

    @Override
    public void showFragmentFloorPlans(String title) {
        if(MainActivity.drawercheck.equals("open")) {
            MainActivity.currentfragment = "FloorPLans";
            showFragment1(FloorPlans.newInstance(title), false);
            MainActivity.drawerpicclose();
        }
        else
        {
            MainActivity.currentfragment = "FloorPLans";
            showFragment2(FloorPlans.newInstance(title), false);

        }
        MainActivity.lookingtoinvest="Home";
        MainActivity.drawerpicclose();
    }

    @Override
    public void showFragmentInvestornoficationfirst(String title) {
        if(MainActivity.drawercheck.equals("open")) {
            MainActivity.currentfragment = "InvestorNotificationFirst";
            showFragment1(InvestorNotificationFirst.newInstance(title), false);
            MainActivity.drawerpicclose();
        }
        else
        {
            MainActivity.currentfragment = "InvestorNotificationFirst";
            showFragment2(InvestorNotificationFirst.newInstance(title), false);

        }
        MainActivity.lookingtoinvest="Home";
        MainActivity.drawerpicclose();
    }

    @Override
    public void showFragmentBookingRequestDetail(String title) {
        if(MainActivity.drawercheck.equals("open")) {
            MainActivity.currentfragment = "BookingRequestDetail";
            showFragment1(BookingRequestDetail.newInstance(title), false);
            MainActivity.drawerpicclose();
        }
        else
        {
            MainActivity.currentfragment = "BookingRequestDetail";
            showFragment2(BookingRequestDetail.newInstance(title), false);

        }
        MainActivity.lookingtoinvest="Home";
        MainActivity.drawerpicclose();
    }

    @Override
    public void showFragmentChatFragment(String title) {
        if(MainActivity.drawercheck.equals("open")) {
            MainActivity.currentfragment = "ChatFragment";
            showFragment1(ChatFragment.newInstance(title), false);
            MainActivity.drawerpicclose();
        }
        else
        {
            MainActivity.currentfragment = "ChatFragment";
            showFragment2(ChatFragment.newInstance(title), false);

        }
        MainActivity.lookingtoinvest="Home";
        MainActivity.drawerpicclose();
    }

    @Override
    public void showFragmentAdmin_Guest_List(String title) {
        if(MainActivity.drawercheck.equals("open")) {
            MainActivity.currentfragment = "Admin_Guest_List";
            showFragment1(Admin_Guest_List.newInstance(title), false);
            MainActivity.drawerpicclose();
        }
        else
        {
            MainActivity.currentfragment = "Admin_Guest_List";
            showFragment2(Admin_Guest_List.newInstance(title), false);

        }
        MainActivity.lookingtoinvest="Home";
        MainActivity.drawerpicclose();
    }

    @Override
    public void showFragmentAdminChatFragment(String title) {
        if(MainActivity.drawercheck.equals("open")) {
            MainActivity.currentfragment = "AdminChatFragment";
            showFragment1(AdminChatFragment.newInstance(title), false);
            MainActivity.drawerpicclose();
        }
        else
        {
            MainActivity.currentfragment = "AdminChatFragment";
            showFragment2(AdminChatFragment.newInstance(title), false);

        }
        MainActivity.lookingtoinvest="Home";
        MainActivity.drawerpicclose();
    }


    private void showFragment(Fragment fragment, boolean allowStateLoss) {




            try {
                FragmentManager fm = mFragmentManager;

                @SuppressLint("CommitTransaction")

                FragmentTransaction ft = fm.beginTransaction();
                ft.setCustomAnimations(R.anim.enterfromdowntoup, R.anim.from_downto_up);

                ft.replace(R.id.mainactivitydrawer, fragment);

                ft.commit();
                fm.executePendingTransactions();

            } catch (Exception e) {

            }


    }
    private void showFragment1(Fragment fragment, boolean allowStateLoss) {




        try {
            FragmentManager fm = mFragmentManager;

            @SuppressLint("CommitTransaction")

            FragmentTransaction ft = fm.beginTransaction();
            ft.setCustomAnimations(R.anim.enteruptodown, R.anim.uptodown);

            ft.replace(R.id.mainactivitydrawer, fragment);
ft.addToBackStack(null);
            ft.commit();
            fm.executePendingTransactions();

        } catch (Exception e) {

        }


    }
    private void showFragment2(Fragment fragment, boolean allowStateLoss) {




        try {
            FragmentManager fm = mFragmentManager;

            @SuppressLint("CommitTransaction")

            FragmentTransaction ft = fm.beginTransaction();
            ft.setCustomAnimations( R.anim.righttoleft, R.anim.backpressedlayout );

            ft.replace(R.id.mainactivitydrawer, fragment);
            ft.addToBackStack(null);
            ft.commit();
            fm.executePendingTransactions();

        } catch (Exception e) {

        }


    }
    private void showFragment3(Fragment fragment, boolean allowStateLoss) {




        try {
            FragmentManager fm = mFragmentManager;

            @SuppressLint("CommitTransaction")

            FragmentTransaction ft = fm.beginTransaction();
            ft.setCustomAnimations( R.anim.homefirst, R.anim.homesecond );

            ft.replace(R.id.mainactivitydrawer, fragment);
            ft.addToBackStack(null);
            ft.commit();
            fm.executePendingTransactions();

        } catch (Exception e) {

        }


    }


}
