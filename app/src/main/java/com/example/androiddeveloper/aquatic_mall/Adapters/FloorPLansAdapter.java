




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










public class FloorPLansAdapter extends PagerAdapter {

    private Context mContext;

    public FloorPLansAdapter(Context context) {
        mContext = context;

    }

    @Override
    public Object instantiateItem(ViewGroup collection, int position) {

        LayoutInflater inflater = LayoutInflater.from(mContext);
        ViewGroup layout = (ViewGroup) inflater.inflate(R.layout.floorimglayout, collection, false);

ImageView img=layout.findViewById(R.id.flooimg);
if(position==0)
{
   img.setImageResource(R.drawable.gfplan);
}
else if(position==1)
{
    img.setImageResource(R.drawable.lgfplan);
}
else if(position==2)
{
    img.setImageResource(R.drawable.foodcourt);
}
else if(position==3)
{
    img.setImageResource(R.drawable.masterplan);
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
