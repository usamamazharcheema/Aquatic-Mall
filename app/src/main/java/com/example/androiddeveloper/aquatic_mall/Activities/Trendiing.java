package com.example.androiddeveloper.aquatic_mall.Activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.bizventure.androiddeveloper.aquatic_mall.Animationopen;
import com.example.androiddeveloper.aquatic_mall.Adapters.CustomPagerAdapter;
import com.example.androiddeveloper.aquatic_mall.Interfaces.ApiClient;
import com.example.androiddeveloper.aquatic_mall.Interfaces.ManageResponse;
import com.example.androiddeveloper.aquatic_mall.MainActivity;
import com.example.androiddeveloper.aquatic_mall.R;
import com.example.androiddeveloper.aquatic_mall.ResponseLists.TrendingLists;
import com.example.androiddeveloper.aquatic_mall.ResponseLists.Trendingresponse;
import com.example.androiddeveloper.aquatic_mall.fragments.DrawerFragment;
import com.example.androiddeveloper.aquatic_mall.fragments.Slider1;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by ANDROID DEVELOPER on 22/01/2018.
 */

public class Trendiing extends Fragment implements View.OnClickListener {
    public View v;
    ViewPager graphpager;
    ImageView back,next;
    TextView itemstrending;
    public static TextView trendfloor;
    List<String> categories = new ArrayList<String>();
    ArrayList<String> floor=new ArrayList<>();
    ArrayList<Trendingresponse> trendingresponses=new ArrayList<>();
    LinearLayout layoutback;
    ImageView imgback;
Spinner trendingspinner;
    public static Trendiing newInstance(String movieTitle) {
        Trendiing fragmentAction = new Trendiing();
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
        return inflater.inflate(R.layout.trending, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        v=view;
        MainActivity.chaticonmain();
        imgback=view.findViewById(R.id.trendingbackimg);
        layoutback=view.findViewById(R.id.trendingbacklayout);
        trendfloor=view.findViewById(R.id.floortrending);
        back=view.findViewById(R.id.backtrend);
//        categories.add("");
        next=view.findViewById(R.id.nexttrend);
        trendingspinner=v.findViewById(R.id.trendingspiner);
        itemstrending=view.findViewById(R.id.itemstrending);
        back.setOnClickListener(this);
        next.setOnClickListener(this);
        imgback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainActivity.mNavigationManager.showFragmentHome("");
            }
        });
        layoutback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainActivity.mNavigationManager.showFragmentHome("");
            }
        });
        trendingspinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                    graphpager.setCurrentItem(position, true);
                    Trendiing.settextmain(floor.get(graphpager.getCurrentItem()).toString());
                    for (int c=0;c<trendingresponses.size();c++)
                    {
                        if(trendingresponses.get(c).getFloor().contains(floor.get(graphpager.getCurrentItem())))
                        {
                            itemstrending.setText(trendingresponses.get(c).getItem().toString());
                            c=trendingresponses.size();
                        }
                    }


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        getgraph(" ");
    }





    public void getgraph(final String mystring)
    {

        final ManageResponse service = ApiClient.getClient().create( ManageResponse.class);

        Call<TrendingLists> userCall =service.GetTrending(mystring);

        userCall.enqueue(new Callback<TrendingLists>() {
            @Override
            public void onResponse(Call<TrendingLists> call, Response<TrendingLists> response) {

                ArrayList<Trendingresponse> trendingresponselist=(ArrayList<Trendingresponse>) response.body().getTrendingresponse();
                if(trendingresponselist!=null)
                {
                    for(int c=0;c<trendingresponselist.size();c++)
                    {
                        trendingresponses.add(trendingresponselist.get(c));

                        if(trendingresponselist.size()-c==1) {
                            for(int b=0;b<trendingresponses.size();b++)
                            {
                                if(floor.isEmpty())
                                {
                                    floor.add(trendingresponselist.get(b).getFloor());
                                    categories.add(trendingresponselist.get(b).getItem().toString()+"       ("+trendingresponselist.get(b).getFloor().toString()+")");
                                }
                                else if(floor.contains(trendingresponses.get(b).getFloor()))
                                {

                                }
                                else
                                {
                                    floor.add(trendingresponselist.get(b).getFloor());
                                    categories.add(trendingresponselist.get(b).getItem().toString()+"       ("+trendingresponselist.get(b).getFloor().toString()+")");
                                }
                                if(trendingresponses.size()-b==1)
                                {
                                    graphpager=v.findViewById(R.id.graphpager);
                                    ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, categories);

                                    // Drop down layout style - list view with radio button
                                    dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);


                                    graphpager.setOffscreenPageLimit(floor.size());
                                    graphpager.setAdapter(new CustomPagerAdapter(getActivity(),trendingresponses,floor));
                                    trendingspinner.setAdapter(dataAdapter);
                                    graphpager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                                        @Override
                                        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                                            Trendiing.settextmain(floor.get(graphpager.getCurrentItem()).toString());
                                            for (int c=0;c<trendingresponses.size();c++)
                                            {
                                                if(trendingresponses.get(c).getFloor().contains(floor.get(graphpager.getCurrentItem())))
                                                {
                                                    itemstrending.setText(trendingresponses.get(c).getItem().toString());
                                                    c=trendingresponses.size();
                                                }
                                            }
                                        }

                                        @Override
                                        public void onPageSelected(int position) {

                                        }

                                        @Override
                                        public void onPageScrollStateChanged(int state) {

                                        }
                                    });
                                    Trendiing.settextmain(floor.get(graphpager.getCurrentItem()).toString());
                                    ImageView img=v.findViewById(R.id.loadingicon);
                                    LinearLayout linearLayout=v.findViewById(R.id.graphlayout);
                                    img.setVisibility(View.GONE);
                                    linearLayout.setVisibility(View.VISIBLE);
                                }

                            }


                        }
                    }
                }
            }

            @Override
            public void onFailure(Call<TrendingLists> call, Throwable t) {
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


    private int getItem(int i) {
        try {
            return graphpager.getCurrentItem() + i;
        }
        catch (Exception e)
        {
            return 0;
        }
    }

    @Override
    public void onClick(View view) {
        if (view.getId()==R.id.backtrend)
        {
            graphpager.setCurrentItem(getItem(-1), true);
            Trendiing.settextmain(floor.get(graphpager.getCurrentItem()).toString());
            for (int c=0;c<trendingresponses.size();c++)
            {
                if(trendingresponses.get(c).getFloor().contains(floor.get(graphpager.getCurrentItem())))
                {
                    itemstrending.setText(trendingresponses.get(c).getItem().toString());
                    c=trendingresponses.size();
                }
            }
          /* Intent i2 = new Intent(Trendiing.this, Animationopen.class);
           startActivity(i2);
           overridePendingTransition( R.anim.layoutup, R.anim.backpressedlayout );*/

        }
        else if(view.getId()==R.id.nexttrend)
        {
            graphpager.setCurrentItem(getItem(+1), true);
            Trendiing.settextmain(floor.get(graphpager.getCurrentItem()).toString());
            for (int c=0;c<trendingresponses.size();c++)
            {
                if(trendingresponses.get(c).getFloor().contains(floor.get(graphpager.getCurrentItem())))
                {
                    itemstrending.setText(trendingresponses.get(c).getItem().toString());
                    c=trendingresponses.size();
                }
            }
        }
    }

    public static void settextmain(String s)
    {
        trendfloor.setText(""+s);
    }

}
