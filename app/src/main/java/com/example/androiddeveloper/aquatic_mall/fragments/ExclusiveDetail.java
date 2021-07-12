
package com.example.androiddeveloper.aquatic_mall.fragments;

        import android.os.Bundle;
        import android.support.annotation.Nullable;
        import android.support.v4.app.Fragment;
        import android.support.v7.widget.DefaultItemAnimator;
        import android.support.v7.widget.LinearLayoutManager;
        import android.support.v7.widget.RecyclerView;
        import android.util.Log;
        import android.view.Gravity;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.ImageView;
        import android.widget.LinearLayout;
        import android.widget.TextView;
        import android.widget.Toast;

        import com.example.androiddeveloper.aquatic_mall.Activities.AboutMallFeature;
        import com.example.androiddeveloper.aquatic_mall.Adapters.ExclusiveRecylerAdapter;
        import com.example.androiddeveloper.aquatic_mall.Adapters.FeatureRecylerAdapter;
        import com.example.androiddeveloper.aquatic_mall.Adapters.NewsAdapter;
        import com.example.androiddeveloper.aquatic_mall.Interfaces.ApiClient;
        import com.example.androiddeveloper.aquatic_mall.Interfaces.ManageResponse;
        import com.example.androiddeveloper.aquatic_mall.MainActivity;
        import com.example.androiddeveloper.aquatic_mall.R;
        import com.example.androiddeveloper.aquatic_mall.ResponseLists.Featureimgresponse;
        import com.example.androiddeveloper.aquatic_mall.ResponseLists.Featureresponse;
        import com.example.androiddeveloper.aquatic_mall.ResponseLists.FeaturesResponseList;
        import com.example.androiddeveloper.aquatic_mall.ResponseLists.PicResponse;

        import java.util.ArrayList;

        import retrofit2.Call;
        import retrofit2.Callback;
        import retrofit2.Response;

/**
 * Created by ANDROID DEVELOPER on 07/02/2018.
 */

public class ExclusiveDetail extends Fragment {
    public static ExclusiveDetail newInstance(String movieTitle) {
        ExclusiveDetail fragmentAction = new ExclusiveDetail();
        Bundle args = new Bundle();
        args.putString(" ", movieTitle);
        fragmentAction.setArguments(args);

        return fragmentAction;
    }
    TextView t;
    ArrayList<Featureimgresponse> featureimgresponses=new ArrayList<>();
    RecyclerView featurerecclerview;
    ExclusiveRecylerAdapter featureRecylerAdapter;
    TextView title;
    ImageView exlcusivebakc;
    LinearLayout backlayout;
ArrayList<Integer> imglists=new ArrayList<>();
String text;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.exclusivedetail, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        if(MianExclusive.exclusivetitle.equals("UnderWater Tunnel"))
        {
          t=view.findViewById(R.id.featuretext);
            t.setText("We are introducing Pakistan’s 1st Underwater Tunnel Aquarium, in The Aquatic Mall, which will house hundreds of marine species. The ambience and environment of the tunnel will make you feel like you are walking inside an ocean, among fishes of all kinds.");
            imglists.add(R.drawable.tunnel1)  ;
            imglists.add(R.drawable.tunnel2)  ;
            imglists.add(R.drawable.tunnel3)  ;
        }
        else if(MianExclusive.exclusivetitle.equals("Apartments With Lawn"))
        {
            t=view.findViewById(R.id.featuretext);
            t.setText("The Aquatic Mall is introducing a new concept of elevated plantation, with the apartments. The 7th and 8th floors of the Mall are fully dedicated for the apartments. No matter how high is your apartment, a private lawn will be made available on the balcony of each apartment.");
            imglists.add(R.drawable.apartmentwithlawn1)  ;
            imglists.add(R.drawable.apartmentwithlawn2)  ;
            imglists.add(R.drawable.apartmentwithlawn3)  ;
            imglists.add(R.drawable.apartmentwithlawn4)  ;
        }
        else if(MianExclusive.exclusivetitle.equals("Deluxe Suites"))
        {
            t=view.findViewById(R.id.featuretext);
t.setText("The Aquatic Mall has dedicated its top most floor for deluxe suites, which are designed with a huge aquarium on one-side of the suite. Moreover, the aquatic ambience of the suite and the royal furnishing will make you live in the moment.");
            imglists.add(R.drawable.suite1)  ;
            imglists.add(R.drawable.suite2)  ;
            imglists.add(R.drawable.suite3)  ;
            imglists.add(R.drawable.suite4)  ;
        }
        else if(MianExclusive.exclusivetitle.equals("Marine Themed Restaurant"))
        {
            t=view.findViewById(R.id.featuretext);
            t.setText("The concept of themed restaurants is new in Pakistan. The Aquatic Mall is introducing an Underwater Themed Restaurant, which will gain everyone’s attention on the food court of the Mall. This restaurant will have a large aquarium, spread throughout the Restaurant’s premises, instead of regular walls.");
            imglists.add(R.drawable.marine1)  ;
            imglists.add(R.drawable.marine2)  ;
            imglists.add(R.drawable.marine3)  ;
            imglists.add(R.drawable.marine4)  ;
        }
        else if(MianExclusive.exclusivetitle.equals("Infinity Pool"))
        {
        t=view.findViewById(R.id.featuretext);
            t.setText("The Aquatic Mall is introducing the concept of an infinity pool on the roof top. A swimming pool, whose positioning will give the impression that it merges into the surrounding landscape. In addition to the pool, a helipad will also be made available.");
//            Toast.makeText(getActivity(), "adkj", Toast.LENGTH_SHORT).show();
            imglists.add(R.drawable.infinitypool1)  ;
            imglists.add(R.drawable.infinitypool2)  ;


        }
        else if(MianExclusive.exclusivetitle.equals("Indoor Water Park"))
        {
            t=view.findViewById(R.id.featuretext);
            t.setText("The Aquatic Mall has something special planned for the kids. We are introducing an indoor water park, which has never been tried before in Pakistan. The parents will feel more comfortable in taking their children to an indoor park, keeping in view the increased security and physical safety as well.");
            imglists.add(R.drawable.waterpark1)  ;
            imglists.add(R.drawable.waterpark2)  ;
            imglists.add(R.drawable.waterpark3)  ;
        }
        else if(MianExclusive.exclusivetitle.equals("Helipad"))
        {
            t=view.findViewById(R.id.featuretext);
            t.setText("The Aquatic Mall is incorporating a Helipad on the roof top, besides the infinity pool. This helipad will be open for any individual wanting to use it. This has never been attempted before in a Mall.");
            imglists.add(R.drawable.helipad1)  ;

        }
        else if(MianExclusive.exclusivetitle.equals("Centralized Aquarium"))
        {
           t=view.findViewById(R.id.featuretext);
            t.setText("The Aquatic Mall is introducing a Centralized Aquarium; a tall cylindrical acrylic glass Aquarium, rising up from the ground floor to the fifth floor of the mall, containing variety of marine species.");
            imglists.add(R.drawable.aquadom1)  ;

            imglists.add(R.drawable.aquadom2);
            imglists.add(R.drawable.aquadom3)  ;
            imglists.add(R.drawable.aquadom4) ;
        }











title=view.findViewById(R.id.titleex);
title.setText(MianExclusive.exclusivetitle.toString());
title.setGravity(Gravity.CENTER);
exlcusivebakc=view.findViewById(R.id.exclusivbakc);
        backlayout=view.findViewById(R.id.exclusivebacklayout);



        featurerecclerview = (RecyclerView) view.findViewById(R.id.featurerecclerview);
        featureRecylerAdapter=new ExclusiveRecylerAdapter(getActivity(),imglists,t.getText().toString());
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        featurerecclerview.setLayoutManager(mLayoutManager);
        featurerecclerview.setItemAnimator(new DefaultItemAnimator());
        featurerecclerview.setAdapter(featureRecylerAdapter);
        featureRecylerAdapter.notifyDataSetChanged();
        exlcusivebakc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainActivity.backpressed();
            }
        });
        backlayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainActivity.backpressed();
            }
        });
//        getfeaturesimg(Features.itemname);
    }


}
