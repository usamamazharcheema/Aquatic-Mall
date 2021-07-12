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
        import com.example.androiddeveloper.aquatic_mall.fragments.LogIn_asGUest_Register;
        import com.example.androiddeveloper.aquatic_mall.fragments.LoginAsInvestor;
        import com.example.androiddeveloper.aquatic_mall.fragments.MianExclusive;
        import com.example.androiddeveloper.aquatic_mall.fragments.Slider1;

        import java.util.ArrayList;

/**
 * Created by ANDROID DEVELOPER on 08/02/2018.
 */

public class LogIn_Activity  extends Fragment {

    public static   FragmentManager fm;
   public static LinearLayout guestlayout,invetorlayout;
    TextView guesttext,investortest;
    public static LogIn_Activity  newInstance(String movieTitle) {
        LogIn_Activity  fragmentAction = new LogIn_Activity ();
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
        return inflater.inflate(R.layout.login_activity, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        LogIn_Activity.fm = getActivity().getSupportFragmentManager();
        guestlayout=view.findViewById(R.id.guestlayout);
        guesttext=view.findViewById(R.id.guesttext);
        invetorlayout=view.findViewById(R.id.invetorlayout);
        investortest=view.findViewById(R.id.investortext);
        guesttext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showFragment(LogIn_asGUest_Register.newInstance(""), true);
                guestlayout.setBackgroundResource(R.drawable.featureselectedbar);
                invetorlayout.setBackgroundResource(R.drawable.featurebar);
            }
        });
        guestlayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showFragment(LogIn_asGUest_Register.newInstance(""), true);
                guestlayout.setBackgroundResource(R.drawable.featureselectedbar);
                invetorlayout.setBackgroundResource(R.drawable.featurebar);
            }
        });
        investortest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showFragment(LoginAsInvestor.newInstance(""), true);
                guestlayout.setBackgroundResource(R.drawable.featurebar);
                invetorlayout.setBackgroundResource(R.drawable.featureselectedbar);
            }
        });
        investortest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showFragment(LoginAsInvestor.newInstance(""), true);
                guestlayout.setBackgroundResource(R.drawable.featurebar);
                invetorlayout.setBackgroundResource(R.drawable.featureselectedbar);
            }
        });
        showFragment(LoginAsInvestor.newInstance(""), true);
    }


    public static void showFragment(Fragment fragment, boolean allowStateLoss) {
        try
        {


            @SuppressLint("CommitTransaction")
            FragmentTransaction ft = LogIn_Activity.fm.beginTransaction();
            ft.setCustomAnimations(android.R.animator.fade_in, android.R.animator.fade_out);

            ft.replace(R.id.login_fragment, fragment);

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
   /* public void replacefragmentex()
    {
        guestlayout.setBackgroundDrawable(getResources().getDrawable(R.drawable.featureselectedbar));
        investorlayout.setBackgroundDrawable(getResources().getDrawable(R.drawable.featurebar));
        showFragment(MianExclusive.newInstance(""), true);
    }
    public void replacefragmentfe()
    {
        guestlayout.setBackgroundDrawable(getResources().getDrawable(R.drawable.featurebar));
        investorlayout.setBackgroundDrawable(getResources().getDrawable(R.drawable.featureselectedbar));
        showFragment(Features.newInstance(""), true);
    }*/
    /*public void drawer(View v)
    {
        Intent i=new Intent(AboutMallFeature.this, DrawerFragment.class);
        startActivity(i);
        overridePendingTransition( R.anim.layoutup, R.anim.backpressedlayout );
    }*/
}
