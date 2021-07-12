package com.example.androiddeveloper.aquatic_mall.Adapters;

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

import com.example.androiddeveloper.aquatic_mall.Activities.Opnecomments;
import com.example.androiddeveloper.aquatic_mall.Interfaces.ApiClient;
import com.example.androiddeveloper.aquatic_mall.Interfaces.ManageResponse;
import com.example.androiddeveloper.aquatic_mall.MainActivity;
import com.example.androiddeveloper.aquatic_mall.R;
import com.example.androiddeveloper.aquatic_mall.ResponseLists.Likeslist;
import com.example.androiddeveloper.aquatic_mall.ResponseLists.MSG;
import com.example.androiddeveloper.aquatic_mall.ResponseLists.Newsresponse;
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






public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.MyViewHolder> {



    Context context;
   ArrayList<String>  ids;
    ArrayList<Newsresponse> newsresponseArrayList;
    HashMap<String, Likeslist> likemap;
    public NewsAdapter(ArrayList<String>  ids, Context context, ArrayList<Newsresponse> newsresponseArrayList, HashMap<String, Likeslist> likemap) {

        this.context = context;
        this.newsresponseArrayList = newsresponseArrayList;
        this.likemap=likemap;
        this.ids=ids;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView heading,details,sharepictext,likes,commentsnumber,commentsopen,addcomment;
        ImageView newsimg,likebtn;
        Button sendcommentbtn;
        RelativeLayout entercommentlayout;
        EditText entercomment;
LinearLayout hidddenfirst,hiddenafter,share;
ProgressBar commentingbar;
        Spinner spinner;

        public MyViewHolder(View view) {
            super(view);
            likebtn=view.findViewById(R.id.likebtn);
            entercommentlayout=view.findViewById(R.id.entercommentlayout);
            sendcommentbtn=view.findViewById(R.id.sendcomment);
share=view.findViewById(R.id.sharepic);
            addcomment=view.findViewById(R.id.addcomment);
            entercomment=view.findViewById(R.id.commententer);
            likes=view.findViewById(R.id.likes);
            commentsopen=view.findViewById(R.id.commentsopen);
            commentsnumber=view.findViewById(R.id.commentsnumber);
            sharepictext=view.findViewById(R.id.sharepictext);
            heading=view.findViewById(R.id.newsheadings);
            details=view.findViewById(R.id.newsdetails);
            newsimg=view.findViewById(R.id.newsimg);
hidddenfirst=view.findViewById(R.id.hiddenfirst);
hiddenafter=view.findViewById(R.id.hiddenafter);
spinner=view.findViewById(R.id.spinner);
            commentingbar=view.findViewById(R.id.progressBarloading);

        }
    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.newslist, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {

if(position==newsresponseArrayList.size())
{
    holder.hidddenfirst.setVisibility(View.VISIBLE);
    holder.hiddenafter.setVisibility(View.GONE);
}

else {
    List<String> categories = new ArrayList<String>();
    categories.add("    ");
    categories.add("save");
    categories.add("share");

    // Creating adapter for spinner
    ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(context, android.R.layout.simple_spinner_item, categories);

    // Drop down layout style - list view with radio button
    dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
    if(MainActivity.Logindtect.equals("logout"))
    {

    }
    else {
        if (likemap.size() == 0) {

        } else {
            if (ids.contains(newsresponseArrayList.get(position).getId())) {


                try {
                    Likeslist likeslist = likemap.get(newsresponseArrayList.get(position).getId());
                    if (likeslist.getEmail().contains(MainActivity.checkemail)) {
                        holder.likebtn.setImageResource(R.drawable.likebutton);
                    } else {
                        holder.likebtn.setImageResource(R.drawable.unlike);
                    }
                } catch (Exception e) {
                    Toast.makeText(context, "dad", Toast.LENGTH_SHORT).show();
                }


//            Toast.makeText(context, "" + newsresponseArrayList.get(position).getId(), Toast.LENGTH_SHORT).show();
            }
        }
    }
   /* if(likemap!=null) {
        try {
//            Toast.makeText(context, likemap.get(newsresponseArrayList.get(position).getId()).getEmail(), Toast.LENGTH_SHORT).show();
            Likeslist likeslist = likemap.get(newsresponseArrayList.get(position).getId());
            if (likeslist.getEmail().contains("qasimrathore999@gmail.com")) {
                holder.newsimg.setImageResource(R.drawable.likebutton);
            } else {
                holder.newsimg.setImageResource(R.drawable.unlike);
            }
        }
        catch (Exception e)
        {
            Toast.makeText(context, "dad", Toast.LENGTH_SHORT).show();
        }
    }*/
    // attaching data adapter to spinner
    holder.spinner.setAdapter(dataAdapter);
    holder.hidddenfirst.setVisibility(View.GONE);
    holder.hiddenafter.setVisibility(View.VISIBLE);
    holder.heading.setText(newsresponseArrayList.get(position).getHeading().toString());
    holder.details.setText(newsresponseArrayList.get(position).getDetail().toString());
    holder.likes.setText(newsresponseArrayList.get(position).getNumberlikes().toString());
    holder.commentsnumber.setText(newsresponseArrayList.get(position).getNumbercomments().toString());
    holder.addcomment.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if(MainActivity.Logindtect.equals("logout"))
            {
                Toast.makeText(context, "Please Login First", Toast.LENGTH_SHORT).show();
            }
            else {
                holder.addcomment.setVisibility(View.GONE);
                holder.entercommentlayout.setVisibility(View.VISIBLE);
            }

        }
    });
    final Target target;
    final Bitmap[] m = new Bitmap[1];
    target = new Target() {
        @Override
        public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
            m[0] =bitmap;
           holder.newsimg.setImageBitmap(bitmap);

        }

        @Override
        public void onBitmapFailed(Drawable errorDrawable) {
            Toast.makeText(context, "Loading Eror", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onPrepareLoad(Drawable placeHolderDrawable) {
        }
    };
    Picasso.with(context)
            .load("https://cpecintel.com/aquatic/news/php/imges/" + newsresponseArrayList.get(position).getImage().toString())


            .into(holder.newsimg);
    holder.likebtn.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            if(MainActivity.Logindtect.equals("logout"))
            {
                Toast.makeText(context, "Please Login First", Toast.LENGTH_SHORT).show();
            }
            else
            {

                holder.likebtn.setImageResource(R.drawable.likebutton);
//                holder.likes.setText(String.valueOf(newsresponseArrayList.get(position).getNumberlikes()+1));
            final ManageResponse service = ApiClient.getClient().create( ManageResponse.class);

            Call<MSG> userCall =service.InsertLikes(String.valueOf(newsresponseArrayList.get(position).getId()), MainActivity.checkemail);

            userCall.enqueue(new Callback<MSG>() {
                @Override
                public void onResponse(Call<MSG> call, Response<MSG> response) {
/*if (response.body().getSuccess()==1)
{
    *//*Newsresponse newsresponse=new Newsresponse();
    newsresponse.setDetail(newsresponseArrayList.get(position).getDetail());
    newsresponse.setHeading(newsresponseArrayList.get(position).getHeading());
    newsresponse.setId(newsresponseArrayList.get(position).getId());
    newsresponse.setImage(newsresponseArrayList.get(position).getImage());
    newsresponse.setNumbercomments(newsresponseArrayList.get(position).getNumbercomments());
    newsresponse.setNumberlikes(newsresponseArrayList.get(position).getNumberlikes()-1);

newsresponseArrayList.set(position,newsresponse);
holder.likes.setText(String.valueOf(newsresponseArrayList.get(position).getNumberlikes()));*//*
//    newsresponseArrayList.set(position,);

}*/
 if(response.body().getSuccess()==3) {

    Newsresponse newsresponse=new Newsresponse();
    newsresponse.setDetail(newsresponseArrayList.get(position).getDetail());
    newsresponse.setHeading(newsresponseArrayList.get(position).getHeading());
    newsresponse.setId(newsresponseArrayList.get(position).getId());
    newsresponse.setImage(newsresponseArrayList.get(position).getImage());
    newsresponse.setNumbercomments(newsresponseArrayList.get(position).getNumbercomments());
    newsresponse.setNumberlikes(newsresponseArrayList.get(position).getNumberlikes()+1);
holder.likebtn.setImageResource(R.drawable.likebutton);

    newsresponseArrayList.set(position,newsresponse);
    holder.likes.setText(String.valueOf(newsresponseArrayList.get(position).getNumberlikes()));
}
else if(response.body().getSuccess()==1)
 {
     /*Toast.makeText(context, ""+response.body().getMessage().toString(), Toast.LENGTH_SHORT).show();
     holder.likebtn.setImageResource(R.drawable.unlike);*/

 }
 else
 {
     Toast.makeText(context, "Connection error", Toast.LENGTH_SHORT).show();
     holder.likebtn.setImageResource(R.drawable.unlike);
 }

                }

                @Override
                public void onFailure(Call<MSG> call, Throwable t) {
                    try {
                        Toast.makeText(context, "Please check your internet connection .", Toast.LENGTH_SHORT).show();
                        holder.likebtn.setImageResource(R.drawable.unlike);
//                        holder.likes.setText(String.valueOf(newsresponseArrayList.get(position).getNumberlikes()));
                        Log.d("onFailure", t.toString());
                    }
                    catch (Exception e)
                    {

                    }
                }
            });
            }
        }
    });
holder.sendcommentbtn.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        holder.commentingbar.setVisibility(View.VISIBLE);
        final ManageResponse service = ApiClient.getClient().create( ManageResponse.class);

        Call<MSG> userCall =service.InsertComment(String.valueOf(newsresponseArrayList.get(position).getId()), MainActivity.checkemail, MainActivity.name,holder.entercomment.getText().toString(),newsresponseArrayList.get(position).getImage().toString(),newsresponseArrayList.get(position).getDetail().toString());

        userCall.enqueue(new Callback<MSG>() {
            @Override
            public void onResponse(Call<MSG> call, Response<MSG> response) {

                if(response.body().getSuccess() == 1) {

                    Newsresponse newsresponse=new Newsresponse();
                    newsresponse.setDetail(newsresponseArrayList.get(position).getDetail());
                    newsresponse.setHeading(newsresponseArrayList.get(position).getHeading());
                    newsresponse.setId(newsresponseArrayList.get(position).getId());
                    newsresponse.setImage(newsresponseArrayList.get(position).getImage());
                    newsresponse.setNumbercomments(newsresponseArrayList.get(position).getNumbercomments()+1);
                    newsresponse.setNumberlikes(newsresponseArrayList.get(position).getNumberlikes());
                    newsresponseArrayList.set(position,newsresponse);
                    holder.commentsnumber.setText(String.valueOf(newsresponseArrayList.get(position).getNumbercomments()));
                    holder.addcomment.setVisibility(View.VISIBLE);
                    holder.entercommentlayout.setVisibility(View.GONE);
                    holder.commentingbar.setVisibility(View.GONE);
                    holder.entercomment.setText("");
//                    startActivity(new Intent(LoginActivity.this, MainActivity.class));
                    Intent i=new Intent(context, Opnecomments.class);
                    i.putExtra("newsid",newsresponseArrayList.get(position).getId());
                    context.startActivity(i);

                }else {
                    Toast.makeText(context, "" + response.body().getMessage(), Toast.LENGTH_SHORT).show();
                    holder.addcomment.setVisibility(View.VISIBLE);
                    holder.entercommentlayout.setVisibility(View.GONE);
                    holder.commentingbar.setVisibility(View.GONE);
                    holder.entercomment.setText("");
                }
            }

            @Override
            public void onFailure(Call<MSG> call, Throwable t) {
                try {
                    Toast.makeText(context, "Please Check Yout Internet connection", Toast.LENGTH_SHORT).show();
                    holder.addcomment.setVisibility(View.VISIBLE);
                    holder.entercommentlayout.setVisibility(View.GONE);
                    holder.commentingbar.setVisibility(View.GONE);
                    holder.entercomment.setText("");
                    Log.d("onFailure", t.toString());
                }
                catch (Exception e)
                {

                }
            }
        });
    }
});
   holder.spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
       @Override
       public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
           String item = adapterView.getItemAtPosition(i).toString();
if(item.equals("share"))
{
    try {
        BitmapDrawable drawable = (BitmapDrawable) holder.newsimg.getDrawable();
        Bitmap bitmap = drawable.getBitmap();
        prepareShareIntent(bitmap, position);
    }
    catch (Exception e)
    {

    }
}
else if(item.equals("save")) {
    try
    {
    ByteArrayOutputStream bytearrayoutputstream;
    File file;
    FileOutputStream fileoutputstream;
    bytearrayoutputstream = new ByteArrayOutputStream();
    BitmapDrawable drawable = (BitmapDrawable) holder.newsimg.getDrawable();
    Bitmap bitmap = drawable.getBitmap();
    bitmap.compress(Bitmap.CompressFormat.PNG, 60, bytearrayoutputstream);

    file = new File(Environment.getExternalStorageDirectory() + "/Aquatic Mall" + "/" + newsresponseArrayList.get(position).getImage().toString());

    try

    {
        file.createNewFile();

        fileoutputstream = new FileOutputStream(file);

        fileoutputstream.write(bytearrayoutputstream.toByteArray());

        fileoutputstream.close();

    } catch (Exception e)

    {

        e.printStackTrace();

    }

    Toast.makeText(context, "Image Saved Successfully", Toast.LENGTH_LONG).show();
}
catch (Exception e)
           {
           }
}
         /*  // Showing selected spinner item
           Toast.makeText(adapterView.getContext(), "Selected: " + item, Toast.LENGTH_LONG).show();*/

       }

       @Override
       public void onNothingSelected(AdapterView<?> adapterView) {

       }
   });
   /* holder.share.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            BitmapDrawable drawable = (BitmapDrawable) holder.newsimg.getDrawable();
            Bitmap bitmap = drawable.getBitmap();
            prepareShareIntent(bitmap,position);
        }
    });*/
   holder.commentsopen.setOnClickListener(new View.OnClickListener() {
       @Override
       public void onClick(View v) {
           Intent i=new Intent(context, Opnecomments.class);
           i.putExtra("newsid",newsresponseArrayList.get(position).getId());
           context.startActivity(i);
       }
   });
    holder.newsimg.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent i=new Intent(context, Opnecomments.class);
            i.putExtra("newsid",newsresponseArrayList.get(position).getId());
            context.startActivity(i);
        }
    });
    holder.sharepictext.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            BitmapDrawable drawable = (BitmapDrawable) holder.newsimg.getDrawable();
            Bitmap bitmap = drawable.getBitmap();
            prepareShareIntent(bitmap,position);
        }
    });
}
    }

    @Override
    public int getItemCount() {
        return newsresponseArrayList.size()+1;
    }

    private void prepareShareIntent(Bitmap bmp,int position) {
        Uri bmpUri = getLocalBitmapUri(bmp); // see previous remote images section
        // Construct share intent as described above based on bitmap

        Intent shareIntent = new Intent();

        shareIntent.setAction(Intent.ACTION_SEND);

        shareIntent.putExtra(Intent.EXTRA_TEXT, newsresponseArrayList.get(position).getDetail().toString()  );
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