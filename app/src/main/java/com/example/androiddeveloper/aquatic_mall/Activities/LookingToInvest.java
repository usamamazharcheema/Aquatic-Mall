package com.example.androiddeveloper.aquatic_mall.Activities;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.androiddeveloper.aquatic_mall.Interfaces.ApiClient;
import com.example.androiddeveloper.aquatic_mall.Interfaces.ManageResponse;
import com.example.androiddeveloper.aquatic_mall.MainActivity;
import com.example.androiddeveloper.aquatic_mall.R;
import com.example.androiddeveloper.aquatic_mall.fragments.Feature_result;
import com.example.androiddeveloper.aquatic_mall.fragments.PlaceholderFragment;
import com.example.androiddeveloper.aquatic_mall.fragments.SoldSectionPagerAdapter;
import com.example.androiddeveloper.aquatic_mall.fragments.feature_model;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LookingToInvest extends Fragment {

    private SectionsPagerAdapter mSectionsPagerAdapter;
        private SoldSectionPagerAdapter soldSectionPagerAdapter;
    CardView available;
   public static ViewPager viewPager;
    Bundle bundle;
    public  LinearLayout mainlayout;
    public ImageView mainimgview;

    LinearLayout forward,backward,backlayout;
  public static   ArrayList<String> arrayList=new ArrayList<>();

    public static ArrayList<String> shoplist=new ArrayList<>();

    String gound = "lower ground";

    String cat="shops";
   public View v;
    TextView shops, floors;
  public static ArrayList<feature_model> featuremodellist=new ArrayList<>();
    private final static String API_KEY = "https://cpecintel.com/aquatic/features/feature_select.php";
    //    TextView floors;
    public static LookingToInvest newInstance(String movieTitle) {
        LookingToInvest fragmentAction = new LookingToInvest();
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
        return inflater.inflate(R.layout.lookingtoinvest, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
v=view;
        MainActivity.chaticonmain();
arrayList.clear();
        shoplist.clear();
arrayList.add("Lower Ground");
        arrayList.add("Ground Floor");
        arrayList.add("Mezzanine Floor");
        arrayList.add("1st Floor");
        arrayList.add("2nd Floor");
        arrayList.add("3rd Floor");
        arrayList.add("5th Floor");
        arrayList.add("6th Floor");
        arrayList.add("7th Floor");
        arrayList.add("8th Floor");
        arrayList.add("9th Floor");

        shoplist.add("Shops");
        shoplist.add("Shops");
        shoplist.add("Shops");
        shoplist.add("Shops");
        shoplist.add("Shops");
        shoplist.add("Food Court");
        shoplist.add("Offices");
        shoplist.add("Offices");
        shoplist.add("Apartments");
        shoplist.add("Apartments");
        shoplist.add("Suites");

        featuremodellist.clear();
        MainActivity.lookingtoinvest="Null";
        viewPager = (ViewPager) view.findViewById(R.id.viewpager);
        available=(CardView)view.findViewById(R.id.available);
        shops= view.findViewById(R.id.shops);

        floors= view.findViewById(R.id.floors);
//        sold=(CardView) view.findViewById(R.id.sold);
//        floors=(TextView) findViewById(R.id.mfloors);
//        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

//        viewPager.setAdapter(mSectionsPagerAdapter);
//        viewPager.setOffscreenPageLimit(1);
        mSectionsPagerAdapter = new SectionsPagerAdapter(getActivity().getSupportFragmentManager());
        forward=(LinearLayout) view.findViewById(R.id.gforward);
        backward=(LinearLayout)view.findViewById(R.id.gbackward);
        backlayout=(LinearLayout)view.findViewById(R.id.backlayout);
        viewPager.setOffscreenPageLimit(1);
        available.setBackgroundResource(R.drawable.avalaible);
        available.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mSectionsPagerAdapter = new SectionsPagerAdapter(getActivity().getSupportFragmentManager());
                mSectionsPagerAdapter.notifyDataSetChanged();
                viewPager.setAdapter(mSectionsPagerAdapter);

                available.setBackgroundResource(R.drawable.avalaible);


            }
        });
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
floors.setText(arrayList.get(position).toString());
shops.setText(shoplist.get(position).toString());

           /*     if(position==0)
                {

                    gound="lower ground";
                    cat="shops";
                    floors.setText(gound);
                    shops.setText(cat);



                }
                else if (position==1)
                {

                    gound="Ground floor";
                    cat="shops";
                    floors.setText(gound);
                    shops.setText(cat);
                }
                else if (position==2)
                {
                    gound="upper ground";
                    cat="shops";
                    floors.setText(gound);
                    shops.setText(cat);
                }
                else if (position==3)
                {
                    gound="first floor";
                    cat="shops";
                    floors.setText(gound);
                    shops.setText(cat);
                }
                else if (position==4)
                {
                    gound="2nd floor";
                    cat="shops";
                    floors.setText(gound);
                    shops.setText(cat);
                }
                else if (position==5)
                {
                    gound="3rd floor";
                    cat="foodcourt";
                    floors.setText(gound);
                    shops.setText(cat);
                }
                else if (position==6)
                {
                    gound="5th floor";
                    cat="offices";
                    floors.setText(gound);
                    shops.setText(cat);
                }
                else if (position==7)
                {
                    gound="6th floor";
                    cat="offices";
                    floors.setText(gound);
                    shops.setText(cat);
                }
                else if (position==8)
                {
                    gound="7th floor";
                    cat="apartments";
                    floors.setText(gound);
                    shops.setText(cat);
                }
                else if (position==9)
                {
                    gound="8th floor";
                    cat="apartments";
                    floors.setText(gound);
                    shops.setText(cat);
                }
                else if (position==10)
                {
                    gound="9th floor";
                    cat="sweets";
                    floors.setText(gound);
                    shops.setText(cat);
                }*/
            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        backward.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(viewPager.getCurrentItem()==0)
                {

                }
                else
                {
                    viewPager.setCurrentItem(viewPager.getCurrentItem()-1);

                }
            }
        });
        forward.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(viewPager.getCurrentItem()==10)
                {

                }
                else
                {
                    viewPager.setCurrentItem(viewPager.getCurrentItem()+1);
                }
            }
        });
        backlayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.t.cancel();
                MainActivity.mNavigationManager.showFragmentHome("");
                MainActivity.backfragment="Home";
                MainActivity.drawercheck="close";
            }
        });
        maincall();
    }
    public void maincall()
    {
        ManageResponse apiService = ApiClient.getClient().create(ManageResponse.class);
        Call<Feature_result> call = apiService.getTopRatedMovies(API_KEY);
        call.enqueue(new Callback<Feature_result>() {
            @Override
            public void onResponse(Call<Feature_result> call, Response<Feature_result> response) {
                int statusCode = response.code();

                ArrayList<feature_model> movies = (ArrayList<feature_model>) response.body().getResults();
                if (movies!=null) {
                    for (int i = 0; i < movies.size(); i = i + 1) {
                      featuremodellist.add(movies.get(i));
                        if(movies.size()-i==1)
                        {

                            viewPager.setAdapter(mSectionsPagerAdapter);
                            mSectionsPagerAdapter.notifyDataSetChanged();
                            mainimgview=v.findViewById(R.id.imgloading);
                            mainlayout=v.findViewById(R.id.mainlayotu);
                            mainimgview.setVisibility(View.GONE);
                            mainlayout.setVisibility(View.VISIBLE);
                        }
                    }
                    if (getActivity()!=null){

                    }}


            }
            @Override
            public void onFailure(Call<Feature_result> call, Throwable t) {
//                Log.e(TAG, t.toString());
            }
        });
    }





    public class SectionsPagerAdapter extends FragmentStatePagerAdapter {

        public SectionsPagerAdapter(FragmentManager fragmentManager) {
            super(fragmentManager);
        }



        @Override
        public Fragment getItem(int position) {



            return PlaceholderFragment.newInstance(position ,gound, cat);

        }
//        @Override
//        public Object instantiateItem(ViewGroup container, int position) {
//            View currentPage = null;
//
//                ((ViewPager)container).setCurrentItem(position);
//
//
//                return currentPage;
//            }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return arrayList.size();

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



}