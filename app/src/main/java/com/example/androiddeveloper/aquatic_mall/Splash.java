package com.example.androiddeveloper.aquatic_mall;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Environment;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import com.cunoraz.gifview.library.GifView;
import com.example.androiddeveloper.aquatic_mall.database.DtabaeHelper;
import com.example.androiddeveloper.aquatic_mall.fragments.Investoryes;
import com.example.androiddeveloper.aquatic_mall.fragments.Slider1;
import com.example.androiddeveloper.aquatic_mall.fragments.Slider2;
import com.example.androiddeveloper.aquatic_mall.fragments.Slider3;
import com.example.androiddeveloper.aquatic_mall.fragments.Slider4;
import com.example.androiddeveloper.aquatic_mall.fragments.Slider5;
import com.example.androiddeveloper.aquatic_mall.fragments.Slider6;
import com.example.androiddeveloper.aquatic_mall.splash.AlbariFragment;
import com.example.androiddeveloper.aquatic_mall.splash.Aquaticfragment;
import com.example.androiddeveloper.aquatic_mall.splash.BizventureFRagment;
import com.example.androiddeveloper.aquatic_mall.splash.GreenEarthFRagment;

import java.io.File;

/**
 * Created by ANDROID DEVELOPER on 13/02/2018.
 */

public class Splash extends FragmentActivity {
        int countermain=2;
    SharedPreferences sharedpreferences,adminsharepreference,chekfragment,checupdate ;
    public static final String mypreference = "guestmessagepreference";
    public static final String checkupdatepreference = "checkupdatepreference";
    public static final String updatecheck="updatecheck";
    public static final String mypreferenceadmin = "adminmessagepreference";
    public static final String mypreferencecheckuserfragmrnt = "checkuserrfragment";
    public static final String usercondition= "usercondition";
    public static final String adminmessage = "adminmessage";
    public static final String adminemail = "adminemail";
    public static final String message = "guestmessage";

CountDownTimer t;
@Override
protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splashmain);

        showFragment(Aquaticfragment.newInstance(""), true);

    sharedpreferences = getSharedPreferences(mypreference,
            Context.MODE_PRIVATE);
    adminsharepreference = getSharedPreferences(mypreferenceadmin,
            Context.MODE_PRIVATE);
    chekfragment = getSharedPreferences(mypreferencecheckuserfragmrnt,
            Context.MODE_PRIVATE);
    checupdate = getSharedPreferences(checkupdatepreference,
            Context.MODE_PRIVATE);

    SharedPreferences.Editor editorupdate = checupdate.edit();

    if (checupdate.contains(updatecheck)) {


    }
    else
    {
        editorupdate.putString(updatecheck, "");

        editorupdate.commit();
    }

//    editorupdate.putString(updatecheck, "");
//
//    editorupdate.commit();
    /*if (sharedpreferences.contains(Name)) {
        name.setText(sharedpreferences.getString(Name, ""));
    }
    if (sharedpreferences.contains(Email)) {
        email.setText(sharedpreferences.getString(Email, ""));

    }*/

    String n = "";
    String e = "";
    SharedPreferences.Editor editor = sharedpreferences.edit();
    editor.putString(message, n);

    editor.commit();
    SharedPreferences.Editor editor2 = chekfragment.edit();
    editor2.putString(usercondition, "no");

    editor2.commit();
    SharedPreferences.Editor editor1 = adminsharepreference.edit();
    editor1.putString(adminmessage, "");
    editor1.putString(adminemail, "");
    editor1.commit();



        mainslidermethod();
        }


        public void mainslidermethod()
        {

                try {
                        t=  new CountDownTimer(6000, 10000) {
                                @Override
                                public void onTick(long l) {

                                }

                                @Override
                                public void onFinish() {
                                        if(countermain==2) {
                                                DtabaeHelper mydb=new DtabaeHelper(Splash.this);
                                                Cursor c=mydb.checkaquatic();
                                                if(c.getCount()==0)
                                                {
mydb.insertauatic("","","","","","","","","","","","");
                                                        try {
                                                                showFragment(AlbariFragment.newInstance(""), true);
                                                                countermain = countermain + 1;
                                                        }
                                                        catch (Exception e)
                                                        {

                                                        }
                                                    SharedPreferences.Editor editorupdate = checupdate.edit();
                                                    editorupdate.putString(updatecheck, "ok");

                                                    editorupdate.commit();
                                                        mainslidermethod();
                                                }
                                                else {
                                                    SharedPreferences.Editor editorupdate = checupdate.edit();

                                                    if (checupdate.contains(updatecheck)) {

if(checupdate.getString(updatecheck, "").equals(""))
{

    mydb.updateaquatic("1","","","","","","","","","","","","");
    editorupdate.putString(updatecheck, "ok");

    editorupdate.commit();
}
else
{

}

                                                    }




                                                    t.cancel();
                                                        Intent i=new Intent(Splash.this,MainActivity.class);
                                                        startActivity(i);
                                                        finish();
                                                }



                                        }
                                        else if(countermain==3) {
                                                try {
                                                        showFragment(BizventureFRagment.newInstance(""), true);
                                                        countermain = countermain + 1;
                                                }
                                                catch (Exception e)
                                                {

                                                }
                                                mainslidermethod();
                                        }
                                        else if(countermain==4) {
                                                try {
                                                        showFragment(GreenEarthFRagment.newInstance(""), true);
                                                        countermain = countermain + 1;
                                                }
                                                catch (Exception e)
                                                {

                                                }
                                                mainslidermethod();
                                        }
                                        else if(countermain==5) {
                                                try {

                                                    Intent i=new Intent(Splash.this, com.example.androiddeveloper.aquatic_mall.investorfirst.Investoryes.class);
                                                    startActivity(i);
                                                    finish();
                                                }
                                                catch (Exception e)
                                                {

                                                }

                                        }


                                }
                        }.start();
                }
                catch (Exception e)
                {

                }
        }










        private void showFragment(Fragment fragment, boolean allowStateLoss) {
                try
                {
                        FragmentManager fm = Splash.this.getSupportFragmentManager();

                        @SuppressLint("CommitTransaction")
                        FragmentTransaction ft = fm.beginTransaction();
                        ft.setCustomAnimations(android.R.animator.fade_in, android.R.animator.fade_out);

                        ft.replace(R.id.splashlayout, fragment);

                        ft.commit();

                }
                catch (Exception e)
                {

                }

        /*if (allowStateLoss || !BuildConfig.DEBUG) {
            ft.commitAllowingStateLoss();
        } else {
            ft.commit();
        }*/

//        fm.executePendingTransactions();
        }
        }

/*{

    private static int SPLASH_TIME_OUT = 3000;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);

    new Handler().postDelayed(new Runnable() {

            *//*
             * Showing splash screen with a timer. This will be useful when you
             * want to show case your app logo / company
             *//*

        @Override
        public void run() {
            Intent intent = new Intent(Splash.this, MainActivity.class);
            startActivity(intent);
            finish();
        }
    }, SPLASH_TIME_OUT);


    }
}

*/