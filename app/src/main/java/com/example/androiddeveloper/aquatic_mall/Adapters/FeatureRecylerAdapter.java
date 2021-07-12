package com.example.androiddeveloper.aquatic_mall.Adapters;



        import android.Manifest;
        import android.app.Dialog;
        import android.content.Context;
        import android.content.Intent;
        import android.content.pm.PackageManager;
        import android.database.Cursor;
        import android.graphics.Bitmap;
        import android.graphics.drawable.BitmapDrawable;
        import android.graphics.drawable.Drawable;
        import android.net.Uri;
        import android.os.Bundle;
        import android.os.Environment;
        import android.support.v4.app.ActivityCompat;
        import android.support.v7.widget.RecyclerView;
        import android.telephony.SmsManager;
        import android.util.Log;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
        import android.view.Window;
        import android.widget.AdapterView;
        import android.widget.ArrayAdapter;
        import android.widget.Button;
        import android.widget.ImageView;
        import android.widget.LinearLayout;
        import android.widget.RelativeLayout;
        import android.widget.Spinner;
        import android.widget.TextView;
        import android.widget.Toast;

        import com.bizventure.androiddeveloper.aquatic_mall.Animationopen;
        import com.example.androiddeveloper.aquatic_mall.R;
        import com.example.androiddeveloper.aquatic_mall.ResponseLists.Featureimgresponse;
        import com.example.androiddeveloper.aquatic_mall.ResponseLists.Newsresponse;
        import com.example.androiddeveloper.aquatic_mall.fragments.Features;
        import com.squareup.picasso.Picasso;
        import com.squareup.picasso.Target;

        import java.io.File;
        import java.io.FileNotFoundException;
        import java.io.FileOutputStream;
        import java.io.IOException;
        import java.util.ArrayList;
        import java.util.List;

        import retrofit2.Call;
        import retrofit2.Callback;
        import retrofit2.Response;

/**
 * Created by ANDROID DEVELOPER on 05/02/2018.
 */






public class FeatureRecylerAdapter extends RecyclerView.Adapter<FeatureRecylerAdapter.MyViewHolder> {



    Context context;

ArrayList<Integer> imglist;

    public FeatureRecylerAdapter( Context context,ArrayList<Integer> imglist) {

        this.context = context;
        this.imglist=imglist;

    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        ImageView featureimg;
        TextView textView;


        Spinner spinner;

        public MyViewHolder(View view) {
            super(view);
         featureimg=view.findViewById(R.id.picturesfeature);
            textView=view.findViewById(R.id.featuretext);

        }
    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.featuresimages, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {

        if(position==0)
        {
          holder.featureimg.setVisibility(View.GONE);
            holder.textView.setVisibility(View.VISIBLE);
            holder.textView.setText(Features.content.get(Features.counter));
        }

        else {


            holder.featureimg.setVisibility(View.VISIBLE);
            holder.textView.setVisibility(View.GONE);

            Picasso.with(context)

                    .load("android.resource://com.bizventure.androiddeveloper.aquatic_mall/"+imglist.get(position-1))


                    .into(holder.featureimg);

//            holder.featureimg.setImageResource(imglist.get(position-1));

        }

    }

    @Override
    public int getItemCount() {
        return imglist.size()+1;
    }


}