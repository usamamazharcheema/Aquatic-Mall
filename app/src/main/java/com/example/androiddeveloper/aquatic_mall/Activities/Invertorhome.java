package com.example.androiddeveloper.aquatic_mall.Activities;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.androiddeveloper.aquatic_mall.Adapters.NewsAdapter;
import com.example.androiddeveloper.aquatic_mall.BuildConfig;
import com.example.androiddeveloper.aquatic_mall.Interfaces.ApiClient;
import com.example.androiddeveloper.aquatic_mall.Interfaces.ManageResponse;
import com.example.androiddeveloper.aquatic_mall.MainActivity;
import com.example.androiddeveloper.aquatic_mall.R;
import com.example.androiddeveloper.aquatic_mall.ResponseLists.Likeslist;
import com.example.androiddeveloper.aquatic_mall.ResponseLists.NewsResponseLists;
import com.example.androiddeveloper.aquatic_mall.ResponseLists.Newsresponse;
import com.example.androiddeveloper.aquatic_mall.fragments.Slider1;

import java.util.ArrayList;
import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by ANDROID DEVELOPER on 13/02/2018.
 */

public class Invertorhome extends Fragment {


    public static Invertorhome newInstance(String movieTitle) {
        Invertorhome fragmentAction = new Invertorhome();
        Bundle args = new Bundle();
        args.putString(" ", movieTitle);
        fragmentAction.setArguments(args);

        return fragmentAction;
    }
    LinearLayout latestneasclick,aboutmall,lookingtoinvestlayout;
    TextView lookingtoinvesttext;
    int countermain=2;
    ArrayList<Newsresponse> newsresponseArrayList=new ArrayList<>();
    RecyclerView newrecycler;
    NewsAdapter newsAdapter;
    ArrayList<String>  ids;
    HashMap<String, Likeslist> likemap = new HashMap<String, Likeslist>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.investorhome, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        showFragment1(Slider1.newInstance(""), true);
//        mainslidermethod();
        latestneasclick=view.findViewById(R.id.latestnewsclick);
        aboutmall=view.findViewById(R.id.aboutmall);
        newrecycler = (RecyclerView) view.findViewById(R.id.newsrycyler);
        newsAdapter=new NewsAdapter(ids,getActivity(),newsresponseArrayList,likemap);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        newrecycler.setLayoutManager(mLayoutManager);
        newrecycler.setItemAnimator(new DefaultItemAnimator());
        newrecycler.setAdapter(newsAdapter);
        newsAdapter.notifyDataSetChanged();
        latestneasclick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainActivity.backfragment=MainActivity.currentfragment;
                MainActivity.mNavigationManager.showFragmentLatesnews("");

            }
        });
        aboutmall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainActivity.backfragment=MainActivity.currentfragment;
                MainActivity.mNavigationManager.showFragmentAboutMall("");

            }
        });
        lookingtoinvestlayout=view.findViewById(R.id.lookingtoinvestlayout);
        lookingtoinvesttext=view.findViewById(R.id.lookingtoinvesttext);
        lookingtoinvestlayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainActivity.backfragment=MainActivity.currentfragment;
                MainActivity.mNavigationManager.showFragmentLookingToInvesment("");
            }
        });
        lookingtoinvesttext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainActivity.backfragment=MainActivity.currentfragment;
                MainActivity.mNavigationManager.showFragmentLookingToInvesment("");
            }
        });
        getnews(" ");
    }







    /*
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        showFragment(Slider1.newInstance(""), true);
        mainslidermethod();
        newrecycler = (RecyclerView) findViewById(R.id.newsrycyler);
        newsAdapter=new NewsAdapter(this,newsresponseArrayList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
        newrecycler.setLayoutManager(mLayoutManager);
        newrecycler.setItemAnimator(new DefaultItemAnimator());
        newrecycler.setAdapter(newsAdapter);
        newsAdapter.notifyDataSetChanged();
        getnews(" ");
    }*/


    public void getnews(final String mystring)
    {

        final ManageResponse service = ApiClient.getClient().create( ManageResponse.class);

        Call<NewsResponseLists> userCall =service.GetNews(1,1);

        userCall.enqueue(new Callback<NewsResponseLists>() {
            @Override
            public void onResponse(Call<NewsResponseLists> call, Response<NewsResponseLists> response) {

                ArrayList<Newsresponse> newsresponselist=(ArrayList<Newsresponse>) response.body().getNewsresponse();
                if(newsresponselist!=null)
                {
                    for(int c=0;c<newsresponselist.size();c++)
                    {
                        newsresponseArrayList.add(newsresponselist.get(c));
                        newsAdapter.notifyDataSetChanged();
                    }
                }
            }

            @Override
            public void onFailure(Call<NewsResponseLists> call, Throwable t) {
                try {
                    Toast.makeText(getActivity(), "Please Check Your Internet Connection", Toast.LENGTH_SHORT).show();
                    Log.d("onFailure", t.toString());
                }
                catch (Exception e)
                {

                }
            }
        });


    }





    /*public void mainslidermethod()
    {

        try {
            new CountDownTimer(3000, 2000) {
                @Override
                public void onTick(long l) {

                }

                @Override
                public void onFinish() {
                    if(countermain==1) {
                        try {
                            showFragment(Slider1.newInstance(""), true);
                            countermain = countermain + 1;
                        }
                        catch (Exception e)
                        {

                        }
                    }
                    else if(countermain==2) {
                        try {
                            showFragment(Slider2.newInstance(""), true);
                            countermain = countermain + 1;
                        }
                        catch (Exception e)
                        {

                        }
                    }
                    else if(countermain==3) {
                        try {
                            showFragment(Slider3.newInstance(""), true);
                            countermain = countermain + 1;
                        }
                        catch (Exception e)
                        {

                        }
                    }
                    else if(countermain==4) {
                        try {
                            showFragment(Slider4.newInstance(""), true);
                            countermain = countermain + 1;
                        }
                        catch (Exception e)
                        {

                        }
                    }
                    else if(countermain==5) {
                        try {
                            showFragment(Slider5.newInstance(""), true);
                            countermain = countermain + 1;
                        }
                        catch (Exception e)
                        {

                        }
                    }
                    else if(countermain==6) {
                        try
                        {
                            showFragment(Slider6.newInstance(""), true);
                            countermain = 1;
                        }
                        catch (Exception e)
                        {

                        }

                    }
                    mainslidermethod();
                }
            }.start();
        }
        catch (Exception e)
        {

        }
    }*/
    private void showFragment(Fragment fragment, boolean allowStateLoss) {
        try
        {
            FragmentManager fm = getActivity().getSupportFragmentManager();

            @SuppressLint("CommitTransaction")
            FragmentTransaction ft = fm.beginTransaction();
            ft.setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_left);

            ft.replace(R.id.mainpager, fragment);

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

    private void showFragment1(Fragment fragment, boolean allowStateLoss) {
        FragmentManager fm =getActivity().getSupportFragmentManager();

        @SuppressLint("CommitTransaction")
        FragmentTransaction ft = fm.beginTransaction()
                .replace(R.id.mainpager, fragment);

        ft.addToBackStack(null);

        if (allowStateLoss || !BuildConfig.DEBUG) {
            ft.commitAllowingStateLoss();
        } else {
            ft.commit();
        }

//        fm.executePendingTransactions();
    }

   /* public void drawer(View v)
    {
        Intent i2 = new Intent(HomeActivity.this, Animationopen.class);
        startActivity(i2);
        overridePendingTransition( R.anim.layoutup, R.anim.backpressedlayout );
    }*/

}
