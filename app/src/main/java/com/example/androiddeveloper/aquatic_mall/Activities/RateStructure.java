package com.example.androiddeveloper.aquatic_mall.Activities;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.androiddeveloper.aquatic_mall.Interfaces.ApiClient;
import com.example.androiddeveloper.aquatic_mall.Interfaces.ManageResponse;
import com.example.androiddeveloper.aquatic_mall.MainActivity;
import com.example.androiddeveloper.aquatic_mall.R;
import com.example.androiddeveloper.aquatic_mall.ResponseLists.MainResponselist;
import com.example.androiddeveloper.aquatic_mall.ResponseLists.MainViewResponse;
import com.example.androiddeveloper.aquatic_mall.fragments.Slider1;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RateStructure extends Fragment {

    public static final String TITLE = "RateStructure";
    ArrayList<MainViewResponse> mainlist=new ArrayList<>();
    TextView lgtext,gftext,mtext,f1text,f2text,f3text,f4text,f5text,f6text,f7text,f8text,f9text;
    LinearLayout back,ratelayout;
    ImageButton imgback;
    ImageView homeicon;

    public static RateStructure newInstance(String movieTitle) {
        RateStructure fragmentAction = new RateStructure();
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
        return inflater.inflate(R.layout.ratestructure_layout, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        homeicon=view.findViewById(R.id.homeicon);
        ratelayout=view.findViewById(R.id.ratelayoutfirst);
        lgtext=(TextView) view.findViewById(R.id.lgtext);
        gftext=(TextView) view.findViewById(R.id.gftext);
        mtext=(TextView)view.findViewById(R.id.mtext);
        f1text=(TextView) view.findViewById(R.id.f1text);
        f2text=(TextView) view.findViewById(R.id.f2text);
        f3text=(TextView) view.findViewById(R.id.f3text);
        f4text=(TextView) view.findViewById(R.id.f4text);
        f5text=(TextView) view.findViewById(R.id.f5text);
        f6text=(TextView) view.findViewById(R.id.f6text);
        f7text=(TextView) view.findViewById(R.id.f7text);
        f8text=(TextView) view.findViewById(R.id.f8text);
        f9text=(TextView) view.findViewById(R.id.f9text);
        back=(LinearLayout) view.findViewById(R.id.rback);
back.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        MainActivity.backpressed();
    }
});

//        imgback=(ImageButton) view.findViewById(R.id.rateback);
//        imgback.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                MainActivity.backfragment=MainActivity.currentfragment;
//                MainActivity.mNavigationManager.showFragmentHome("");
//            }
//        });


        recallmethod(1);
    }



    public void recallmethod(int j)
    {
        mainlist.clear();
        ManageResponse service = ApiClient.getClient().create( ManageResponse.class);

        Call<MainResponselist> userCall =service.numberrworesponse(j);

        userCall.enqueue(new Callback<MainResponselist>() {
            @Override
            public void onResponse(Call<MainResponselist> call, Response<MainResponselist> response) {
                ArrayList<MainViewResponse> mainview = (ArrayList<MainViewResponse>) response.body().getResults();

                if (mainview!=null) {
                    for (int i = 0; i < mainview.size(); i = i + 1) {
                        mainlist.add(mainview.get(i));
                        lgtext.setText(mainview.get(0).getRate());
                        gftext.setText(mainview.get(1).getRate());
                        mtext.setText(mainview.get(2).getRate());
                        f1text.setText(mainview.get(3).getRate());
                        f2text.setText(mainview.get(4).getRate());
                        f3text.setText(mainview.get(5).getRate());
                        f4text.setText(mainview.get(6).getRate());
                        f5text.setText(mainview.get(7).getRate());
                        f6text.setText(mainview.get(8).getRate());
                        f7text.setText(mainview.get(9).getRate());
                        f8text.setText(mainview.get(10).getRate());
                        f9text.setText(mainview.get(11).getRate());
                       homeicon.setVisibility(View.GONE);
                       ratelayout.setVisibility(View.VISIBLE);
//                        Toast.makeText(MainActivity.this, mainview.get(2).getFloor(), Toast.LENGTH_SHORT).show();
//                        if(mainview.size()-i==1)
//                        {
//                            homeRecyclerViewAdapter.notifyDataSetChanged();
//                        }
                    }


//                    homeRecyclerViewAdapter.notifyDataSetChanged();
//                    c=c+1;
//                    recallmethod(c);
//                                Toast.makeText(getActivity(), String.valueOf(mainlist.size()), Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<MainResponselist> call, Throwable t) {
                Toast.makeText(getActivity(), "Please Check Your Internet Connection", Toast.LENGTH_SHORT).show();
                Log.d("onFailure", t.toString());
            }
        });
    }
}
