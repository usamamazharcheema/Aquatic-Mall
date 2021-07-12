package com.example.androiddeveloper.aquatic_mall.fragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.androiddeveloper.aquatic_mall.Adapters.ChatAdapter;
import com.example.androiddeveloper.aquatic_mall.Adapters.ExclusiveRecylerAdapter;
import com.example.androiddeveloper.aquatic_mall.Interfaces.ApiClient;
import com.example.androiddeveloper.aquatic_mall.Interfaces.ManageResponse;
import com.example.androiddeveloper.aquatic_mall.MainActivity;
import com.example.androiddeveloper.aquatic_mall.R;
import com.example.androiddeveloper.aquatic_mall.ResponseLists.Getchatresponse;
import com.example.androiddeveloper.aquatic_mall.ResponseLists.GetchatresponseList;
import com.example.androiddeveloper.aquatic_mall.ResponseLists.MSG;
import com.example.androiddeveloper.aquatic_mall.ResponseLists.Newsresponse;
import com.example.androiddeveloper.aquatic_mall.database.DtabaeHelper;
import com.google.firebase.iid.FirebaseInstanceId;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by ANDROID DEVELOPER on 14/03/2018.
 */





public class ChatFragment extends Fragment {

RecyclerView chatrecycler;
ChatAdapter chatAdapter;
ImageView back;
LinearLayout backlay;
LinearLayout scrolllayout;
Button sendmessage;
View mainview;
EditText messagefield;
    SharedPreferences sharedpreferences ;
    String mypreference = "guestmessagepreference";
    String message = "guestmessage";
TextView chatheader;
    ProgressBar commentingbar,mainprogress;
    DtabaeHelper mydb;
ScrollView scrollView;
LinearLayout commentlayout;
ArrayList<Getchatresponse> getchatresponses=new ArrayList<>();
    public static ChatFragment newInstance(String movieTitle) {
        ChatFragment fragmentAction = new ChatFragment();
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
        return inflater.inflate(R.layout.layoutchat, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
mainview=view;
        SharedPreferences sharedpreferencesuser ;
        String mypreferencecheck = "checkuserrfragment";
        String status = "usercondition";
        sharedpreferencesuser = getActivity().getSharedPreferences(mypreferencecheck,
                Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedpreferencesuser.edit();
        editor.putString(status, "yes");

        editor.commit();


scrolllayout=view.findViewById(R.id.scollllayout);
scrollView=view.findViewById(R.id.scroll);
        back=view.findViewById(R.id.chatbackimg);
        backlay=view.findViewById(R.id.chatback);
        sendmessage=view.findViewById(R.id.sendmessage);
        messagefield=view.findViewById(R.id.messagefield);
        commentingbar=view.findViewById(R.id.progressBarloading);
        mainprogress=view.findViewById(R.id.mainprogress);
        chatheader=view.findViewById(R.id.chatheader);
        commentlayout=view.findViewById(R.id.bottonerite);
//        chatrecycler = (RecyclerView) view.findViewById(R.id.chatrecyler);
        /*chatAdapter=new ChatAdapter(getActivity(),getchatresponses);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        chatrecycler.setLayoutManager(mLayoutManager);
        chatrecycler.setItemAnimator(new DefaultItemAnimator());
        chatrecycler.setAdapter(chatAdapter);
        chatAdapter.notifyDataSetChanged();*/
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.backpressed();
            }
        });
        backlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.backpressed();
            }
        });
        chatheader.setText("Admin");
        sendmessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(messagefield.getText().toString().equals(""))
                {

                }
                else {
                    commentingbar.setVisibility(View.VISIBLE);
                    sendmessageuser(MainActivity.checkemail,MainActivity.name,messagefield.getText().toString(),FirebaseInstanceId.getInstance().getToken(),"user");
                }
            }
        });
      //  Toast.makeText(getActivity(), ""+MainActivity.checkemail, Toast.LENGTH_SHORT).show();
        getchat(MainActivity.checkemail);
        new LongOperation().execute("MSG");
    }
    public void sendmessageuser(String email,String name,String message,String token,String sender)
    {
        ManageResponse service = ApiClient.getClient().create(ManageResponse.class);

        Call<MSG> userCall = service.sendmessageguest(email,name,message,token,sender);

        userCall.enqueue(new Callback<MSG>() {
            @Override
            public void onResponse(Call<MSG> call, Response<MSG> response) {

if(response.body().getSuccess()==1)
{

    Getchatresponse getchatresponsenew=new Getchatresponse();
    getchatresponsenew.setMessage(messagefield.getText().toString());
    getchatresponsenew.setSender("user");
    LinearLayout.LayoutParams lparams = new LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
    LayoutInflater vi = (LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    View v = vi.inflate(R.layout.chatuserlayout, null);
    TextView tv=v.findViewById(R.id.sender);
    tv.setLayoutParams(lparams);
    tv.setText(getchatresponsenew.getMessage().toString());
    scrolllayout.addView(v);
scrollView.fullScroll(ScrollView.FOCUS_DOWN);
    commentingbar.setVisibility(View.GONE);
  messagefield.setText("");
}
else
{
    Toast.makeText(getActivity(), "Please check your internet connection", Toast.LENGTH_SHORT).show();
    sendmessageuser(MainActivity.checkemail,MainActivity.name,messagefield.getText().toString(),FirebaseInstanceId.getInstance().getToken(),"user");
}



            }

            @Override
            public void onFailure(Call<MSG> call, Throwable t) {
            /*try {
                Toast.makeText(getContext(), "You don't have internet connection", Toast.LENGTH_SHORT).show();
                Log.d("onFailure", t.toString());
            } catch (Exception e) {
                e.printStackTrace();
            }*/
                sendmessageuser(MainActivity.checkemail,MainActivity.name,messagefield.getText().toString(),FirebaseInstanceId.getInstance().getToken(),"user");        }
        });

    }
    public void getchat(String email)
    {
        ManageResponse service = ApiClient.getClient().create(ManageResponse.class);

        Call<GetchatresponseList> userCall = service.getchat(email);

        userCall.enqueue(new Callback<GetchatresponseList>() {
            @Override
            public void onResponse(Call<GetchatresponseList> call, Response<GetchatresponseList> response) {


                ArrayList<Getchatresponse> chatlist=(ArrayList<Getchatresponse>) response.body().getGetchatresponse();
                if(chatlist!=null)
                {
                    commentlayout.setVisibility(View.VISIBLE);
                    mainprogress.setVisibility(View.GONE);
                    for(int c=0;c<chatlist.size();c++)
                    {

if(c==0)
{
    commentlayout.setVisibility(View.VISIBLE);
    mainprogress.setVisibility(View.GONE);
}
                      if( chatlist.get(c).getSender().equals("user"))
                      {
                          LinearLayout.LayoutParams lparams = new LinearLayout.LayoutParams(
                                  LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                          LayoutInflater vi = (LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                          View v = vi.inflate(R.layout.chatuserlayout, null);
                          TextView tv=v.findViewById(R.id.sender);
                          tv.setLayoutParams(lparams);
                          tv.setText(chatlist.get(c).getMessage().toString());
                          scrolllayout.addView(v);

                          getchatresponses.add(chatlist.get(c));
                      }
                      else
                      {
                          LinearLayout.LayoutParams lparams = new LinearLayout.LayoutParams(
                                  LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                          LayoutInflater vi = (LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                          View v = vi.inflate(R.layout.chatadminlayout, null);
                          TextView tv=v.findViewById(R.id.receiver);
                          tv.setLayoutParams(lparams);
                          tv.setText(chatlist.get(c).getMessage().toString());
                          scrolllayout.addView(v);
                          getchatresponses.add(chatlist.get(c));
                      }




                        if(chatlist.size()-c==1) {


                            scrollView.fullScroll(ScrollView.FOCUS_DOWN);

//                                 chatAdapter.notifyDataSetChanged();

                        }
                    }
                }


            }

            @Override
            public void onFailure(Call<GetchatresponseList> call, Throwable t) {
            /*try {
                Toast.makeText(getContext(), "You don't have internet connection", Toast.LENGTH_SHORT).show();
                Log.d("onFailure", t.toString());
            } catch (Exception e) {
                e.printStackTrace();
            }*/
                getchat(MainActivity.checkemail);        }
        });

    }
    private class LongOperation extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... params) {
            String mystring=params[0];
try {
    sharedpreferences = getActivity().getSharedPreferences(mypreference,
            Context.MODE_PRIVATE);

    if (sharedpreferences.contains(message)) {
        mystring = sharedpreferences.getString(message, "");

    }
}
catch (Exception e)
{

}
            return mystring;
        }

        @Override
        protected void onPostExecute(String result) {


if(result.equals(""))
{

}
else
{
    try
    {


    LinearLayout.LayoutParams lparams = new LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
    LayoutInflater vi = (LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    View v = vi.inflate(R.layout.chatadminlayout, null);
    TextView tv=v.findViewById(R.id.receiver);
    tv.setLayoutParams(lparams);
    tv.setText(result);
    scrolllayout.addView(v);
    scrollView.fullScroll(ScrollView.FOCUS_DOWN);
    SharedPreferences.Editor editor = sharedpreferences.edit();
    editor.putString(message, "");

    editor.commit();
    }
    catch (Exception e)
    {

    }
}


            try {
                new LongOperation().execute("MSG");
            }
            catch(Exception e)
            {

            }
        }

        @Override
        protected void onPreExecute() {}

        @Override
        protected void onProgressUpdate(Void... values) {}
    }
}