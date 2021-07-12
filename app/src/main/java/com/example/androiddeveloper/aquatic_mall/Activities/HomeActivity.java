package com.example.androiddeveloper.aquatic_mall.Activities;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.database.Cursor;
import android.os.CountDownTimer;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bizventure.androiddeveloper.aquatic_mall.Animationopen;
import com.example.androiddeveloper.aquatic_mall.Adapters.ExpandableListAdapter;
import com.example.androiddeveloper.aquatic_mall.Adapters.NewsAdapter;
import com.example.androiddeveloper.aquatic_mall.BuildConfig;
import com.example.androiddeveloper.aquatic_mall.Interfaces.ApiClient;
import com.example.androiddeveloper.aquatic_mall.Interfaces.ManageResponse;
import com.example.androiddeveloper.aquatic_mall.MainActivity;
import com.example.androiddeveloper.aquatic_mall.R;
import com.example.androiddeveloper.aquatic_mall.ResponseLists.Likeslist;
import com.example.androiddeveloper.aquatic_mall.ResponseLists.NewsResponseLists;
import com.example.androiddeveloper.aquatic_mall.ResponseLists.Newsresponse;
import com.example.androiddeveloper.aquatic_mall.database.DtabaeHelper;
import com.example.androiddeveloper.aquatic_mall.fragments.DrawerFragment;
import com.example.androiddeveloper.aquatic_mall.fragments.InvestorProfile;
import com.example.androiddeveloper.aquatic_mall.fragments.InvestorPropertyNew;
import com.example.androiddeveloper.aquatic_mall.fragments.Slider1;
import com.example.androiddeveloper.aquatic_mall.fragments.Slider2;
import com.example.androiddeveloper.aquatic_mall.fragments.Slider3;
import com.example.androiddeveloper.aquatic_mall.fragments.Slider4;
import com.example.androiddeveloper.aquatic_mall.fragments.Slider5;
import com.example.androiddeveloper.aquatic_mall.fragments.Slider6;
import com.example.androiddeveloper.aquatic_mall.fragments.Slider7;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeActivity extends Fragment {


    public static HomeActivity newInstance(String movieTitle) {
        HomeActivity fragmentAction = new HomeActivity();
        Bundle args = new Bundle();
        args.putString(" ", movieTitle);
        fragmentAction.setArguments(args);

        return fragmentAction;
    }
    ArrayList<Likeslist> likeslists=new ArrayList<>();
    LinearLayout faq;
    LinearLayout latestneasclick,aboutmall,lookingtoinvestlayout,profile,trendiing;
    TextView lookingtoinvesttext;
    int countermain=2;
    ArrayList<Newsresponse> newsresponseArrayList=new ArrayList<>();
    RecyclerView newrecycler;
    NewsAdapter newsAdapter;
    ArrayList<String>  ids;
    HashMap<String, Likeslist> likemap = new HashMap<String, Likeslist>();
    ExpandableListAdapter listAdapter;
    ExpandableListView expListView;
    List<String> listDataHeader;
    HashMap<String, List<String>> listDataChild;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.newhomelatest, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        showFragment1(Slider1.newInstance(""), true);
        mainslidermethod();
        MainActivity.backfragment = "Back";
        profile=view.findViewById(R.id.profile);
        trendiing=view.findViewById(R.id.trendinglayout);

        latestneasclick=view.findViewById(R.id.latestnewsclick);
        faq=view.findViewById(R.id.faq);
        aboutmall=view.findViewById(R.id.aboutmall);
        newrecycler = (RecyclerView) view.findViewById(R.id.newsrycyler);

        newsAdapter=new NewsAdapter(ids,getActivity(),newsresponseArrayList,likemap);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        newrecycler.setLayoutManager(mLayoutManager);
        newrecycler.setItemAnimator(new DefaultItemAnimator());

        newrecycler.setAdapter(newsAdapter);
        newsAdapter.notifyDataSetChanged();
        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(MainActivity.Logindtect.equals("Investor"))
                {
                    DtabaeHelper mydb=new DtabaeHelper(getActivity());
                    Cursor c=mydb.checkaquatic();
                    while (c.moveToNext())
                    {
                        Intent i=new Intent(getActivity(), InvestorPropertyNew.class);

                        startActivity(i);
                        getActivity().overridePendingTransition(R.anim.newlefttoright, R.anim.newrighttoleft);
                    }

                }
                else
                {
                    Toast.makeText(getActivity(), "Please login as Investor First", Toast.LENGTH_SHORT).show();
                }




            }
        });
        latestneasclick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

               MainActivity.t.cancel();
                MainActivity.backfragment=MainActivity.currentfragment;
                MainActivity.mNavigationManager.showFragmentLatesnews("");

            }
        });
        trendiing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainActivity.t.cancel();
                MainActivity.backfragment=MainActivity.currentfragment;
                MainActivity.mNavigationManager.showFragmentAboutTrending("");

            }
        });
        aboutmall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               MainActivity.t.cancel();
                MainActivity.backfragment=MainActivity.currentfragment;
                MainActivity.mNavigationManager.showFragmentAboutMall("");

            }
        });
        lookingtoinvestlayout=view.findViewById(R.id.lookingtoinvestlayout);
        lookingtoinvesttext=view.findViewById(R.id.lookingtoinvesttext);
        lookingtoinvestlayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainActivity.t.cancel();
                MainActivity.backfragment=MainActivity.currentfragment;
                MainActivity.mNavigationManager.showFragmentLookingToInvesment("");
            }
        });
        lookingtoinvesttext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               MainActivity.t.cancel();
                MainActivity.backfragment=MainActivity.currentfragment;
                MainActivity.mNavigationManager.showFragmentLookingToInvesment("");
            }
        });
faq.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        faq();
    }
});
    }










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
                    Toast.makeText(getActivity(), "Failure", Toast.LENGTH_SHORT).show();
                    Log.d("onFailure", t.toString());
                }
                catch (Exception e)
                {

                }
            }
        });


    }




    public void mainslidermethod()
    {

        try {
         MainActivity.t=  new CountDownTimer(3000, 2000) {
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
                            countermain = countermain + 1;
                        }
                        catch (Exception e)
                        {

                        }

                    }
                    else if(countermain==7) {
                        try
                        {
                            showFragment(Slider7.newInstance(""), true);
                            countermain = countermain + 1;
                            countermain = 2;
                        }
                        catch (Exception e)
                        {

                        }

                    }
                    else if(countermain==8) {
                        try
                        {
                            showFragment(Slider1.newInstance(""), true);

                            countermain = 2;
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
    }
    private void showFragment(Fragment fragment, boolean allowStateLoss) {
        try
        {
        FragmentManager fm = getActivity().getSupportFragmentManager();

        @SuppressLint("CommitTransaction")
        FragmentTransaction ft = fm.beginTransaction();
        ft.setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_left);

            ft.replace(R.id.mainpager, fragment);
            ft.addToBackStack(null);
            ft.commit();
            fm.executePendingTransactions();

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
try
{
        @SuppressLint("CommitTransaction")
        FragmentTransaction ft = fm.beginTransaction()
                .replace(R.id.mainpager, fragment);



    ft.addToBackStack(null);
    ft.commit();
    fm.executePendingTransactions();
    }
        catch (Exception e)
    {

    }
//        fm.executePendingTransactions();
    }

    public void faq() {

        final Dialog dialog = new Dialog(getActivity());
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        dialog.setCancelable(true);
        dialog.setContentView(R.layout.faq2);

        expListView = (ExpandableListView) dialog.findViewById(R.id.lvExp);

        prepareListData();

        listAdapter = new ExpandableListAdapter(getActivity(), listDataHeader, listDataChild) {

        };

        expListView.setAdapter(listAdapter);


        expListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {

            @Override
            public boolean onGroupClick(ExpandableListView parent, View v,
                                        int groupPosition, long id) {
                return false;
            }
        });

        expListView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {

            @Override
            public void onGroupExpand(int groupPosition) {
               /* Toast.makeText(dialog.getContext(),
                        listDataHeader.get(groupPosition) + " Expanded",
                        Toast.LENGTH_SHORT).show();*/
            }
        });

        expListView.setOnGroupCollapseListener(new ExpandableListView.OnGroupCollapseListener() {

            @Override
            public void onGroupCollapse(int groupPosition) {
                /*Toast.makeText(dialog.getContext(),
                        listDataHeader.get(groupPosition) + " Collapsed",
                        Toast.LENGTH_SHORT).show();*/

            }
        });


        expListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {

            @Override
            public boolean onChildClick(ExpandableListView parent, View v,
                                        int groupPosition, int childPosition, long id) {
                // TODO Auto-generated method stub
               /* Toast.makeText(
                        dialog.getContext(),
                        listDataHeader.get(groupPosition)
                                + " : "
                                + listDataChild.get(
                                listDataHeader.get(groupPosition)).get(
                                childPosition), Toast.LENGTH_SHORT)
                        .show();*/
                return false;
            }
        });


        Button dialogButton = (Button) dialog.findViewById(R.id.btn_dialog);
        dialogButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        dialog.show();



    }
    private void prepareListData() {
        listDataHeader = new ArrayList<String>();
        listDataChild = new HashMap<String, List<String>>();


        listDataHeader.add("1.\tWhen will the construction of The Aquatic Mall begin?");
        listDataHeader.add("2.\tWhere is The Aquatic Mall located?");
        listDataHeader.add("3.\tHow is The Aquatic Mall different from the other malls?");
        listDataHeader.add("4.\tWhat is the payment procedure?");
        listDataHeader.add("5.\tWhat is the profit ratio?");
        listDataHeader.add("6.\tIs the down payment refundable?");


        List<String> one = new ArrayList<String>();
        one.add("Construction is expected to begin in the month of September, 2018.");;

        List<String> two = new ArrayList<String>();
        two.add("The Aquatic Mall is situated adjacent to Zaraj Housing Society, opposite to World Trade Center on main GT Road, Islamabad.");

        List<String> three = new ArrayList<String>();
        three.add("The Aquatic Mall is the first mall in Pakistan based on an underwater theme, offering many exclusive features like an underwater tunnel, Aquadom and much more.");

        List<String> four = new ArrayList<String>();
        four.add("Units can be purchased either on full payment or on installments with 25% down payment followed by a four year installments plan.");

        List<String> five = new ArrayList<String>();
        five.add("Profit ratio offered by The Aquatic Mall is currently the highest in the twin cities, ranging from 40% to 60% per annum.");

        List<String> six = new ArrayList<String>();
        six.add("Down Payment can be fully refunded back any time during the installment period, without any charges or deductions.");

        listDataChild.put(listDataHeader.get(0), one); // Header, Child data
        listDataChild.put(listDataHeader.get(1), two);
        listDataChild.put(listDataHeader.get(2), three);
        listDataChild.put(listDataHeader.get(3), four);
        listDataChild.put(listDataHeader.get(4), five);
        listDataChild.put(listDataHeader.get(5), six);
    }
}
