


package com.example.androiddeveloper.aquatic_mall.Adapters;

        import android.app.Activity;
        import android.content.Context;
        import android.content.Intent;
        import android.graphics.Bitmap;
        import android.graphics.drawable.BitmapDrawable;
        import android.graphics.drawable.Drawable;
        import android.net.Uri;
        import android.os.Environment;
        import android.support.v7.widget.RecyclerView;
        import android.util.Log;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.AdapterView;
        import android.widget.ArrayAdapter;
        import android.widget.Button;
        import android.widget.EditText;
        import android.widget.ImageView;
        import android.widget.LinearLayout;
        import android.widget.ProgressBar;
        import android.widget.RelativeLayout;
        import android.widget.Spinner;
        import android.widget.TextView;
        import android.widget.Toast;

        import com.example.androiddeveloper.aquatic_mall.Activities.InvestorRentalDetail;
        import com.example.androiddeveloper.aquatic_mall.Activities.InvestoreDetailInfonew;
        import com.example.androiddeveloper.aquatic_mall.Activities.Opnecomments;
        import com.example.androiddeveloper.aquatic_mall.Interfaces.ApiClient;
        import com.example.androiddeveloper.aquatic_mall.Interfaces.ManageResponse;
        import com.example.androiddeveloper.aquatic_mall.MainActivity;
        import com.example.androiddeveloper.aquatic_mall.R;
        import com.example.androiddeveloper.aquatic_mall.ResponseLists.Investorpropertynewlist;
        import com.example.androiddeveloper.aquatic_mall.ResponseLists.Likeslist;
        import com.example.androiddeveloper.aquatic_mall.ResponseLists.MSG;
        import com.example.androiddeveloper.aquatic_mall.ResponseLists.Newsresponse;
        import com.example.androiddeveloper.aquatic_mall.fragments.InvestorPropertyNew;
        import com.squareup.picasso.Picasso;
        import com.squareup.picasso.Target;

        import java.io.ByteArrayOutputStream;
        import java.io.File;
        import java.io.FileNotFoundException;
        import java.io.FileOutputStream;
        import java.io.IOException;
        import java.util.ArrayList;
        import java.util.HashMap;
        import java.util.List;

        import retrofit2.Call;
        import retrofit2.Callback;
        import retrofit2.Response;

/**
 * Created by ANDROID DEVELOPER on 05/02/2018.
 */






public class INvestornewPropertyAdapter extends RecyclerView.Adapter<INvestornewPropertyAdapter.MyViewHolder> {



    Context context;
ArrayList<Investorpropertynewlist> investorPropertyNews;
    public INvestornewPropertyAdapter( Context context,ArrayList<Investorpropertynewlist> investorPropertyNews) {

        this.context = context;
        this.investorPropertyNews=investorPropertyNews;

    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView shopno,shopcode;
        LinearLayout propertylayout;


        public MyViewHolder(View view) {
            super(view);
            shopcode=view.findViewById(R.id.shopecode);
            shopno=view.findViewById(R.id.shopeno);
            propertylayout=view.findViewById(R.id.propertylayout);

        }
    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.investorpropertiesnewlayout, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {

holder.shopno.setText("SHOP # "+investorPropertyNews.get(position).getShopno());
holder.shopcode.setText("("+investorPropertyNews.get(position).getShopcode()+")");
holder.propertylayout.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        Activity activity = (Activity) context;
        Intent i = new Intent(context, InvestoreDetailInfonew.class);
        i.putExtra("shopeno", "SHOP # " + investorPropertyNews.get(position).getShopno());
        i.putExtra("shopcode", investorPropertyNews.get(position).getShopcode().toString());
        activity.startActivity(i);
        activity.overridePendingTransition(R.anim.newlefttoright, R.anim.newrighttoleft);
/*if(investorPropertyNews.get(position).getPayment().toString().equals("P")) {
    Intent i = new Intent(context, InvestoreDetailInfonew.class);
    i.putExtra("shopeno", "SHOP # " + investorPropertyNews.get(position).getShopno());
    i.putExtra("shopcode", investorPropertyNews.get(position).getShopcode().toString());
    activity.startActivity(i);
    activity.overridePendingTransition(R.anim.newlefttoright, R.anim.newrighttoleft);
}
else
{
    Intent i = new Intent(context, InvestorRentalDetail.class);
    i.putExtra("shopeno", "SHOP # " + investorPropertyNews.get(position).getShopno());
    i.putExtra("shopcode", investorPropertyNews.get(position).getShopcode().toString());
    activity.startActivity(i);
    activity.overridePendingTransition(R.anim.newlefttoright, R.anim.newrighttoleft);
}*/
    }
});

    }

    @Override
    public int getItemCount() {
        return investorPropertyNews.size();
    }


}