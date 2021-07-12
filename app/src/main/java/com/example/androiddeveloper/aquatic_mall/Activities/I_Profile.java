package com.example.androiddeveloper.aquatic_mall.Activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.androiddeveloper.aquatic_mall.Interfaces.ManageResponse;
import com.example.androiddeveloper.aquatic_mall.MainActivity;
import com.example.androiddeveloper.aquatic_mall.R;
import com.example.androiddeveloper.aquatic_mall.fragments.InvestorProfile;
import com.example.androiddeveloper.aquatic_mall.fragments.MainViewResponse;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class I_Profile extends Activity {
    TextView pr_name,pr_email,pr_cell,pr_cnic,view_account;
//    ArrayList<MainViewResponse> mainlist=new ArrayList<>();
    LinearLayout back;
    String code;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_i_profile);
        pr_name=(TextView) findViewById(R.id.pr_name);
        pr_email=(TextView) findViewById(R.id.pr_email);
        pr_cell=(TextView) findViewById(R.id.pr_cell);
        pr_cnic=(TextView) findViewById(R.id.pr_cnic);
        back=(LinearLayout) findViewById(R.id.pr_back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        view_account=(TextView) findViewById(R.id.view_account);
        pr_name.setText(MainActivity.name);
        pr_email.setText(MainActivity.checkemail);
        pr_cell.setText(MainActivity.phone);
        pr_cnic.setText(MainActivity.cnic);

//        Bundle bundle=getIntent().getExtras();
//        code=bundle.getString("investorcodess");
        view_account.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(I_Profile.this, InvestorProfile.class);
                intent.putExtra("investorcodee",MainActivity.investorcode);
                startActivity(intent);
                overridePendingTransition(R.anim.newlefttoright, R.anim.newrighttoleft);
            }
        });
//        recallmethod(1);
    }
    /*public void recallmethod(int j)
    {
        mainlist.clear();
        ManageResponse service = ApiClient.getClient().create( NumberOfRowsMain.class);

        Call<MainResponselist> userCall =service.numberrworesponses(j);

        userCall.enqueue(new Callback<MainResponselist>() {
            @Override
            public void onResponse(Call<MainResponselist> call, Response<MainResponselist> response) {
                ArrayList<MainViewResponse> mainview = (ArrayList<MainViewResponse>) response.body().getResults();

                if (mainview!=null) {
                    for (int i = 0; i < mainview.size(); i = i + 1) {
                        if (code.equals(mainview.get(i).getCodeno())) {
                            mainlist.add(mainview.get(i));
                            pr_name.setText(mainview.get(i).getIn_name());
                            pr_email.setText(mainview.get(i).getIn_email());
                            pr_cell.setText(mainview.get(i).getIn_phonenumber());
                            pr_cnic.setText(mainview.get(i).getIn_cnicno());
//                            shopno.setText(mainlist.get(0).getShopno().toString());
//                            shopecode.setText(mainlist.get(0).getShopcode().toString());

//                            Toast.makeText(InvestorProfile.this, mainlist.get(0).getIarea().toString(), Toast.LENGTH_SHORT).show();

//                            mSectionsPagerAdapter.notifyDataSetChanged();
                        }
                    }
                }
            }

            @Override
            public void onFailure(Call<MainResponselist> call, Throwable t) {
                Toast.makeText(I_Profile.this, "Failure", Toast.LENGTH_SHORT).show();
                Log.d("onFailure", t.toString());
            }
        });
    }*/

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.newrighttoleftback, R.anim.newback);
    }
}
