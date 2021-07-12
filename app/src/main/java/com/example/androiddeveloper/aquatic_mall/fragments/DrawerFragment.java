package com.example.androiddeveloper.aquatic_mall.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.androiddeveloper.aquatic_mall.Activities.LatestNews;
import com.example.androiddeveloper.aquatic_mall.R;

/**
 * Created by ANDROID DEVELOPER on 06/02/2018.
 */

public class DrawerFragment  extends FragmentActivity implements View.OnTouchListener {
    float x1,x2;
    float y1, y2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.drawer_layout);
    }
    public void close (View v)
    {
        super.onBackPressed();
        overridePendingTransition( R.anim.backpressedlayout, R.anim.layoutin );
    }
    public void latest (View v)
    {
        Intent i=new Intent(DrawerFragment.this,LatestNews.class);
      startActivity(i);
      finish();
      overridePendingTransition( R.anim.layoutup, R.anim.backpressedlayout );
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition( R.anim.backpressedlayout, R.anim.layoutin );
    }
    public boolean onTouchEvent(MotionEvent touchevent)
    {
        switch (touchevent.getAction())
        {
            // when user first touches the screen we get x and y coordinate
            case MotionEvent.ACTION_DOWN:
            {
                x1 = touchevent.getX();
                y1 = touchevent.getY();
                break;
            }
            case MotionEvent.ACTION_UP:
            {
                x2 = touchevent.getX();
                y2 = touchevent.getY();

//                                                 / /if left to right sweep event on screen
                if (x1 < x2)
                {
//                    Toast.makeText(this, "Left to Right Swap Performed", Toast.LENGTH_LONG).show();
                }

                // if right to left sweep event on screen
                if (x1 > x2)
                {
//                    Toast.makeText(this, "Right to Left Swap Performed", Toast.LENGTH_LONG).show();
                }

                // if UP to Down sweep event on screen
                if (y1 < y2)
                {
                    super.onBackPressed();
                    overridePendingTransition( R.anim.backpressedlayout, R.anim.layoutin );
                }

//                                                 / /if Down to UP sweep event on screen
                if (y1 > y2)
                {
//                    Toast.makeText(this, "Down to UP Swap Performed", Toast.LENGTH_LONG).show();
                }
                break;
            }
        }
        return false;
    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        onTouchEvent(motionEvent);
        return false;
    }
}