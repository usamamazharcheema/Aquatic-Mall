package com.example.androiddeveloper.aquatic_mall.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.androiddeveloper.aquatic_mall.Adapters.NewsAdapter;
import com.example.androiddeveloper.aquatic_mall.Interfaces.ApiClient;
import com.example.androiddeveloper.aquatic_mall.Interfaces.ManageResponse;
import com.example.androiddeveloper.aquatic_mall.MainActivity;
import com.example.androiddeveloper.aquatic_mall.R;
import com.example.androiddeveloper.aquatic_mall.ResponseLists.LikesResponseList;
import com.example.androiddeveloper.aquatic_mall.ResponseLists.Likeslist;
import com.example.androiddeveloper.aquatic_mall.ResponseLists.MSG;
import com.example.androiddeveloper.aquatic_mall.ResponseLists.NewsResponseLists;
import com.example.androiddeveloper.aquatic_mall.ResponseLists.Newsresponse;
import com.example.androiddeveloper.aquatic_mall.ResponseLists.StoriesResponseList;
import com.example.androiddeveloper.aquatic_mall.ResponseLists.Storiesresponse;
import com.example.androiddeveloper.aquatic_mall.StatusActivity;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by ANDROID DEVELOPER on 06/02/2018.
 */

public class LatestNews extends Fragment {
    ArrayList<Newsresponse> newsresponseArrayList=new ArrayList<>();
    ArrayList<Likeslist> likeslists=new ArrayList<>();
    ArrayList<String>  ids=new ArrayList<>();
    RecyclerView newrecycler;
    NewsAdapter newsAdapter;
    ImageView icon,imgback;
    LinearLayout newslayout,layoutback;
    int newesnumbers=10;
    int idsnumbers=0;
    boolean isCacheEnabled = true;
    boolean isImmersiveEnabled = true;
    boolean isTextEnabled = false;
    long storyDuration = 3500L;
    ArrayList<String> resources= new ArrayList<String>();;
    ArrayList<String> duration = new ArrayList<String>();
    ArrayList<String> idss = new ArrayList<String>();
    HashMap<String, Likeslist> likemap = new HashMap<String, Likeslist>();
int likescounter=0;
ImageView stories_btn;
    public static LatestNews newInstance(String movieTitle) {
        LatestNews fragmentAction = new LatestNews();
        Bundle args = new Bundle();
        args.putString(" ", movieTitle);
        fragmentAction.setArguments(args);

        return fragmentAction;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.latestnews, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        MainActivity.chaticonmain();
try {
    File f = new File(Environment.getExternalStorageDirectory() + "/Aquatic Mall");
    if (f.isDirectory()) {

    } else {
        File folder = new File(Environment.getExternalStorageDirectory() +
                File.separator + "Aquatic Mall");
        boolean success = true;
        if (!folder.exists()) {
            success = folder.mkdirs();
        }
        if (success) {
            // Do something on success
        } else {
            // Do something else on failure
        }
    }

}
catch (Exception e)
{

}

imgback=view.findViewById(R.id.backlatest);
stories_btn=view.findViewById(R.id.stories_btn);
layoutback=view.findViewById(R.id.layoutbacklatest);
        icon=view.findViewById(R.id.homeicon);
        newslayout=view.findViewById(R.id.newslayout);
        newrecycler = (RecyclerView) view.findViewById(R.id.latestnews);
        newsAdapter=new NewsAdapter(ids,getActivity(),newsresponseArrayList,likemap);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        newrecycler.setLayoutManager(mLayoutManager);
        newrecycler.setItemAnimator(new DefaultItemAnimator());
        newrecycler.setAdapter(newsAdapter);
        newsAdapter.notifyDataSetChanged();
        imgback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainActivity.backpressed();
            }
        });
        layoutback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainActivity.backpressed();
            }
        });
        recallmethod(1);
        stories_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(resources.equals("")){
                    Toast.makeText(getActivity(), "server error", Toast.LENGTH_SHORT).show();
                }
                else {
                    Intent a = new Intent(getActivity(), StatusActivity.class);
                    a.putStringArrayListExtra(StatusActivity.STATUS_RESOURCES_KEY, resources);
                    a.putExtra(StatusActivity.STATUS_Video_DURATION_KEY, duration);
                    a.putExtra(StatusActivity.ids_key, idss);
                    a.putExtra(StatusActivity.STATUS_DURATION_KEY, storyDuration);
                    a.putExtra(StatusActivity.IS_IMMERSIVE_KEY, isImmersiveEnabled);
                    a.putExtra(StatusActivity.IS_CACHING_ENABLED_KEY, isCacheEnabled);
                    a.putExtra(StatusActivity.IS_TEXT_PROGRESS_ENABLED_KEY, isTextEnabled);
                    startActivity(a);
                }
            }
        });
        getnewsrows();


    }
    public void getnews(final int rownumbers)
    {

        final ManageResponse service = ApiClient.getClient().create( ManageResponse.class);

        Call<NewsResponseLists> userCall =service.GetNews(idsnumbers,rownumbers);

        userCall.enqueue(new Callback<NewsResponseLists>() {
            @Override
            public void onResponse(Call<NewsResponseLists> call, Response<NewsResponseLists> response) {

                ArrayList<Newsresponse> newsresponselist=(ArrayList<Newsresponse>) response.body().getNewsresponse();
                if(newsresponselist!=null)
                {
                    for(int c=0;c<newsresponselist.size();c++)
                    {
                        newsresponseArrayList.add(newsresponselist.get(c));
                        if(newsresponselist.size()-c==1) {



                            getlikes(newsresponseArrayList.get(likescounter).getId());
                            likescounter=likescounter+1;
                     //       newsAdapter.notifyDataSetChanged();
                        }
                    }
                }

            }

            @Override
            public void onFailure(Call<NewsResponseLists> call, Throwable t) {
                try {
                    Toast.makeText(getActivity(), "Please Check Your Internet Connection", Toast.LENGTH_SHORT).show();
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
                      if (likescounter < newsresponseArrayList.size()) {

//                          Toast.makeText(getActivity(), "check", Toast.LENGTH_SHORT).show();
                          getlikes(newsresponseArrayList.get(likescounter).getId());
                          likescounter = likescounter + 1;
                      }
                      else {
//                          Toast.makeText(getActivity(), "uncheck", Toast.LENGTH_SHORT).show();
                          icon.setVisibility(View.GONE);
                          newslayout.setVisibility(View.VISIBLE);
                          stories_btn.setVisibility(View.VISIBLE);
                          newsAdapter.notifyDataSetChanged();
                          if(newesnumbers>0) {
                              if (newesnumbers > 10) {
                                  newesnumbers = newesnumbers - 10;
                                  idsnumbers = idsnumbers + 10;
                                  getnews(10);

                              } else {
                                  newesnumbers = newesnumbers - 10;
                                  idsnumbers = idsnumbers + 10;
                                  getnews(newesnumbers);
                              }
                          }
                      }
                  }
                  else
                  {
                      ids.add(mystring);

                      if (newsresponselist != null) {
                          for (int c = 0; c < newsresponselist.size(); c++) {
                              likemap.put(mystring, newsresponselist.get(c));
//                        likeslists.add(newsresponselist.get(c));
                              if (newsresponselist.size() - c == 1) {
                                  if (likescounter < newsresponseArrayList.size()) {

//                                      Toast.makeText(getActivity(), "check", Toast.LENGTH_SHORT).show();
                                      getlikes(newsresponseArrayList.get(likescounter).getId());
                                      likescounter = likescounter + 1;
                                  } else {
//                                      Toast.makeText(getActivity(), "uncheck", Toast.LENGTH_SHORT).show();
                                      icon.setVisibility(View.GONE);
                                      newslayout.setVisibility(View.VISIBLE);
                                      stories_btn.setVisibility(View.VISIBLE);
                                      newsAdapter.notifyDataSetChanged();
                                      if(newesnumbers>0) {
                                          if (newesnumbers > 10) {
                                              newesnumbers = newesnumbers - 10;
                                              idsnumbers = idsnumbers + 10;
                                              getnews(10);

                                          } else {
                                              newesnumbers = newesnumbers - 10;
                                              idsnumbers = idsnumbers + 10;
                                              getnews(newesnumbers);
                                          }
                                      }
                                  }
                              }
//                        icon.setVisibility(View.GONE);
//                        newslayout.setVisibility(View.VISIBLE);
//                        newsresponseArrayList.add(newsresponselist.get(c));
//                        newsAdapter.notifyDataSetChanged();
                          }
                      }
                  }
                }
/*if(response.body()!=null) {

}
                else
                {
                    icon.setVisibility(View.GONE);
                    newslayout.setVisibility(View.VISIBLE);
                    newsAdapter.notifyDataSetChanged();
                }*/
            }

            @Override
            public void onFailure(Call<LikesResponseList> call, Throwable t) {
                try {
                    Toast.makeText(getActivity(), "Please Check Your Internet Connection", Toast.LENGTH_SHORT).show();
                    Log.d("onFailure", t.toString());
                }
                catch (Exception e)
                {

                }
            }
        });


    }


    public void getnewsrows()
    {

        ManageResponse apiService = ApiClient.getClient().create(ManageResponse.class);
        Call<MSG> call = apiService.getnotification("https://cpecintel.com/aquatic/news/php/numberofrowsinnews.php");
        call.enqueue(new Callback<MSG>() {
            @Override
            public void onResponse(Call<MSG> call, Response<MSG> response) {
                int statusCode = response.code();

//                Toast.makeText(MainActivity.this, ""+response.body().getSuccess(), Toast.LENGTH_SHORT).show();

                if (response.body()!=null) {
//                    Toast.makeText(MainActivity.this, ""+response.body().getSuccess(), Toast.LENGTH_SHORT).show();
                    newesnumbers=response.body().getSuccess();
if(newesnumbers>10) {
    getnews(10);
    newesnumbers=newesnumbers-10;
}
else {
    getnews(newesnumbers);
    newesnumbers=newesnumbers-10;
}
                }
            }
            @Override
            public void onFailure(Call<MSG> call, Throwable t) {
                getnewsrows();
            }
        });


    }

    public void recallmethod(int j)
    {
//        statusResources.clear();
        ManageResponse service = ApiClient.getClient().create( ManageResponse.class);
        Call<StoriesResponseList> userCall =service.stories(j);

        userCall.enqueue(new Callback<StoriesResponseList>() {
            @Override
            public void onResponse(Call<StoriesResponseList> call, Response<StoriesResponseList> response) {
                ArrayList<Storiesresponse> mainview = (ArrayList<Storiesresponse>) response.body().getResults();
                if (mainview!=null) {

                    for (int i = 0; i < mainview.size(); i = i + 1) {
                        resources.add("https://cpecintel.com/aquatic/news/php/imges/" +mainview.get(i).getS_images().toString());
                        duration.add(mainview.get(i).getDuration().toString());
//                        idss.add(mainview.get(i).getId());

                    }

                }

            }

            @Override
            public void onFailure(Call<StoriesResponseList> call, Throwable t) {
//                Toast.makeText(MainActivity.this, "check internet connection",Toast.LENGTH_SHORT).show();
            }
        });
    }



   /* public void back (View v)
    {
        super.onBackPressed();
        overridePendingTransition( R.anim.backpressedlayout, R.anim.lefttoright );
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition( R.anim.backpressedlayout, R.anim.lefttoright );
    }*/
}
