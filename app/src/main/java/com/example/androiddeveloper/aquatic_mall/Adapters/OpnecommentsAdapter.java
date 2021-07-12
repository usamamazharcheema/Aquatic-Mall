package com.example.androiddeveloper.aquatic_mall.Adapters;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Environment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.androiddeveloper.aquatic_mall.R;
import com.example.androiddeveloper.aquatic_mall.ResponseLists.Likeslist;
import com.example.androiddeveloper.aquatic_mall.ResponseLists.Opencomment;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by ANDROID DEVELOPER on 05/02/2018.
 */






public class OpnecommentsAdapter extends RecyclerView.Adapter<OpnecommentsAdapter.MyViewHolder> {



    Context context;
    ArrayList<Opencomment> newsresponseArrayList;
HashMap<String, Likeslist> likemap;
String ids;
    public OpnecommentsAdapter(Context context, ArrayList<Opencomment> newsresponseArrayList, HashMap<String, Likeslist> likemap, String ids) {

        this.context = context;
        this.newsresponseArrayList = newsresponseArrayList;
        this.likemap=likemap;
        this.ids=ids;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView name,details;
        public MyViewHolder(View view) {
            super(view);
            name=view.findViewById(R.id.name);

            details=view.findViewById(R.id.commentsdetail);
        }
    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.opencommentlayoutlist, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
holder.name.setText(newsresponseArrayList.get(position).getName().toString());
        holder.details.setText(newsresponseArrayList.get(position).getCommentsdetail().toString());
     /*   if(MainActivity.Logindtect.equals("logout"))
        {

        }
        else {
            if (likemap.size() == 0) {

            } else {



//                    try {
                        Likeslist likeslist = likemap.get(ids);
                        if (likeslist.getEmail().contains(MainActivity.checkemail)) {
                            Opnecomments.like();
                        } else {
                            Opnecomments.unlike();
                        }
//                    } catch (Exception e) {

//                    }



            }
        }*/
    }

    @Override
    public int getItemCount() {
        return newsresponseArrayList.size();
    }

    private void prepareShareIntent(Bitmap bmp,int position) {
        Uri bmpUri = getLocalBitmapUri(bmp); // see previous remote images section
        // Construct share intent as described above based on bitmap

        Intent shareIntent = new Intent();

        shareIntent.setAction(Intent.ACTION_SEND);

        shareIntent.putExtra(Intent.EXTRA_TEXT, "" );
        shareIntent.putExtra(Intent.EXTRA_STREAM, bmpUri);
        shareIntent.setType("image/*");
        shareIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        context.startActivity(Intent.createChooser(shareIntent, "Share Opportunity"));

    }
    private Uri getLocalBitmapUri(Bitmap bmp) {
        Uri bmpUri = null;
        File file = new File(context.getExternalFilesDir(Environment.DIRECTORY_PICTURES), "share_image_" + System.currentTimeMillis() + ".png");
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
}