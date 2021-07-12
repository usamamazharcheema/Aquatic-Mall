package com.example.androiddeveloper.aquatic_mall.fragments;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.IInterface;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.androiddeveloper.aquatic_mall.Adapters.FeatureRecylerAdapter;
import com.example.androiddeveloper.aquatic_mall.Interfaces.ApiClient;
import com.example.androiddeveloper.aquatic_mall.Interfaces.ManageResponse;
import com.example.androiddeveloper.aquatic_mall.MainActivity;
import com.example.androiddeveloper.aquatic_mall.R;
import com.example.androiddeveloper.aquatic_mall.ResponseLists.Featureresponse;
import com.example.androiddeveloper.aquatic_mall.ResponseLists.FeaturesResponseList;
import com.example.androiddeveloper.aquatic_mall.ResponseLists.NewsResponseLists;
import com.example.androiddeveloper.aquatic_mall.ResponseLists.Newsresponse;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by ANDROID DEVELOPER on 07/02/2018.
 */

public class Features extends Fragment {

    ArrayList<Featureresponse> featureresponses1=new ArrayList<>();
    TextView itemnames;
    public static String itemname=null;
   public static int counter=0;
   LinearLayout exclusive;
    ImageView back,next;
    LinearLayout featurenext,featureback;
  public static   ArrayList<String> content=new ArrayList<>();
    public    ArrayList<String> itemnamest=new ArrayList<>();
    public static Features newInstance(String movieTitle) {
        Features fragmentAction = new Features();
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
        return inflater.inflate(R.layout.features_layout, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        content.add("The Aquatic Mall is offering everything a keen buyer could possibly want. We are committed to transform your shopping into great experience with aquatic ambience. Expanded on 5 dedicated floors, featuring top national and international brands.");
        content.add("The Aquatic Mall dedicates a separate floor for food court, hosting national and international vendors to provide diverse varieties of food, ranging from fast food to international to local cuisines. No matter what type of food you prefer, you will find it here.");
        content.add("The Aquatic Mall is offering everything a keen buyer could possibly want. We are committed to transform your shopping into great experience with aquatic ambience. Expanded on 5 dedicated floors, featuring top national and international brands.");
        content.add("The Aquatic Mall is also introducing a fully equipped exclusive Business Centre. An ideal place to set-up your business at, giving easy access to programs, services, administrations, assets, support and perfect environment for all parts of your business.");
        content.add("Specifically designed to address the increasing demands for secure, well maintained and lavish units. The apartments are available in different sizes. Blending a new concept of relaxation and sophistication to create an ideal life style.");
        content.add("Our Suites will be on the top floor of our Mall. These suites have been impeccably designed, with flawless furnishing, keeping in mind the underwater theme of the mall. All the amenities needed for a luxurious life style are made available.");




        itemnamest.add("Shopping Area");
        itemnamest.add("Food Court");
        itemnamest.add("Kids Area");
        itemnamest.add("Business Centre");
        itemnamest.add("Luxury Apartments");
        itemnamest.add("Deluxe Suites");

        itemname="Shopping Area";
        counter=0;
//        Features.itemname = itemnamest.get(0);




        itemnames=view.findViewById(R.id.itemname);

       back=view.findViewById(R.id.featuresback);
       next=view.findViewById(R.id.featuresnext);
       back.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               back1();
           }
       });
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
next1();
            }
        });
        featurenext=view.findViewById(R.id.featurenext);
        featureback=view.findViewById(R.id.featureback);
        featureback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                back1();
            }
        });
        featurenext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                next1();
            }
        });
        itemnames.setText("Shopping Area");
//        Features.itemname = itemnamest.get(0);
        exclusive=view.findViewById(R.id.exclusivelayout);
        exclusive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.backfragment=MainActivity.currentfragment;
                MainActivity.mNavigationManager.showFragmentExclusive("");
            }
        });
        LinearLayout featurelayoiut=view.findViewById(R.id.featurelayout);
        showFragment(FeatureRcyclerlayout.newInstance(""), true);
featurelayoiut.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        MainActivity.backpressed();
    }
});
    }


    private void showFragment(Fragment fragment, boolean allowStateLoss) {

            FragmentManager fm = getActivity().getSupportFragmentManager();

            @SuppressLint("CommitTransaction")
            FragmentTransaction ft = fm.beginTransaction();
            ft.setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_left);

            ft.replace(R.id.featurefragment, fragment);

            ft.commit();



    }

    public void next1()
    {
       back.setVisibility(View.VISIBLE);
//        Toast.makeText(getActivity(), "next", Toast.LENGTH_SHORT).show();
        counter = counter + 1;
        if(counter>5)
        {
            featurenext.setVisibility(View.GONE);
            counter = counter - 1;
        }
        else {

            Features.itemname = itemnamest.get(counter);

            itemnames.setText(itemnamest.get(counter));
            showFragment(FeatureRcyclerlayout.newInstance(""), true);
        }
//        FeatureRcyclerlayout.textset(content.get(counter));
    }
    public void back1()
    {
        featurenext.setVisibility(View.VISIBLE);
        counter = counter - 1;
        if(counter<0)
        {
            counter = counter + 1;
            back.setVisibility(View.GONE);
        }
        else
        {

            Features.itemname = itemnamest.get(counter);

            itemnames.setText(itemnamest.get(counter));
            showFragment(FeatureRcyclerlayout.newInstance(""), true);
        }
//        FeatureRcyclerlayout.textset(content.get(counter));
    }
}
