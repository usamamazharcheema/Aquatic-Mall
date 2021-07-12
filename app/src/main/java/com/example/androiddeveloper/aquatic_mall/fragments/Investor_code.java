package com.example.androiddeveloper.aquatic_mall.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.androiddeveloper.aquatic_mall.Interfaces.ApiClient;
import com.example.androiddeveloper.aquatic_mall.Interfaces.ManageResponse;
import com.example.androiddeveloper.aquatic_mall.R;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Investor_code extends AppCompatActivity {
    TextView tv;
    Button ok;
    EditText codee;
    String codes;
    ArrayList<MainViewResponse> mainlist=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_investor_code);
        tv= (TextView)findViewById(R.id.ctext);
        ok=(Button) findViewById(R.id.iok);
        codee= (EditText) findViewById(R.id.icode);

        String s="Enter your code for \n The Aquatic Mall";


        setText(s);
        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                recallmethod(1);

            }
        });
    }
    public void setText(final String s)
    {

        final int[] i = new int[1];
        i[0] = 0;
        final int length = s.length();
        final Handler handler = new Handler()
        {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                char c= s.charAt(i[0]);
                Log.d("Strange",""+c);
                tv.append(String.valueOf(c));
                i[0]++;
            }
        };


        final Timer timer = new Timer();
        TimerTask taskEverySplitSecond = new TimerTask() {
            @Override
            public void run() {
                handler.sendEmptyMessage(0);
                if (i[0] == length - 1) {
                    timer.cancel();
                }
            }
        };
        timer.schedule(taskEverySplitSecond, 1, 100);
    }
    public void recallmethod(int j)
    {
        mainlist.clear();
        ManageResponse service = ApiClient.getClient().create( ManageResponse.class);

        Call<MainResponselist> userCall =service.numberrworesponses(j);

        userCall.enqueue(new Callback<MainResponselist>() {
            @Override
            public void onResponse(Call<MainResponselist> call, Response<MainResponselist> response) {
                ArrayList<MainViewResponse> mainview = (ArrayList<MainViewResponse>) response.body().getResults();

                if (mainview!=null) {
                    for (int i = 0; i < mainview.size(); i = i + 1) {
                        mainlist.add(mainview.get(i));
                        codes = codee.getText().toString();

                        if (codes.equals(mainview.get(i).getCodeno())){
                            Intent intent=new Intent(Investor_code.this, InvestorProfile.class);
                            intent.putExtra("investorcode",codes);
                            startActivity(intent);

                        }

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
//                Toast.makeText(getActivity(), "Failure", Toast.LENGTH_SHORT).show();
                Log.d("onFailure", t.toString());
            }
        });
    }

}
