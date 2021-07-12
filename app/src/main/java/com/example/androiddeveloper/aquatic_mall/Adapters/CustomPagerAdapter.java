package com.example.androiddeveloper.aquatic_mall.Adapters;

import android.content.Context;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.TextureView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.androiddeveloper.aquatic_mall.Activities.MyAxisValueFormatter;
import com.example.androiddeveloper.aquatic_mall.Activities.MyValueFormatter;
import com.example.androiddeveloper.aquatic_mall.Activities.MyXAxisValueFormatter;
import com.example.androiddeveloper.aquatic_mall.Activities.Trendiing;
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

public class CustomPagerAdapter extends PagerAdapter {

    private Context mContext;
    ArrayList<Trendingresponse> trendingresponses;
    ArrayList<String> floor;
    public CustomPagerAdapter(Context context, ArrayList<Trendingresponse> trendingresponses,ArrayList<String> floor) {
        mContext = context;
        this.trendingresponses=trendingresponses;
        this.floor=floor;
    }

    @Override
    public Object instantiateItem(ViewGroup collection, int position) {
//        ModelObject modelObject = ModelObject.values()[position];
        LayoutInflater inflater = LayoutInflater.from(mContext);
        ViewGroup layout = (ViewGroup) inflater.inflate(R.layout.graphtrending, collection, false);
//        TextView floortext=layout.findViewById(R.id.floortrending);


        LineChart mChart;
        mChart =  layout.findViewById(R.id.chart1);


        mChart.setDrawGridBackground(false);


        mChart.getDescription().setEnabled(false);
        mChart.setTouchEnabled(true);
        mChart.setDragEnabled(true);
        mChart.setScaleEnabled(true);
        mChart.setPinchZoom(true);
        mChart.setBackgroundColor(Color.WHITE);
        mChart.getLegend().setEnabled(false);


        XAxis xAxis = mChart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setTextSize(6f);
        xAxis.setTextColor(Color.BLACK);
        xAxis.setDrawAxisLine(false);
        xAxis.setDrawGridLines(false);

        xAxis.setGranularity(1f); // restrict interval to 1 (minimum)
        xAxis.setXOffset(25f);
        int arraysize=0;
        for (int v=0;v<trendingresponses.size();v++)
        {
            if(trendingresponses.get(v).getFloor().equals(floor.get(position))) {


                arraysize=arraysize+1;
            }

        }
        String[] values1 = new String[arraysize+1];
        values1[0]="";
        int valuescounter=1;
        for (int v=0;v<trendingresponses.size();v++)
        {
            if(trendingresponses.get(v).getFloor().equals(floor.get(position))) {
                values1[valuescounter] = trendingresponses.get(v).getDate();

                valuescounter=valuescounter+1;
            }
            if (trendingresponses.size() - v == 1) {
                xAxis.setValueFormatter(new MyXAxisValueFormatter(values1));
            }
        }


        YAxis leftAxis = mChart.getAxisLeft();
        IAxisValueFormatter custom = new MyAxisValueFormatter();
        leftAxis.setValueFormatter(custom);
        leftAxis.setPosition(YAxis.YAxisLabelPosition.OUTSIDE_CHART);
        ArrayList<Integer> j=new ArrayList<>();
        for (int v=0;v<trendingresponses.size();v++)
        {
            if(trendingresponses.get(v).getFloor().equals(floor.get(position))) {
                j.add(Integer.parseInt(trendingresponses.get(v).getPrice().toString()));
            }

        }

        leftAxis.setAxisMaximum((float)(Collections.max(j)+Collections.max(j)/2));
        leftAxis.setAxisMinimum(1f);
        leftAxis.setDrawLabels(false);
        leftAxis.setDrawGridLines(false);
        leftAxis.setDrawAxisLine(false);

        ArrayList<Entry> values = new ArrayList<Entry>();
        mChart.getAxisRight().setEnabled(false);
        values.add(new Entry(0, 0, ""));

        int entrycounter=1;
        for (int v=0;v<trendingresponses.size();v++)
        {
            if(trendingresponses.get(v).getFloor().equals(floor.get(position))) {
                values.add(new Entry(entrycounter, Integer.parseInt(trendingresponses.get(v).getPrice().toString()), ""));
                entrycounter=entrycounter+1;
            }

        }

//        values.add(new Entry(0, 0, getResources().getDrawable(R.drawable.star)));
//        values.add(new Entry(0, 0f, ""));
//        values.add(new Entry(1, 27000f, ""));
//        values.add(new Entry(2, 35000f, ""));
//        values.add(new Entry(3, 40000f, ""));
//        values.add(new Entry(4, 45000f, ""));
        //        values.add(new Entry(6, 0, getResources().getDrawable(R.drawable.star)));

        /*for (int i = 0; i < count; i++) {

        }*/

        LineDataSet set1;
        if (mChart.getData() != null &&
                mChart.getData().getDataSetCount() > 0) {
            set1 = (LineDataSet) mChart.getData().getDataSetByIndex(0);
            set1.setValues(values);
            mChart.getData().notifyDataChanged();

        } else {
            set1 = new LineDataSet(values, "");

            set1.setDrawIcons(false);
            set1.setMode(LineDataSet.Mode.CUBIC_BEZIER);
            set1.setCubicIntensity(0.2f);
            set1.setColor(Color.BLACK);
            set1.setCircleColor(Color.rgb(78,160,160));
            set1.setCircleColorHole(Color.WHITE);
            set1.setValueTextSize(6f);
            set1.setDrawCircles(true);
            set1.setDrawFilled(false);
            set1.setHighlightEnabled(false);
            set1.setColor(Color.rgb(78,160,160));
            set1.setFormLineWidth(2f);
            set1.setDrawHorizontalHighlightIndicator(false);
            set1.setFormLineDashEffect(new DashPathEffect(new float[]{10f, 5f}, 0f));
            set1.setFormSize(15.f);

            set1.setFillColor(Color.BLACK);


            ArrayList<ILineDataSet> dataSets = new ArrayList<ILineDataSet>();
            dataSets.add(set1);

            LineData data = new LineData(dataSets);
            data.setValueFormatter( new MyValueFormatter());
            mChart.setData(data);
        }

        mChart.animateX(2500);

        collection.addView(layout);
        return layout;
    }

    @Override
    public void destroyItem(ViewGroup collection, int position, Object view) {
        collection.removeView((View) view);
    }

    @Override
    public int getCount() {
        return floor.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public CharSequence getPageTitle(int position) {

        return " ";
    }
    public void mainmethod(ViewGroup layout)
    {

        LineChart mChart;
        mChart =  layout.findViewById(R.id.chart1);


        mChart.setDrawGridBackground(false);
        mChart.getDescription().setEnabled(false);
        mChart.setTouchEnabled(true);
        mChart.setDragEnabled(true);
        mChart.setScaleEnabled(true);
        mChart.setPinchZoom(true);
        mChart.setBackgroundColor(Color.WHITE);
        mChart.getLegend().setEnabled(false);


        XAxis xAxis = mChart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setTextSize(10f);
        xAxis.setTextColor(Color.BLACK);
        xAxis.setDrawAxisLine(false);
        xAxis.setDrawGridLines(false);
        xAxis.setGranularity(1f); // restrict interval to 1 (minimum)
        String[] values = new String[]{ trendingresponses.get(1).getDate(), trendingresponses.get(0).getDate()};
        xAxis.setValueFormatter(new MyXAxisValueFormatter(values));

        YAxis leftAxis = mChart.getAxisLeft();
        IAxisValueFormatter custom = new MyAxisValueFormatter();
        leftAxis.setValueFormatter(custom);
        leftAxis.setPosition(YAxis.YAxisLabelPosition.OUTSIDE_CHART);
        leftAxis.setAxisMaximum(1000000000);
        leftAxis.setAxisMinimum(1f);
        leftAxis.setDrawLabels(false);
        leftAxis.setDrawGridLines(false);
        leftAxis.setDrawAxisLine(false);


        mChart.getAxisRight().setEnabled(false);

        setData(4, 4,mChart);

        mChart.animateX(2500);
    }

    private void setData(int count, float range,LineChart mChart) {

        ArrayList<Entry> values = new ArrayList<Entry>();
//        values.add(new Entry(0, 0, getResources().getDrawable(R.drawable.star)));
        values.add(new Entry(0, Integer.parseInt(trendingresponses.get(1).getPrice()), mContext.getResources().getDrawable(R.drawable.star)));
        values.add(new Entry(1, Integer.parseInt(trendingresponses.get(0).getPrice()), mContext.getResources().getDrawable(R.drawable.star)));
        //        values.add(new Entry(6, 0, getResources().getDrawable(R.drawable.star)));

        /*for (int i = 0; i < count; i++) {

        }*/

        LineDataSet set1;
        if (mChart.getData() != null &&
                mChart.getData().getDataSetCount() > 0) {
            set1 = (LineDataSet) mChart.getData().getDataSetByIndex(0);
            set1.setValues(values);
            mChart.getData().notifyDataChanged();

        } else {
            set1 = new LineDataSet(values, "");

            set1.setDrawIcons(false);
            set1.setMode(LineDataSet.Mode.CUBIC_BEZIER);
            set1.setCubicIntensity(0.2f);
            set1.setColor(Color.BLACK);
            set1.setCircleColor(Color.rgb(78,160,160));
            set1.setCircleColorHole(Color.WHITE);
            set1.setValueTextSize(9f);
            set1.setDrawCircles(true);
            set1.setDrawFilled(true);
            set1.setHighlightEnabled(false);
            set1.setColor(Color.rgb(78,160,160));
            set1.setFormLineWidth(2f);
            set1.setDrawHorizontalHighlightIndicator(false);
            set1.setFormLineDashEffect(new DashPathEffect(new float[]{10f, 5f}, 0f));
            set1.setFormSize(15.f);

            if (Utils.getSDKInt() >= 18) {
                Drawable drawable = ContextCompat.getDrawable(mContext, R.drawable.fade_red);
                set1.setFillDrawable(drawable);
            } else {
                set1.setFillColor(Color.BLACK);
            }

            ArrayList<ILineDataSet> dataSets = new ArrayList<ILineDataSet>();
            dataSets.add(set1);

            LineData data = new LineData(dataSets);
            data.setValueFormatter( new MyValueFormatter());
            mChart.setData(data);
        }
    }
}
