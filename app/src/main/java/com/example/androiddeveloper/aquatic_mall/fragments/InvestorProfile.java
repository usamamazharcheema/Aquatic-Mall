package com.example.androiddeveloper.aquatic_mall.fragments;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.androiddeveloper.aquatic_mall.Interfaces.ApiClient;
import com.example.androiddeveloper.aquatic_mall.Interfaces.ManageResponse;
import com.example.androiddeveloper.aquatic_mall.R;

import java.util.ArrayList;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
public class InvestorProfile extends Activity {
    CardView myprofile;
    LinearLayout view_intsall,forward,backward,back,pagerheader;
    TextView in_code;
    private SectionsPagerAdapter mSectionsPagerAdapter;
    ViewPager viewPager;
    String code;
    TextView shopno,shopecode;
    ArrayList<MainViewResponse> mainlist=new ArrayList<>();
    ProgressBar spinner;
    //    ImageView next,back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_investor_profile);
        spinner=(ProgressBar)findViewById(R.id.progressBarcart);
        myprofile=(CardView) findViewById(R.id.available);
        view_intsall=(LinearLayout) findViewById(R.id.view_install);
        in_code=(TextView) findViewById(R.id.in_pfcodeno);
        viewPager= (ViewPager) findViewById(R.id.viewpager);
        shopno=(TextView)findViewById(R.id.shopno);
        shopecode=(TextView)findViewById(R.id.shopecode);
        forward=(LinearLayout) findViewById(R.id.gforward);
        backward=(LinearLayout) findViewById(R.id.gbackward);
        back=(LinearLayout) findViewById(R.id.pback);
        mSectionsPagerAdapter = new SectionsPagerAdapter(mainlist,this);
        viewPager.setAdapter(mSectionsPagerAdapter);
        mSectionsPagerAdapter.notifyDataSetChanged();
        recallmethod(1);
        Bundle bundle=getIntent().getExtras();
        code=bundle.getString("investorcodee");
        in_code.setText(code);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();

            }
        });

        view_intsall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(InvestorProfile.this, Investor_Installments.class);
                intent.putExtra("inve_code",code);
                intent.putExtra("shopno",mainlist.get(viewPager.getCurrentItem()).getShopno().toString());
                intent.putExtra("shopcode",mainlist.get(viewPager.getCurrentItem()).getShopcode().toString());
//               Toast.makeText(InvestorProfile.this,mainlist.get(viewPager.getCurrentItem()).getShopcode().toString(),Toast.LENGTH_SHORT).show();
                startActivity(intent);
                overridePendingTransition(R.anim.newlefttoright, R.anim.newrighttoleft);
            }
        });
        backward.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mainlist.size()-viewPager.getCurrentItem()==1)
                {

                }
                else
                {
                    viewPager.setCurrentItem(viewPager.getCurrentItem()+1);

                }
            }
        });
        forward.setOnClickListener(new View.OnClickListener() {
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
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                shopno.setText("SHOP # "+mainlist.get(position).getShopno().toString());
                shopecode.setText(mainlist.get(position).getShopcode().toString());
//        shop_code=mainlist.get(position).getShopcode();
            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }
    public class SectionsPagerAdapter extends PagerAdapter {

        ArrayList<MainViewResponse> mainview;
        Context context;
        public SectionsPagerAdapter(ArrayList<MainViewResponse> mainview, Context c) {
            this.mainview=mainview;
            context=c;
        }

        @Override
        public int getCount() {
            return mainview.size();
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view==object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            LayoutInflater inflater=LayoutInflater.from(context);
            ViewGroup layout=(ViewGroup)inflater.inflate(R.layout.fragment_my__profile,container,false);
            TextView rate,area,floor;
            rate=(TextView)layout.findViewById(R.id.rate);
            area=(TextView)layout.findViewById(R.id.area);
            floor=(TextView)layout.findViewById(R.id.floor);
            rate.setText(mainview.get(position).getIrate().toString());
            area.setText(mainview.get(position).getIarea().toString());
            floor.setText(mainview.get(position).getIfloor().toString());
            container.addView(layout);
            return layout;
        }




    }
    public void recallmethod(int j)
    {

        ManageResponse service = ApiClient.getClient().create( ManageResponse.class);

        Call<MainResponselist> userCall =service.ivestor_profile(j);

        userCall.enqueue(new Callback<MainResponselist>() {
            @Override
            public void onResponse(Call<MainResponselist> call, Response<MainResponselist> response) {
                ArrayList<MainViewResponse> mainview = (ArrayList<MainViewResponse>) response.body().getResults();

                if (mainview!=null) {
                    for (int i = 0; i < mainview.size(); i = i + 1) {
                        if (code.equals(mainview.get(i).getIn_fcodeno())) {
                            mainlist.add(mainview.get(i));
                            shopno.setText(mainlist.get(0).getShopno().toString());
                            shopecode.setText(mainlist.get(0).getShopcode().toString());
pagerheader=findViewById(R.id.pagerheader);
pagerheader.setVisibility(View.VISIBLE);
                            mSectionsPagerAdapter.notifyDataSetChanged();
                            spinner.setVisibility(View.GONE);
                            viewPager.setVisibility(View.VISIBLE);
//                            Toast.makeText(InvestorProfile.this, mainlist.get(0).getIarea().toString(), Toast.LENGTH_SHORT).show();


                        }
                    }
                }
            }

            @Override
            public void onFailure(Call<MainResponselist> call, Throwable t) {
                Toast.makeText(InvestorProfile.this, "Please Check Your Internet Connection", Toast.LENGTH_SHORT).show();
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
