package com.example.androiddeveloper.aquatic_mall.Activities;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.androiddeveloper.aquatic_mall.Adapters.OpnecommentsAdapter;
import com.example.androiddeveloper.aquatic_mall.Interfaces.ApiClient;
import com.example.androiddeveloper.aquatic_mall.Interfaces.ManageResponse;
import com.example.androiddeveloper.aquatic_mall.MainActivity;
import com.example.androiddeveloper.aquatic_mall.R;
import com.example.androiddeveloper.aquatic_mall.ResponseLists.LikesResponseList;
import com.example.androiddeveloper.aquatic_mall.ResponseLists.Likeslist;
import com.example.androiddeveloper.aquatic_mall.ResponseLists.MSG;
import com.example.androiddeveloper.aquatic_mall.ResponseLists.Opencomment;
import com.example.androiddeveloper.aquatic_mall.ResponseLists.OpencommentsList;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by ANDROID DEVELOPER on 14/02/2018.
 */

public class Opnecomments extends Activity {

    String newsid;
    ImageView img,iconmain;
    TextView likesnumber,commentsnumber,detail;
    LinearLayout mainlayout;
    ArrayList<Opencomment> newsresponseArrayList=new ArrayList<>();
    RecyclerView newrecycler;
    OpnecommentsAdapter newsAdapter;
    public  static ImageView likebtn;
    Button sendcommentbtn;
    RelativeLayout entercommentlayout;
    EditText entercomment;
    LinearLayout hidddenfirst,hiddenafter,share;
    ProgressBar commentingbar;
    HashMap<String, Likeslist> likemap = new HashMap<String, Likeslist>();
    int likescounter=0;
    ArrayList<String>  ids=new ArrayList<>();
    ArrayList<Opencomment> opencomments=new ArrayList<>();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.opencomments);
mainlayout=findViewById(R.id.mainlayout);
        likebtn=findViewById(R.id.likebtn);
        entercommentlayout=findViewById(R.id.entercommentlayout);
        sendcommentbtn=findViewById(R.id.sendcomment);
        commentingbar=findViewById(R.id.progressBarloading);
        entercomment=findViewById(R.id.commententer);
        iconmain=findViewById(R.id.iconmain);
        Bundle bundle=getIntent().getExtras();
        newsid=bundle.getString("newsid");
        newrecycler = (RecyclerView) findViewById(R.id.opencomments);
        newsAdapter=new OpnecommentsAdapter(this,newsresponseArrayList,likemap,newsid);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
        newrecycler.setLayoutManager(mLayoutManager);
        newrecycler.setItemAnimator(new DefaultItemAnimator());
        newrecycler.setAdapter(newsAdapter);
        newsAdapter.notifyDataSetChanged();
        if(MainActivity.Logindtect.equals("logout")) {
            RelativeLayout relativeLayout=findViewById(R.id.entercommentlayout);
            relativeLayout.setVisibility(View.GONE);
        }
        else {

        }
        likebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(MainActivity.Logindtect.equals("logout"))
                {
                    Toast.makeText(Opnecomments.this, "Please Login First", Toast.LENGTH_SHORT).show();
                }
                else
                {

                   likebtn.setImageResource(R.drawable.likebutton);

                    final ManageResponse service = ApiClient.getClient().create( ManageResponse.class);

                    Call<MSG> userCall =service.InsertLikes(newsid, MainActivity.checkemail);

                    userCall.enqueue(new Callback<MSG>() {
                        @Override
                        public void onResponse(Call<MSG> call, Response<MSG> response) {

                            if(response.body().getSuccess()==3) {

                               /* Newsresponse newsresponse=new Newsresponse();
                                newsresponse.setDetail(newsresponseArrayList.get(position).getDetail());
                                newsresponse.setHeading(newsresponseArrayList.get(position).getHeading());
                                newsresponse.setId(newsresponseArrayList.get(position).getId());
                                newsresponse.setImage(newsresponseArrayList.get(position).getImage());
                                newsresponse.setNumbercomments(newsresponseArrayList.get(position).getNumbercomments());
                                newsresponse.setNumberlikes(newsresponseArrayList.get(position).getNumberlikes()+1);
                                holder.likebtn.setImageResource(R.drawable.likebutton);

                                newsresponseArrayList.set(position,newsresponse);*/
                                likesnumber.setText(String.valueOf(Integer.parseInt(likesnumber.getText().toString()+1)));
                            }
                            else if(response.body().getSuccess()==1)
                            {
     /*Toast.makeText(context, ""+response.body().getMessage().toString(), Toast.LENGTH_SHORT).show();
     holder.likebtn.setImageResource(R.drawable.unlike);*/

                            }
                            else
                            {
                                Toast.makeText(Opnecomments.this, "Connection error", Toast.LENGTH_SHORT).show();
                                likebtn.setImageResource(R.drawable.unlike);
                            }

                        }

                        @Override
                        public void onFailure(Call<MSG> call, Throwable t) {
                            try {
                                Toast.makeText(Opnecomments.this, "Please check your internet connection .", Toast.LENGTH_SHORT).show();
                                likebtn.setImageResource(R.drawable.unlike);
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
        sendcommentbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               commentingbar.setVisibility(View.VISIBLE);
                final ManageResponse service = ApiClient.getClient().create( ManageResponse.class);

                Call<MSG> userCall =service.InsertComment(String.valueOf(newsid), MainActivity.checkemail, MainActivity.name,entercomment.getText().toString(),opencomments.get(0).getImg().toString(),opencomments.get(0).getDetail().toString());

                userCall.enqueue(new Callback<MSG>() {
                    @Override
                    public void onResponse(Call<MSG> call, Response<MSG> response) {

                        if(response.body().getSuccess() == 1) {




                            Opencomment newsresponse=new Opencomment();
                            newsresponse.setDetail(newsresponseArrayList.get(0).getDetail());
                            newsresponse.setEmail(newsresponseArrayList.get(0).getEmail());
                            newsresponse.setId(newsresponseArrayList.get(0).getId());
                            newsresponse.setImg(newsresponseArrayList.get(0).getImg());
                         newsresponse.setCommentsdetail(entercomment.getText().toString());
                            newsresponse.setName(MainActivity.name);
                            newsresponseArrayList.add(newsresponse);
                            newsAdapter.notifyDataSetChanged();
                            commentsnumber.setText(String.valueOf(Integer.parseInt(commentsnumber.getText().toString())+1));

                            commentingbar.setVisibility(View.GONE);
                            entercomment.setText("");

                        }else {
                            Toast.makeText(Opnecomments.this, "" + response.body().getMessage(), Toast.LENGTH_SHORT).show();

                          commentingbar.setVisibility(View.GONE);

                        }
                    }

                    @Override
                    public void onFailure(Call<MSG> call, Throwable t) {
                        try {
                            Toast.makeText(Opnecomments.this, "Please Check Yout Internet connection", Toast.LENGTH_SHORT).show();

                           commentingbar.setVisibility(View.GONE);

                            Log.d("onFailure", t.toString());
                        }
                        catch (Exception e)
                        {

                        }
                    }
                });
            }
        });
        getnews(newsid);

    }



    public void getnews(final String mystring)
    {

        final ManageResponse service = ApiClient.getClient().create( ManageResponse.class);

        Call<OpencommentsList> userCall =service.GetOpneComments(mystring);

        userCall.enqueue(new Callback<OpencommentsList>() {
            @Override
            public void onResponse(Call<OpencommentsList> call, Response<OpencommentsList> response) {

                ArrayList<Opencomment> newsresponselist=(ArrayList<Opencomment>) response.body().getOpencomments();
                if(newsresponselist!=null)
                {
                    for(int c=0;c<newsresponselist.size();c++)
                    {
                        if(c==0)
                        {
                            opencomments.add(newsresponselist.get(c));
                            detail=findViewById(R.id.newsdetails);
                            detail.setText(newsresponselist.get(c).getDetail().toString());
                            commentsnumber=findViewById(R.id.commentsnumber);

                            likesnumber=findViewById(R.id.likes);
                            likesnumber.setText(String.valueOf(newsresponselist.get(c).getNumberlikes()));

                            img=findViewById(R.id.newsimg);
                            getlikes(newsid);
                            Picasso.with(Opnecomments.this)
                                    .load("https://cpecintel.com/aquatic/news/php/imges/" + newsresponselist.get(c).getImg().toString())


                                    .into(img);

                        }
                        if(newsresponselist.size()-c==1)
                        {
                            if (newsresponselist.size()==1) {
                                commentsnumber.setText("0");
                            }
                            else
                            {
                                commentsnumber.setText(String.valueOf(newsresponselist.size()));
                            }
                        }
                        newsresponseArrayList.add(newsresponselist.get(c));
                        newsAdapter.notifyDataSetChanged();

                    }
                }
            }

            @Override
            public void onFailure(Call<OpencommentsList> call, Throwable t) {
                try {
                    Toast.makeText(Opnecomments.this, "Please Check Your Internet Connection", Toast.LENGTH_SHORT).show();
                    Log.d("onFailure", t.toString());
                }
                catch (Exception e)
                {

                }
            }
        });


    }
    public void getlikes(final String mystring)
    {

        final ManageResponse service = ApiClient.getClient().create( ManageResponse.class);

        Call<LikesResponseList> userCall =service.GetLikes(mystring);

        userCall.enqueue(new Callback<LikesResponseList>() {
            @Override
            public void onResponse(Call<LikesResponseList> call, Response<LikesResponseList> response) {
                if(response.isSuccessful()) {
                    ArrayList<Likeslist> newsresponselist = (ArrayList<Likeslist>) response.body().getLikeslist();
                    if(newsresponselist.isEmpty()) {
                        newsAdapter.notifyDataSetChanged();
                        iconmain.setVisibility(View.GONE);
                        mainlayout.setVisibility(View.VISIBLE);
                    }
                    else
                    {
                        ids.add(mystring);

                        if (newsresponselist != null) {
                            for (int c = 0; c < newsresponselist.size(); c++) {
                                likemap.put(mystring, newsresponselist.get(c));
                                newsAdapter.notifyDataSetChanged();
                                iconmain.setVisibility(View.GONE);
                                mainlayout.setVisibility(View.VISIBLE);
//                                Toast.makeText(Opnecomments.this, "success", Toast.LENGTH_SHORT).show();

                            }
                        }
                    }
                }

            }

            @Override
            public void onFailure(Call<LikesResponseList> call, Throwable t) {
                try {
                    Toast.makeText(Opnecomments.this, "Please Check Your Internet Connection", Toast.LENGTH_SHORT).show();
                    Log.d("onFailure", t.toString());
                }
                catch (Exception e)
                {

                }
            }
        });


    }
    public static void unlike()
    {
        likebtn.setImageResource(R.drawable.likebutton);
    }
    public static void like()
    {
        likebtn.setImageResource(R.drawable.unlike);
    }
}
