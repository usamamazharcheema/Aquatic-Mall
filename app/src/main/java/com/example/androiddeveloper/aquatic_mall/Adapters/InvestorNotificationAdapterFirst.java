package com.example.androiddeveloper.aquatic_mall.Adapters;


        import android.content.Context;

        import android.support.v4.view.PagerAdapter;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.ImageView;
        import android.widget.TextView;

        import com.example.androiddeveloper.aquatic_mall.R;
        import com.example.androiddeveloper.aquatic_mall.ResponseLists.Notificationresponse;

        import java.util.ArrayList;

/**
 * Created by ANDROID DEVELOPER on 22/02/2018.
 */










public class InvestorNotificationAdapterFirst extends PagerAdapter {

    private Context mContext;
    ArrayList<Notificationresponse> mnotifications;
    public InvestorNotificationAdapterFirst(Context context,ArrayList<Notificationresponse> notifications) {
        mContext = context;
mnotifications=notifications;
    }

    @Override
    public Object instantiateItem(ViewGroup collection, int position) {

        LayoutInflater inflater = LayoutInflater.from(mContext);
        ViewGroup layout = (ViewGroup) inflater.inflate(R.layout.topsellerpager, collection, false);
        ImageView featureimage;
        TextView item,unitcode,shopcode,floono;
        item=layout.findViewById(R.id.item);
        unitcode=layout.findViewById(R.id.unitcode);
        shopcode=layout.findViewById(R.id.shopcode);
        floono=layout.findViewById(R.id.floorno);
item.setText(mnotifications.get(position).getItem().toString());
        unitcode.setText("Unit No: "+mnotifications.get(position).getUnitno().toString());
        shopcode.setText("Code: "+mnotifications.get(position).getShopcode().toString());
        floono.setText(mnotifications.get(position).getFloorno().toString());

        collection.addView(layout);
        return layout;
    }

    @Override
    public void destroyItem(ViewGroup collection, int position, Object view) {
        collection.removeView((View) view);
    }

    @Override
    public int getCount() {
        return mnotifications.size();
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
