package com.example.androiddeveloper.aquatic_mall.Activities;

import android.app.Activity;
import android.app.ProgressDialog;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.androiddeveloper.aquatic_mall.Adapters.INstalllistadapternew;
import com.example.androiddeveloper.aquatic_mall.Interfaces.ApiClient;
import com.example.androiddeveloper.aquatic_mall.Interfaces.ManageResponse;
import com.example.androiddeveloper.aquatic_mall.R;
import com.example.androiddeveloper.aquatic_mall.ResponseLists.Investorpropertynewlist;
import com.example.androiddeveloper.aquatic_mall.ResponseLists.NewInvestorDetailLists;
import com.example.androiddeveloper.aquatic_mall.ResponseLists.NewInvestyorLIsts;
import com.example.androiddeveloper.aquatic_mall.ResponseLists.Newinvestordetaillist;
import com.example.androiddeveloper.aquatic_mall.database.DtabaeHelper;
import com.example.androiddeveloper.aquatic_mall.fragments.InvestorPropertyNew;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by ANDROID DEVELOPER on 06/04/2018.
 */

public class InvestoreDetailInfonew extends Activity {
    TextView code,shopnumber,shopcode,floor,area,rate,duetotal,paidtotal,totalpayementamount,downpayment,propetydate;
    DtabaeHelper mydb;

    TextView totalpaidamount,totalremainingamount;
    INstalllistadapternew iNstalllistadapternew;
    RecyclerView recyclerView;
    String investorcode,shopcod;
    LinearLayout installmenylayout,installmentupper,accountdetail,totalpaidamountlayout;
    ProgressBar progressBar;
    int counter=0;
    ArrayList<Newinvestordetaillist> newinvestordetaillists=new ArrayList<>();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_investor_new);





        code=findViewById(R.id.account_codeno);
        shopnumber=findViewById(R.id.account_shopno);
        shopcode=findViewById(R.id.account_shopcode);
        totalpayementamount=findViewById(R.id.account_totalamount);
        downpayment=findViewById(R.id.account_downpayment);
        propetydate=findViewById(R.id.account_date);
        duetotal=findViewById(R.id.duetotal);
        paidtotal=findViewById(R.id.paidtotal);
        totalpaidamountlayout=findViewById(R.id.totalamoountslayout);
        floor=findViewById(R.id.account_floor);
        area=findViewById(R.id.account_area);
        rate=findViewById(R.id.account_rate);
        totalpaidamount=findViewById(R.id.totalpaidamounttext);
        totalremainingamount=findViewById(R.id.totalremainingamouttext);
        recyclerView = (RecyclerView) findViewById(R.id.install_recview);
       iNstalllistadapternew= new INstalllistadapternew(this,newinvestordetaillists);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(iNstalllistadapternew);
        iNstalllistadapternew.notifyDataSetChanged();

        mydb=new DtabaeHelper(this);
        Cursor c=mydb.checkaquatic();
        if(c.getCount()==0)
        {

        }
        else
        {
            while (c.moveToNext())
            {

                code.setText(c.getString(3));
                investorcode=c.getString(3);

                Bundle bundle = getIntent().getExtras();
                shopnumber.setText(""+bundle.getString("shopeno"));
                shopcode.setText("("+bundle.getString("shopcode")+")");
                shopcod=bundle.getString("shopcode").toString();
              //  Toast.makeText(this, bundle.getString("shopcode"), Toast.LENGTH_SHORT).show();
               recallmethod(c.getString(3).toString(),bundle.getString("shopcode"));
                        }
        }


    }



    public void recallmethod(String code,String shopcode)
    {
        ManageResponse service = ApiClient.getClient().create( ManageResponse.class);

        Call<NewInvestorDetailLists> userCall =service.investordetailnew(code,shopcode);

        userCall.enqueue(new Callback<NewInvestorDetailLists>() {
            @Override
            public void onResponse(Call<NewInvestorDetailLists> call, Response<NewInvestorDetailLists> response) {
                ArrayList<Newinvestordetaillist> mainview = (ArrayList<Newinvestordetaillist>) response.body().getNewinvestordetaillist();
if(mainview.isEmpty())
{
    Toast.makeText(InvestoreDetailInfonew.this, "Information is not added yet.", Toast.LENGTH_SHORT).show();
}
else {
    if (mainview != null) {
        int duetotalamount = 0, paidtotalamount = Integer.parseInt(mainview.get(0).getDownpaymentamount());
        floor.setText(mainview.get(0).getFloor().toString());
        area.setText(mainview.get(0).getArea().toString());
        rate.setText(mainview.get(0).getRate().toString());

        totalpayementamount.setText(mainview.get(0).getTotalpropertyamount().toString());
        downpayment.setText(mainview.get(0).getDownpaymentamount().toString());
        propetydate.setText(mainview.get(0).getPropertydate().toString());

        for (int c = 0; c < mainview.size(); c++) {
            duetotalamount = duetotalamount + Integer.parseInt(mainview.get(c).getIamount());
            if (mainview.get(c).getAmountpaid().equals("")) {

            } else {
                paidtotalamount = paidtotalamount + Integer.parseInt(mainview.get(c).getAmountpaid());
            }
            newinvestordetaillists.add(mainview.get(c));
            if (mainview.size() - c == 1) {
                progressBar = findViewById(R.id.progressbaarnew);
                accountdetail = findViewById(R.id.account_detail);
                RelativeLayout relativeLayout = findViewById(R.id.progresslayout);

                installmentupper = findViewById(R.id.installmentlayoutupper);
                installmenylayout = findViewById(R.id.installmentlayout);
                progressBar.setVisibility(View.GONE);
                relativeLayout.setVisibility(View.GONE);
                accountdetail.setVisibility(View.VISIBLE);
                installmenylayout.setVisibility(View.VISIBLE);
                installmentupper.setVisibility(View.VISIBLE);
                totalpaidamountlayout.setVisibility(View.VISIBLE);

              //  totalpaidamount.setText(String.valueOf(paidtotalamount));

                if (mainview.size() == 1) {
                    totalpaidamount.setText(String.valueOf(mainview.get(0).getDownpaymentamount().toString()));
                    totalremainingamount.setText(String.valueOf(Integer.parseInt(mainview.get(0).getTotalpropertyamount().toString()) -Integer.parseInt(mainview.get(0).getDownpaymentamount().toString())));
                } else {
                    totalpaidamount.setText(String.valueOf(paidtotalamount));
                    duetotalamount = Integer.parseInt(mainview.get(0).getTotalpropertyamount().toString()) - paidtotalamount;
                    if (duetotalamount >= 0) {

                    } else {
                        duetotalamount = 0;
                    }
                    totalremainingamount.setText(String.valueOf(duetotalamount));
                }
                            /*Newinvestordetaillist nliast=new Newinvestordetaillist();
                            nliast.setAmountpaid(String.valueOf(paidtotalamount));
                            nliast.setIamount(String.valueOf(duetotalamount));
                         nliast.setArea("");
                         nliast.setCash("");
                         nliast.setFloor("");
                         nliast.setId("");
                         nliast.setIduedate("");
                         nliast.setIinstallments("Total");
                         nliast.setInFcode("");
                         nliast.setIstatus("");
                         nliast.setShopcodeFid("");
                         nliast.setRate("");

                            newinvestordetaillists.add(nliast);*/
                iNstalllistadapternew.notifyDataSetChanged();
            }
        }
    }
}
            }

            @Override
            public void onFailure(Call<NewInvestorDetailLists> call, Throwable t) {
                try {
                    if(counter==0) {
                        Toast.makeText(InvestoreDetailInfonew.this, "Please Check Your Internet Connection", Toast.LENGTH_SHORT).show();
                        counter=counter+1;
                    }
                    recallmethod(investorcode,shopcod);
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
