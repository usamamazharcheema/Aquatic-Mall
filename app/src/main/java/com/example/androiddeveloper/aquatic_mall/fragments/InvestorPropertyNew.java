package com.example.androiddeveloper.aquatic_mall.fragments;


import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.androiddeveloper.aquatic_mall.Activities.Selling_Investor;
import com.example.androiddeveloper.aquatic_mall.Adapters.INvestornewPropertyAdapter;
import com.example.androiddeveloper.aquatic_mall.Adapters.NewsAdapter;
import com.example.androiddeveloper.aquatic_mall.Interfaces.ApiClient;
import com.example.androiddeveloper.aquatic_mall.Interfaces.ManageResponse;
import com.example.androiddeveloper.aquatic_mall.MainActivity;
import com.example.androiddeveloper.aquatic_mall.R;
import com.example.androiddeveloper.aquatic_mall.ResponseLists.InvestorLoginnewLIsts;
import com.example.androiddeveloper.aquatic_mall.ResponseLists.Investorloginnew;
import com.example.androiddeveloper.aquatic_mall.ResponseLists.Investorpropertynewlist;
import com.example.androiddeveloper.aquatic_mall.ResponseLists.NewInvestyorLIsts;
import com.example.androiddeveloper.aquatic_mall.database.DtabaeHelper;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by ANDROID DEVELOPER on 06/04/2018.
 */

public class InvestorPropertyNew extends FragmentActivity {

RecyclerView recyclerView;
    DtabaeHelper mydb;
    TextView name,email,cnic,sonof,profession,phone,officephone,address;
    ImageView img;
INvestornewPropertyAdapter iNvestornewPropertyAdapter;
ArrayList<Investorpropertynewlist> investorPropertyNew=new ArrayList<>();

int counter=0;
String investorcode;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.newinvestorprofile);
        img=findViewById(R.id.profile_image);
        name=findViewById(R.id.profile_name);
        email=findViewById(R.id.profile_email);
        cnic=findViewById(R.id.profile_cnic);
        sonof=findViewById(R.id.profile_so);
        profession=findViewById(R.id.profile_profession);
        phone=findViewById(R.id.profile_cellno);
        officephone=findViewById(R.id.profile_officeno);
        address=findViewById(R.id.profile_address);

       recyclerView = (RecyclerView) findViewById(R.id.investorproperties);
        iNvestornewPropertyAdapter=new INvestornewPropertyAdapter(this,investorPropertyNew);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(iNvestornewPropertyAdapter);
        iNvestornewPropertyAdapter.notifyDataSetChanged();
        DtabaeHelper db=new DtabaeHelper(this);
        Cursor c=db.checkaquatic();

        if (c.getCount() == 0) {

        } else {
            while (c.moveToNext()) {
                name.setText(c.getString(2));
                email.setText(c.getString(1));
                cnic.setText(c.getString(6));
                sonof.setText(c.getString(7));
                profession.setText(c.getString(9));
                phone.setText(c.getString(5));
                officephone.setText(c.getString(10));
                address.setText(c.getString(8));
                Picasso.with(this).load("https://cpecintel.com/aquatic/login/profile_img/"+c.getString(11)).into(img);

                recallmethod(c.getString(3));
                investorcode=c.getString(3);
            }
            }

   }


    public void recallmethod(String code)
    {
        ManageResponse service = ApiClient.getClient().create( ManageResponse.class);

        Call<NewInvestyorLIsts> userCall =service.investornewproprty(code);

        userCall.enqueue(new Callback<NewInvestyorLIsts>() {
            @Override
            public void onResponse(Call<NewInvestyorLIsts> call, Response<NewInvestyorLIsts> response) {
                ArrayList<Investorpropertynewlist> mainview = (ArrayList<Investorpropertynewlist>) response.body().getInvestorpropertynewlist();

                if (mainview!=null) {

for(int c=0;c<mainview.size();c++)
{
    investorPropertyNew.add(mainview.get(c));
    if(mainview.size()-c==1)
    {
        RelativeLayout relativeLayout=findViewById(R.id.progresslayout);
        ProgressBar progressBar=findViewById(R.id.progressbaarnewproperty);
        relativeLayout.setVisibility(View.GONE);
        progressBar.setVisibility(View.GONE);

        iNvestornewPropertyAdapter.notifyDataSetChanged();
    }
}
                }
            }

            @Override
            public void onFailure(Call<NewInvestyorLIsts> call, Throwable t) {
                try {
                    if(counter==0) {
                        Toast.makeText(InvestorPropertyNew.this, "Please Check Your Internet Connection", Toast.LENGTH_SHORT).show();
                        counter=counter+1;
                    }
                    recallmethod(investorcode);
                    Log.d("onFailure", t.toString());
                }
                catch (Exception e)
                {

                }
            }
        });
    }
    public void backclick(View v)
    {
        super.onBackPressed();
        overridePendingTransition(R.anim.newrighttoleftback, R.anim.newback);
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.newrighttoleftback, R.anim.newback);
    }
}
