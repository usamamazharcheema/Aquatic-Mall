package com.example.androiddeveloper.aquatic_mall.Adapters;

import android.content.Context;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.androiddeveloper.aquatic_mall.Activities.MyAxisValueFormatter;
import com.example.androiddeveloper.aquatic_mall.Activities.MyValueFormatter;
import com.example.androiddeveloper.aquatic_mall.Activities.MyXAxisValueFormatter;
import com.example.androiddeveloper.aquatic_mall.R;
import com.example.androiddeveloper.aquatic_mall.ResponseLists.Trendingresponse;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.github.mikephil.charting.utils.Utils;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by ANDROID DEVELOPER on 22/02/2018.
 */










public class AboutAdapter extends PagerAdapter {

    private Context mContext;

    public AboutAdapter(Context context) {
        mContext = context;

    }

    @Override
    public Object instantiateItem(ViewGroup collection, int position) {

        LayoutInflater inflater = LayoutInflater.from(mContext);
        ViewGroup layout = (ViewGroup) inflater.inflate(R.layout.aquatic_slide, collection, false);
        ImageView logo,title;
        TextView textView;
        title=layout.findViewById(R.id.titleimgabout);
        logo=layout.findViewById(R.id.logoimg);
        textView=layout.findViewById(R.id.text);

if(position==0)
{
    title.getLayoutParams().height = 40;
    title.getLayoutParams().width = 230;
    title.requestLayout();
    final int sdk = android.os.Build.VERSION.SDK_INT;
    if(sdk < android.os.Build.VERSION_CODES.JELLY_BEAN) {
        title.setBackgroundDrawable( mContext.getResources().getDrawable(R.drawable.tvaq) );
    } else {
        title.setBackground( mContext.getResources().getDrawable(R.drawable.tvaq));
    }

    logo.setImageResource(R.drawable.about_aquatic);
    textView.setText("Al Bari Group of Companies is launching an Underwater Themed Mall in Pakistan, which will be the first of its kind. The Aquatic Mall, located at a very prime location of G.T road Islamabad, is going to introduce a bunch of unique features, including an Aqua Dom, an Underwater Tunnel and much more.");
}
else if(position==1)
{
    title.getLayoutParams().height = 60;
    title.getLayoutParams().width = 200;
    title.requestLayout();
    final int sdk = android.os.Build.VERSION.SDK_INT;
    if(sdk < android.os.Build.VERSION_CODES.JELLY_BEAN) {
        title.setBackgroundDrawable( mContext.getResources().getDrawable(R.drawable.tval) );
    } else {
        title.setBackground( mContext.getResources().getDrawable(R.drawable.tval));
    }
    logo.setImageResource(R.drawable.about_albari);
    textView.setText("Al-Bari Group of Companies has been established with a vision of introducing the trend of transparency in the business market of Pakistan. We are constantly adapting to the needs of the rapidly changing corporate industry. We are here to initiate change.");

}
else if(position==2)
{
    title.getLayoutParams().height = 60;
    title.getLayoutParams().width = 200;
    title.requestLayout();
    final int sdk = android.os.Build.VERSION.SDK_INT;
    if(sdk < android.os.Build.VERSION_CODES.JELLY_BEAN) {
        title.setBackgroundDrawable( mContext.getResources().getDrawable(R.drawable.tvbz) );
    } else {
        title.setBackground( mContext.getResources().getDrawable(R.drawable.tvbz));
    }
    logo.setImageResource(R.drawable.about_bizventure);
    textView.setText("Bizventure Marketing, a subsidiary of Al-Bari Group of Companies, provides 360 degree marketing solutions, with the commitment of finding leads for your business and a better way to reach your target audience. We deliver what we commit.");

}
else if(position==3)
{
    title.getLayoutParams().height = 60;
    title.getLayoutParams().width = 200;
    title.requestLayout();
    final int sdk = android.os.Build.VERSION.SDK_INT;
    if(sdk < android.os.Build.VERSION_CODES.JELLY_BEAN) {
        title.setBackgroundDrawable( mContext.getResources().getDrawable(R.drawable.tvgn) );
    } else {
        title.setBackground( mContext.getResources().getDrawable(R.drawable.tvgn));
    }
    logo.setImageResource(R.drawable.about_greenearth);
    textView.setText("Green Earth is a full service Real Estate agency, introducing a new concept of property consultancy in Pakistan. Our services include extensive ground surveys, property co-investment and investment pocket identification and destination consultancy.");

}



        collection.addView(layout);
        return layout;
    }

    @Override
    public void destroyItem(ViewGroup collection, int position, Object view) {
        collection.removeView((View) view);
    }

    @Override
    public int getCount() {
        return 4;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public CharSequence getPageTitle(int position) {

        return " ";
    }

}
