

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
        import android.widget.ExpandableListView;
        import android.widget.ImageView;
        import android.widget.LinearLayout;
        import android.widget.ProgressBar;
        import android.widget.RelativeLayout;
        import android.widget.TextView;
        import android.widget.Toast;

        import com.example.androiddeveloper.aquatic_mall.Adapters.ExpandableAdapterRental;
        import com.example.androiddeveloper.aquatic_mall.Adapters.ExpandableListAdapter;
        import com.example.androiddeveloper.aquatic_mall.Adapters.INstalllistadapternew;
        import com.example.androiddeveloper.aquatic_mall.Adapters.RentalAdapter;
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
        import java.util.HashMap;
        import java.util.List;

        import retrofit2.Call;
        import retrofit2.Callback;
        import retrofit2.Response;

/**
 * Created by ANDROID DEVELOPER on 06/04/2018.
 */

public class InvestorRentalDetail extends Activity {
    TextView code,shopnumber,shopcode,floor,area,rate,duetotal,paidtotal,totalpayementamount,downpayment,propetydate;
    DtabaeHelper mydb;
    ExpandableAdapterRental listAdapter;
    ExpandableListView expListView;
    List<String> listDataHeader;
    HashMap<String, List<Newinvestordetaillist>> listDataChild;
    TextView totalpaidamount,totalremainingamount;

    private int lastExpandedPosition = -1;
    String investorcode,shopcod;
    LinearLayout installmenylayout,installmentupper,accountdetail,totalpaidamountlayout;
    ProgressBar progressBar;
    int counter=0;
    ArrayList<Newinvestordetaillist> newinvestordetaillists=new ArrayList<>();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.investorrentalyearslayout);





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

        expListView = (ExpandableListView) findViewById(R.id.lvExp);
        listDataHeader = new ArrayList<String>();
        listDataChild = new HashMap<String, List<Newinvestordetaillist>>();



        listAdapter = new ExpandableAdapterRental(this, listDataHeader, listDataChild,newinvestordetaillists);


        expListView.setAdapter(listAdapter);
        expListView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {

            @Override
            public void onGroupExpand(int groupPosition) {
                if (lastExpandedPosition != -1
                        && groupPosition != lastExpandedPosition) {
                    expListView.collapseGroup(lastExpandedPosition);
                }
                lastExpandedPosition = groupPosition;
            }
        });

    }



    public void recallmethod(String code,String shopcode)
    {
        ManageResponse service = ApiClient.getClient().create( ManageResponse.class);

        Call<NewInvestorDetailLists> userCall =service.investordetailnewrental(code,shopcode);

        userCall.enqueue(new Callback<NewInvestorDetailLists>() {
            @Override
            public void onResponse(Call<NewInvestorDetailLists> call, Response<NewInvestorDetailLists> response) {
                ArrayList<Newinvestordetaillist> mainview = (ArrayList<Newinvestordetaillist>) response.body().getNewinvestordetaillist();
                if(mainview.isEmpty())
                {
                    Toast.makeText(InvestorRentalDetail.this, "Information is not added yet.", Toast.LENGTH_SHORT).show();
                }
                else {
                    if (mainview != null) {

                        floor.setText(mainview.get(0).getFloor().toString());
                        area.setText(mainview.get(0).getArea().toString());
                        rate.setText(mainview.get(0).getRate().toString());

                        totalpayementamount.setText(mainview.get(0).getTotalpropertyamount().toString());
                        downpayment.setText(mainview.get(0).getDownpaymentamount().toString());
                        propetydate.setText(mainview.get(0).getPropertydate().toString());


                        for (int c = 0; c < mainview.size(); c++) {

                            if(listDataHeader.contains(mainview.get(c).getAmountpaid().toString()))
                            {

                            }
                            else
                            {
                                listDataHeader.add(mainview.get(c).getAmountpaid().toString());
                             //   List<String> childlist = new ArrayList<String>();
                                ArrayList<Newinvestordetaillist> childlist=new ArrayList<>();
                                for(int child=0;child<mainview.size();child++)
                                {
                                    if(mainview.get(child).getAmountpaid().equals(mainview.get(c).getAmountpaid().toString()))
                                    {
                                        childlist.add(mainview.get(child));

                                    }
                                    if(mainview.size()-child==1)
                                    {
                                        listDataChild.put(mainview.get(c).getAmountpaid().toString(), childlist);
                                    }
                                }
                            }

                            newinvestordetaillists.add(mainview.get(c));


                            if (mainview.size() - c == 1)
                            {
                                accountdetail=findViewById(R.id.account_detail);
                                progressBar=findViewById(R.id.progressbaarnew);
                                accountdetail.setVisibility(View.VISIBLE);
                                progressBar.setVisibility(View.GONE);
listAdapter.notifyDataSetChanged();
expListView.scrollTo(0, 0);
                            }
                        }
                    }
                }
            }

            @Override
            public void onFailure(Call<NewInvestorDetailLists> call, Throwable t) {
                try {
                    if(counter==0) {
                        Toast.makeText(InvestorRentalDetail.this, "Please Check Your Internet Connection", Toast.LENGTH_SHORT).show();
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
