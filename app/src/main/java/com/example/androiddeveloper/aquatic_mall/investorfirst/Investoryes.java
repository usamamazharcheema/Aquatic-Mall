package com.example.androiddeveloper.aquatic_mall.investorfirst;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;

import com.example.androiddeveloper.aquatic_mall.MainActivity;
import com.example.androiddeveloper.aquatic_mall.R;

import java.util.Timer;
import java.util.TimerTask;

public class Investoryes extends FragmentActivity {
    TextView tv;
    TextView sv;
    String b;
    Button yes, no;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_investoryes);
        yes= (Button) findViewById(R.id.iyes);
        no=(Button) findViewById(R.id.ino);
        String s="Are You an Investor in \n The Aquatic Mall?";
        setText(s);
        Animation fade1 = AnimationUtils.loadAnimation(this, R.anim.anim_slide_in_left);
        yes.startAnimation(fade1);
        yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(com.example.androiddeveloper.aquatic_mall.investorfirst.Investoryes.this, com.example.androiddeveloper.aquatic_mall.investorfirst.LoginAsInvestor.class);
                startActivity(intent);
                finish();
            }
        });
        no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(com.example.androiddeveloper.aquatic_mall.investorfirst.Investoryes.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
        Animation fade2 = AnimationUtils.loadAnimation(this,R.anim.anim_slide_in_right);
        no.startAnimation(fade2);

    }

    public void setText(final String s)
    {
        tv= (TextView)findViewById(R.id.text);
        final int[] i = new int[1];
        i[0] = 0;
        final int length = s.length();
        final Handler handler = new Handler()
        {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                try {
                    char c = s.charAt(i[0]);
                    Log.d("Strange", "" + c);
                    tv.append(String.valueOf(c));
                    i[0]++;
                }
                catch (Exception e)
                {

                }
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


}
