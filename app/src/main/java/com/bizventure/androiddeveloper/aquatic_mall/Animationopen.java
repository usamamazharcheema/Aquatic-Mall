package com.bizventure.androiddeveloper.aquatic_mall;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.cunoraz.gifview.library.GifView;
import com.example.androiddeveloper.aquatic_mall.Activities.Trendiing;
import com.example.androiddeveloper.aquatic_mall.Adapters.CustomPagerAdapter;
import com.example.androiddeveloper.aquatic_mall.Interfaces.ApiClient;
import com.example.androiddeveloper.aquatic_mall.Interfaces.ManageResponse;
import com.example.androiddeveloper.aquatic_mall.R;
import com.example.androiddeveloper.aquatic_mall.ResponseLists.NewsResponseLists;
import com.example.androiddeveloper.aquatic_mall.ResponseLists.Newsresponse;
import com.example.androiddeveloper.aquatic_mall.ResponseLists.TrendingLists;
import com.example.androiddeveloper.aquatic_mall.ResponseLists.Trendingresponse;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Animationopen extends AppCompatActivity{
TextView heading,details;
ImageView newsimg;
    Target target;
    Bitmap m;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animationopen);
heading=findViewById(R.id.newsheadings);
details=findViewById(R.id.newsdetails);
newsimg=findViewById(R.id.newsimg);

        target = new Target() {
            @Override
            public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
m=bitmap;
                newsimg.setImageBitmap(bitmap);

            }

            @Override
            public void onBitmapFailed(Drawable errorDrawable) {
                Toast.makeText(Animationopen.this, "Loading Eror", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onPrepareLoad(Drawable placeHolderDrawable) {
            }
        };
        getnews("");
    }

    @Override
    public void onBackPressed() {

        super.onBackPressed();
        overridePendingTransition( R.anim.backpressedlayout, R.anim.layoutin );

    }
    public void getnews(final String mystring)
    {

        final ManageResponse service = ApiClient.getClient().create( ManageResponse.class);

        Call<NewsResponseLists> userCall =service.GetNews(1,1);

        userCall.enqueue(new Callback<NewsResponseLists>() {
            @Override
            public void onResponse(Call<NewsResponseLists> call, Response<NewsResponseLists> response) {

                ArrayList<Newsresponse> newsresponselist=(ArrayList<Newsresponse>) response.body().getNewsresponse();
                if(newsresponselist!=null)
                {
                    for(int c=0;c<newsresponselist.size();c++)
                    {
                      heading.setText(newsresponselist.get(c).getHeading().toString());
                      details.setText(newsresponselist.get(c).getDetail().toString());
                        Picasso.with(Animationopen.this)
                                .load("https://cpecintel.com/aquatic/news/php/imges/"+newsresponselist.get(c).getImage().toString())


                                .into(target);
                    }
                }
            }

            @Override
            public void onFailure(Call<NewsResponseLists> call, Throwable t) {
                try {
                    Toast.makeText(Animationopen.this, "Failure", Toast.LENGTH_SHORT).show();
                    Log.d("onFailure", t.toString());
                }
                catch (Exception e)
                {

                }
            }
        });


    }
    private void prepareShareIntent(Bitmap bmp) {
        Uri bmpUri = getLocalBitmapUri(bmp); // see previous remote images section
        // Construct share intent as described above based on bitmap

      Intent shareIntent = new Intent();

        shareIntent.setAction(Intent.ACTION_SEND);

        shareIntent.putExtra(Intent.EXTRA_TEXT, ""  );
        shareIntent.putExtra(Intent.EXTRA_STREAM, bmpUri);
        shareIntent.setType("image/*");
        shareIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        startActivity(Intent.createChooser(shareIntent, "Share Opportunity"));

    }
    private Uri getLocalBitmapUri(Bitmap bmp) {
        Uri bmpUri = null;
        File file = new File(getExternalFilesDir(Environment.DIRECTORY_PICTURES), "share_image_" + System.currentTimeMillis() + ".png");
        FileOutputStream out = null;
        try {
            out = new FileOutputStream(file);
            bmp.compress(Bitmap.CompressFormat.PNG, 90, out);
            try {
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            bmpUri = Uri.fromFile(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return bmpUri;
    }
    public void share(View v)
    {
        prepareShareIntent(m);
    }
}
