package com.example.androiddeveloper.aquatic_mall.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.TextView;

import com.example.androiddeveloper.aquatic_mall.Adapters.LookinToInvestAdapter;
import com.example.androiddeveloper.aquatic_mall.Interfaces.ApiClient;
import com.example.androiddeveloper.aquatic_mall.Interfaces.ManageResponse;
import com.example.androiddeveloper.aquatic_mall.R;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * A simple {@link Fragment} subclass.
 */
public class SoldPlaceholderFragment extends Fragment {
    /**
     * The fragment argument representing the section number for this
     * fragment.
     */
    private static final String ARG_SECTION_NUMBER = "section_number";
    GridView gv;
    TextView shops, floors;
    public static String checkfilter=null;
    public static String catfilter=null;

    ArrayList<feature_model> moviees=new ArrayList<>();
    private final static String API_KEY = "https://cpecintel.com/aquatic/features/featurecat_select.php";

    public SoldPlaceholderFragment() {
    }

    public static SoldPlaceholderFragment newInstance(int sectionNumber, String filter, String shops) {
        checkfilter=filter;
        catfilter=shops;
        SoldPlaceholderFragment fragment = new SoldPlaceholderFragment();
        Bundle args = new Bundle();

        args.putInt(ARG_SECTION_NUMBER, sectionNumber);

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_sold_placeholder, container, false);
//        Toast.makeText(getActivity(), catfilter, Toast.LENGTH_SHORT).show();
        gv=(GridView) rootView.findViewById(R.id.sgridView1);
        shops=(TextView) rootView.findViewById(R.id.sshops);
        floors=(TextView) rootView.findViewById(R.id.sfloors);
//        gv.setAdapter(new CustomAdapter(getContext(), moviees));
        ManageResponse apiService = ApiClient.getClient().create(ManageResponse.class);
        Call<Featurecat_result> call = apiService.getTopRated(API_KEY);
        call.enqueue(new Callback<Featurecat_result>() {
            @Override
            public void onResponse(Call<Featurecat_result> call, Response<Featurecat_result> response) {
                int statusCode = response.code();
                moviees.clear();
                ArrayList<feature_model> movies = (ArrayList<feature_model>) response.body().getResults();
                if (movies!=null) {
                    for (int i = 0; i < movies.size(); i = i + 1) {
                        if (movies.get(i).getFeaturename().equals(catfilter) && checkfilter.equals(movies.get(i).getFloor())) {
                            moviees.add(movies.get(i));
                            shops.setText(catfilter);
                            floors.setText(checkfilter);
                        }
                    }
                    if (getActivity() != null) {
//                        gv.setAdapter(new LookinToInvestAdapter(getActivity(), moviees));
                    }


                }
            }
            @Override
            public void onFailure(Call<Featurecat_result> call, Throwable t) {
//                Log.e(TAG, t.toString());
            }
        });
        return rootView;
    };
}