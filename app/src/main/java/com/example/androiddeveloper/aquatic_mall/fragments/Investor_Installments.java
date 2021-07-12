package com.example.androiddeveloper.aquatic_mall.fragments;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.androiddeveloper.aquatic_mall.Activities.I_Profile;
import com.example.androiddeveloper.aquatic_mall.Interfaces.ApiClient;
import com.example.androiddeveloper.aquatic_mall.Interfaces.ManageResponse;
import com.example.androiddeveloper.aquatic_mall.R;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Investor_Installments extends Activity {
    TextView install_code, install_shopno,install_shopcode;
    LinearLayout view_profile,iback;
    private RecyclerView recyclerView;
    Installments_Adapter installments_adapter;
    String inv_code,ishop_code,ishopno;

    ArrayList<MainViewResponse> mainlist=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_investor__installments);
//        mainlist.clear();
        install_code=(TextView) findViewById(R.id.instl_code);
        install_shopno=(TextView) findViewById(R.id.instl_shopno);
        install_shopcode=(TextView) findViewById(R.id.instl_shopcode);
        view_profile=(LinearLayout) findViewById(R.id.view_profile);
        iback=(LinearLayout) findViewById(R.id.iback);
        Bundle bundle=getIntent().getExtras();
        inv_code=bundle.getString("inve_code");
        ishop_code=bundle.getString("shopcode");
        ishopno=bundle.getString("shopno");

        install_code.setText(inv_code);
        install_shopno.setText("SHOP # "+ishopno);
        install_shopcode.setText(ishop_code);
//        Toast.makeText(Investor_Installments.this, inv_code+ishop_code,Toast.LENGTH_SHORT).show();
        installments_adapter=new Installments_Adapter(mainlist,R.layout.install_list,Investor_Installments.this);
        iback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        view_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                view_profile.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent=new Intent(Investor_Installments.this, I_Profile.class);

                        startActivity(intent);
                       overridePendingTransition(R.anim.newlefttoright, R.anim.newrighttoleft);
                        finish();
                    }
                });
            }
        });
        recyclerView = (RecyclerView) findViewById(R.id.install_recview);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(installments_adapter);
        installments_adapter.notifyDataSetChanged();
        recallmethod(1);
    }
    public void recallmethod(int j)
    {
        mainlist.clear();
        ManageResponse service = ApiClient.getClient().create( ManageResponse.class);

        Call<MainResponselist> userCall =service.ivestor_ints(j);

        userCall.enqueue(new Callback<MainResponselist>() {
            @Override
            public void onResponse(Call<MainResponselist> call, Response<MainResponselist> response) {
                ArrayList<MainViewResponse> mainview = (ArrayList<MainViewResponse>) response.body().getResults();

                if (mainview!=null) {
                    for (int i = 0; i < mainview.size(); i = i + 1) {
                        if (inv_code.equals(mainview.get(i).getIn_fcode()) && ishop_code.equals(mainview.get(i).getShopcode_fid())){
                        mainlist.add(mainview.get(i));
//                        mainview.clear();
                    }
                    if (mainview.size()-i==1){
                        installments_adapter.notifyDataSetChanged();
                      ProgressBar  spinner=(ProgressBar)findViewById(R.id.progressBarcart);
                        RelativeLayout detail;
                        LinearLayout installheader;
                        installheader=findViewById(R.id.installheader);
                        detail=findViewById(R.id.detailinstall);
                        spinner.setVisibility(View.GONE);
                        installheader.setVisibility(View.VISIBLE);
                        detail.setVisibility(View.VISIBLE);


                    }

//                    installments_adapter.notifyDataSetChanged();
//                    c=c+1;
//
//                    recallmethod(c);
                    }
//                                Toast.makeText(getActivity(), String.valueOf(mainlist.size()), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<MainResponselist> call, Throwable t) {
                Toast.makeText(Investor_Installments.this, "Please Check Your Internet Connection", Toast.LENGTH_SHORT).show();
                Log.d("onFailure", t.toString());
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.newrighttoleftback, R.anim.newback);
    }
}
